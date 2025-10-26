package DynamicProgramming.DPOnStrings;

import java.util.Arrays;

public class DistinctSubsequencesDP {

    public int numDistinctViaTabulation(String s, String t) {
        int N1 = s.length();
        int N2 = t.length();

        int dp[][] = new int[N1 + 1][N2 + 1];
        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (j == 0) dp[i][j] = 1;
                else if (i == 0) dp[i][j] = 0;

                if (i > 0 && j > 0) {
                    // Chars match case
                    if (s.charAt(i - 1) == t.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    else
                        dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[N1][N2];
    }

    public int numDistinctViaMemoization(String s, String t) {
        int N1 = s.length();
        int N2 = t.length();

        int dp[][] = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return numDistinctHelper(N1 - 1, N2 - 1, s, t, dp);

        // Time complexity: from Exponential to O(N1 * N2)
        // Space complexity: O(N1 * N2) + O(N1 + N2)
    }

    private static int numDistinctHelper(int i, int j, String s, String t, int[][] dp) {
        // Base cases
        // If t ran out
        if (j < 0) return 1;
        // If i ran out w/o the match
        if (i < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        // If the chars do match: Pick the char, and still not pick the character
        // despite the match.
        if (s.charAt(i) == t.charAt(j))
            return dp[i][j] = numDistinctHelper(i - 1, j - 1, s, t, dp) + numDistinctHelper(i - 1, j, s, t, dp);

        // No match
        return dp[i][j] = numDistinctHelper(i - 1, j, s, t, dp);
    }

}
