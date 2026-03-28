package DynamicProgramming;

import java.util.*;

public class HouseRobber {

    public int robMemoisation(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        return robHelper(0, nums, dp);
    }

    private int robHelper(int idx, int[] nums, int[] dp) {
        // Edge cases
        int N = nums.length;
//        if (idx == N - 1) return nums[N - 1]; // Meaning the ele before it wasn't picked; Don't really need it
        if (idx >= N) return 0;

        if (dp[idx] != -1) return dp[idx];

        int pick = nums[idx] + robHelper(idx + 2, nums, dp);
        int notPick = robHelper(idx + 1, nums, dp);

        return dp[idx] = Math.max(pick, notPick);
    }

    public int rob(int[] nums) {
        int N = nums.length;

        // No adjacent houses are to be robbed
        int[] dp = new int[N + 2]; // To account picking of the last house

        // Bottom-up approach: We start from the smallest
        // overlapping sub-problem and build our way up
        for (int i = N - 1; i >= 0; i--) {
            int pick = nums[i] + dp[i + 2];
            int notPick = dp[i + 1];

            dp[i] = Math.max(pick, notPick);
        }

        return dp[0];
    }


}
