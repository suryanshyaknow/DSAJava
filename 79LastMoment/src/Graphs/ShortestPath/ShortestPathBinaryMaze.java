package Graphs.ShortestPath;

import java.util.*;

public class ShortestPathBinaryMaze {

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


    int shortestDistance(int N, int M, int A[][], int X, int Y) {
        // code here
        // Build a directions array for neighbors
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Basically, compute each cell w 1 val shortest distance
        // and terminate the moment you encounter the destination.

        // Maintain a dist array as well and fill w -1.
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }
        // Mark the src node dist
        dist[0][0] = 0;

        // Since we're dealing w the unit weights, we won't be needing
        // PQ as there're gonna be uniform distances
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(0, 0, 0));
        if (A[0][0] != 1) return -1; // Can't go thru arc node anyway
        if (X == 0 && Y == 0) return 0; // src node is itself the destination

        while (!q.isEmpty()) {
            Tuple tup = q.poll();
            int d = tup.dist;
            int row = tup.row;
            int col = tup.col;

            // Compute for neighbors - 4 directional
            for (int[] dir : dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                // Nei should be a valid one: idx in limits, val 1, not visited
                if (nr >= 0 && nr < N
                        && nc >= 0 && nc < M
                        && A[nr][nc] == 1
                        && dist[nr][nc] == -1
                ) {
                    int newDist = d + 1; // Unit distances
                    dist[nr][nc] = newDist;
                    if (nr == X && nc == Y)
                        return newDist;

                    q.offer(new Tuple(newDist, nr, nc));
                }
            }
        }

        return -1;

        // Time complexity: O(N * M)
        // Space complexity: O(N * M) for dist arr + O(N * M) nodes for Queue
    }
}
