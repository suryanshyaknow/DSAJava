package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class MCM {

    static int matrixMultiplication(int arr[]) {
        // code here
        // Modus Operandi to solve such problems
        // i. Start w entire array. (i, j)
        // ii. Run a loop to exercise every partition. Simple enough.
        // iii. Return the best possible two partitions.

        int N = arr.length;
        return matrixMultiplicationHelper(1, N - 1, arr);
        // Represents the min number of steps taken to multiply matrices from 1 to N-1

        // Time complexity: O(N^2) * O(N) (for the partition loop)
        // Space complexity: O(N^2) + O(N)
    }

    private static int matrixMultiplicationHelper(int i, int j, int[] arr) {
        // Base case
        if (i == j) return 0; // Matrix multiplied by itself makes no sense, thereby 0

        if (dp[i][j] != -1) return dp[i][j];

        int minSteps = Integer.MAX_VALUE;
        // Solve partitions
        for (int k = i; k <= j - 1; k++) {
            // steps = number of steps taken to multiply two partitioned matrices plus partitioned matrices within themselves
            int steps = arr[i - 1] * arr[k] * arr[j] + matrixMultiplicationHelper(i, k, arr) + matrixMultiplicationHelper(k + 1, j, arr);
            minSteps = Math.min(steps, minSteps);
        }

        return minSteps;
    }
}
