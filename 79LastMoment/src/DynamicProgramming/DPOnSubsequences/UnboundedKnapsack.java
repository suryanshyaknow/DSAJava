package DynamicProgramming.DPOnSubsequences;

import java.util.*;

public class UnboundedKnapsack {

    public int knapSackTabulation(int val[], int wt[], int capacity) {
        // code here
        // So the idea is to maximize the values and
        // not exceed the weight capacity

        int N = val.length;
        int dp[][] = new int[N][capacity + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= capacity; t++) {
                // Edge cases
                if (t == 0) {
                    dp[i][t] = 0;
                    continue;
                }
                if (i == 0) {
                    if (t % wt[i] == 0) {
                        dp[i][t] = val[i] * t / wt[i];
                    } else {
                        dp[i][t] = 0;
                    }
                    continue;
                }

                // Pick the item
                int pick = 0;
                if (wt[i] <= t) pick = val[i] + dp[i][t - wt[i]];

                // Don't pick
                int notPick = dp[i - 1][t];

                dp[i][t] = Math.max(pick, notPick);
            }
        }

        return dp[N - 1][capacity];

        //Time: O( N * capacity)
        // Space: O(N * capacity)
    }

    public int knapSackMemo(int val[], int wt[], int capacity) {
        // code here
        // So the idea is to maximize the values and
        // not exceed the weight capacity

        int N = val.length;

        int dp[][] = new int[N][capacity + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return helperMemo(N - 1, val, wt, capacity, dp);

        //Time: O( N * capacity)
        // Space: O(N * capacity) + O(capacity)
    }

    private int helperMemo(int idx, int[] val, int[] wt, int capacity, int dp[][]) {
        // Edge cases
        if (capacity == 0) return 0;
        if (idx == 0) {
            if (capacity % wt[idx] == 0) return capacity / wt[idx] * val[idx];
            else
                return 0;
        }

        if (dp[idx][capacity] != -1) return dp[idx][capacity];

        // Pick the item
        int pick = Integer.MIN_VALUE;
        if (wt[idx] <= capacity) {
            pick = val[idx] + helperMemo(idx, val, wt, capacity - wt[idx], dp);
        }

        // Don't pick the item
        int notPick = helperMemo(idx - 1, val, wt, capacity, dp);

        // Keep the max
        return dp[idx][capacity] = Math.max(pick, notPick);

    }

    public int knapSack(int val[], int wt[], int capacity) {
        // code here
        // So the idea is to maximize the values and
        // not exceed the weight capacity

        int N = val.length;

        return helper(N - 1, val, wt, capacity);
    }

    private int helper(int idx, int[] val, int[] wt, int capacity) {
        // Edge cases
        if (capacity == 0) return 0;
        if (idx == 0) {
            if (capacity % wt[idx] == 0) return capacity / wt[idx] * val[idx];
            else
                return 0;
        }

        // Pick the item
        int pick = Integer.MIN_VALUE;
        if (wt[idx] <= capacity) {
            pick = val[idx] + helper(idx, val, wt, capacity - wt[idx]);
        }

        // Don't pick the item
        int notPick = helper(idx - 1, val, wt, capacity);

        // Keep the max
        return Math.max(pick, notPick);

    }
}
