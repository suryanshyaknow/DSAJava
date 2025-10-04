package DynamicProgramming.DP2D;

public class MinPathSum {

    public int minPathSum(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        return minPathSumHelper(N - 1, M - 1, grid);
    }

    private static int minPathSumHelper(int i, int j, int[][] grid) {
        // Base case
        if (i == 0 && j == 0) return grid[i][j];

        // We're outta bounds
        if (i < 0 || j < 0) return -1; // proxy for incorrect paths

        // For all other indices
        int left = minPathSumHelper(i, j - 1, grid);
        int up = minPathSumHelper(i - 1, j, grid);

        if (left == -1) return up + grid[i][j];
        if (up == -1) return left + grid[i][j];
        return grid[i][j] + Integer.min(left, up);
    }
}
