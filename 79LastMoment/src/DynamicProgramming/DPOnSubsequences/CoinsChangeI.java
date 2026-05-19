package DynamicProgramming.DPOnSubsequences;

import java.util.*;

public class CoinsChangeI {

    public int coinChangeMemo(int[] coins, int amount) {
        int N = coins.length;

        int dp[][] = new int[N][amount + 1];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        int ans = helperMemo(N - 1, coins, amount, dp);
        if (ans == Integer.MAX_VALUE)
            return -1;
        return ans;

        // Time: O(N * amount)
        // Space: O(N * amount) + O(amount)
    }

    private int helperMemo(int idx, int[] coins, int amount, int[][] dp) {
        // Base cases
        if (amount == 0)
            return 0; // Need 0 coins
        if (idx == 0) {
            if (amount % coins[0] == 0)
                return amount / coins[0];
            else
                return Integer.MAX_VALUE;
        }

        if (dp[idx][amount] != -1)
            return dp[idx][amount];

        int notTake = 0 + helperMemo(idx - 1, coins, amount, dp);
        int take = Integer.MAX_VALUE;
        if (coins[idx] <= amount) {
            int res = helperMemo(idx, coins, amount - coins[idx], dp);
            if (res != Integer.MAX_VALUE) {
                take = 1 + res;
            }
        }

        return dp[idx][amount] = Math.min(take, notTake);
    }

    public int coinChange(int[] coins, int amount) {
        int N = coins.length;

        int ans = helper(N - 1, coins, amount);
        if (ans == Integer.MAX_VALUE)
            return -1;
        return ans;

        // Time: >>> O(2^N) since we're standing over same idx in take cases
        // Space: O(N) -> O(amount)
    }

    private int helper(int idx, int[] coins, int amount) {
        // Base cases
        if (amount == 0) return 0; // Need 0 coins
        if (idx == 0) {
            if (amount % coins[0] == 0) return amount / coins[0];
            else return Integer.MAX_VALUE;
        }

        int notTake = 0 + helper(idx - 1, coins, amount);
        int take = Integer.MAX_VALUE;
        if (coins[idx] <= amount) {
            int res = helper(idx, coins, amount - coins[idx]);
            if (res != Integer.MAX_VALUE) take = 1 + res;
        }

        return Math.min(take, notTake);
    }

}
