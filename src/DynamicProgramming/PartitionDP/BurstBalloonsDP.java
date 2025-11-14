package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class BurstBalloonsDP {

    public int maxCoinsTabulation(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        int N = arr.length;
        arr[0] = 1;
        arr[N - 1] = 1;
        for (int i = 1; i <= N - 2; i++) {
            arr[i] = nums[i - 1];
        }

        int dp[][] = new int[N][N];

        for (int i = N - 1; i >= 1; i--) {
            for (int j = 1; j <= N - 2; j++) {
                if (i > j) {
                    dp[i][j] = 0;
                    continue;
                }

                int maxCost = 0;
                for (int k = i; k <= j; k++) {
                    int cost = arr[i-1] * arr[k] * arr[j+1] + dp[i][k-1] + dp[k+1][j];
                    maxCost = Math.max(cost, maxCost);
                }
                dp[i][j] = maxCost;
            }
        }
        return dp[1][N-2];
    }

    public int maxCoinsMemoization(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        int N = arr.length;
        arr[0] = 1;
        arr[N - 1] = 1;
        for (int i = 1; i <= N - 2; i++) {
            arr[i] = nums[i - 1];
        }

        int dp[][] = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        // Since there's neighbors dependency, the sub problems ain't independent.
        // So we gotta think more logically. Instead of picking the first balloon
        // to burst, we're gonna pick the last one.

        return maxCoinsHelper(1, N - 2, arr, dp);
    }

    private static int maxCoinsHelper(int i, int j, int[] arr, int[][] dp) {
        if (i > j) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int maxCoins = 0;
        // The recursion doesn’t burst k immediately.
        // It tries every possible k (from i to j) as a candidate for being the last — and recursively computes the result if that were true.
        for (int k = i; k <= j; k++) {
            int coins = arr[i - 1] * arr[k] * arr[j + 1] + maxCoinsHelper(i, k - 1, arr, dp) + maxCoinsHelper(k + 1, j, arr, dp);
            maxCoins = Math.max(coins, maxCoins);
        }
        return dp[i][j] = maxCoins;
    }

}
