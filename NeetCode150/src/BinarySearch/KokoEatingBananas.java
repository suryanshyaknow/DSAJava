package BinarySearch;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int N = piles.length;

        // Intuition: We can think of it in this way.
        // If we pick the maximum of the array and assume it to be
        // the koko's speed, then the number of hours taken by koko
        // in any given scenario will be N.
        // And we take koko's speed to be the minimum of the piles
        // then it's gonna be summation of piles[i]/min(piles) for
        // every index.
        // Now since we're to find the minimum speed to let koko eat
        // all piles in h hours, we have our sample space and we can
        // simply apply BS on it.

        int max = piles[0];
        for (int i = 0; i < N; i++) {
            max = Math.max(max, piles[i]);
        }

        // Sample search set: 1 to Max(piles)
        int l = 1; // least speed koko can have to consume atleast 1 banana
        int r = max;
        int res = max; // max speed koko can have

        while (l <= r) {
            int k = (l + r) / 2;

            // Assume k speed and see it koko can consume all
            // the bananas in h hours
            long hrs = computeHours(piles, k);
            if (hrs <= h) {
                // Meaning it a potential solution
                res = Math.min(k, res);
                // we're gonna try even less speed since we're to find the minimum speed
                r = k - 1;
            } else l = k + 1; // try greater speeds
        }
        return res;

        // Time: O(log Max(piles) * N)
    }

    private static long computeHours(int[] piles, int k) {
        int N = piles.length;
        long hrs = 0;

        for (int i = 0; i < N; i++) {
            // ceil(a/b) = (a + b - 1) / b
            hrs += (piles[i] + k - 1L) / k;
        }
        return hrs;
    }
}
