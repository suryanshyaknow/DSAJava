package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    private static class Pair {
        private int row;
        private int col;
        private int tm;

        Pair(int row, int col, int tm) {
            this.row = row;
            this.col = col;
            this.tm = tm;
        }
    }

    public int orangesRotting(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        Queue<Pair> q = new LinkedList<>();

        int freshCnt = 0;
        // Maintain a visited array
        int[][] vis = new int[N][M];

        // Add starting nodes i.e. rotten oranges into queue and mark 'em visited
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 2) {
                    vis[i][j] = 1; // visited
                    q.offer(new Pair(i, j, 0));
                }
                if (grid[i][j] == 1) freshCnt++;
            }
        }

        int time = 0;
        int rotten = 0; // Manually rotten
        int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // Start off BFS
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            time = Integer.max(pair.tm, time);
            int row = pair.row;
            int col = pair.col;

            // Process the neighbors
            for (int[] dir : dirs) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if (nRow >= 0 && nCol >= 0
                        && nRow < grid.length && nCol < grid[0].length
                        && vis[nRow][nCol] != 1 && grid[nRow][nCol] == 1
                ) {
                    vis[nRow][nCol] = 1;
                    grid[nRow][nCol] = 2; // Mark rotten
                    rotten += 1;
                    q.offer(new Pair(nRow, nCol, pair.tm + 1));
                }
            }
        }

        // See if there are any fresh oranges left
        if (freshCnt != rotten) return -1;
        return time;
    }

}
