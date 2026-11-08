package Graphs;

public class NumberOfIslandsDFS {

    public int numIslands(char[][] grid) {
        // Intuition: Each cluster of 1s can
        // be thought of as an island. And the
        // whole grid as graph having disconnected
        // components.
        //
        // Pick one unvisited land and traverse till
        // its respective cluster is visited and count
        // that as one island.

        // Needn't prep the adjacency list here cuz it's
        // given that from a land one can move horizontally
        // or vertically.

        int N = grid.length;
        int M = grid[0].length;

        // Need to maintain the list of visited islands
        int[][] vis = new int[N][M];

        int cnt = 0;
        // Iterate the grid from an the first unvisited island
        for (int i=0; i < N; i++) {
            for (int j=0; j < M; j++) {
                // Should be an island as well as unvisited
                if (grid[i][j] == '1' && vis[i][j] != 1) {
                    dfs(i, j, grid, vis);
                    cnt += 1;
                }
            }
        }
        return cnt;

        // Time: O(N * M) + O(4 N * M) ~ neighbor visits
        // Space: O(N * M)
    }

    private void dfs(int i, int j, char[][] grid, int[][] vis) {
        vis[i][j] = 1;

        // Traverse its neighbors and traverse the islands in
        // immediate vicinity.
        int N = grid.length;
        int M = grid[0].length;

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // directions offset
        for (int[] dir : dirs) {
            int r = i + dir[0]; // potential neighbor co-ordinates
            int c = j + dir[1];

            // Confirm if nei is inbounds
            // Is an islands and NOT visited
            if (r >= 0 && r < N
                    && c >= 0 && c < M
                    && grid[r][c] == '1'
                    && vis[r][c] != 1) {
                dfs(r, c, grid, vis);
            }
        }
    }
}
