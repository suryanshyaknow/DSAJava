package DynamicProgramming.DPOnStrings;

import java.util.Arrays;

public class DeleteOperationsForTwoStringDP {

    public int minDistanceTabulate(String word1, String word2) {
        // This one could be approached w two different approaches
        // Approach 1: Compute the LCS, and remaining chars in both are what we need to delete, pretty fks simple.
        // Approach 2: String matching at each character w couple choices, almost a simplified variant of Edit Distance.

        int N1 = word1.length();
        int N2 = word2.length();
        int[][] dp = new int[N1 + 1][N2 + 1]; // Cuz from bottom up we can't go negative, so we gotta treat 0 as such

        String s = word1;
        String t = word2;
        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (i == 0) dp[i][j] = j; // j chars to delete from t
                if (j == 0) dp[i][j] = i; // i chars to delete from s

                if (i > 0 && j > 0) {

                    if (s.charAt(i - 1) == t.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else {
                        int delFromS = 1 + dp[i - 1][j];
                        int delFromT = 1 + dp[i][j - 1];
                        dp[i][j] = Math.min(delFromS, delFromT);
                    }
                }
            }
        }

        return dp[N1][N2];
    }

    public int minDistanceMemo(String word1, String word2) {
        // This one could be approached w two different approaches
        // Approach 1: Compute the LCS, and remaining chars in both are what we need to delete, pretty fks simple.
        // Approach 2: String matching at each character w couple choices, almost a simplified variant of Edit Distance.

        int N1 = word1.length();
        int N2 = word2.length();
        int[][] dp = new int[N1][N2];
        for (int i = 0; i < N1; i++) Arrays.fill(dp[i], -1);

        return minDistanceHelper2(N1 - 1, N2 - 1, word1, word2, dp);
    }

    private int minDistanceHelper2(int i, int j, String s, String t, int[][] dp) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        if (dp[i][j] != -1) return dp[i][j];

        // If the chars do match, shrink both strings
        if (s.charAt(i) == t.charAt(j))
            return dp[i][j] = minDistanceHelper2(i - 1, j - 1, s, t, dp);

        // If they don't
        int delFromS = 1 + minDistanceHelper2(i - 1, j, s, t, dp);
        int delFromT = 1 + minDistanceHelper2(i, j - 1, s, t, dp);
        return dp[i][j] = Math.min(delFromS, delFromT);
    }


}
