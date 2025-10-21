package DynamicProgramming.DPOnStrings;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseqViaTabulation(String s) {
        // We just gotta find the LCS in s and its reverse
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();

        int N1 = s1.length();
        int N2 = s2.length();
        int[][] dp = new int[N1 + 1][N2 + 1];

        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                if (i > 0 && j > 0) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else {
                        int c1 = dp[i - 1][j];
                        int c2 = dp[i][j - 1];
                        dp[i][j] = Math.max(c1, c2);
                    }
                }
            }
        }

        return dp[N1][N2];
    }

    public int longestPalindromeSubseqViaMemoization(String s) {
        // We just gotta find the LCS in s and its reverse
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();

        int N1 = s1.length();
        int N2 = s2.length();
        int[][] dp = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return lcsHelper(N1 - 1, N2 - 1, s1, s2, dp);
    }

    private static int lcsHelper(int i, int j, String s1, String s2, int[][] dp) {
        // Base case
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = 1 + lcsHelper(i - 1, j - 1, s1, s2, dp);

        int c1 = lcsHelper(i - 1, j, s1, s2, dp);
        int c2 = lcsHelper(i, j - 1, s1, s2, dp);
        return dp[i][j] = Math.max(c1, c2);
    }
}
