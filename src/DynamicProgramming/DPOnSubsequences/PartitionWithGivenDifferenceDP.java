package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class PartitionWithGivenDifferenceDP {

    int countPartitionsViaTabulation(int[] arr, int d) {
        // First off, compute S
        int S = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            S += arr[i];
        }

        // S2 couldn't be negative, S2 couldn't be fractional
        if (S < d || (S - d) % 2 != 0) return 0;
        int S2 = (S - d) / 2;
        int dp[][] = new int[N][S2 + 1];

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= S2; t++) {
                if (i == 0) {
                    if (arr[i] == 0 && t == 0) dp[i][t] = 2;
                    else if (t == 0 || arr[i] == t) dp[i][t] = 1;
                    else dp[i][t] = 0;
                }
                if (i > 0) {
                    int notTake = dp[i - 1][t];
                    int take = 0;
                    if (arr[i] <= t) take = dp[i - 1][t - arr[i]];

                    dp[i][t] = take + notTake;
                }
            }
        }
        return dp[N - 1][S2];
    }

    int countPartitionsViaMemoization(int[] arr, int d) {
        // code here
        // S1 >= S2
        // S1 - S2 = d
        // S1 + S2 = S
        // S1 = S - S2
        // S - 2S2 = d
        // S2 = (S - d) / 2

        // First off, compute S
        int S = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            S += arr[i];
        }

        // S2 couldn't be negative, S2 couldn't be fractional
        if (S < d || (S - d) % 2 != 0) return 0;
        int S2 = (S - d) / 2;
        int dp[][] = new int[N][S2 + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return countPartitionHelper(N - 1, arr, S2, dp);
    }

    private static int countPartitionHelper(int idx, int[] arr, int target, int[][] dp) {
        if (dp[idx][target] != -1) return dp[idx][target];

        // Edge cases
        if (idx == 0) {
            if (arr[idx] == 0 && target == 0) return 2;
            else if (target == 0 || target == arr[idx]) return 1;
            else return 0;
        }


        int notTake = countPartitionHelper(idx - 1, arr, target, dp);
        int take = 0;
        if (arr[idx] <= target) take = countPartitionHelper(idx - 1, arr, target - arr[idx], dp);
        return dp[idx][target] = take + notTake;
    }
}
