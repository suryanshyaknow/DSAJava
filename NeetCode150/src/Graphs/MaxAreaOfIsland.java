package Graphs;

public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int N = grid.length;
        int M = grid[0].length;

        int[][] vis = new int[N][M];
        int res = 0;

        // Iterate over grid starting off first unvisited land
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1 && vis[i][j] != 1) {
                    // Update max area res w candidate areas
                    // of each comp
                    res = Math.max(res, dfs(i, j, grid, vis));
                }
            }
        }

        return res;
    }

    private int dfs(int i, int j, int[][] grid, int[][] vis) {
        vis[i][j] = 1;
        int area = 1;

        int N = grid.length;
        int M = grid[0].length;

        // Iterate over its neighbors; traverse the connected lands
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];

            if (inBounds(r, c, grid)
                    && grid[r][c] == 1
                    && vis[r][c] != 1) {
                area += dfs(r, c, grid, vis);
            }
        }

        return area;
    }

    private boolean inBounds(int r, int c, int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        return (r >= 0 && r < N) && (c >= 0 && c < M);
    }
}
