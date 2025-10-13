package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class TargetSumDP {

    public int findTargetSumWaysViaTabulation(int[] nums, int target) {
        int tot = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) tot += nums[i];

        // S2 couldn't be negative and fractional
        if (tot - target < 0 || (tot - target) % 2 != 0) return 0;
        int newTarget = (tot - target) / 2;

        int[][] dp = new int[N][newTarget + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= newTarget; t++) {
                if (i == 0) {
                    if (nums[i] == 0 && t == 0) dp[i][t] = 2;
                    else if (t == 0 || nums[i] == t) dp[i][t] = 1;
                    else dp[i][t] = 0;
                }

                if (i > 0) {
                    int notTake = dp[i - 1][t];
                    int take = 0;
                    if (t >= nums[i]) take = dp[i - 1][t - nums[i]];

                    dp[i][t] = take + notTake;
                }
            }
        }

        return dp[N - 1][newTarget];
    }

    public int findTargetSumWays(int[] nums, int target) {
        // Minus could be taken common and this prob would
        // boil down to computing/counting the subsets.
        // S = S1 + S2
        // S1 - S2 = target
        // S- 2S2 = target
        // S2 = (S - target)/2
        int tot = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) tot += nums[i];

        // S2 couldn't be negative and fractional
        if (tot - target < 0 || (tot - target) % 2 != 0) return 0;
        int newTarget = (tot - target) / 2;

        int dp[][] = new int[N][newTarget + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);


        return helper(N - 1, nums, newTarget, dp);
    }

    private static int helper(int idx, int[] nums, int target, int[][] dp) {
        if (dp[idx][target] != -1) return dp[idx][target];

        if (idx == 0) {
            if (target == 0 && nums[idx] == 0) return 2;
            else if (target == 0 || nums[idx] == target) return 1;
            else return 0;
        }

        int notTake = helper(idx - 1, nums, target, dp);
        int take = 0;
        if (target >= nums[idx]) take = helper(idx - 1, nums, target - nums[idx], dp);

        return dp[idx][target] = take + notTake;
    }
}
