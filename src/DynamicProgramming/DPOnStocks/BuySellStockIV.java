package DynamicProgramming.DPOnStocks;

public class BuySellStockIV {

    public int maxProfit(int k, int[] prices) {
        int N = prices.length;

        // Tabulation Space Optimized
        int ahead[][] = new int[2][k + 1]; // dp[i+1][canBuy][k]
        int curr[][] = new int[2][k + 1];

        for (int i = N; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                for (int cap = 0; cap <= k; cap++) {
                    if (i == N) {
                        curr[j][cap] = 0;
                        continue;
                    }
                    if (cap == 0) {
                        curr[j][cap] = 0;
                        continue;
                    }

                    int maxPro;
                    if (j == 1) {
                        int buy = -prices[i] + ahead[0][cap]; // cuz bought
                        int notBuy = ahead[1][cap];
                        maxPro = Math.max(buy, notBuy);
                    } else {
                        int sell = prices[i] + ahead[1][cap - 1];
                        int notSell = ahead[0][cap];
                        maxPro = Math.max(sell, notSell);
                    }
                    curr[j][cap] = maxPro;
                }
            }
            for (int a = 0; a < 2; a++)
                System.arraycopy(curr[a], 0, ahead[a], 0, k + 1);
        }

        return ahead[1][k];
    }

    public int maxProfitOptimal(int k, int[] prices) {
        int N = prices.length;

        int ahead[][] = new int[2][k + 1];
        int curr[][] = new int[2][k + 1];

        for (int i = N; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) { // can/can't buy
                for (int cap = 0; cap <= k; cap++) {
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
            for (int a = 0; a < 2; a++)
                System.arraycopy(curr[a], 0, ahead[a], 0, k + 1);
        }

        return ahead[1][k]; // cap being no of txns allowed
    }

    private static int maxProfitHelper(int i, int canBuy, int[] arr, int cap) {
        // Base cases
        if (i == arr.length || cap == 0) return 0;

        int maxProfit;
        if (canBuy == 1) {
            int buy = -arr[i] + maxProfitHelper(i + 1, 0, arr, cap);
            int notBuy = maxProfitHelper(i + 1, 1, arr, cap);
            maxProfit = Math.max(buy, notBuy);
        } else {
            int sell = arr[i] + maxProfitHelper(i + 1, 1, arr, cap - 1);
            int notSell = maxProfitHelper(i + 1, 0, arr, cap);
            maxProfit = Math.max(sell, notSell);
        }
        return maxProfit;
    }
}
