package Graphs;

import java.util.*;

public class NumberOfIslandsBFS {

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(char[][] grid) {
        // Intuition: Each cluster of 1s can
        // be thought of as an island. And the
        // whole grid as graph having disconnected
        // components.
        //
        // Pick one unvisited island and traverse till
        // its respective cluster is visited and count
        // that as one island.

        // Needn't prep the adjacency list here cuz it's
        // given that from an island one can move horizontally
        // or vertically.

        int N = grid.length;
        int M = grid[0].length;

        // Need to maintain the list of visited islands
        int[][] vis = new int[N][M];

        int cnt = 0;
        // Iterate the grid from an the first unvisited island
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // Should be an island as well as unvisited
                if (grid[i][j] == '1' && vis[i][j] != 1) {
                    bfs(i, j, grid, vis);
                    cnt += 1;
                }
            }
        }
        return cnt;

        // Time: O(N * M) + O(4 N * M) ~ neighbor visits
        // Space: O(N * M)
    }

    private void bfs(int i, int j, char[][] grid, int vis[][]) {
        int N = grid.length;
        int M = grid[0].length;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(i, j));
        vis[i][j] = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int curri = node.x;
            int currj = node.y;

            // Directions offset
            int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            // Iterate over neighbors and push 'em in the queue
            for (int[] dir : dirs) {
                int r = curri + dir[0];
                int c = currj + dir[1];

                // Neighbors gotta be inbound, a land, and unvisited
                if (r >= 0 && r < N
                        && c >= 0 && c < M
                        && grid[r][c] == '1'
                        && vis[r][c] != 1) {
                    q.offer(new Node(r, c));
                    vis[r][c] = 1;
                }
            }
        }
    }
}
