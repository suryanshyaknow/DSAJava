package BinarySearch;

public class MinimizeMaxDistanceBetweenTwoStations {

    public double minMaxDistOptimalBS(int[] stations, int K) {
        // The question asks where do you place the stations to minimize the max distance.
        // While applying the BS on answers, rather ask if I fix the max allowed gap,
        // how many stations would I fkn need?
        // If 'd' is too small -> you'll need too many stations to maintain that dist across
        // many stations.

        // We want: diff / (stations + 1) ≤ D; Dist between sections after placing gates
        // Rearrange:
        // stations + 1 ≥ diff / D
        // stations ≥ (diff / D) - 1
        // Hence, stations needed = ceil(diff/D) - 1

        int N = stations.length;
        // Figure out the max distance between stations to decide the BS range
        double low = 0.0;
        double high = 0.0;
        for (int i = 0; i <= N - 2; i++) {
            high = Math.max(high, stations[i + 1] - stations[i]);
        }

        double eps = 1e-6;
        while (high - low > eps) {
            double mid = (high + low) / 2;
            int stationsCnt = countStationsForGivenMaxDist(stations, mid);

            if (stationsCnt > K) // increase the distance
                low = mid;
            else { // Potential ans, try lower distance
                high = mid;
            }
        }

        return high;
    }

    private int countStationsForGivenMaxDist(int[] stations, double mid) {
        int k = 0;

        for (int i = 0; i < stations.length - 1; i++) {
            double diff = stations[i + 1] - stations[i];
            k += (int) Math.ceil(diff / mid) - 1;
        }

        return k;
    }

    public double minMaxDistBruteForce(int[] stations, int K) {
        // code here
        // Intuition: We'll iterate over each gate, and try
        // to place them one at a time between the maximum
        // distance stations.
        // And keep track of the idx where the gate has been
        // placed.

        int N = stations.length;
        if (N == 1) return 0;
        double[] howMany = new double[N - 1]; // To keep track of the stations being placed

        for (int k = 1; k <= K; k++) {
            double maxDistance = -1;
            int maxDistanceIdx = -1;

            for (int i = 0; i <= N - 2; i++) {
                // Here we're iterating over the sections bw two stations
                double dist = stations[i + 1] - stations[i];
                double sectionLen = dist / (howMany[i] + 1);

                if (sectionLen > maxDistance) {
                    maxDistance = sectionLen;
                    maxDistanceIdx = i;
                }
            }
            howMany[maxDistanceIdx]++;
        }

        // now figure out the max distance between two stations
        // upon placing all the gates
        double res = -1;
        for (int i = 0; i <= N - 2; i++) {
            double dist = stations[i + 1] - stations[i];
            double sectionLen = dist / (howMany[i] + 1);

            res = Math.max(sectionLen, res);
        }

        return res;

        // Time complexity: O(K * N) + O(N)
        // Space complexity: O(N - 1)
    }
}

