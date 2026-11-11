package TwoDDP;

import java.util.*;

public class MinPathSum {

    private static final int INF = 1_000_000_000;

    public int minPathSum(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        // Intuition: Sum up the number along the path
        // and pick the path w minimum usm.
        // Can only move either down or right.
        //
        // f(i, j) -> Computes the min path sum from the top left of
        // the matrix to given coordinate i, j.

        int[][] dp = new int[N][M];
        for (int i=0; i < N; i++) Arrays.fill(dp[i], -1);

        return helper(N - 1, M - 1, grid, dp);

        // Recursive Approach's
        // Time: O(2^N.M) -> O(N.M)
        // Space: O(N.M) -> 2O(N.M)
    }

    private int helper(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) return grid[0][0];
        if (i < 0 || j < 0) return INF; // Safegaurd for going outta bounds

        if (dp[i][j] != -1) return dp[i][j];

        int up = helper(i - 1, j, grid, dp);
        int left = helper(i, j - 1, grid, dp);
        int pathSum = Math.min(up, left);

        return dp[i][j] = (pathSum == INF) ? INF : pathSum + grid[i][j];
    }

    public int minPathSumTabulation(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;

        // Intuition: Sum up the number along the path
        // and pick the path w minimum usm.
        // Can only move either down or right.
        //
        // f(i, j) -> Computes the min path sum from the top left of
        // the matrix to given coordinate (i, j).

        int[][] dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int up = (i > 0) ? dp[i - 1][j] : INF;
                int left = (j > 0) ? dp[i][j - 1] : INF;
                int pathSum = Math.min(up, left);

                dp[i][j] = grid[i][j] + pathSum;
            }
        }

        return dp[N - 1][M - 1];

        // Recursive Approach's
        // Time: O(2^N.M) -> O(N.M)
        // Space: O(N.M) -> O(N.M), got rid of the recursion depth
    }
}