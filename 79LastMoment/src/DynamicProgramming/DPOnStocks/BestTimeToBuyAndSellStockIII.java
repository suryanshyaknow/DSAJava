package DynamicProgramming.DPOnStocks;

import java.util.*;

public class BestTimeToBuyAndSellStockIII {

    public int maxProfitSpaceOptimized(int[] prices) {
        int N = prices.length;

        // In here, we've got three states to manage
        // int dp[][][] = new int[N + 1][2][3]; // dp[idx][canBuy][cap]

        // int[][] curr = new int[2][3]; // dp[idx][][]
        int[][] ahead = new int[2][3]; // dp[idx + 1][][]

        for (int i = N; i >= 0; i--) {
            int[][] curr = new int[2][3]; // dp[idx][][]
            for (int j = 1; j >= 0; j--) {
                for (int k = 2; k >= 0; k--) {
                    if (i == N || k == 0) {
                        curr[j][k] = 0;
                        continue;
                    }

                    int maxProfit = 0;
                    if (j == 1) {
                        int buy = -prices[i] + ahead[0][k];
                        int notBuy = ahead[1][k];
                        maxProfit = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + ahead[1][k - 1];
                        int notSell = ahead[0][k];
                        maxProfit = Math.max(sell, notSell);
                    }
                    curr[j][k] = maxProfit;
                }
            }
            ahead = curr;
        }

        return ahead[1][2]; // Allowed to make no more than 2 txns

        // Time: O(N * 2 * 3)
        // Space: O(N)
    }

    public int maxProfitTabulation(int[] prices) {
        int N = prices.length;

        // In here, we've got three states to manage
        int dp[][][] = new int[N + 1][2][3]; // dp[idx][canBuy][cap]
        /**
         * dp[idx][canBuy][cap]
         *
         * = Maximum profit that can be earned
         * starting from day idx till the end,
         *
         * given that:
         *
         * 1. canBuy tells whether I'm currently free to buy a stock
         *    or already holding one.
         *
         * 2. cap transactions are still available.
         */

        for (int i = N; i >= 0; i--) {
            for (int j = 1; j >= 0; j--) {
                for (int k = 2; k >= 0; k--) {
                    if (i == N || k == 0) {
                        dp[i][j][k] = 0;
                        continue;
                    }

                    int maxProfit = 0;
                    if (j == 1) {
                        int buy = -prices[i] + dp[i + 1][0][k];
                        int notBuy = dp[i + 1][1][k];
                        maxProfit = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + dp[i + 1][1][k - 1];
                        int notSell = dp[i + 1][0][k];
                        maxProfit = Math.max(sell, notSell);
                    }
                    dp[i][j][k] = maxProfit;
                }
            }
        }

        return dp[0][1][2]; // Allowed to make no more than 2 txns

        // Time: O(N * 2 * 3)
        // Space: O(N)
    }

    public int maxProfitMemo(int[] prices) {
        int N = prices.length;

        // In here, we've got three states to manage
        int dp[][][] = new int[N][2][3]; // dp[idx][canBuy][cap]
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return helperMemo(0, prices, 1, 2, dp); // Allowed to make no more than 2 txns
    }

    private int helperMemo(int idx, int[] arr, int canBuy, int cap, int[][][] dp) {
        int N = arr.length;

        // Base case
        if (idx == N || cap == 0)
            return 0; // Ran out of days or allowed transactions

        if (dp[idx][canBuy][cap] != -1)
            return dp[idx][canBuy][cap];

        // Explore the possibilities and maximize the profit
        if (canBuy == 1) {
            int buy = -arr[idx] + helperMemo(idx + 1, arr, 0, cap, dp);
            int notBuy = helperMemo(idx + 1, arr, 1, cap, dp);
            return dp[idx][canBuy][cap] = Math.max(buy, notBuy);
        } else {
            int sell = arr[idx] + helperMemo(idx + 1, arr, 1, cap - 1, dp);
            int notSell = helperMemo(idx + 1, arr, 0, cap, dp);
            return dp[idx][canBuy][cap] = Math.max(sell, notSell);
        }
    }

    public int maxProfit(int[] prices) {
        // Intuition: What makes this problem diff
        // from others is the cap on number of transactions.
        // I.e., no more than 2, whatsoever.
        // The rest is obvious, each day I'll be given a choice to
        // buy a stock if I dont have any and sell it if I do
        // have one.
        // Also note that, buy + sell = 1 transaction, so you should
        // increment the txn count on selling the stock.

        // On the first day you're free to buy the stock, hence canBuy = 1
        return helper(0, prices, 1, 2); // Allowed to make no more than 2 txns
    }

    private int helper(int idx, int[] arr, int canBuy, int cap) {
        int N = arr.length;

        // Base case
        if (idx == N || cap == 0) return 0; // Ran out of days or allowed transactions

        // Explore the possibilities and maximize the profit
        if (canBuy == 1) {
            int buy = -arr[idx] + helper(idx + 1, arr, 0, cap);
            int notBuy = helper(idx + 1, arr, 1, cap);
            return Math.max(buy, notBuy);
        } else {
            int sell = arr[idx] + helper(idx + 1, arr, 1, cap - 1);
            int notSell = helper(idx + 1, arr, 0, cap);
            return Math.max(sell, notSell);
        }
    }

}
