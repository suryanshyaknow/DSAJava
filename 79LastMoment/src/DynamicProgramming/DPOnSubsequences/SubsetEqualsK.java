package DynamicProgramming.DPOnSubsequences;

import java.util.Arrays;

public class SubsetEqualsK {

    static Boolean isSubsetSumTabulation(int arr[], int sum) {
        // code here
        int N = arr.length;

        int dp[][] = new int[N][sum + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int t = 0; t <= sum; t++) {
                // Edge cases
                if (t == 0) {
                    dp[i][t] = 1;
                    continue;
                }

                if (i == 0) {
                    if (arr[0] == t)
                        dp[i][t] = 1;
                    else
                        dp[i][t] = 0;
                    continue;
                }

                // Don't pick the curr ele
                boolean notTake = dp[i - 1][t] == 1; // target stays intact

                boolean take = false;
                if (arr[i] <= t) take = dp[i - 1][t - arr[i]] == 1;

                dp[i][t] = (take || notTake) ? 1 : 0;
            }
        }


        return dp[N - 1][sum] == 1;
    }

    static Boolean isSubsetSumMemo(int arr[], int sum) {
        // code here
        int N = arr.length;

        int dp[][] = new int[N][sum + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helperMemo(N - 1, arr, sum, dp);

        // Time: O(N * target)
        // Space: O(N * target) + O(N)
    }

    private static boolean helperMemo(int i, int[] arr, int target, int[][] dp) {
        // Edge cases
        if (target == 0) return true;
        if (i == 0) return (arr[0] == target);

        if (dp[i][target] != -1) {
            if (dp[i][target] == 1) return true;
            else return false;
        }

        // Take, not take elements, and try to form the subset
        boolean notTake = helperMemo(i - 1, arr, target, dp); // target stays intact

        // see first if you could even pick it
        boolean take = false;
        if (arr[i] <= target)
            take = helperMemo(i - 1, arr, target - arr[i], dp);

        boolean res = take || notTake;
        dp[i][target] = res ? 1 : 0;

        return res;
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int N = arr.length;

        return helper(N - 1, arr, sum);
        // Meaning is there a subset of sum 'sum' from
        // 0 till idx N-1 in the arr 'arr'

        // Time: O(2^N)
        // Space: O(N)
    }

    private static boolean helper(int i, int[] arr, int target) {
        // Edge cases
        if (target == 0) return true;
        if (i == 0) return (arr[0] == target);

        // Take, not take elements, and try to form the subset
        boolean notTake = helper(i - 1, arr, target); // target stays intact

        // see first if you could even pick it
        boolean take = false;
        if (arr[i] <= target)
            take = helper(i - 1, arr, target - arr[i]);

        return take || notTake;
    }
}
