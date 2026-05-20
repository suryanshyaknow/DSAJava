package DynamicProgramming.DPOnSubsequences;

import java.util.*;

public class CoinsChangeII {

    public int changeTabulation(int amount, int[] coins) {
        int N = coins.length;

        // The idea is pretty simple as counting the fewest
        // number of the coins w the difference being here we
        // gotta count the number of ways, i.e. valid paths.

        int dp[][] = new int[N][amount + 1];

        for (int i=0; i < N; i++) {
            for (int t=0; t <= amount; t++) {
                // Edge cases
                if (t == 0) {
                    dp[i][t] = 1; // one valid path concludes
                    continue;
                }
                if (i == 0) {
                    if (t % coins[i] == 0) dp[i][t] = 1;
                    else dp[i][t] = 0;
                    continue;
                }

                // pick
                int pick = 0;
                if (coins[i] <= t) pick = dp[i][t - coins[i]];

                // not pick
                int notPick = dp[i - 1][t];

                dp[i][t] = pick + notPick;
            }
        }

        return dp[N - 1][amount];

        // Time: O(N * amount)
        // Space: O(N * amount)
    }

    public int change(int amount, int[] coins) {
        int N = coins.length;

        // The idea is pretty simple as counting the fewest
        // number of the coins w the difference being here we
        // gotta count the number of ways, i.e. valid paths.

        int dp[][] = new int[N][amount + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return helper(N - 1, coins, amount, dp);

        // Time: O(N * amount)
        // Space: O(N * amount) for dp arr + O(amount)
    }

    private int helper(int idx, int[] nums, int target, int[][] dp) {
        // Edge cases
        if (target == 0) return 1; // one valid way

        if (idx == 0) {
            if (target % nums[idx] == 0) return 1;
            else return 0;
        }

        if (dp[idx][target] != -1) return dp[idx][target];

        // Pick the coin
        int pick = 0;
        if (nums[idx] <= target) pick += helper(idx, nums, target - nums[idx], dp);

        // Not pick the coin
        int notPick = helper(idx - 1, nums, target, dp);

        // Return all valid paths
        return dp[idx][target] = pick + notPick;
    }
}
