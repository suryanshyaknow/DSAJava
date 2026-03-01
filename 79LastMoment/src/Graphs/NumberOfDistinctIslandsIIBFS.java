package Graphs;

import java.util.*;

public class NumberOfDistinctIslandsIIBFS {

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int countDistinctIslands(int[][] grid) {
        // Your Code here

        // Since we gotta account for the shape of the islands,
        // we oughts store each different shape encountered.
        int N = grid.length;
        int M = grid[0].length;

        Set<String> set = new HashSet<>();
        int vis[][] = new int[N][M];

        int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int i=0; i < N; i++) {
            for (int j=0; j < M; j++) {
                if (vis[i][j] != 1 && grid[i][j] == 1) { // land && not visited
                    List<String> shape = new ArrayList<>();
                    bfs(i, j, vis, grid, shape, i, j, dirs); // secondary i, j -> base co-ordinates
                    set.add(String.join(";", shape));
                }
            }
        }
        return set.size();
    }

    private void bfs(int i, int j, int[][] vis, int[][] grid, List<String> shape,
                     int basei, int basej, int[][] dirs) {
        int N = grid.length;
        int M = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j));
        vis[i][j] = 1;
        shape.add((i - basei) + ";" + (j - basej));

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int r = pair.x;
            int c = pair.y;

            for (int[] dir: dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < N && nc >=0 && nc < M && grid[nr][nc] == 1
                        && vis[nr][nc] != 1) {
                    q.offer(new Pair(nr, nc));
                    vis[nr][nc] = 1;
                    shape.add((nr - basei) + ";" + (nc - basej));
                }
            }
        }
    }
}
