package DynamicProgramming.DPOnStocks;

import java.util.*;

public class BestTimeToBuyAndSellStockIV {

    public int maxProfitSpaceOptimized(int k, int[] prices) {
        int N = prices.length;

        // Intuition: We're allowed k txns.

        // int dp[][][] = new int[N + 1][2][k + 1];
        int[][] ahead = new int[2][k + 1]; // dp[idx + 1][..][..]

        // Where dp[i][j][k] states that the max
        // profit obtainable from ith day till the end
        // where I can buy or not buy the stock depending
        // upon the val of j, and I have k as many txns left
        // to make.
        for (int i = N; i >= 0; i--) {
            int[][] curr = new int[2][k + 1];
            for (int j = 1; j >= 0; j--) {
                for (int cap = k; cap >= 0; cap--) {
                    // Base cases
                    if (i == N || cap == 0) {
                        curr[j][cap] = 0;
                        continue;
                    }

                    int maxProfit = 0;
                    // Explore the possibilities
                    if (j == 1) {
                        int buy = -prices[i] + ahead[0][cap];
                        int notBuy = ahead[1][cap];
                        maxProfit = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + ahead[1][cap - 1];
                        int notSell = ahead[0][cap];
                        maxProfit = Math.max(sell, notSell);
                    }
                    curr[j][cap] = maxProfit;
                }
            }
            ahead = curr;
        }

        // Max price from 0th day till the end where I can buy the stock
        // on this day and have k many transactions.
        return ahead[1][k];

        // Time complexity: O(N * k)
        // Space: O(N * k) got reduced to O(k)
    }

    public int maxProfitTabulation(int k, int[] prices) {
        int N = prices.length;

        // Intuition: We're allowed k txns.

        int dp[][][] = new int[N + 1][2][k + 1];
        // Where dp[i][j][k] states that the max
        // profit obtainable from ith day till the end
        // where I can buy or not buy the stock depending
        // upon the val of j, and I have k as many txns left
        // to make.
        for (int i = N; i >= 0; i--) {
            for (int j = 1; j >= 0; j--) {
                for (int cap = k; cap >= 0; cap--) {
                    // Base cases
                    if (i == N || cap == 0) {
                        dp[i][j][cap] = 0;
                        continue;
                    }

                    int maxProfit = 0;
                    // Explore the possibilities
                    if (j == 1) {
                        int buy = -prices[i] + dp[i + 1][0][cap];
                        int notBuy = dp[i + 1][1][cap];
                        maxProfit = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + dp[i + 1][1][cap - 1];
                        int notSell = dp[i + 1][0][cap];
                        maxProfit = Math.max(sell, notSell);
                    }
                    dp[i][j][cap] = maxProfit;
                }
            }
        }

        // Max price from 0th day till the end where I can buy the stock
        // on this day and have k many transactions.
        return dp[0][1][k];

        // Time complexity: O(N * k)
        // Space: O(N * k)
    }

    public int maxProfitMemo(int k, int[] prices) {
        int N = prices.length;

        // Intuition: We're allowed k txns.

        int dp[][][] = new int[N][2][k + 1];
        // Where dp[i][j][k] states that the max
        // profit obtainable from ith day till the end
        // where I can buy or not buy the stock depending
        // upon the val of j, and I have k as many txns left
        // to make.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return helper(0, prices, 1, k, dp);
    }

    private int helper(int idx, int[] prices, int canBuy, int cap, int[][][] dp) {
        int N = prices.length;
        // Base case
        if (idx == N || cap == 0) return 0;

        if (dp[idx][canBuy][cap] != -1) return dp[idx][canBuy][cap];

        // Explore the possobilities and maximize the profit
        if (canBuy == 1) {
            int notBuy = helper(idx + 1, prices, 1, cap, dp);
            int buy = -prices[idx] + helper(idx + 1, prices, 0, cap, dp);
            return dp[idx][canBuy][cap] = Math.max(buy, notBuy);
        } else {
            int notSell = helper(idx + 1, prices, 0, cap, dp);
            int sell = prices[idx] + helper(idx + 1, prices, 1, cap - 1, dp);
            return dp[idx][canBuy][cap] = Math.max(sell, notSell);
        }
    }

}
