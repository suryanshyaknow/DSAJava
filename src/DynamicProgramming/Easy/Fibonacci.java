package DynamicProgramming.Easy;

public class Fibonacci {

    public int fib(int n) {
        if (n <= 1) return n;

        return fib(n - 1) + fib(n - 2);
    }


    public int fibUsingDP(int n) {
        // Via Memoization: Where we tend to store the value of subproblems in some map/table.
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }
        return fibHelper(n, dp);

        // Time complexity: O(N)
        // Space complexity: O(N) for recursive stackspace + O(N) for dp
    }

    public int fibUsingTabulation(int n) {
        // Via Tabulation: From bottom up i.e., from base case to required ans.
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }

        // Save the base cases already
        dp[0] = 0;
        if (n > 0)
            dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

        // Time complexity: O(N)
        // Space complexity: O(N) just for dp
    }

    public int fibUsingTabulationOptimal(int n) {
        // Via Tabulation: From bottom up i.e., from base case to required ans.

        // Save the base cases already
        int prev2 = 0;
        int prev1 = 1;

        int curr = 1;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;

            prev2 = prev1;
            prev1 = curr;
        }
        return curr;

        // Time complexity: O(N)
        // Space complexity: O(1)
    }

    private int fibHelper(int n, int[] dp) {
        if (n <= 1) return n;

        // Check if we already stored ans for this is our dp
        if (dp[n] != -1) return dp[n];

        return dp[n] = fibHelper(n - 1, dp) + fibHelper(n - 2, dp);
    }


}
