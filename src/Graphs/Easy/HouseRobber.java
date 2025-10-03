package Graphs.Easy;

import java.util.Arrays;

public class HouseRobber {

    public int robOptimal(int[] nums) {
        int N = nums.length;

        // Prefill w base cases
        int prev1 = nums[0];
        int prev2 = 0;

        for (int i = 1; i < N; i++) {
            int take = nums[i];
            if (i - 2 >= 0) take += prev2;
            int skip = prev1;

            int curr = Integer.max(take, skip);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public int robViaTabulation(int[] nums) {
        int N = nums.length;
        int dp[] = new int[N + 1];
        Arrays.fill(dp, -1);

        // Prefill w base cases
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            int take = nums[i];
            if (i - 2 >= 0) take += dp[i - 2];
            int skip = dp[i - 1];

            dp[i] = Integer.max(take, skip);
        }

        return dp[N - 1];
    }

    public int rob(int[] nums) {
        int dp[] = new int[nums.length + 1];
        return robHelper(nums.length - 1, nums, dp);
    }

    private static int robHelper(int idx, int[] nums, int[] dp) {
        // Base cases
        if (idx == 0) return nums[idx];
        if (idx < 0) return 0;

        if (dp[idx] != -1) return dp[idx];

        // Take the curr ele, but then we oughta skip the next one
        int take = nums[idx] + robHelper(idx - 2, nums, dp);
        int skip = robHelper(idx - 1, nums, dp);
        return dp[idx] = Integer.max(take, skip);
    }


}
