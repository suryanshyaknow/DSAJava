package BinarySearch;

import java.util.*;

public class AggressiveCows {

    public int aggressiveCows(int[] stalls, int k) {
        // code here

        // The task is to assign stalls to k cows such that minimum distance bw
        // any two of them is the maximum possible.

        // To ensure that k cows be placed and the minimum distance between any two
        // is max possible, we could try each distance one by one from 1, and try
        // to keep at least that much of the distance between two cows. And see if
        // it works out for a given number of cows.

        // Now, instead of trying out all distances, we could simply try out a range
        // and apply BS.

        int N = stalls.length;
        // Sort the given array for greedy assignment of cows
        Arrays.sort(stalls);
        int low = 1;
        int high = stalls[N - 1] - stalls[0]; // the max possible distance between two cows

        int res = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Potential min-maxed distance between two cows

            if (canWePlaceCows(stalls, k, mid)) {
                // If mid is possible, try a bigger distance
                res = Math.max(res, mid);
                low = mid + 1;
            } else {
                // If mid ain't possible, try a smaller distance
                high = mid - 1;
            }
        }
        return res;
    }

    private boolean canWePlaceCows(int[] stalls, int k, int potentialDist) {
        int N = stalls.length;
        // Assign the first cow, optimal greedy choice
        int cntCows = 1;
        int lastPlaced = 0; // stall idx the last cow was placed at

        for (int i = 1; i < N; i++) {
            if (stalls[i] - stalls[lastPlaced] >= potentialDist) {
                lastPlaced = i;
                cntCows++;
            }
            if (cntCows == k) return true;
        }

        return false;
    }

}
