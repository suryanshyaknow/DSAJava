package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class SubsetSumEqualsKDP {

    static Boolean isSubsetSumViaTabulation(int arr[], int sum) {
        // code here
        int N = arr.length;
        boolean dp[][] = new boolean[N][sum + 1];

        // Base cases
        for (int i = 0; i < N; i++) {
            for (int t = 0; t < sum + 1; t++) {
                if (t == 0) dp[i][t] = true; // 1: true

                if (i == 0 && arr[i] == t) dp[i][t] = true;

                if (i >= 1) {
                    boolean nonTake = dp[i - 1][t];
                    boolean take = false;
                    if (arr[i] <= t)
                        take = dp[i - 1][t - arr[i]];

                    boolean bool = take || nonTake;
                    dp[i][t] = bool;
                }
            }
        }
        return dp[N - 1][sum];
    }

    static Boolean isSubsetSumViaMemoization(int arr[], int sum) {
        // code here
        int N = arr.length;
        int dp[][] = new int[N][sum + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return isSubsetSumHelper(N - 1, arr, sum, dp);
    }

    private static Boolean isSubsetSumHelper(int idx, int[] arr, int target, int[][] dp) {
        // Base cases
        if (target == 0) return true;
        if (idx == 0) return arr[idx] == target;

        if (dp[idx][target] != -1) return dp[idx][target] == 1;

        // Don't pick the curr ele and move to the next (because we're generating all possible combinations)
        boolean notTake = isSubsetSumHelper(idx - 1, arr, target, dp); // target remains the same

        // Pick the curr ele and move to the next
        boolean take = false;
        // But to pick the curr ele it should be less than or equal to the target
        if (arr[idx] <= target) take = isSubsetSumHelper(idx - 1, arr, target - arr[idx], dp);

        boolean bool = take || notTake;
        dp[idx][target] = bool ? 1 : 0;
        return bool;
    }

}
