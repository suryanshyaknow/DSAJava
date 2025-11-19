package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class BooleanParenthesizationDP {

    static int countWaysTabulation(String s) {
        // code here
        int N = s.length();
        int dp[][][] = new int[N][N][2]; // 2 is for isTrue

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= N - 1; j++) {
                for (int eval = 1; eval >= 0; eval--) {
                    // Base cases
                    if (i > j) {
                        dp[i][j][eval] = 0;
                        continue;
                    }
                    if (i == j) {
                        if (eval == 1) dp[i][j][eval] = s.charAt(i) == 'T' ? 1 : 0;
                        else dp[i][j][eval] = s.charAt(i) == 'F' ? 1 : 0;
                        continue;
                    }

                    int ways = 0;
                    for (int k = i + 1; k <= j - 1; k += 2) {
                        int lTrue = dp[i][k - 1][1];
                        int lFalse = dp[i][k - 1][0];
                        int rTrue = dp[k + 1][j][1];
                        int rFalse = dp[k + 1][j][0];

                        if (s.charAt(k) == '&' && eval == 1) ways += lTrue * rTrue; // both true
                        else if (s.charAt(k) == '&' && eval == 0)
                            ways += lFalse * rTrue + lTrue * rFalse + lFalse * rFalse; // either false or both false
                        else if (s.charAt(k) == '|' && eval == 1)
                            ways += lTrue * rTrue + lFalse * rTrue + lTrue * rFalse; // either true or both true
                        else if (s.charAt(k) == '|' && eval == 0) ways += lFalse * rFalse; // both false
                        else if (s.charAt(k) == '^' && eval == 1) ways += lTrue * rFalse + lFalse * rTrue; // none same
                        else if (s.charAt(k) == '^' && eval == 0) ways += lTrue * rTrue + lFalse * rFalse; // both same
                    }
                    dp[i][j][eval] = ways;
                }
            }
        }

        return dp[0][N - 1][1];
    }

    static int countWaysMemoization(String s) {
        // code here
        int N = s.length();
        int dp[][][] = new int[N][N][2]; // 2 is for isTrue
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) Arrays.fill(dp[i][j], -1);
        }

        return countWaysHelper(0, N - 1, 1, s, dp);
    }

    private static int countWaysHelper(int i, int j, int isTrue, String s, int[][][] dp) {
        if (dp[i][j][isTrue] != -1) return dp[i][j][isTrue];

        if (i > j) return 0;
        if (i == j && isTrue == 1) return dp[i][j][isTrue] = s.charAt(i) == 'T' ? 1 : 0;
        if (i == j && isTrue == 0) return dp[i][j][isTrue] = s.charAt(i) == 'F' ? 1 : 0;

        int ways = 0;
        for (int k = i + 1; k <= j; k += 2) {
            int lTrue = countWaysHelper(i, k - 1, 1, s, dp);
            int lFalse = countWaysHelper(i, k - 1, 0, s, dp);
            int rTrue = countWaysHelper(k + 1, j, 1, s, dp);
            int rFalse = countWaysHelper(k + 1, j, 0, s, dp);

            if (s.charAt(k) == '&' && isTrue == 1) ways += lTrue * rTrue; // both true
            else if (s.charAt(k) == '&' && isTrue == 0)
                ways += lFalse * rTrue + lTrue * rFalse + lFalse * rFalse; // either false or both false
            else if (s.charAt(k) == '|' && isTrue == 1)
                ways += lTrue * rTrue + lFalse * rTrue + lTrue * rFalse; // either true or both true
            else if (s.charAt(k) == '|' && isTrue == 0) ways += lFalse * rFalse; // both false
            else if (s.charAt(k) == '^' && isTrue == 1) ways += lTrue * rFalse + lFalse * rTrue; // none same
            else if (s.charAt(k) == '^' && isTrue == 0) ways += lTrue * rTrue + lFalse * rFalse; // both same

        }
        return dp[i][j][isTrue] = ways;

    }
}
