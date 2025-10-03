package DynamicProgramming.DP2D;

import java.util.Arrays;

public class GridUniquePathsDP {

    public int uniquePathsViaTabulation(int m, int n) {
        int[][] dp = new int[m][n];

        // Populate the base cases
        // Populate row 0 and col 0
        for (int c = 0; c < n; c++) {
            dp[0][c] = 1;
        }
        for (int r = 0; r < m; r++) {
            dp[r][0] = 1;
        }

        // For other cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int up = dp[i - 1][j];
                int left = dp[i][j - 1];
                dp[i][j] = up + left;
            }
        }
        return dp[m - 1][n - 1];

        // Time complexity: O(m*n)
        // Space complexity: O(m*n), No recursive stack space
    }

    public int uniquePathsViaMemoization(int m, int n) {
        // Step i. Express the prob in terms of indices
        // Step ii. Do what problem has stated on those indices
        // Step iii. Count or sum whatever the problem has stated

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return countPaths(m - 1, n - 1, dp);

        // Time complexity: O(m*n)
        // Space complexity: O((n-1) + (m -1)) + O(m*n)
    }

    private static int countPaths(int i, int j, int[][] dp) {
        // Base cases
        // We reached our destination
        if (i == 0 && j == 0)
            return 1;
        // We're outta bounds
        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int left = countPaths(i - 1, j, dp);
        int up = countPaths(i, j - 1, dp);
        return dp[i][j] = left + up;
    }

}
