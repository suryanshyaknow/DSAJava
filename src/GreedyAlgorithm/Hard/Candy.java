package GreedyAlgorithm.Hard;

public class Candy {

    public int candyOptimal(int[] ratings) {
        int N = ratings.length;

        // We'll use the concept of the slope.
        // Till the slope is increasing our distribution of candies will increase.
        // And till it's decreasing, similarly the distribution will decrease.
        // But.. But if we do that i.e. decrease the distribution, it may even go into negative.
        // So what we gotta do is even treat the decreasing slope as increasing and restart the distribution, there'd be no diff in distribution.
        // Gotta remember the peak and bottom of the slopes and adjust the distribution if bottom happens to be greater than the peak;

        int sum = 1; // Regardless of whether the slope is increasing or not
        int i = 1;
        while (i < N) {
            // If the slope doesn't change
            if (ratings[i] == ratings[i - 1]) {
                sum += 1;
                i++;
                continue;
            }
            int peak = 1;
            // Increasing slope
            while (i < N && ratings[i] > ratings[i - 1]) {
                peak += 1;
                sum += peak;
                i++;
            }
            // Decreasing slope
            int bottom = 1;
            while (i < N && ratings[i] < ratings[i - 1]) {
                sum += bottom;
                i++;
                bottom += 1;
            }

            // Now if bottom happens to be greater than the peak, gotta adjust the dist
            if (bottom > peak)
                sum += bottom - peak;

        }
        return sum;
    }


    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] left = new int[N]; // Distribution considering the left neighbours
        left[0] = 1; // Since the first ele ain't got any left neighbour

        for (int i=1; i < N; i++) {
            if (ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            } else { // Or I'll be greedy
                left[i] = 1;
            }
        }

        // Second pass to compute the distribution considering the right neighbours
        left[N-1] = Integer.max(1, left[N - 1]);
        int count = left[N-1];
        for (int i=N-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1]) {
                left[i] = Integer.max(left[i+1] + 1, left[i]);
            } else {
                left[i] = Integer.max(1, left[i]);
            }
            count += left[i];
        }
        return count;
    }

}
