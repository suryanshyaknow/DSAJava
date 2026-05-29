package DynamicProgramming.DPOnStrings;

import java.util.*;

public class DeleteOperationsForTwoStrings {

    public int minDistanceTabulation(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // The idea is pretty simple: We just gotta match the
        // string chars and shrink it, and if there's not a
        // match, try deleting chars in either string.

        int[][] dp = new int[N1 + 1][N2 + 1];

        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                // Edge cases
                if (i == 0) {
                    // word1 ran out of chars
                    // delete all in word2
                    dp[i][j] = 1 + (j - 1);
                    continue;
                }
                if (j == 0) {
                    // word2 ran out of chars
                    // delete all in word1
                    dp[i][j] = 1 + (i - 1);
                    continue;
                }

                // Chars match
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[N1][N2];

        // Time: O(N1 * N2)
        // Space: O(N1 * N2)
    }

    public int minDistanceMemo(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // The idea is pretty simple: We just gotta match the
        // string chars and shrink it, and if there's not a
        // match, try deleting chars in either string.

        int[][] dp = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return helperMemo(N1 - 1, N2 - 1, word1, word2, dp);
    }

    private int helperMemo(int idx1, int idx2, String word1, String word2, int[][] dp) {
        // Edge cases
        if (idx1 < 0) return 1 + idx2; // word1 ran out; delete all the chars in word2
        if (idx2 < 0) return 1 + idx1; // word2 ran out; delete all the chars in word1

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        // Chars match, shrink the string
        if (word1.charAt(idx1) == word2.charAt(idx2))
            return dp[idx1][idx2] = helperMemo(idx1 - 1, idx2 - 1, word1, word2, dp);

        // Explore the possibilities
        return dp[idx1][idx2] = 1 + Math.min(
                helperMemo(idx1 - 1, idx2, word1, word2, dp),
                helperMemo(idx1, idx2 - 1, word1, word2, dp)
        );
    }

    public int minDistance(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // The idea is pretty simple: We just gotta match the
        // string chars and shrink it, and if there's not a
        // match, try deleting chars in either string.

        return helper(N1 - 1, N2 - 1, word1, word2);

        // Time: Exponential
        // Space: O(N1 + N2)
    }

    private int helper(int idx1, int idx2, String word1, String word2) {
        // Edge cases
        if (idx1 < 0) return 1 + idx2; // word1 ran out; delete all the chars in word2
        if (idx2 < 0) return 1 + idx1; // word2 ran out; delete all the chars in word1

        // Chars match, shrink the string
        if (word1.charAt(idx1) == word2.charAt(idx2))
            return helper(idx1 - 1, idx2 - 1, word1, word2);

        // Explore the possibilities
        return 1 + Math.min(
                helper(idx1 - 1, idx2, word1, word2),
                helper(idx1, idx2 - 1, word1, word2)
        );
    }

}
