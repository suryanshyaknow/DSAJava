package SlidingWindow;

public class BeestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int N = prices.length;

        // For every possible selling day, buy at the
        // cheapest price seen before that day.
        // Track the best profit found so far.

        // Now, if I could track the min price seen so far
        // then at any give day I could compute the profit
        // and I just have to dynamically keep updating it to
        // get the max.

        int minPriceSeen = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < N; i++) {
            int profit = prices[i] - minPriceSeen;

            maxProfit = Math.max(profit, maxProfit);

            // Update the min seen so far
            minPriceSeen = Math.min(minPriceSeen, prices[i]);
        }

        return maxProfit;
    }

}
