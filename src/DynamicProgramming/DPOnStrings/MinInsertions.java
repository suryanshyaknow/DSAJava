package DynamicProgramming.DPOnStrings;

import java.util.Arrays;

public class MinInsertions {

    public int minInsertionsViaTabulation(String s) {
        // Find the LPS
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        int N = s.length();

        int dp[][] = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }

                if (i > 0 && j > 0) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
        }

        return N - dp[N][N];
    }

    public int minInsertionsViaMemoization(String s) {
        // Do a practical:
        // You'd find that if you keep the LPS of given string intact
        // and try to account for rest of the character, then you'd
        // tranform the given string into palindrome w min insertions.
        // I.e., N - len(LPS)

        // Find the LPS
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        int N = s.length();

        int dp[][] = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return N - lps(N - 1, N - 1, s1, s2, dp);
    }

    private static int lps(int i, int j, String s1, String s2, int[][] dp) {
        // Edge cases
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1) return dp[i][j];

        // If the curr characters match
        if (s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = 1 + lps(i - 1, j - 1, s1, s2, dp);

        return dp[i][j] = Math.max(lps(i, j - 1, s1, s2, dp), lps(i - 1, j, s1, s2, dp));
    }
}
