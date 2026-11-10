package OneDDP;

import java.util.*;

public class HouseRobber {

    public int robMemo(int[] nums) {
        // f(i) -> Maximum amount of money I can rob from
        // house 0 to house i.
        // At every house, I've got two choices -- either
        // pick the house and rob it OR skip the house.
        // And return the max whatever choice gives you.
        int N = nums.length;
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        return robHelper(N - 1, nums, dp);
    }

    private int robHelper(int idx, int[] nums, int[] dp) {
        if (idx == 0) return nums[0];
        // Since (idx - 2) can be negative -- another edge case
        if (idx < 0) return 0;

        if (dp[idx] != -1) return dp[idx];

        // Pick the house
        int pick = nums[idx] + robHelper(idx - 2, nums, dp); // adjacent one gott be skipped
        // Don't pick the house
        int notPick = robHelper(idx - 1, nums, dp);

        return dp[idx] = Math.max(pick, notPick);
    }

    public int robTabulation(int[] nums) {
        // f(i) -> Maximum amount of money I can rob from
        // house 0 to house i.
        // At every house, I've got two choices -- either
        // pick the house and rob it OR skip the house.
        // And return the max whatever choice gives you.
        int N = nums.length;
        int[] dp = new int[N];

        // Tabulation: Bottom-up
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dp[i] = nums[i];
                continue;
            }
            int pick = nums[i] + (i >= 2 ? dp[i - 2] : 0);
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[N - 1];
    }

}
