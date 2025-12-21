package BinarySearch;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int N = piles.length;

        // We could see that minimum time could be taken
        // if the speed is max number of bananas in piles
        // per hour. But we need the minimum speed to make
        // koko eat all of 'em in h hrs.
        // So we could workout the range from 1 to maximum
        // ele in piles and apply BS.

        // Figure out the max ele to determine the range sp that BS could be applied
        int maxEle = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxEle = Math.max(maxEle, piles[i]);
        }

        int low = 1;
        int high = maxEle;
        int res = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2; // Compute the hrs taken for this speed

            long hrsTaken = computeHours(piles, mid);

            if (hrsTaken <= (long) h) {
                res = Math.min(res, mid); // Potential ans
                high = mid - 1; // taking less hrs, so reduce the speed
            } else
                low = mid + 1;
        }

        return res;
        // Time complexity: O(N log maxEle)
    }

    private long computeHours(int[] piles, int mid) {
        int N = piles.length;
        long hrsTaken = 0;
        for (int i = 0; i < N; i++) {
            hrsTaken += Math.ceilDiv(piles[i], mid);
        }
        return hrsTaken;
    }
}
