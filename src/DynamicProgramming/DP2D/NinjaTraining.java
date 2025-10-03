package DynamicProgramming.DP2D;

public class NinjaTraining {

    public int maximumPoints(int arr[][]) {
        // code here
        // Step i. See, first off, where we wanna pick the max outta something, greedy approach hits.
        // But here it won't work, because if we pick a certain max being greedy on one day,
        // the next day's max could outweigh it, and we won't be able to pick that up.

        // Step ii. Now, since greedy won't work. We gotta try all ways.
        // And when it comes to trying all ways -> Recursion.

        // Step iii. Now think about it, we're not allowed to redo a training done on prior day.
        // So we need something to tell us that. So a param to pass thru all the recursion calls.

        // Task performed: 0 (task 0), 1 (task 1), 2 (task 2), 3 (None)

        int days = arr.length;
        return maximumPointsHelper(days - 1, 3, arr);

        // Time complexity: O(3^N)
    }

    private int maximumPointsHelper(int idx, int last, int[][] arr) {
        // Base case
        if (idx == 0) {
            int max = 0;
            // Iterate over all tasks
            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    // Find the max
                    max = Integer.max(max, arr[0][i]);
                }
            }
            return max;
        }

        // For any other day leverage recursion
        // Iterate over all tasks to find the best
        int maxPoints = 0;
        for (int i = 0; i <= 2; i++) {
            if (i != last) {
                int points = arr[idx][i] + maximumPointsHelper(idx - 1, i, arr);
                maxPoints = Integer.max(maxPoints, points);
            }
        }
        return maxPoints;
    }

}
