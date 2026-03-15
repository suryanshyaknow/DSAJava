package Graphs.ShortestPath;

import java.util.*;

public class ShortestPathBinaryMatrix {

    private static class Tuple {

        private int dist; // distance from src node
        private int row;
        private int col;

        Tuple(int dist, int row, int col) {
            this.dist = dist;
            this.row = row;
            this.col = col;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        int destRow = N - 1; // destination row
        int destCol = M - 1;

        // Maintain a dist array
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
        dist[0][0] = 1;

        // Compute shortest path for neighbors of the src node
        // and keep expanding implmenting BFS
        // Since there are unit distances, we don't really need PQ
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(1, 0, 0));

        // See, if src node is 1
        if (grid[0][0] == 1) return -1;
        if (N == 1 && M == 1) return 1;

        int dirs[][] = {{0, 1}, {1, 1}, {1, 0}, {1, -1},
                {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}}; // 8-directional

        while (!q.isEmpty()) {
            Tuple tup = q.poll();

            // Iterate thru the neighbors
            for (int[] dir : dirs) {
                int nr = tup.row + dir[0];
                int nc = tup.col + dir[1];
                int newDist = tup.dist + 1;

                // Neighbor should be valid: witihin indices,
                // 0 valued, not visted
                if (nr >= 0 && nr < N
                        && nc >= 0 && nc < M
                        && dist[nr][nc] == -1
                        && grid[nr][nc] == 0
                ) {
                    dist[nr][nc] = newDist;

                    if (nr == destRow && nc == destCol) return newDist;

                    q.offer(new Tuple(newDist, nr, nc));
                }
            }
        }
        return -1;
    }
}
