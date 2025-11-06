package DynamicProgramming.DPOnStocks;

public class BuySellStockWithCooldown {

    public int maxProfitOptimal(int[] prices) {
        int N = prices.length;
        int arr[] = prices;

        int ahead2[] = new int[2];
        int ahead1[] = new int[2];
        int curr[] = new int[2];
        for (int i = N; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (i >= N) {
                    curr[canBuy] = 0;
                    continue;
                }

                int maxPro;
                if (canBuy == 1) {
                    int buy = -arr[i] + ahead1[0];
                    int notBuy = ahead1[1];
                    maxPro = Math.max(buy, notBuy);
                } else {
                    int sell = arr[i] + ahead2[1]; // cuz of cooldown
                    int notSell = ahead1[0];
                    maxPro = Math.max(sell, notSell);
                }
                curr[canBuy] = maxPro;
            }

            ahead2 = ahead1.clone();
            ahead1 = curr.clone();
        }

        // After selling a stock, we're not allowed to buy for next immediate day
        return ahead1[1];
    }


    public int maxProfitTabulate(int[] prices) {
        int N = prices.length;
        int arr[] = prices;
        int dp[][] = new int[N + 2][2];

        for (int i = N; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (i >= N) {
                    dp[i][canBuy] = 0;
                    continue;
                }

                int maxPro;
                if (canBuy == 1) {
                    int buy = -arr[i] + dp[i + 1][0];
                    int notBuy = dp[i + 1][1];
                    maxPro = Math.max(buy, notBuy);
                } else {
                    int sell = arr[i] + dp[i + 2][1]; // cuz of cooldown
                    int notSell = dp[i + 1][0];
                    maxPro = Math.max(sell, notSell);
                }
                dp[i][canBuy] = maxPro;
            }
        }

        // After selling a stock, we're not allowed to buy for next immediate day
        return dp[0][1];
    }


    public int maxProfit(int[] prices) {
        int N = prices.length;

        // After selling a stock, we're not allowed to buy for next immediate day
        return maxProfitHelper(0, 1, prices);
    }

    private static int maxProfitHelper(int i, int canBuy, int[] arr) {
        if (i >= arr.length) { // Cuz of jumping to day after tmrw for cooldown
            return 0;
        }

        int maxPro;
        if (canBuy == 1) {
            int buy = -arr[i] + maxProfitHelper(i + 1, 0, arr);
            int notBuy = maxProfitHelper(i + 1, 1, arr);
            maxPro = Math.max(buy, notBuy);
        } else {
            int sell = arr[i] + maxProfitHelper(i + 2, 1, arr); // cuz of cooldown
            int notSell = maxProfitHelper(i + 1, 0, arr);
            maxPro = Math.max(sell, notSell);
        }
        return maxPro;
    }
}
