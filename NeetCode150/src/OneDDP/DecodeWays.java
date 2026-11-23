package OneDDP;

import java.util.*;

public class DecodeWays {

    public int numDecodings(String s) {
        int N = s.length();

        // Intuition: At every idx I've got two choices
        // whether to use the char as a single digit or
        // use two digits.
        //
        // f(i) -> Number of ways to decode the string
        // from idx 0 till idx i.

        // For caching the subproblems
        int[] dp = new int[N];
        Arrays.fill(dp, -1);

        return helper(N - 1, s, dp);

        // Time: O(N)
        // Space: 2.O(N)
    }

    private int helper(int i, String s, int[] dp) {
        if (i == 0)
            return s.charAt(i) != '0' ? 1 : 0;
        if (i < 0)
            return 1; // All chars decoded

        if (dp[i] != -1) return dp[i];

        // Consume char as single digit
        int oneDigit = (s.charAt(i) != '0') ? helper(i - 1, s, dp) : 0;

        // Consume char as a part of two digits
        // Now for to form two digits, we've gotta make sure
        // the number formed should lie between 10 & 26 inclusive.
        int twoDigit = 0;
        if (i - 1 >= 0) {
            int num = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (num >= 10 && num <= 26)
                twoDigit = helper(i - 2, s, dp);
        }

        return dp[i] = oneDigit + twoDigit;
    }

    public int numDecodingsTabulation(String s) {
        int N = s.length();

        // Intuition: At every idx I've got two choices
        // whether to use the char as a single digit or
        // use two digits.
        //
        // f(i) -> Number of ways to decode the string
        // from idx 0 till idx i.

        // For caching the subproblems
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dp[0] = (s.charAt(i) != '0' ? 1 : 0);
                continue;
            }

            int oneDigit = (s.charAt(i) != '0' && (i - 1 >= 0)) ? dp[i - 1] : 0;
            int twoDigit = 0;
            if (i - 1 >= 0) {
                int num = ((s.charAt(i - 1) - '0') * 10) + (s.charAt(i) - '0');
                if (num >= 10 && num <= 26) twoDigit = (i - 2 >= 0) ? dp[i - 2] : 1;
            }

            dp[i] = oneDigit + twoDigit;
        }

        return dp[N - 1];
    }
}