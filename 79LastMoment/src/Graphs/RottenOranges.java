package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    private static class Tuple {
        int x;
        int y;
        int time;

        public Tuple(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public int orangesRotting(int[][] grid) {
        // Muti-source BFS
        // Since we oughta find the minimum time,
        // and the oranges are getting rotten
        // simultaneously, we gotta use BFS.

        int N = grid.length;
        int M = grid[0].length;

        int[][] vis = new int[N][M];
        Queue<Tuple> q = new LinkedList<>();
        int t = 0; // to keep trakc of the time

        // Add all already rotten oranges into Queue
        // Also, keep a count of fresh oranges so as to
        // determine if all of them got rotten at the end.
        int freshCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 2) {
                    vis[i][j] = 1;
                    q.offer(new Tuple(i, j, 0));
                } else if (grid[i][j] == 1) {
                    freshCnt += 1;
                }
            }
        }
        // Perform BFS
        // and also keep track of manually rotten oranges
        int rotten = 0;
        while (!q.isEmpty()) {
            Tuple tup = q.poll();
            int r = tup.x;
            int c = tup.y;
            t = Math.max(tup.time, t);

            // Neighbors: directions
            int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
            // Now, rot the unvisited and fresh oranges,
            // and add them to the queue. Within bounds.
            for (int[] dir: dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < N
                        && nc >=0 && nc < M
                        && vis[nr][nc] != 1
                        && grid[nr][nc] == 1) {
                    q.offer(new Tuple(nr, nc, tup.time + 1));
                    vis[nr][nc] = 1;
                    rotten += 1;
                }
            }
        }

        if (rotten != freshCnt) return -1;
        return t;
    }
}
