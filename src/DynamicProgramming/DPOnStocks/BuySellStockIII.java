package DynamicProgramming.DPOnStocks;

import java.util.Arrays;

public class BuySellStockIII {

    public int maxProfitOptimal(int[] prices) {
        int N = prices.length;

        int ahead[][] = new int[2][3]; // dp[i+1][canBuy][cap]
        int curr[][] = new int[2][3];
        for (int i = N; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) { // can/can't buy
                for (int cap = 0; cap <= 2; cap++) {
                    if (i == N) {
                        curr[j][cap] = 0;
                        continue;
                    }
                    if (cap == 0) {
                        curr[j][cap] = 0;
                        continue;
                    }

                    int maxProfit;
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
            ahead = curr.clone();
        }

        return ahead[1][2]; // cap being no of txns allowed
    }

    public int maxProfitTabulate(int[] prices) {
        int N = prices.length;
        int dp[][][] = new int[N + 1][2][3];

        for (int i = N; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) { // can/can't buy
                for (int cap = 0; cap <= 2; cap++) {
                    if (i == N) {
                        dp[i][j][cap] = 0;
                        continue;
                    }
                    if (cap == 0) {
                        dp[i][j][cap] = 0;
                        continue;
                    }

                    int maxProfit;
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

        return dp[0][1][2]; // cap being no of txns allowed
    }

    public int maxProfitMemoize(int[] prices) {
        int N = prices.length;

        int dp[][][] = new int[N][2][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= 1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return maxProfitHelper(0, prices, 1, 2, dp); // cap being no of txns allowed
    }

    private int maxProfitHelper(int i, int[] arr, int canBuy, int cap, int dp[][][]) {
        // Starting off an ith-day where you can/can't buy the stock, what's the max profit you can get?

        if (i == arr.length) return 0;
        if (cap == 0) return 0; // No more txns allowed

        if (dp[i][canBuy][cap] != -1) return dp[i][canBuy][cap];

        int maxProfit;
        if (canBuy == 1) {
            int buy = -arr[i] + maxProfitHelper(i + 1, arr, 0, cap, dp);
            int notBuy = maxProfitHelper(i + 1, arr, 1, cap, dp);
            maxProfit = Math.max(buy, notBuy);
        } else {
            int sell = arr[i] + maxProfitHelper(i + 1, arr, 1, cap - 1, dp);
            int notSell = maxProfitHelper(i + 1, arr, 0, cap, dp);
            maxProfit = Math.max(sell, notSell);
        }
        return dp[i][canBuy][cap] = maxProfit;
    }

}
