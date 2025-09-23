package DynamicProgramming.Easy;

public class ClimbingStairs {

    public int climbStairs(int n) {
        // How to identify a dp problem? Essentially a recursion problem.
        // - Try all possible ways.
        // - Counting the total number of ways.
        // - Multiple ways but ques asking for min/max output.

        // Shorthand trick:
        // 1. Represent the problem in terms of indexes
        //  -> Instead of thinking “how do I solve this whole thing at once,” think: “What does the solution look like at each position/index?”

        // 2. Do all possible stuff on those indexes according to the problem statement.
        //  -> For each index, consider all choices/options allowed by the problem.

        // 3. If ques states:
        //  - Count all ways: Sum up all the stuff done.
        //  - Find min: Min of all the stuff done.

        // We oughta find the number of ways to reach to step N.
        // Since recursion naturally works from the destination backward, we gotta think it like that way only.
        // ways(5) = ways(4) + ways(3)
        // Meaning: To get to step 5, I coulda come from:
        // - Step 4 (1 step jump)
        // - Step 3 (2 steps jump)

        // Base cases
        if (n == 0) return 1;
        if (n == 1) return 1;

        int oneStepWays = climbStairs(n - 1);
        int twoStepWays = climbStairs(n - 2);
        return oneStepWays + twoStepWays;
    }

    public int climbStairsViaMemoization(int n) {
        int[] dp = new int[n+1];
        for (int i=0; i <= n; i++) {
            dp[i] = -1;
        }
        return helper(n, dp);
    }

    private int helper(int n, int[] dp) {
        // Base cases
        if (n == 0) return 1;
        if (n == 1) return 1;

        // Check if we already stored ans for this iteration
        if (dp[n] != -1) return dp[n];

        return dp[n] = helper(n-1, dp) + helper(n-2, dp);
    }

    public int climbStairsViaTabulation(int n) {
        int[] dp = new int[n+1];
        // Bottom up: from base case to the ans
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

}
