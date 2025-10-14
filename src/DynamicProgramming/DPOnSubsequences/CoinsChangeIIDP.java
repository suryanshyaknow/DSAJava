package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class CoinsChangeIIDP {

    public int changeViaTabulation(int amount, int[] coins) {
        // Important point here is we could use a certain type
        // of denominations any number of times
        int N = coins.length;
        int dp[][] = new int[N][amount + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= amount; t++) {
                if (t == 0) dp[i][t] = 1;
                if (i == 0) {
                    if (t % coins[0] == 0) dp[i][t] = 1;
                    else dp[i][t] = 0;
                }

                if (i > 0) {
                    int notPick = dp[i - 1][t];
                    int pick = 0;
                    if (coins[i] <= t) pick = dp[i][t - coins[i]];

                    dp[i][t] = pick + notPick;
                }
            }
        }

        return dp[N - 1][amount];
        // Time complexity:
    }

    public int changeViaMemoization(int amount, int[] coins) {
        // Important point here is we could use a certain type
        // of denominations any number of times
        int N = coins.length;
        int dp[][] = new int[N][amount + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return changeHelper(N - 1, coins, amount, dp);

        // Time complexity: O(N * target)
        // Space complexity: O(N * target) + O(target), i.e., auxiliary stack space
    }

    private static int changeHelper(int idx, int[] arr, int target, int[][] dp) {
        if (dp[idx][target] != -1) return dp[idx][target];

        // We won't have any coins w zero denomination
        if (target == 0) return 1;
        if (idx == 0) {
            // Since even last ele could be picked any number of times
            if (target % arr[0] == 0) return 1;
            else return 0;
        }

        // Don't pick the coin
        int notPick = changeHelper(idx - 1, arr, target, dp);
        // Pick the coin
        int pick = 0;
        if (arr[idx] <= target) pick = changeHelper(idx, arr, target - arr[idx], dp);

        return dp[idx][target] = pick + notPick;
    }
}
