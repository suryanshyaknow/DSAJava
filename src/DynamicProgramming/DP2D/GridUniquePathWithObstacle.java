package DynamicProgramming.DP2D;

import java.util.Arrays;

public class GridUniquePathWithObstacle {

    public int uniquePathsWithObstaclesViaTabulation(int[][] obstacleGrid) {
        int N = obstacleGrid.length;
        int M = obstacleGrid[0].length;

        int dp[][] = new int[N][M];

        // Populate the base cases
        for (int c = 0; c < M; c++) {
            if (obstacleGrid[0][c] == 1) break;
            dp[0][c] = 1;
        }
        for (int r = 0; r < N; r++) {
            if (obstacleGrid[r][0] == 1) break;
            dp[r][0] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else {
                    int left = dp[i][j - 1];
                    int up = dp[i - 1][j];

                    dp[i][j] = left + up;
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public int uniquePathsWithObstaclesViaMemoization(int[][] obstacleGrid) {
        int N = obstacleGrid.length;
        int M = obstacleGrid[0].length;

        int dp[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        return countPaths(N - 1, M - 1, obstacleGrid, dp);
    }

    private static int countPaths(int i, int j, int[][] grid, int[][] dp) {
        // Base case
        // We're outta bounds
        if (i < 0 || j < 0)
            return 0;

        // Obstacle encounter
        if (grid[i][j] == 1)
            return 0; // Destination itself could be an obstacle

        // We reached our destination
        if (i == 0 && j == 0)
            return 1;

        if (dp[i][j] != -1) return dp[i][j];

        int left = countPaths(i - 1, j, grid, dp);
        int up = countPaths(i, j - 1, grid, dp);
        return dp[i][j] = left + up;
    }
}
