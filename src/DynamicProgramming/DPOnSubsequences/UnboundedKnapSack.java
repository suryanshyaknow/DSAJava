package DynamicProgramming.DPOnSubsequences;

public class UnboundedKnapSack {

    static int knapSack(int val[], int wt[], int capacity) {
        // code here
        int N = val.length;

        return knapsackHelper(N-1, val, wt, capacity);

        // Time complexity: >> O(2^N), Exponential
        // Space complexity: O(capacity)
    }

    private static int knapsackHelper(int idx, int[] val, int[] wt, int W) {
        if (W == 0) return 0;
        if (idx == 0) return W/wt[idx] * val[idx];

        int notTake = 0 + knapsackHelper(idx-1, val, wt, W);
        int take = Integer.MIN_VALUE;
        if (wt[idx] <= W) take = val[idx] + knapsackHelper(idx, val, wt, W-wt[idx]);

        return Integer.max(take, notTake);
    }
}
