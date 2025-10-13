package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    public boolean canPartitionViaTabulation(int[] arr) {
        // First off see, if the sum of nums is even to determine
        // if we could have two subsets w equal sum
        int s = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            s += arr[i];
        }
        if (s % 2 != 0)
            return false;

        int target = s / 2;
        boolean dp[][] = new boolean[N][target + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t < target + 1; t++) {
                if (t == 0) dp[i][t] = true;
                if (i == 0 && arr[i] == t) dp[i][t] = true;

                if (i > 0) {
                    boolean take = dp[i - 1][t];
                    boolean nonTake = false;
                    if (arr[i] <= t) nonTake = dp[i - 1][t - arr[i]];
                    boolean res = take || nonTake;
                    dp[i][t] = res;
                }
            }
        }
        return dp[N - 1][target];
    }

    public boolean canPartitionViaMemoization(int[] arr) {
        // First off see, if the sum of nums is even to determine
        // if we could have two subsets w equal sum
        int s = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            s += arr[i];
        }
        if (s % 2 != 0)
            return false;

        int dp[][] = new int[N][s / 2 + 1];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        return canPartitionHelper(N - 1, arr, s / 2, dp);
    }

    private static boolean canPartitionHelper(int idx, int[] arr, int target, int[][] dp) {
        // Base cases
        if (target == 0)
            return true;
        if (idx == 0)
            return arr[0] == target;

        if (dp[idx][target] != -1)
            return dp[idx][target] == 1;

        // Now, implement the take and not take approach to actually form the subset
        boolean nonTake = canPartitionHelper(idx - 1, arr, target, dp);
        boolean take = false;
        if (arr[idx] <= target)
            take = canPartitionHelper(idx - 1, arr, target - arr[idx], dp);

        boolean res = nonTake || take;
        dp[idx][target] = res ? 1 : 0;
        return res;
    }
}
