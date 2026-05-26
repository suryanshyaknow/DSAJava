package DynamicProgramming.DPOnStrings;

import java.util.*;

public class EditDistance {

    public int minDistanceTabulation(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // Intuition: If you think about it, it's pretty simple.
        // We just gotta match the chars as we've been always doing.
        // and if there's a match shrink oboth the string and if not
        // try out all possibilities -- insert, delete, replace --
        // and pick the one that gives us ans in min no. of ops.

        int[][] dp = new int[N1 + 1][N2 + 1];

        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                // base cases
                if (i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = i;
                    continue;
                }

                // Match
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                // Else
                int ins = 1 + dp[i][j - 1];

                // Delete the char from str2
                int del = 1 + dp[i - 1][j];

                // Replace the char
                int rep = 1 + dp[i - 1][j - 1];

                dp[i][j] = Math.min(rep, Math.min(ins, del));
            }
        }

        return dp[N1][N2];
    }

    public int minDistanceMemo(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // Intuition: If you think about it, it's pretty simple.
        // We just gotta match the chars as we've been always doing.
        // and if there's a match shrink oboth the string and if not
        // try out all possibilities -- insert, delete, replace --
        // and pick the one that gives us ans in min no. of ops.

        int[][] dp = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return helperMemo(N1 - 1, N2 - 1, word1, word2, dp);

        // Time complexity: O(N1 * N2)
        // Space: O(N1 * N2) + O(N1 + N2) ~ Max recursion depth
    }

    private int helperMemo(int idx1, int idx2, String str1, String str2, int[][] dp) {
        // Base cases to exit outta recursion
        // If either of the strings get exhausted
        if (idx1 < 0) return idx2 + 1;
        if (idx2 < 0) return idx1 + 1;

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        // If there's a char match -- shrink the strings
        if (str1.charAt(idx1) == str2.charAt(idx2))
            return dp[idx1][idx2] = helperMemo(idx1 - 1, idx2 - 1, str1, str2, dp);

        // Explore the rest of the possibilities
        // Upon insertion when boith strings shrink
        int ins = 1 + helperMemo(idx1, idx2 - 1, str1, str2, dp); // Upon inserting when both strings shrink

        // Delete the char from str2
        int del = 1 + helperMemo(idx1 - 1, idx2, str1, str2, dp);

        // Replace the char
        int rep = 1 + helperMemo(idx1 - 1, idx2 - 1, str1, str2, dp);

        return dp[idx1][idx2] = Math.min(rep, Math.min(ins, del));
    }

    public int minDistance(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // Intuition: If you think about it, it's pretty simple.
        // We just gotta match the chars as we've been always doing.
        // and if there's a match shrink oboth the string and if not
        // try out all possibilities -- insert, delete, replace --
        // and pick the one that gives us ans in min no. of ops.

        return helper(N1 - 1, N2 - 1, word1, word2);
    }

    private int helper(int idx1, int idx2, String str1, String str2) {
        // Base cases to exit outta recursion
        // If either of the strings get exhausted
        if (idx1 < 0) return idx2 + 1;
        if (idx2 < 0) return idx1 + 1;

        // If there's a char match -- shrink the strings
        if (str1.charAt(idx1) == str2.charAt(idx2))
            return helper(idx1 - 1, idx2 - 1, str1, str2);

        // Explore the rest of the possibilities
        // Upon insertion when boith strings shrink
        int ins = 1 + helper(idx1, idx2 - 1, str1, str2); // Upon inserting when both strings shrink

        // Delete the char from str2
        int del = 1 + helper(idx1 - 1, idx2, str1, str2);

        // Replace the char
        int rep = 1 + helper(idx1 - 1, idx2 - 1, str1, str2);

        return Math.min(rep, Math.min(ins, del));
    }

}
