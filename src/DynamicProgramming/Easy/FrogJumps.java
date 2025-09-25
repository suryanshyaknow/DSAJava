package DynamicProgramming.Easy;

import java.util.Arrays;

public class FrogJumps {

    int minCostViaOptimal(int[] heights) {
        // code here: Bottom-up approach
        int N = heights.length;

        // Base case
        int prev1 = 0;
        int prev2 = 0;
        for (int i = 1; i <= N - 1; i++) {
            int oneStepE = prev1 + Math.abs(heights[i] - heights[i - 1]);
            int twoStepE = Integer.MAX_VALUE;
            if (i > 1)
                twoStepE = prev2 + Math.abs(heights[i] - heights[i - 2]);

           int curr = Integer.min(oneStepE, twoStepE);
           prev2 = prev1;
           prev1 = curr;
        }

        return prev1;
    }

    int minCostViaTabulation(int[] heights) {
        // code here: Bottom-up approach
        int N = heights.length;
        int[] dp = new int[N];
        Arrays.fill(dp, -1);

        // Base case
        dp[0] = 0;
        for (int i = 1; i <= N - 1; i++) {
            int oneStepE = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int twoStepE = Integer.MAX_VALUE;
            if (i > 1)
                twoStepE = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);

            dp[i] = Integer.min(oneStepE, twoStepE);
        }

        return dp[N - 1];
    }

    int minCost(int[] heights) {
        // code here
        int N = heights.length;
        int[] dp = new int[N];
        Arrays.fill(dp, -1);

        return minCostHelper(N - 1, heights, dp);
    }

    private static int minCostHelper(int n, int[] heights, int[] dp) {
        if (n == 0) return 0;

        if (dp[n] != -1) return dp[n];

        int leftRec = minCostHelper(n - 1, heights, dp);
        int left = leftRec + Math.abs(heights[n] - heights[n - 1]);

        int right = Integer.MAX_VALUE;
        if (n > 1) {
            int rightRec = minCostHelper(n - 2, heights, dp);
            right = rightRec + Math.abs(heights[n] - heights[n - 2]);
        }
        return dp[n] = Integer.min(left, right);
    }

}
