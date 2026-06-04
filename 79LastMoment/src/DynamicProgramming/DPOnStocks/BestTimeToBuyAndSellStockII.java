package DynamicProgramming.DPOnStocks;

import java.util.*;

public class BestTimeToBuyAndSellStockII {

    public int maxProfitSpaceOptimized(int[] prices) {
        int N = prices.length;

        // int dp[][] = new int[N + 1][2]; // dp[idx][canBuy]

        int ahead[] = new int[2]; // dp[i + 1][..]
        int curr[] = new int[2]; // dp[i][..]

        // Tabulation: Bottom-up
        for (int i = N; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (i == N) {
                    curr[j] = 0;
                    continue;
                }

                // Explore the possibilities and maximize the profit
                if (j == 1) {
                    int buy = -prices[i] + ahead[0];
                    int notBuy = ahead[1];
                    curr[j] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + ahead[1];
                    int notSell = ahead[0];
                    curr[j] = Math.max(sell, notSell);
                }
            }
            // Update the ahead by current val
            ahead = curr.clone();
        }

        return curr[1];

        // Time: O(N * 2)
        // Space: O(1)
    }

    public int maxProfitTabulation(int[] prices) {
        int N = prices.length;

        int dp[][] = new int[N + 1][2]; // dp[idx][canBuy]

        // Tabulation: Bottom-up
        for (int i = N; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (i == N) {
                    dp[i][j] = 0;
                    continue;
                }

                // Explore the possibilities and maximize the profit
                if (j == 1) {
                    int buy = -prices[i] + dp[i + 1][0];
                    int notBuy = dp[i + 1][1];
                    dp[i][j] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + dp[i + 1][1];
                    int notSell = dp[i + 1][0];
                    dp[i][j] = Math.max(sell, notSell);
                }
            }
        }

        return dp[0][1];

        // Time: O(N * 2)
        // Space: O(N * 2)
    }


    public int maxProfitMemo(int[] prices) {
        int N = prices.length;

        int[][] dp = new int[N][2]; // dp[idx][canBuy]
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        return helperMemo(0, prices, 1, dp); // On the very first day, I'm free to buy.

        // Time: O(N * 2)
        // Space: O(N * 2) + O(N)
    }

    private int helperMemo(int idx, int[] prices, int canBuy, int[][] dp) {
        // Base case
        if (idx == prices.length)
            return 0;

        if (dp[idx][canBuy] != -1)
            return dp[idx][canBuy];

        // Choices; Profit: prices(sell day) - prices(buy day)
        // If I'm free to buy
        if (canBuy == 1) {
            int buy = -prices[idx] + helperMemo(idx + 1, prices, 0, dp);
            int notBuy = helperMemo(idx + 1, prices, 1, dp);
            return dp[idx][canBuy] = Math.max(buy, notBuy);
        } else {
            // If Im holding a share of stock
            int sell = prices[idx] + helperMemo(idx + 1, prices, 1, dp);
            int notSell = helperMemo(idx + 1, prices, 0, dp);
            return dp[idx][canBuy] = Math.max(sell, notSell);
        }
    }

    public int maxProfit(int[] prices) {
        // Intuition:
        // On every day, I have a decision to make.
        // The available choices depend on whether I'm currently
        // holding a stock or not.
        //
        // If I'm not holding a stock, I can:
        // 1. Buy it
        // 2. Skip the day
        //
        // If I'm holding a stock, I can:
        // 1. Sell it
        // 2. Continue holding it
        //
        // Since each choice affects the profit obtainable in future
        // days, recursively explore all valid choices and take the
        // one yielding maximum profit..

        return helper(0, prices, true); // On the very first day, I'm free to buy.

        // Time: O(2^N)
        // Space: O(N)
    }

    private int helper(int idx, int[] prices, boolean canBuy) {
        // Base case
        if (idx == prices.length)
            return 0;

        // Choices; Profit: prices(sell day) - prices(buy day)
        // If I'm free to buy
        if (canBuy) {
            int buy = -prices[idx] + helper(idx + 1, prices, false);
            int notBuy = helper(idx + 1, prices, true);
            return Math.max(buy, notBuy);
        } else {
            // If Im holding a share of stock
            int sell = prices[idx] + helper(idx + 1, prices, true);
            int notSell = helper(idx + 1, prices, false);
            return Math.max(sell, notSell);
        }
    }
}
