package DynamicProgramming.DPOnStrings;

import java.util.*;

public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseqTabulation(String s) {
        // If you've already done LCS, this one's
        // piece of cake.
        // We just gotta reverse the string and find the
        // LCS in 'em both.

        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();

        int N1 = s1.length();
        int N2 = s2.length();

        int[][] dp = new int[N1 + 1][N2 + 1];

        for (int i=0; i <= N1; i++) {
            for (int j=0; j <= N2; j++) {
                // base cases
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                // Match found
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    continue;
                }

                // Explore other possibilites
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[N1][N2];

        // Time complexity: O(N1 * N2)
        // Space complexty: O(N1 * N2) ~ Recursion depth is out of the picture now
    }

    public int longestPalindromeSubseqMemo(String s) {
        // If you've already done LCS, this one's
        // piece of cake.
        // We just gotta reverse the string and find the
        // LCS in 'em both.

        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();

        int N1 = s1.length();
        int N2 = s2.length();

        int[][] dp = new int[N1][N2];
        for (int i=0; i < N1; i++) Arrays.fill(dp[i], -1);

        return helperMemo(N1 - 1, N2 - 1, s1, s2, dp);

        // Time complexity: O(N1 * N2)
        // Space complexity: O(N1 * N2) + O(N1 + N2) ~ Max recursion depth
    }

    private int helperMemo(int idx1, int idx2, String s1, String s2, int[][] dp) {
        // Base cases
        if (idx1 < 0 || idx2 < 0)
            return 0;

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        // If the current chars are match
        if (s1.charAt(idx1) == s2.charAt(idx2))
            return dp[idx1][idx2] = 1 + helperMemo(idx1 - 1, idx2 - 1, s1, s2, dp);

        // Now, we gotta explore by keeping one idx fixed and
        // moving the another and take the max of the responses
        // of both

        return dp[idx1][idx2] = Math.max(helperMemo(idx1, idx2 - 1, s1, s2, dp), helperMemo(idx1 - 1, idx2, s1, s2, dp));
    }

    public int longestPalindromeSubseq(String s) {
        // If you've already done LCS, this one's
        // piece of cake.
        // We just gotta reverse the string and find the
        // LCS in 'em both.

        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();

        int N1 = s1.length();
        int N2 = s2.length();

        return helper(N1 - 1, N2 - 1, s1, s2);

        // Time complexity: O(2^N1 * 2^N2)
        // Space complexity: O(N1 + N2) ~ Max recursion depth
    }

    private int helper(int idx1, int idx2, String s1, String s2) {
        // Base cases
        if (idx1 < 0 || idx2 < 0)
            return 0;

        // If the current chars are match
        if (s1.charAt(idx1) == s2.charAt(idx2))
            return 1 + helper(idx1 - 1, idx2 - 1, s1, s2);

        // Now, we gotta explore by keeping one idx fixed and
        // moving the another and take the max of the responses
        // of both

        return Math.max(helper(idx1, idx2 - 1, s1, s2), helper(idx1 - 1, idx2, s1, s2));
    }
}
