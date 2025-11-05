package DynamicProgramming.DPOnStocks;

public class BuySellStockII {

    public int maxProfit(int[] prices) {
        return maxProfitHelper(0, true, prices); // Since it's first day, you've got liberty to buy

        // Time complexity: 2^N
        // Space complexity: O(N)

    }

    private int maxProfitHelper(int i, boolean canBuy, int[] arr) {
        // Base case
        if (i == arr.length)
            return 0;

        int profit = 0;
        // If you could buy
        if (canBuy) {
            int buy = -arr[i] + maxProfitHelper(i + 1, false, arr); // decide to buy, you won't be able to biuy any further till you sell it
            int notBuy = 0 + maxProfitHelper(i + 1, true, arr);
            profit = Math.max(profit, Math.max(buy, notBuy));
        } else {
            // If you couldn't, you sell
            int sell = arr[i] + maxProfitHelper(i + 1, true, arr); // Since you've sold, you'll eligible to rebuy
            int notSell = maxProfitHelper(i + 1, false, arr);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return profit;
    }
}
