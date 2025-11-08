package DynamicProgramming.DPOnStocks;

public class BuySellStocksWithTransactionFee {

    public int maxProfitOptimize(int[] prices, int fee) {
        int N = prices.length;
        int arr[] = prices;

//        int dp[][] = new int[N + 1][2];
        int ahead[] = new int[2];
        int curr[] = new int[2];

        for (int i = N - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                int maxPro;
                if (canBuy == 1) {
                    int buy = -arr[i] - fee + ahead[0];
                    int notBuy = ahead[1];
                    maxPro = Math.max(buy, notBuy);
                } else {
                    int sell = arr[i] + ahead[1]; // cuz of cooldown
                    int notSell = ahead[0];
                    maxPro = Math.max(sell, notSell);
                }
                curr[canBuy] = maxPro;
            }
            ahead = curr.clone();
        }

        return ahead[1];
    }

    public int maxProfitTabulate(int[] prices, int fee) {
        int N = prices.length;
        int arr[] = prices;

        int dp[][] = new int[N + 1][2];

        for (int i = N - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                int maxPro;
                if (canBuy == 1) {
                    int buy = -arr[i] - fee + dp[i + 1][0];
                    int notBuy = dp[i + 1][1];
                    maxPro = Math.max(buy, notBuy);
                } else {
                    int sell = arr[i] + dp[i + 1][1]; // cuz of cooldown
                    int notSell = dp[i + 1][0];
                    maxPro = Math.max(sell, notSell);
                }
                dp[i][canBuy] = maxPro;
            }
        }

        return dp[0][1];
    }
}
