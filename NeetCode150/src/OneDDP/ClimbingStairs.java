package OneDDP;

import java.util.*;

public class ClimbingStairs {

    public int climbStairs(int n) {

        // At each stair, we can either take one or two steps.
        // f(i) -> Represents the number of ways one can reach from
        // step 0 to step i.

        // Memoization: Top-down approach. It's a technique in which
        // we store/ remember the result of a subproblem instead of
        // computing it again in recursion.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return helper(n, dp);

        // Time: O(N)
        // Space: O(N)+ O(H)
    }

    private int helper(int n, int[] dp) {
        if (n == 0) return 1;
        if (n == 1) return 1;

        // Before proceeding w the main logic we see
        // if this subproblem has been already solved
        if (dp[n] != -1) return dp[n];

        // What and all can we do on a give step
        int one = helper(n - 1, dp);
        int two = helper(n - 2, dp);

        return dp[n] = one + two;
    }

    public int climbStairsTabulation(int n) {

        // At each stair, we can either take one
        // or two steps.
        // f(i) -> Represents the number of ways one can reach from
        // step 0 to step i.

        int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);

        // Tabulation: Bottom-up approach. We start off w the base cases
        // and use them to form the solution till N.
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == 1) {
                dp[i] = 1;
                continue;
            }
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

        // Time: O(N)
        // Space: O(N)
    }
}
