package TwoDDP;

import java.util.*;

public class CoinChange {

    private static final int INF = 1_000_000_000; // inf sentinel

    public int coinChangeTabulation(int[] coins, int amount) {
        int N = coins.length;

        // Given: Need to form target using min number
        // of given coins.
        // Can use one coin any number of times.
        // Intuition: Since to form a target, we can pick
        // any number of any coin and still have to ensure
        // min number of coins, I'm thinking of using recursion
        // to come up w every possible combination and choose
        // the best outta them -- min number of coins.

        // Representation of my recurrence fn:
        // f(i, T) -> represents the min number of coins
        // from 0th till ith coin to sum up to T amount;

        int[][] dp = new int[N][amount + 1];

        for (int i=0; i < N; i++) {
            for (int t=0; t <= amount; t++) {
                if (t == 0) {
                    dp[i][t] = 0;
                    continue;
                }
                if (i == 0) {
                    if (t % coins[i] == 0) dp[i][t] = t / coins[0];
                    else return dp[i][t] = INF;
                    continue;
                }

                // Take
                int take = INF;
                if (coins[i] <= t) {
                    int res = dp[i][t - coins[i]]; // Can still pick the same coin
                    if (res != INF) take = 1 + res;
                }

                // Not take
                int notTake = dp[i - 1][t];

                dp[i][t] = Math.min(take, notTake);
            }
        }

        return (dp[N - 1][amount] == INF) ? -1 : dp[N - 1][amount];
        // Time: O(N * T)
        // Space: O(N * T)
    }

    public int coinChangeMemo(int[] coins, int amount) {
        int N = coins.length;

        // Given: Need to form target using min number
        // of given coins.
        // Can use one coin any number of times.
        // Intuition: Since to form a target, we can pick
        // any number of any coin and still have to ensure
        // min number of coins, I'm thinking of using recursion
        // to come up w every possible combination and choose
        // the best outta them -- min number of coins.

        // Representation of my recurrence fn:
        // f(i, T) -> represents the min number of coins
        // from 0th till ith coin to sum up to T amoount;

        int[][] dp = new int[N][amount + 1];
        for (int i=0; i < N; i++) Arrays.fill(dp[i], -1);

        int res = coinChangeHelper(N - 1, coins, amount, dp);
        return res == Integer.MAX_VALUE ? -1 : res;

        // Time: O(N * T)
        // Space: O(N * T + T)

        // Recursion's:
        // Time: >> O(2^N) since we stays at one coin any number of times
        // Space: >> O(N)
    }

    private int coinChangeHelper(int i, int[] coins, int target, int[][] dp) {
        // Base case
        // What choice do you have at 0th idx
        if (target == 0) return 0;
        if (i == 0) {
            if (target % coins[0] == 0) {
                // Means we can pick the 0th coin to fulfuil the target
                return target / coins[0];
            } else return Integer.MAX_VALUE; // so that this path doesn't get picked
        }

        if (dp[i][target] != -1) return dp[i][target];

        // First off at every coin, I've got a choice to pick
        // or not pick the coin.
        // Note that a coin can be picked any number of times.
        // Not Pick
        int notPick = coinChangeHelper(i - 1, coins, target, dp);
        int pick = Integer.MAX_VALUE;
        // Ensure that coins is not larger than the target
        if (coins[i] <= target) {
            int res = coinChangeHelper(i, coins, target - coins[i], dp); // Could overflow
            pick = (res == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + res;
        }
        return dp[i][target] = Math.min(pick, notPick);
    }

}