package DynamicProgramming.DPOnStrings;

import java.util.Arrays;

public class EditDistanceDP {

    public int minDistanceTabulate(String s, String t) {
        // We basically have to convert str s into t,
        // and we've got practically three choices to accomplish that.
        // Replacing a matching char, inserting a matching char, and deleting a char.

        int N1 = s.length();
        int N2 = t.length();

        int[][] dp = new int[N1 + 1][N2 + 1];

        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (j == 0) // t has been exhausted, need to delete chars from s
                    dp[i][j] = i; // delete ops
                if (i == 0) // i has been exhausted, need to insert remaning chars from t to s
                    dp[i][j] = j; // insert ops

                if (i > 0 && j > 0) {
                    // If the chars do match
                    if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = 0 + dp[i - 1][j - 1];

                    else {
                        // If they don't make 'em
                        // Replace the ith char
                        int replace = 1 + dp[i - 1][j - 1];
                        // Insert at the ith char
                        int insert = 1 + dp[i][j - 1];
                        // Delete the ith char
                        int delete = 1 + dp[i - 1][j];

                        dp[i][j] = Math.min(replace, Math.min(insert, delete));
                    }
                }
            }
        }

        return dp[N1][N2];
    }

    public int minDistanceMemoize(String s, String t) {
        // We basically have to convert str s into t,
        // and we've got practically three choices to accomplish that.
        // Replacing a matching char, inserting a matching char, and deleting a char.

        int N1 = s.length();
        int N2 = t.length();

        int[][] dp = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return minDistanceHelper(N1 - 1, N2 - 1, s, t, dp);

        // Time complexity: From exp to O(N1 * N2)
        // Space complexity: O(N1 + N2) + O(N1 * N2)
    }

    private int minDistanceHelper(int i, int j, String s, String t, int[][] dp) {
        // Base case
        // If t runs out, meaning there are still characters left in 's' to match w empty string 't'.
        if (j < 0) return i + 1; // delete left chars in i

        // If s runs out, meaning i is an empty string, we've gotta insert that many chars in it
        if (i < 0) return j + 1; // insert remaining chars in ii

        if (dp[i][j] != -1) return dp[i][j];


        // It all practically boils down to string matching
        // If the chars match
        if (s.charAt(i) == t.charAt(j))
            return 0 + minDistanceHelper(i - 1, j - 1, s, t, dp); // Since the chars matched, shrink both strings

        // If they don't, you exercise your options
        // Insert a matching char
        int insert = 1 + minDistanceHelper(i, j - 1, s, t, dp);

        // Replace 'i' w a matching char
        int replace = 1 + minDistanceHelper(i - 1, j - 1, s, t, dp);

        // Delete a char
        int delete = 1 + minDistanceHelper(i - 1, j, s, t, dp);

        return dp[i][j] = Math.min(insert, Math.min(replace, delete));
    }
}
