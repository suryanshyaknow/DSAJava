package Graphs;

import java.util.*;

public class RottingOranges {

    private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        // Intuition: Since there could be multiple rotten
        // oranges that can be present, and they could infect
        // other oranges simultaneously, gotta use multi-source
        // bfs -- such that in a given min, we can infect multiple
        // oranges simultaneously. i.e. level by level.
        //
        // Can't use dfs in here, because it goes deep along one
        // path before trying others. It's almost like letting
        // the other rotten oranges wait to infect the rest.
        int N = grid.length;
        int M = grid[0].length;

        Queue<Node> q = new LinkedList<>();
        int time = 0;

        // First and foremost, impl the init config for this
        // multi source bfs. Insert all the rotten oranges in
        // the queue.
        // Also, count the number of fresh oranges at the start.

        int fresh = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new Node(i, j));
                }
                if (grid[i][j] == 1) fresh += 1;
            }
        }

        // Directions offset to infect the oranges
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Till q is empty and fresh oranges are left
        while (!q.isEmpty() && fresh > 0) {
            // Now poll all the rotten oranges
            int level = q.size();
            for (int i = 0; i < level; i++) {
                Node curr = q.poll();
                int curri = curr.x;
                int currj = curr.y;

                // Iterate over neighbors and insert them into
                // queue provided1 they are unvisited and got rotten
                for (int[] dir : dirs) {
                    int nr = curri + dir[0];
                    int nc = currj + dir[1];

                    if (inBounds(nr, nc, grid)
                            && grid[nr][nc] == 1) {
                        q.offer(new Node(nr, nc));
                        grid[nr][nc] = 2;
                        fresh -= 1;
                    }
                }
            }
            time += 1;
        }

        return fresh == 0 ? time : -1;

        // Time: O(N.M)
        // Space: O(N.M)
    }

    private boolean inBounds(int nr, int nc, int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
