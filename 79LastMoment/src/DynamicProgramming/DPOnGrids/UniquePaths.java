package DynamicProgramming.DPOnGrids;

import java.util.*;

public class UniquePaths {

    public int uniquePathsTabulation(int m, int n) {
        // The robot can either move down
        // or right at any given time.

        int[][] dp = new int[m + 1][n + 1]; // Offset by 1 to handle negative indices
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], -1);

        int N = m;
        int M = n;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                    continue;
                }
                // i < 0 and j < 0
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];
                dp[i][j] = left + up;
            }
        }

        return dp[N][M];
    }

    public int uniquePathsMemoization(int m, int n) {
        // The robit can either move down
        // or right at any given time.

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], -1);

        return countPathsMemo(m - 1, n - 1, m, n, dp);
        // Returns the number of ways to reach form M & N
        // to any given (i, j)
    }

    private int countPathsMemo(int i, int j, int N, int M, int[][] dp) {
        if (i == 0 && j == 0)
            return 1; // Count this way
        // Safeguard for going outta bounds
        if (i < 0 || j < 0)
            return 0;
        if (dp[i][j] != -1) return dp[i][j];

        // Now, from the N & M, we could either go up
        // or left.
        int left = countPathsMemo(i, j - 1, N, M, dp);
        int up = countPathsMemo(i - 1, j, N, M, dp);

        return dp[i][j] = left + up;

        // Time complexity: O(N * M)
        // Space complexity: O(path length) + O(N * M)
    }

    public int uniquePaths(int m, int n) {
        // The robot can either move down
        // or right at any given time.

        return countPaths(m - 1, n - 1, m, n);
        // Returns the number of ways to reach form M & N
        // to any given (i, j)
    }

    private int countPaths(int i, int j, int N, int M) {
        if (i == 0 && j == 0)
            return 1; // Count this way
        // Safeguard for going outta bounds
        if (i < 0 || j < 0)
            return 0;

        // Now, from the N & M, we could either go up
        // or left.
        int left = countPaths(i, j - 1, N, M);
        int up = countPaths(i - 1, j, N, M);

        return left + up;

        // Time complexity: At every cell, we've got two choices, thus 2^N*M
        // Space complexity: O(Path length)
    }

}
