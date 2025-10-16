package DynamicProgramming.DPOnSubsequences;

public class Knapsack01 {

    static int knapsack(int W, int val[], int wt[]) {
        // code here
        int N = val.length;
        return knapsackHelper(N-1, val, wt, W);

        // Time complexity: O(2^N) cuz at every instance we've got two choices
        // Space complexity: O(N)
    }

    private static int knapsackHelper(int idx, int[] val, int[] wt, int W) {

        if (W == 0) return 0;
        if (idx == 0) {
            // See, if we could fit the last item in our bag
            if (wt[0] <= W) return val[idx];
            else return 0;
        }

        int notTake = knapsackHelper(idx - 1, val, wt, W);
        int take = Integer.MIN_VALUE;
        if (wt[idx] <= W) take = val[idx] + knapsackHelper(idx - 1, val, wt, W-wt[idx]);

        return Integer.max(notTake, take);
    }

}
