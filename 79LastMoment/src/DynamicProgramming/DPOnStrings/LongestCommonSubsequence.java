package DynamicProgramming.DPOnStrings;

import java.util.*;

public class LongestCommonSubsequence {

    public int longestCommonSubsequenceMemo(String text1, String text2) {
        int N1 = text1.length();
        int N2 = text2.length();

        // The idea is to move each char of both the strings and
        // see if they're a match -- if they're, then increase the
        // length count, however, if they're not then we're gonna
        // wanna try each combination by keeping one string intact and
        // moving the pointer in another.

        int[][] dp = new int[N1][N2];
        for (int i=0; i < N1; i++) Arrays.fill(dp[i], -1);

        return helperMemo(N1-1, N2-1, text1, text2, dp);

        // Time: O(2^N1 * 2^N2) since we're essentially are trying out
        // all the subsequences
    }

    private int helperMemo(int idx1, int idx2, String str1, String str2, int[][] dp) {
        // Base case
        // If any string goes outta bounds, we exit -- no more chars to match
        if (idx1 < 0 || idx2 < 0)
            return 0;

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        // See if the curr chars are a match
        if (str1.charAt(idx1) == str2.charAt(idx2))
            return dp[idx1][idx2] = 1 + helperMemo(idx1 - 1, idx2 - 1, str1, str2, dp);

        // Keep one pointer still, and move the other
        int move1 = helperMemo(idx1 - 1, idx2, str1, str2, dp);
        int move2 = helperMemo(idx1, idx2 - 1, str1, str2, dp);

        return dp[idx1][idx2] = Math.max(move1, move2);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int N1 = text1.length();
        int N2 = text2.length();

        // The idea is to move each char of both the strings and
        // see if they're a match -- if they're, then increase the
        // length count, however, if they're not then we're gonna
        // wanna try each combination by keeping one string intact and
        // moving the pointer in another.

        return helper(N1 - 1, N2 - 1, text1, text2);

        // Time: O(2^N1 * 2^N2) since we're essentially are trying out
        // all the subsequences
    }

    private int helper(int idx1, int idx2, String str1, String str2) {
        // Base case
        // If any string goes outta bounds, we exit -- no more chars to match
        if (idx1 < 0 || idx2 < 0)
            return 0;

        // See if the curr chars are a match
        if (str1.charAt(idx1) == str2.charAt(idx2))
            return 1 + helper(idx1 - 1, idx2 - 1, str1, str2);

        // Keep one pointer still, and move the other
        int move1 = helper(idx1 - 1, idx2, str1, str2);
        int move2 = helper(idx1, idx2 - 1, str1, str2);

        return Math.max(move1, move2);
    }

}
