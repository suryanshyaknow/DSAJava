package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class MCMDP {

    static int matrixMultiplicationTabulate(int arr[]) {
        int N = arr.length;
        int dp[][] = new int[N][N]; // dp[i][j] is min cost to multiply matrices from idx i to j

        // Base case population, i == j, dp[i][j] = 0
//        for (int i = 0; i < N; i++) dp[i][i] = 0;


        // Since in recurrence, i started off from 1 to N-1
        // quite obvious, in tabulation, it's gonna be opposite
        // Now the same goes for j: N-1 -> 0 ~ 0 to N-1
        // but we gotta be lil practical since i is always left of j

        for (int i = N - 1; i >= 1; i--) {
            for (int j = i; j <= N - 1; j++) {
                // Now this is a shrunk array block to be processed via partition
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }

                int minSteps = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
                    minSteps = Math.min(minSteps, steps);
                }
                dp[i][j] = minSteps;
            }
        }

        return dp[1][N - 1];
    }

    static int matrixMultiplicationMemoize(int arr[]) {
        // code here
        // Modus Operandi to solve such problems
        // i. Start w entire array. (i, j)
        // ii. Run a loop to exercise every partition. Simple enough.
        // iii. Return the best possible two partitions.

        int N = arr.length;
        // i -> 1 to N-1 and j -> 1 to N-1
        int dp[][] = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return matrixMultiplicationHelper(1, N - 1, arr, dp);
        // Represents the min number of steps taken to multiply matrices from 1 to N-1
    }

    private static int matrixMultiplicationHelper(int i, int j, int[] arr, int[][] dp) {
        // Base case
        if (i == j) return 0; // Matrix multiplied by itself makes no sense, thereby 0

        if (dp[i][j] != -1) return dp[i][j];

        int minSteps = Integer.MAX_VALUE;
        // Solve partitions
        for (int k = i; k <= j - 1; k++) {
            // steps = number of steps taken to multiply two partitioned matrices plus partitioned matrices within themselves
            int steps = arr[i - 1] * arr[k] * arr[j] + matrixMultiplicationHelper(i, k, arr, dp) + matrixMultiplicationHelper(k + 1, j, arr, dp);
            minSteps = Math.min(steps, minSteps);
        }

        return dp[i][j] = minSteps;
    }
}
