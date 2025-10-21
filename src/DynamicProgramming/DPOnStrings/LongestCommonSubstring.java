package DynamicProgramming.DPOnStrings;

import java.util.Arrays;
import java.util.Map;

public class LongestCommonSubstring {

    public int longestCommonSubstrViaTabulation(String s1, String s2) {
        // code here
        int N1 = s1.length();
        int N2 = s2.length();

        int dp[][] = new int[N1 + 1][N2 + 1]; // to account for the base cases from the recursion
        int ans = 0;
        for (int i = 0; i <= N1; i++) {
            for (int j = 0; j <= N2; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                if (i > 0 && j > 0) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1]; // See if the prev chars match
                        ans = Integer.max(ans, dp[i][j]);
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    public int longestCommonSubstr(String s1, String s2) {
        // code here
        int N1 = s1.length();
        int N2 = s2.length();

        return longestCommonSubstrHelper(N1 - 1, N2 - 1, s1, s2, 0);
    }

    private int longestCommonSubstrHelper(int i, int j, String s1, String s2, int cnt) {
        if (i < 0 || j < 0) return cnt;


        if (s1.charAt(i) == s2.charAt(j))
            cnt = longestCommonSubstrHelper(i - 1, j - 1, s1, s2, cnt + 1);


        int c1 = longestCommonSubstrHelper(i - 1, j, s1, s2, 0); // reset count to 0
        int c2 = longestCommonSubstrHelper(i, j - 1, s1, s2, 0); // reset count to 0
        return Math.max(cnt, Math.max(c1, c2));
    }

}
