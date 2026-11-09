package Graphs;

import java.util.*;

public class PacificAtlanticWaterFlow {

    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0)
            return new ArrayList<>();

        // Given: Water can flow to adjacent cells given their
        // height is less than or equal to the current cell.
        // Also, water can flow from border cells into the ocean.
        //
        // "res": List of cells coordinates from which water can
        // flow to both oceans.

        // Intuition: The general idea is to start the traversal from
        // respective borders cells for both oceans and mark the cells
        // visited separately for each ocean.
        //
        // Maintain two visited sets for each ocean and mark the respective
        // reachable cells and then at last traverse the grid and store the
        // cells that are present in both sets.

        int[][] grid = heights;
        int N = grid.length;
        int M = grid[0].length;

        List<List<Integer>> res = new ArrayList<>();

        int[][] pac = new int[N][M]; // Visited set for pacific
        int[][] atl = new int[N][M];

        // We're gonna start from the left and top border for Pacific
        // And right and bottom for Atlantic.
        // Criteria for a reachable cell: height[curr] >= heihgt[prev]

        // Traverse the top and bottom for pac and atl respectively
        for (int c = 0; c < M; c++) {
            // Pacific: r = 0, c = c
            if (pac[0][c] != 1)
                dfs(0, c, grid, pac);

            // Atlantic: r = N-1, c = c
            if (atl[N - 1][c] != 1)
                dfs(N - 1, c, grid, atl);
        }

        // Traverse the left and right edges for pac and atl respectively
        for (int r = 0; r < N; r++) {
            // Pacific: r = 0, c = c
            if (pac[r][0] != 1)
                dfs(r, 0, grid, pac);

            // Atlantic: r = N-1, c = c
            if (atl[r][M - 1] != 1)
                dfs(r, M - 1, grid, atl);
        }

        // Store the cells reachable by both oceans
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (pac[i][j] == 1 && atl[i][j] == 1)
                    res.add(new ArrayList<>(Arrays.asList(i, j)));
            }
        }
        return res;

        // Time: O(2 * 4 * N.M)
        // Space: O(N.M)
    }

    private void dfs(int i, int j, int[][] grid, int[][] vis) {
        vis[i][j] = 1;
        int N = grid.length;
        int M = grid[0].length;

        // Iterate over neighboring cells
        for (int[] dir : DIRS) {
            int dr = i + dir[0];
            int dc = j + dir[1];

            if (inBounds(dr, dc, N, M)
                    && vis[dr][dc] != 1
                    && grid[i][j] <= grid[dr][dc])
                dfs(dr, dc, grid, vis);
        }
    }

    private boolean inBounds(int r, int c, int N, int M) {
        return (r >= 0 && r < N) && (c >= 0 && c < M);
    }

}
