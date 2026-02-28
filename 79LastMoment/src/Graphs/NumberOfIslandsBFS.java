package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslandsBFS {

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public int numIslands(char[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        // Keep another grid to maintain visited spots
        int[][] vis = new int[N][M];

        // WE're gonna start w a spot and connect it w surrounding
        // lands till it can't further be conncected. And call it
        // an island.

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // If it's not visited and a land
                // start off the dfs
                if (vis[i][j] != 1 && grid[i][j] == '1') {
                    bfs(i, j, vis, grid);
                    res += 1;
                }
            }
        }

        return res;
    }

    private void bfs(int i, int j, int[][] vis, char[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        // i, j would be our starting cell
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j));
        vis[i][j] = 1;


        // Add a cell, remove it and add its connected cells, and repeat..
        while (!q.isEmpty()) {
            Pair node = q.poll();
            int x = node.x;
            int y = node.y;

            int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir: dirs) {
                int r = x + dir[0];
                int c = y + dir[1];

                // If the nei is valid, then add to the queue
                if (r >= 0 && r < N
                        && c >=0 && c < M
                        && vis[r][c] != 1
                        && grid[r][c] == '1') {
                    q.offer(new Pair(r, c));
                    vis[r][c] = 1;
                }
            }
        }
    }

}
