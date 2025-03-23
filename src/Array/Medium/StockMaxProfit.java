package Array.Medium;

public class StockMaxProfit {

    public int maxProfit(int[] prices) {
        int[] arr = prices;
        int N = arr.length;
        int maxProfit = 0;

        for (int i = 0; i < N; i++) { // buying day
            for (int j = i + 1; j < N; j++) { // selling day
                maxProfit = Integer.max(maxProfit, arr[j] - arr[i]);
            }
        }
        return maxProfit;
    }

    public int maxProfitOptimal(int[] prices) {
        int[] arr = prices;
        int N = arr.length;
        int maxProfit = 0;
        int minPriceSoFar = arr[0]; // Makes sense!

        for (int i = 0; i < N; i++) { // Selling day
            maxProfit = Integer.max(maxProfit, arr[i] - minPriceSoFar);

            // But we gotta update the minPriceSoFar dynamically with each new buying day
//            minPriceSoFar = Integer.min(minPriceSoFar, arr[++i]); // Can't meddle with 'i'
            minPriceSoFar = Integer.min(minPriceSoFar, arr[i]); // Cuz i is already a day ahead of initial buying day
        }
        return maxProfit;
    }

}
