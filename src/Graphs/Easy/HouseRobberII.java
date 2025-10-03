package Graphs.Easy;

import java.util.Arrays;

public class HouseRobberII {

    public int robOptimal(int[] nums) {
        int prev1 = nums[0];
        int prev2 = 0;

        for (int i = 1; i < nums.length; i++) {
            int take = prev2 + nums[i];
            int skip = prev1;

            int curr = Integer.max(take, skip);
            // Move the pointers
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public int robViaTabulation(int idx, int[] nums) {
        // We could either have first house in our answer or the last one
        int N = nums.length;
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        // Base cases
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            int take = nums[i];
            if (i - 2 >= 0) take += nums[i - 2];
            int skip = nums[i - 1];

            dp[i] = Integer.max(take, skip);
        }

        return dp[idx];
    }

    public int rob(int[] nums) {
        // We could either have first house in our answer or the last one
        int N = nums.length;
        if (N == 1) return nums[0];
        int dp[] = new int[N + 1];
        Arrays.fill(dp, -1);

        int[] temp1 = new int[N - 1];
        for (int i = 1; i < N; i++) {
            temp1[i - 1] = nums[i];
        }

        int[] temp2 = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            temp2[i] = nums[i];
        }

        return Integer.max(robHelper(0, temp1, dp), robHelper(0, temp2, dp));
    }

    private int robHelper(int i, int[] nums, int[] dp) {
        if (i == 0) return nums[0];
        if (i < 0) return 0;

        if (dp[i] != -1) return dp[i];

        // Take and skip
        int take = robHelper(i - 2, nums, dp) + nums[i]; // if current is being taken
        int skip = robHelper(i - 1, nums, dp); // If curr is not taken, we oughta take the next
        return dp[i] = Integer.max(take, skip);
    }

}
