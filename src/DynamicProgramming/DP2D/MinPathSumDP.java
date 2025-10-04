package DynamicProgramming.DP2D;

import java.util.Arrays;

public class MinPathSumDP {

    public int minPathSumViaTabulation(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dp = new int[N][M];

        // Populate the base cases
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else {
                    int left = Integer.MAX_VALUE;
                    if (j > 0) left = dp[i][j - 1] + grid[i][j];
                    int up = Integer.MAX_VALUE;
                    if (i > 0) up = dp[i - 1][j] + grid[i][j];

                    dp[i][j] = Integer.min(left, up);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public int minPathSumViaMemoization(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return minPathSumHelper(N - 1, M - 1, grid, dp);
    }

    private static int minPathSumHelper(int i, int j, int[][] grid, int[][] dp) {
        // Base case
        if (i == 0 && j == 0) return grid[i][j];

        // We're outta bounds
        if (i < 0 || j < 0) return -1;

        if (dp[i][j] != -1) return dp[i][j];

        // For all other indices
        int left = minPathSumHelper(i, j - 1, grid, dp);
        int up = minPathSumHelper(i - 1, j, grid, dp);

        if (left == -1) return up + grid[i][j];
        if (up == -1) return left + grid[i][j];
        return dp[i][j] = grid[i][j] + Integer.min(left, up);
    }

}
