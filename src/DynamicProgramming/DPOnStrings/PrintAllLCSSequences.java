package DynamicProgramming.DPOnStrings;

import java.util.*;

public class PrintAllLCSSequences {

    public List<String> allLCS(String s1, String s2) {
        // Code here
        int N1 = s1.length();
        int N2 = s2.length();

        int dp[][] = new int[N1 + 1][N2 + 1];

        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (i < 1 || j < 1) dp[i][j] = 0;

                if (i >= 1 && j >= 1) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                    else {
                        dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }

        /*
        int maxLen = dp[N1][N2];
        StringBuilder stringBuilder = new StringBuilder(maxLen);

        int i = N1;
        int j = N2;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // Move diagonally
                stringBuilder.append(s1.charAt(i));
                i--;
                j--;
            } else {
                // Cuz we took the max, we're essentially now backtracking
                if (dp[i - 1][j] >= dp[i][j - 1])
                    i--;
                else
                    j++;
            }
        }
         */

        Set<String> res = new HashSet<>();
        int i = N1;
        int j = N2;
        int maxLen = dp[N1][N2];
        backtrack(i, j, new StringBuilder(maxLen), dp, s1, s2, res);
        List<String> res1 = new ArrayList<>(res);
        Collections.sort(res1);

        return res1;
    }

    private void backtrack(int i, int j, StringBuilder sb, int[][] dp, String s1, String s2, Set<String> res) {
        if (i == 0 || j == 0) {
            res.add(sb.reverse().toString());
            sb.reverse();
            return;
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            sb.append(s1.charAt(i - 1));
            backtrack(i - 1, j - 1, sb, dp, s1, s2, res);
            sb.deleteCharAt(sb.length() - 1); // backtrack
        } else {
            if (dp[i - 1][j] == dp[i][j - 1]) {
                backtrack(i - 1, j, sb, dp, s1, s2, res);
                backtrack(i, j - 1, sb, dp, s1, s2, res);
            } else if (dp[i - 1][j] > dp[i][j - 1])
                backtrack(i - 1, j, sb, dp, s1, s2, res);
            else
                backtrack(i, j - 1, sb, dp, s1, s2, res);
        }
    }

}
