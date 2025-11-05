package DynamicProgramming.DPOnStocks;

import java.util.Arrays;

public class BuySellStockIIDP {

    public int maxProfitOptimal(int[] prices) {
        int N = prices.length;

        int ahead[] = new int[2]; // represents dp[i+1][j]
        int curr[] = new int[2]; // represents dp[i][j]
        for (int i = N; i >= 0; i--) {
            for (int j = 1; j >= 0; j--) {
                if (i == N) {
                    curr[j] = 0;
                    continue;
                }

                int maxProfit;
                if (j == 1) {
                    int buy = -prices[i] + ahead[0];
                    int notBuy = ahead[1];
                    maxProfit = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + ahead[1];
                    int notSell = ahead[0];
                    maxProfit = Math.max(sell, notSell);
                }
                curr[j] = maxProfit;
            }
            // Now curr becomes ahead
            ahead = curr.clone();
        }

        return ahead[1]; // Since it's first day, you've got liberty to buy

        // Time complexity: O(2N)
        // Space complexity: O(1)
    }

    public int maxProfitTabulate(int[] prices) {
        int N = prices.length;
        int dp[][] = new int[N + 1][2];

        for (int i = N; i >= 0; i--) {
            for (int j = 1; j >= 0; j--) {
                if (i == N) {
                    dp[i][j] = 0;
                    continue;
                }

                int maxProfit;
                if (j == 1) {
                    int buy = -prices[i] + dp[i + 1][0];
                    int notBuy = 0 + dp[i + 1][1];
                    maxProfit = Math.max(buy, notBuy);
                } else {
                    int sell = prices[i] + dp[i + 1][1];
                    int notSell = 0 + dp[i + 1][0];
                    maxProfit = Math.max(sell, notSell);
                }
                dp[i][j] = maxProfit;
            }
        }

        return dp[0][1]; // Since it's first day, you've got liberty to buy
    }

    public int maxProfitMemoize(int[] prices) {
        int N = prices.length;
        int dp[][] = new int[N][2];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return maxProfitHelper(0, 1, prices, dp); // Since it's first day, you've got liberty to buy
    }

    private int maxProfitHelper(int i, int canBuy, int[] arr, int dp[][]) {
        // Base case
        if (i == arr.length)
            return 0;

        if (dp[i][canBuy] != -1) return dp[i][canBuy];

        int profit;
        // If you could buy
        if (canBuy == 1) {
            int buy = -arr[i] + maxProfitHelper(i + 1, 0, arr, dp); // decide to buy, you won't be able to buy any further till you sell it
            int notBuy = maxProfitHelper(i + 1, 1, arr, dp);
            profit = Math.max(buy, notBuy);
        } else {
            // If you couldn't, you sell
            int sell = arr[i] + maxProfitHelper(i + 1, 1, arr, dp); // Since you've sold, you'll eligible to rebuy
            int notSell = maxProfitHelper(i + 1, 0, arr, dp);
            profit = Math.max(sell, notSell);
        }

        return dp[i][canBuy] = profit;
    }

}
