package DynamicProgramming.DPOnStrings;

public class LongestCommonSubstring {

    public int longCommSubstrTabulation(String s1, String s2) {
        // code here

        // In substring, we're not allowed to carry over
        // the matched chars once a break/mismatch happens
        // in between. So we gotta reset the count to zero
        // everytime a mismatch happens.

        int N1 = s1.length();
        int N2 = s2.length();

        // Unlike LCS (subsequence), the state here represents the current
        // matching suffix ending at i,j.
        // On mismatch the streak resets to 0, so tabulation becomes more
        // natural since every cell independently represents a streak ending
        // there.

        int[][] dp = new int[N1 + 1][N2 + 1];

        int maxLen = 0; // max substring len seen so far
        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                // Match happens
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                    continue;
                }

                dp[i][j] = 0;
            }
        }

        return maxLen;
    }

    int maxLen = 0;

    public int longCommSubstr(String s1, String s2) {
        // code here

        // In substring, we're not allowed to carry over
        // the matched chars once a break/mismatch happens
        // in between. So we gotta reset the count to zero
        // everytime a mismatch happens.

        int N1 = s1.length();
        int N2 = s2.length();

        helper(N1 - 1, N2 - 1, s1, s2);
        return maxLen;
    }

    private int helper(int idx1, int idx2, String s1, String s2) {
        if (idx1 < 0 || idx2 < 0)
            return 0;

        int currCount = 0;

        // Match happens
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            currCount =  1 + helper(idx1 - 1, idx2 - 1, s1, s2);

            // now is this our best answerr? We don't know that
            maxLen = Math.max(maxLen, currCount);
        }

        // Reset count to zero
        int move1 = helper(idx1 - 1, idx2, s1, s2);
        int move2 = helper(idx1, idx2 - 1, s1, s2);

        return currCount;

    }
}
