package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class PerfectSumDP {

    public int perfectSumViaTabulation(int[] nums, int target) {
        // code here
        int N = nums.length;
        int dp[][] = new int[N][target + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= target; t++) {
                if (i == 0) {
                    // Zero included
                    if (t == 0 && nums[0] == 0) dp[i][t] = 2;

                    // nums[idx] happens to be the target
                    else if (t == 0 || nums[0] == t) dp[i][t] = 1;
                    else dp[i][t] = 0;
                }

                if (i > 0) {
                    int notPick = dp[i - 1][t];
                    int pick = 0;
                    if (nums[i] <= t) pick = dp[i - 1][t - nums[i]];
                    dp[i][t] = pick + notPick;
                }
            }
        }
        return dp[N - 1][target];

        // Time complexity: O(N * target)
        // Space complexity: O(N * target)
    }

    public int perfectSumViaMemoization(int[] nums, int target) {
        // code here
        int N = nums.length;
        int dp[][] = new int[N][target + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        return perfectSumCounter(N - 1, nums, target, dp);

        // Time complexity: O(N * target)
        // Space complexity: O(N*target) + O(N)
    }

    private static int perfectSumCounter(int idx, int[] arr, int target, int[][] dp) {
        // if (target == 0) {
        //     return 1;
        // }

        if (idx == 0) {
            if (target == 0 && arr[0] == 0)
                return 2; // zero included
            if (target == 0 || arr[0] == target)
                return 1;
            return 0;

        }

        if (dp[idx][target] != -1) return dp[idx][target];

        // Implement the pick/non-pick strategy
        int notPick = perfectSumCounter(idx - 1, arr, target, dp);
        int pick = 0;
        if (arr[idx] <= target)
            pick = perfectSumCounter(idx - 1, arr, target - arr[idx], dp);
        return dp[idx][target] = pick + notPick;
    }
}
