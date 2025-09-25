package DynamicProgramming.Easy;

import java.util.Arrays;

public class FrogJumpWithKDistances {

    int minCostViaTabulation(int[] heights, int k) {
        // code here
        // Here frog could jump from 1 to kth stairs
        int N = heights.length;
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        for (int i = 1; i <= N - 1; i++) {
            int minE = Integer.MAX_VALUE;

            for (int j = 1; i < k; j++) {
                int curri = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                minE = Integer.min(minE, curri);
            }
        }
        return dp[N - 1];
    }

    int minCost(int[] heights, int k) {
        // code here
        // Here frog could jump from 1 to kth stairs
        int N = heights.length;
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);

        return minCostHelper(N - 1, heights, k, dp);

        // Time complexity: O(N * k)
        // Space complexity: O(N) + O(N)
    }

    private int minCostHelper(int n, int[] height, int k, int[] dp) {
        if (n == 0) return 0;

        if (dp[n] != -1) return dp[n];

        // Frog could make 1 jump to kth jumps
        int minE = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (n - i >= 0) {
                int curr = minCostHelper(n - i, height, k, dp) + Math.abs(height[n] - height[n - i]);
                minE = Integer.min(minE, curr);
            }
        }
        return dp[n] = minE;
    }

}
