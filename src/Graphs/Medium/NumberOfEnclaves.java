package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {

    private static class Node {

        private int row;
        private int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int numEnclaves(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] vis = new int[N][M];

//        bfs(vis, grid);
        // Iterate over each boundary cell and recurse if it's sea land
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // First row, last row, first col, last col
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    if (grid[i][j] == 1) {
                        dfs(new Node(i, j), vis, grid);
                    }
                }
            }
        }

        // Count the untouched ones
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1 && vis[i][j] != 1) cnt += 1;
            }
        }
        return cnt;
    }

    private static void dfs(Node node, int[][] vis, int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int r = node.row;
        int c = node.col;
        vis[r][c] = 1; // visited

        int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // Process the neighbors
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] == 1 && vis[nr][nc] != 1) {
                dfs(new Node(nr, nc), vis, grid);
            }
        }
    }

    private static void bfs(int[][] vis, int[][] grid) {
        // Traverse the boundaries and add the land cells to the queue for init config
        int N = grid.length;
        int M = grid[0].length;

        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // First row, last row, first col, last col
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    if (grid[i][j] == 1) {
                        vis[i][j] = 1;
                        q.offer(new Node(i, j));
                    }
                }
            }
        }

        int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // Now flood fill all the possible 1s off the boundaries
        while (!q.isEmpty()) {
            Node node = q.poll();
            int r = node.row;
            int c = node.col;

            // Process the neighbors
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Valid idx, land cell, and not visited
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] == 1 && vis[nr][nc] != 1) {
                    vis[nr][nc] = 1;
                    q.offer(new Node(nr, nc));
                }
            }
        }
    }

}
