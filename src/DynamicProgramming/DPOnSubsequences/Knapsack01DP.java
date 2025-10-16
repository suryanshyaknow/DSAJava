package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class Knapsack01DP {

    static int knapsackViaTabulation(int W, int val[], int wt[]) {
        // code here
        int N = val.length;
        int dp[][] = new int[N][W + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= W; t++) {
                if (t == 0) dp[i][t] = 0;
                if (i == 0) {
                    if (wt[i] <= t) dp[i][t] = val[i];
                    else dp[i][t] = 0;
                }
                if (i > 0) {
                    int notTake = dp[i - 1][t];
                    int take = Integer.MIN_VALUE;
                    if (wt[i] <= t) take = val[i] + dp[i - 1][t - wt[i]];

                    dp[i][t] = Integer.max(take, notTake);
                }
            }
        }

        return dp[N - 1][W];

        // Time complexity: O(N * W+1)
        // Space complexity: O(N * W+1)
    }

    static int knapsackViaMemoization(int W, int val[], int wt[]) {
        // code here
        int N = val.length;
        int dp[][] = new int[N][W + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return knapsackHelper(N - 1, val, wt, W, dp);

        // Time complexity: O(N * W+1)
        // Space complexity: O(N) + O(N * W+1)
    }

    private static int knapsackHelper(int idx, int[] val, int[] wt, int W, int[][] dp) {
        if (dp[idx][W] != -1) return dp[idx][W];

        if (W == 0) return 0;
        if (idx == 0) {
            // See, if we could fit the last item in our bag
            if (wt[0] <= W) return val[idx];
            else return 0;
        }

        int notTake = knapsackHelper(idx - 1, val, wt, W, dp);
        int take = Integer.MIN_VALUE;
        if (wt[idx] <= W) take = val[idx] + knapsackHelper(idx - 1, val, wt, W - wt[idx], dp);

        return dp[idx][W] = Integer.max(notTake, take);
    }

}
