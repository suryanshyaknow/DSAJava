package DynamicProgramming.DPOnGrids;

import java.util.*;

public class UniquePathsII {

    public int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int[][] grid = obstacleGrid;
        int N = grid.length;
        int M = grid[0].length;

        // Also, if the destination/start itself is an obstacle
        if (grid[0][0] == 1 || grid[N - 1][M - 1] == 1)
            return 0;

        int dp[][] = new int[N + 1][M + 1]; // offset by 1 to handle outta bounds cases

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                    continue;
                }

                if (grid[i - 1][j - 1] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[N][M];

        // Time complexity: O(N * M)
        // Space: O(N * M)
    }

    public int uniquePathsWithObstaclesMemoization(int[][] obstacleGrid) {
        int[][] grid = obstacleGrid;
        int N = grid.length;
        int M = grid[0].length;

        // Also, if the destination/start itself is an obstacle
        if (grid[0][0] == 1 || grid[N - 1][M - 1] == 1) return 0;

        int dp[][] = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return countPaths(N - 1, M - 1, grid, dp);
    }

    private int countPaths(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) return 1;
        // Outta bounds
        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        // See if it's an obstacle
        if (grid[i][j] == 1) return dp[i][j] = 0;

        int up = countPaths(i - 1, j, grid, dp);
        int left = countPaths(i, j - 1, grid, dp);

        return dp[i][j] = up + left;

        // Time complexity: O(N * M)
        // Space: O(N * M) + O(N + M) path length
    }
}
