package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class RodCuttingDP {

    public int cutRodViaTabulation(int[] price) {
        // code here
        int N = price.length;

        int dp[][] = new int[N][N + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= N; t++) {
                if (t == 0) dp[i][t] = 0;
                if (i == 0) dp[i][t] = t * price[i];

                if (i > 0) {
                    int rodLen = i + 1;
                    int notTake = dp[i - 1][t];
                    int take = rodLen <= t ? price[i] + dp[i][t - rodLen] : 0;

                    dp[i][t] = Integer.max(take, notTake);
                }
            }
        }

        return dp[N - 1][N];
    }

    public int cutRod(int[] price) {
        // code here
        int N = price.length;

        int dp[][] = new int[N][N + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return cutRodHelper(N - 1, price, N, dp);
    }

    private int cutRodHelper(int idx, int[] price, int target, int[][] dp) {
        if (target == 0) return 0;

        if (dp[idx][target] != -1) return dp[idx][target];

        int rodLength = idx + 1;
        if (idx == 0) {
            return target / 1 * price[idx];
        }

        // If we don't consider the current piece
        int notTake = cutRodHelper(idx - 1, price, target, dp);
        int take = rodLength <= target ? price[idx] + cutRodHelper(idx, price, target - rodLength, dp) : 0;
        return dp[idx][target] = Integer.max(notTake, take);
    }
}
