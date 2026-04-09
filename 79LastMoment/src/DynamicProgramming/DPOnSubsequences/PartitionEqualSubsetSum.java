package DynamicProgramming.DPOnSubsequences;

import java.util.*;

public class PartitionEqualSubsetSum {

    public boolean canPartitionTabulation(int[] nums) {
        // Given that we gotta determine if there
        // are two subsets w equal sum..
        // Simply translates to we gotta find a subset
        // w half the sum of all the elements in the arr.

        int N = nums.length;
        int S = 0;
        for (int i = 0; i < N; i++) {
            S += nums[i];
        }
        if (S % 2 != 0)
            return false; // Odd Sum
        int s = S / 2;
        int[][] dp = new int[N][s + 1]; // plus 1 to include 0 target as well

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= s; t++) {
                if (t == 0) {
                    dp[i][t] = 1;
                    continue;
                }
                if (i == 0) {
                    dp[i][t] = (nums[i] == t) ? 1 : 0;
                    continue;
                }

                // take
                boolean take = false;
                if (nums[i] <= t)
                    take = dp[i - 1][t - nums[i]] == 1;
                boolean notTake = dp[i - 1][t] == 1;

                dp[i][t] = (take || notTake) ? 1 : 0;
            }
        }

        return dp[N - 1][s] == 1;

        // Time: O(N * s)
        // Space: O(N * s)
    }

    public boolean canPartition(int[] nums) {
        // Given that we gotta determine if there
        // are two subsets w equal sum..
        // Simply translates to we gotta find a subset
        // w half the sum of all the elements in the arr.

        int N = nums.length;
        int S = 0;
        for (int i = 0; i < N; i++) {
            S += nums[i];
        }
        if (S % 2 != 0)
            return false; // Odd Sum
        int s = S / 2;
        int[][] dp = new int[N][s + 1]; // plus 1 to include 0 target as well
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        return helper(N - 1, S / 2, nums, dp);

        // Time: O(N * s)
        // Space: O(N) + O(N * s)
    }

    private boolean helper(int i, int t, int[] nums, int[][] dp) {
        int N = nums.length;
        if (t == 0)
            return true;
        if (i == 0)
            return nums[i] == t;

        if (dp[i][t] != -1)
            return dp[i][t] == 1;

        // Take the ele
        boolean take = false;
        // See, if it could even be taken
        if (nums[i] <= t)
            take = helper(i - 1, t - nums[i], nums, dp);
        boolean notTake = helper(i - 1, t, nums, dp);

        boolean res = take || notTake;
        dp[i][t] = res ? 1 : 0;
        return res;
    }
}
