package DynamicProgramming.DPOnStocks;

import java.util.*;

public class BuySellStockWithTransactionFee {

    public int maxProfitSpaceOptimized(int[] prices, int fee) {
        int N = prices.length;

        // Intuition: For evry transaction (buy + sell), *fee*
        // gets deducted as a transaction fee.

        // int dp[][] = new int[N + 1][2];
        int[] ahead = new int[2]; // dp[idx + 1][..]

        for (int i = N - 1; i >= 0; i--) {
            int[] curr = new int[2];
            for (int j = 0; j < 2; j++) {
                int maxProfit = 0;
                if (j == 1) {
                    int buy = -prices[i] + ahead[0];
                    int notBuy = ahead[1];
                    maxProfit = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] - fee + ahead[1];
                    int notSell = ahead[0];
                    maxProfit = Math.max(sell, notSell);
                }
                curr[j] = maxProfit;
            }
            ahead = curr.clone();
        }

        return ahead[1]; // profit accumulated starting from 0th day till last
        // where on the very first day you can buy the stock

        // Time: O(N)
        // Space: O(1)
    }

    public int maxProfit(int[] prices, int fee) {
        int N = prices.length;

        // Intuition: For evry transaction (buy + sell), *fee*
        // gets deducted as a transaction fee.

        int dp[][] = new int[N + 1][2];
        for (int i=0; i < N; i++) Arrays.fill(dp[i], -1);

        return helper(0, prices, fee, 1, dp);

        // Time: O(N)
        // Space: O(N) + O(N)
    }

    private int helper(int idx, int[] prices, int fee, int canBuy, int[][] dp) {
        int N = prices.length;

        if (idx == N) return 0;

        if (dp[idx][canBuy] != -1) return dp[idx][canBuy];

        // Explore the possibilities and maximize the profit
        if (canBuy == 1) {
            int buy = -prices[idx] + helper(idx + 1, prices, fee, 0, dp);
            int notBuy = helper(idx + 1, prices, fee, 1, dp);
            return dp[idx][canBuy] = Math.max(buy, notBuy);
        } else {
            int sell = prices[idx] - fee + helper(idx + 1, prices, fee, 1, dp);
            int notSell = helper(idx + 1, prices, fee, 0, dp);
            return dp[idx][canBuy] = Math.max(sell, notSell);
        }
    }
}
