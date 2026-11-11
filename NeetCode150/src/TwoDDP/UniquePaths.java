package TwoDDP;

import java.util.*;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int N = m;
        int M = n;

        // Intuition: The idea is to generate all ways and sum
        // them up.
        // f(i, j) -> Represents the number of unique ways the
        // robot can take from (0, 0) to reach (i, j).
        // Moreover, robot can only move right or down.
        // So at every step/cell robot can either go right or down.

        return countPaths(N - 1, M - 1, N, M);

        // Time: O(2^N.M)
        // Space: O(N.M) ~ recursion depth
    }

    private int countPaths(int i, int j, int N, int M) {
        // Base cases
        // Reached the destination
        if (i == 0 && j == 0)
            return 1;
        // Went outta bounds
        if (i < 0 || j < 0)
            return 0; // can never reach the dest

        // Since in recursion we're trying to decompose
        // the bigger problem into small ones, we're kinda
        // moving from i, j to 0, 0
        // Thus directions will be reversed
        int up = countPaths(i - 1, j, N, M);
        int left = countPaths(i, j - 1, N, M);

        return up + left;
    }

    public int uniquePathsMemo(int m, int n) {
        int N = m;
        int M = n;

        // Intuition: The idea is to generate all ways and sum
        // them up.
        // f(i, j) -> Represents the number of unique ways the
        // robot can take from (0, 0) to reach (i, j).
        // Moreover, robot can only move right or left.
        // So at every step/cell robot can either go right or down.

        // Caching arrays to cache subproblems
        int[][] dp = new int[N][M];
        for (int i=0; i < N; i++) Arrays.fill(dp[i], -1);
        // dp[i][j] represents the number of ways robot can reach to
        // (i, j) starting at (0, 0)

        return countPathsMemo(N - 1, M - 1, N, M, dp);

        // Time: O(N.M)
        // Space: O(N.M) + O(N.M)
    }

    private int countPathsMemo(int i, int j, int N, int M, int[][] dp) {
        // Base cases
        // Reached the destination
        if (i == 0 && j == 0)
            return 1;
        // Went outta bounds
        if (i < 0 || j < 0)
            return 0; // Can never reach the dest

        if (dp[i][j] != -1) return dp[i][j];

        // Since in recursion wer're trying to decompose
        // the bigger problem into small ones, we're kinda
        // moving from i, j to 0, 0
        // Thus directions will be reversed
        int up = countPathsMemo(i - 1, j, N, M, dp);
        int left = countPathsMemo(i, j - 1, N, M, dp);

        return dp[i][j] = up + left;
    }

    public int uniquePathsTabulation(int m, int n) {
        int N = m;
        int M = n;

        // Intuition: The idea is to generate all ways and sum
        // them up.
        // f(i, j) -> Represents the number of unique ways the
        // robot can take from (0, 0) to reach (i, j).
        // Moreover, robot can only move right or left.
        // So at every step/cell robot can either go right or down.

        // Caching arrays to cache subproblems
        int[][] dp = new int[N][M];

        for (int i=0; i < N; i++) {
            for (int j=0; j < M; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int up = (i - 1 >= 0) ? dp[i - 1][j] : 0;
                int left = (j - 1 >= 0) ? dp[i][j - 1] : 0;
                dp[i][j] = up + left;
            }
        }

        return dp[N - 1][M - 1];
        // Time: O(N.M)
        // Space: O(N.M) + O(N.M)
    }
}