package TwoDDP;

import java.util.*;

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int N = coins.length;

        // Representation of my recurrence fn:
        // f(i, T) -> represents the number of ways
        // from 0th till ith coin to sum up to T amount;

        int[][] dp = new int[N][amount + 1];
        for (int i=0; i < N; i++) Arrays.fill(dp[i], -1);

        return coinChangeHelper(N - 1, coins, amount, dp);
    }

    private int coinChangeHelper(int i, int[] coins, int target, int[][] dp) {
        // Base case
        // What choice do you have at 0th idx
        if (target == 0) return 1;
        if (i == 0) {
            if (target % coins[0] == 0) {
                // Means we can pick the 0th coin to fulfuil the target
                return 1;
            } else return 0; // so that this path doesn't get picked
        }

        if (dp[i][target] != -1) return dp[i][target];

        // First off at every coin, I've got a choice to pick
        // or not pick the coin.
        // Note that a coin can be picked any number of times.
        // Not Pick
        int notPick = coinChangeHelper(i - 1, coins, target, dp);
        int pick = 0;
        // Ensure that coins is not larger than the target
        if (coins[i] <= target) {
            pick = coinChangeHelper(i, coins, target - coins[i], dp);
        }
        return dp[i][target] = pick + notPick;
    }
}