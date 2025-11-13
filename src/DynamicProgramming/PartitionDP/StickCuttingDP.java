package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class StickCuttingDP {

    public int minCostTabulation(int n, int[] cuts) {
        int[] nums = new int[cuts.length + 2];
        Arrays.sort(cuts);
        int N = nums.length;

        nums[0] = 0;
        nums[N - 1] = n;
        for (int i = 1; i < N - 1; i++) {
            nums[i] = cuts[i - 1];
        }

        int dp[][] = new int[N][N];

        for (int i = N - 2; i >= 1; i--) {
            for (int j = 1; j <= N - 2; j++) {
                int minCost = Integer.MAX_VALUE;

                if (i > j) {
                    dp[i][j] = 0;
                    continue;
                }

                for (int k = i; k <= j; k++) { // Each cut is being processed
                    int cost = nums[j + 1] - nums[i - 1] + dp[i][k - 1] + dp[k + 1][j];
                    minCost = Math.min(cost, minCost);
                }
                dp[i][j] = minCost;
            }
        }

        return dp[1][N - 2];
    }

    public int minCostMemoization(int n, int[] cuts) {
        int[] nums = new int[cuts.length + 2];
        Arrays.sort(cuts);
        int N = nums.length;

        nums[0] = 0;
        nums[N - 1] = n;
        for (int i = 1; i < N - 1; i++) {
            nums[i] = cuts[i - 1];
        }

        int dp[][] = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return minCostHelper(1, N - 2, nums, dp);
    }

    private int minCostHelper(int i, int j, int[] nums, int[][] dp) {
        if (i > j) return 0; // nothing left to cut, just a piece left out in bw paddings
        if (dp[i][j] != -1) return dp[i][j];

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            minCost = Math.min(minCost, nums[j + 1] - nums[i - 1] + minCostHelper(i, k - 1, nums, dp) + minCostHelper(k + 1, j, nums, dp));
        }

        return dp[i][j] = minCost;
    }
}
