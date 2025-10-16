package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class UnboundedKnapsackDP {

    static int knapSackViaTabulation(int val[], int wt[], int capacity) {
        // code here
        int N = val.length;
        int dp[][] = new int[N][capacity + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= capacity; t++) {
                if (t == 0) dp[i][t] = 0;
                if (i == 0) dp[i][t] = t / wt[i] * val[i];

                if (i > 0) {
                    int notTake = dp[i - 1][t];
                    int take = Integer.MIN_VALUE;
                    if (wt[i] <= t) take = val[i] + dp[i][t - wt[i]];

                    dp[i][t] = Integer.max(take, notTake);
                }
            }
        }

        return dp[N - 1][capacity];

        // Time complexity: O(N * wt)
        // Space complexity: O(N * wt)
    }

    static int knapSackViaMemoization(int val[], int wt[], int capacity) {
        // code here
        int N = val.length;
        int dp[][] = new int[N][capacity + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return knapsackHelper(N - 1, val, wt, capacity, dp);

        // Time complexity: O(N * wt)
        // Space complexity: O(capacity) + O(N * wt)
    }

    private static int knapsackHelper(int idx, int[] val, int[] wt, int W, int[][] dp) {
        if (W == 0) return 0;
        if (idx == 0) return W / wt[idx] * val[idx];

        if (dp[idx][W] != -1) return dp[idx][W];

        int notTake = knapsackHelper(idx - 1, val, wt, W, dp);
        int take = Integer.MIN_VALUE;
        if (wt[idx] <= W) take = val[idx] + knapsackHelper(idx, val, wt, W - wt[idx], dp);

        return dp[idx][W] = Integer.max(take, notTake);
    }
}
