package DynamicProgramming.DPOnStrings;

import java.util.Arrays;

public class LongestCommonSubsequenceDP {

    public int longestCommonSubsequenceViaTabulation(String text1, String text2) {
        int N1 = text1.length();
        int N2 = text2.length();

        int dp[][] = new int[N1 + 1][N2 + 1];

        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (i < 1 || j < 1) dp[i][j] = 0;

                if (i >= 1 && j >= 1) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                    else {
                        dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }

        return dp[N1][N2];
        // Time complexity: O(N1 * N2)
        // Space complexity: O(N1 * N2)
    }

    public int longestCommonSubsequenceViaMemoization(String text1, String text2) {
        int N1 = text1.length();
        int N2 = text2.length();

        int dp[][] = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return helper(N1 - 1, N2 - 1, text1, text2, dp);

        // Time complexity: O(N1 * N2)
        // Space complexity: O(N1 + N2) + O(N1 * N2), recursion call stack.
        // In the worst case (when no characters match), recursion can go as deep as N1 + N2.
    }

    private int helper(int i1, int i2, String s1, String s2, int[][] dp) {
        // Base case
        // If any of the idx falls beyond zero, then there's no sense in continuing
        if (i1 < 0 || i2 < 0) return 0;

        if (dp[i1][i2] != -1) return dp[i1][i2];

        // If the curr characters are a match
        if (s1.charAt(i1) == s2.charAt(i2))
            return dp[i1][i2] = 1 + helper(i1 - 1, i2 - 1, s1, s2, dp);

        // If not we wanna try all combinations
        int mov1 = helper(i1 - 1, i2, s1, s2, dp);
        int mov2 = helper(i1, i2 - 1, s1, s2, dp);
        return dp[i1][i2] = Integer.max(mov1, mov2);
    }

}
