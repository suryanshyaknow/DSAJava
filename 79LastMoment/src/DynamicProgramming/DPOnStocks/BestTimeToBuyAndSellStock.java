package DynamicProgramming.DPOnStocks;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int N = prices.length;

        // Intuition: For every day -- assuming I sell the stock today
        // -- if I could track the day where the stock's cost was
        // minimal, I'm golden. Now, we just gotta dynamically keep
        // updating the min and maximizing the profit.
        // Two invariants: minSeenSoFar, maxProfit

        // Can only start selling from the second day,
        // so that you're left w leeway to buy a day before.
        int min = prices[0];
        int maxProfit = 0;

        for (int i=1; i < N; i++) {
            // Assuming I sell today
            int profit = prices[i] - min;
            maxProfit = Math.max(profit, maxProfit);

            min = Math.min(min, prices[i]); // potential day where stock val is min
        }

        return maxProfit;
    }
}
