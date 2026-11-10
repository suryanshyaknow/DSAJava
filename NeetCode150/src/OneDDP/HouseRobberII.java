package OneDDP;

import java.util.*;

public class HouseRobberII {

    public int rob(int[] nums) {
        // Given that first and last houses are neighbors.
        // Intuition: The idea is fairly simple. Compute
        // the max amount that can be robbed if we leave
        // first house outta the picture, and similarly,
        // compute the same leaving the last house in the
        // picture.

        int N = nums.length;
        if (N == 1)
            return nums[0];

        // Temp1: Leave the last house
        int[] temp1 = new int[N - 1];
        for (int i = 0; i < N - 1; i++)
            temp1[i] = nums[i];
        // Temp2: Leave the first house
        int[] temp2 = new int[N - 1];
        for (int i = 1; i < N; i++)
            temp2[i - 1] = nums[i];

        // f(i) -> Represents the max amount robbed from
        // house 1 to house i.
        int[] dp1 = new int[N - 1];
        Arrays.fill(dp1, -1);
        int[] dp2 = new int[N - 1];
        Arrays.fill(dp2, -1);

        return Math.max(
                robHelper(N - 2, temp1, dp1),
                robHelper(N - 2, temp2, dp2));
    }

    private int robHelper(int idx, int[] nums, int[] dp) {
        if (idx == 0)
            return nums[idx];
        if (idx < 0)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        // At each idx, I can either pick the house to rob
        // or leave it
        int pick = nums[idx] + robHelper(idx - 2, nums, dp);
        int notPick = robHelper(idx - 1, nums, dp);

        return dp[idx] = Math.max(pick, notPick);
    }
}
