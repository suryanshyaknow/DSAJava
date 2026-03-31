package DynamicProgramming.DPOnGrids;

import java.util.*;

public class MinPathSum {

    public int minPathSumTabulation(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        int[][] dp = new int[N + 1][M + 1]; // Offset to handle negative indices

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = grid[i - 1][j - 1];
                    continue;
                }
                if (i == 0 || j == 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                    continue;
                }

                int up = dp[i - 1][j];
                int left = dp[i][j - 1];

                int min = Math.min(up, left);

                dp[i][j] = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : grid[i - 1][j - 1] + min);
            }
        }

        return dp[N][M];

        // Time complexity: O(N * M)
        // Space complexity: O(N * M)
    }

    public int minPathSumMemoization(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return recurseMemoization(N - 1, M - 1, grid, dp);
    }

    private int recurseMemoization(int i, int j, int grid[][], int[][] dp) {
        if (i == 0 && j == 0)
            return grid[i][j];
        // Safeguard for going outta bounds
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;

        if (dp[i][j] != -1) return dp[i][j];

        // Now, from the N & M, we could either go up
        // or left.
        int left = recurseMemoization(i, j - 1, grid, dp);
        int up = recurseMemoization(i - 1, j, grid, dp);

        int min = Math.min(left, up);
        return dp[i][j] = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : grid[i][j] + min);

        // Time complexity: O(N * M)
        // Space complexity: O(N * M) dp arr + O(N + M) path length
    }

    public int minPathSum(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        return recurse(N - 1, M - 1, grid);
    }

    private int recurse(int i, int j, int grid[][]) {
        if (i == 0 && j == 0)
            return grid[i][j];
        // Safeguard for going outta bounds
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;

        // Now, from the N & M, we could either go up
        // or left.
        int left = recurse(i, j - 1, grid);
        int up = recurse(i - 1, j, grid);

        int min = Math.min(left, up);

        return min == Integer.MAX_VALUE ? Integer.MAX_VALUE : grid[i][j] + min;

        // Time complexity: At every cell, we've got two choices, thus 2^N*M
        // Space complexity: O(Path length)
    }
}
