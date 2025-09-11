package Graphs.Medium;

import java.util.*;

public class NumberOfDistinctIslands {

    private static class Pair {

        private int first;
        private int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

    }

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        int N = grid.length;
        int M = grid[0].length;

        int[][] vis = new int[N][M];
        Set<String> set = new HashSet<>();

        // Iterate over connected components
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vis[i][j] != 1 && grid[i][j] == 1) {
                    List<String> shape = new ArrayList<>();
//                    dfsHelper(i, j, vis, shape, grid, i, j); // Secondary i and j's are base co-ordinates
                    bfsHelper(i, j, vis, shape, grid, i, j); // Secondary i and j's are base co-ordinates
                    set.add(String.join(";", shape));
                }
            }
        }
        return set.size();
    }

    private void bfsHelper(int i, int j, int[][] vis, List<String> shape, int[][] grid, int i0, int j0) {
        int N = grid.length;
        int M = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j));
        shape.add((i - i0) + "," + (j - j0));

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int r = pair.first;
            int c = pair.second;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && vis[nr][nc] != 1 && grid[nr][nc] == 1) {
                    q.offer(new Pair(nr, nc));
                    vis[nr][nc] = 1;
                    shape.add((nr - i0) + "," + (nc - j0));
                }
            }
        }
    }

    private void dfsHelper(int i, int j, int[][] vis, List<String> shape, int[][] grid, int i0, int j0) {
        int N = grid.length;
        int M = grid[0].length;
        vis[i][j] = 1;
        shape.add((i - i0) + "," + (j - j0));

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];

            if (nr >= 0 && nr < N && nc >= 0 && nc < M && vis[nr][nc] != 1 && grid[nr][nc] == 1) {
                dfsHelper(nr, nc, vis, shape, grid, i0, j0);
            }
        }
    }

}
