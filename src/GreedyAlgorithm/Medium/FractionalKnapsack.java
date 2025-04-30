package GreedyAlgorithm.Medium;

import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsack {

    static class Item {
        int val, weight;
        double ratio;

        Item(int val, int weight) {
            this.val = val;
            this.weight = weight;
            this.ratio = (double) val / weight;
        }

    }

    double fractionalKnapsack(int[] values, int[] weights, int W) {
        // code here
        int N = values.length;
        List<Item> items = new ArrayList<>();

        // Creating a list so that it could be sorted based on the greed
        for (int i = 0; i < N; i++) { // O(N)
            items.add(new Item(values[i], weights[i]));
        }

        // The idea is to pick those items whose unit weight is maximum
        // We'll pick those items who give us MAX VAL PER WEIGHT not just the
        // maximum value regardless of the weight
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio)); // O(N Log N)

        double totVal = 0;
        for (int i = 0; i < N; i++) { // O (N)
            int val = items.get(i).val;
            int weight = items.get(i).weight;
            double ratio = items.get(i).ratio;
            if (weight <= W) {
                totVal += val;
                W -= weight;
            } else {
                // Then pick the fraction of the available as much as we require
                double remWtVal = ratio * W;
                totVal += remWtVal;
                break;
            }
        }
        return Math.round(totVal * 1e6) / 1e6; // 1e6 is just scientific notation for 1000000
//        return totVal;
    }

}
