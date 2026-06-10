package DynamicProgramming.DPOnStocks;

import java.util.*;

public class BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfitTabulation(int[] prices) {
        int N = prices.length;

        int dp[][] = new int[N + 2][2];

        for (int i = N+1; i >= 0; i--) {
            for (int j = 0; j < 2; j++) {
                if (i >= N) {
                    dp[i][j] = 0;
                    continue;
                }

                // Explore the possibilities and maximize the profit
                if (j == 1) {
                    int buy = -prices[i] + dp[i + 1][0];
                    int notBuy = dp[i + 1][1];
                    dp[i][j] = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + dp[i + 2][1];
                    int notSell = dp[i + 1][0];
                    dp[i][j] = Math.max(sell, notSell);
                }
            }
        }

        return dp[0][1];
    }

    public int maxProfitMemo(int[] prices) {
        int N = prices.length;

        int dp[][] = new int[N][2];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return helperMemo(0, prices, 1, dp);
    }

    private int helperMemo(int idx, int[] prices, int canBuy, int[][] dp) {
        int N = prices.length;

        // Base case
        if (idx >= N) return 0;
        // Note that if on the last (or the second last) day you sell the stock
        // then w 2 days cooldown, the next txn would fall outta bounds.

        if (dp[idx][canBuy] != -1) return dp[idx][canBuy];

        // Explore the possibilities to maximize the profit
        // Do note that we gotta skip the txn possibily by two days
        // post selling a stock because of cooldown period.
        if (canBuy == 1) {
            int buy = -prices[idx] + helperMemo(idx + 1, prices, 0, dp);
            int notBuy = helperMemo(idx + 1, prices, 1, dp);
            return dp[idx][canBuy] = Math.max(buy, notBuy);
        } else {
            int sell = prices[idx] + helperMemo(idx + 2, prices, 1, dp);
            int notSell = helperMemo(idx + 1, prices, 0, dp);
            return dp[idx][canBuy] = Math.max(sell, notSell);
        }
    }

    public int maxProfit(int[] prices) {
        int N = prices.length;

        return helper(0, prices, 1);
    }

    private int helper(int idx, int[] prices, int canBuy) {
        int N = prices.length;

        // Base case
        if (idx >= N) return 0;
        // Note that if on the last (or the second last) day you sell the stock
        // then w 2 days cooldown, the next txn would fall outta bounds.

        // Explore the possibilities to maximize the profit
        // Do note that we gotta skip the txn possibily by two days
        // post selling a stock because of cooldown period.
        if (canBuy == 1) {
            int buy = -prices[idx] + helper(idx + 1, prices, 0);
            int notBuy = helper(idx + 1, prices, 1);
            return Math.max(buy, notBuy);
        } else {
            int sell = prices[idx] + helper(idx + 2, prices, 1);
            int notSell = helper(idx + 1, prices, 0);
            return Math.max(sell, notSell);
        }
    }

}
