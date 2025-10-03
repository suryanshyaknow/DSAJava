package DynamicProgramming.DP2D;

import java.util.Arrays;

public class NinjaTrainingDP {

    public int maximumPointsOptimal(int arr[][]) {
        int N = arr.length;
        int[] prev = new int[4]; // Where 4 states the choices of tasks a day have

        // For the 0th day
        prev[0] = Integer.max(arr[0][1], arr[0][2]);
        prev[1] = Integer.max(arr[0][0], arr[0][2]);
        prev[2] = Integer.max(arr[0][0], arr[0][1]);
        prev[3] = Integer.max(arr[0][0], Integer.max(arr[0][1], arr[0][2]));

        // For other days
        for (int day = 1; day < N; day++) {
            int[] temp = new int[4];
            for (int last = 0; last <= 3; last++) {
                temp[last] = 0;
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        temp[last] = Integer.max(temp[last], arr[day][task] + prev[task]);
                    }
                }
            }
            prev = temp;
        }
        return prev[3];

        // Time complexity: O(N * 4 * 3)
        // Space complexity: O(N*4)
    }

    public int maximumPointsViaTabulation(int arr[][]) {
        int N = arr.length;
        int[][] dp = new int[N][4]; // Where 4 states the choices of tasks a day have
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        // For the 0th day
        for (int i = 0; i <= 3; i++) { // i being the last task done
            int maxi = 0;
            for (int j = 0; j < 3; j++) { // j = potential task today
                if (j != i) maxi = Integer.max(maxi, arr[0][j]);
            }
            dp[0][i] = maxi;
        }

        // For other days
        for (int day = 1; day < N; day++) {
            for (int last = 0; last <= 3; last++) {
                int maxi = 0;
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        int curr = arr[day][task] + dp[day - 1][task];
                        maxi = Integer.max(curr, maxi);
                    }
                    dp[day][last] = maxi;
                }
            }
        }
        return dp[N - 1][3];

        // Time complexity: O(N * 4 * 3)
        // Space complexity: O(N*4)
    }

    public int maximumPointsViaMemoization(int arr[][]) {
        int N = arr.length;
        int dp[][] = new int[N][4]; // Where 4 states the choices of tasks a day have
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        return maximumPointsHelper(N - 1, 3, arr, dp);

        // Time complexity: O(N * 4 * 3) = O(12N)
        // Number of states = N * 4 (because day ranges from 0..N-1 and last ranges from 0..3).
        // For each state, you try up to 3 tasks.

        // Space complexity: O(N) + O(N * 4)
    }

    private int maximumPointsHelper(int d, int last, int[][] arr, int[][] dp) {
        // Base case
        if (d == 0) {
            // Choose the max merit task
            int maxi = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    maxi = Integer.max(maxi, arr[0][i]);
                }
            }
            return maxi;
        }

        if (dp[d][last] != -1) return dp[d][last];

        // For other days
        int maxi = 0;
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                int curr = arr[d][i] + maximumPointsHelper(d - 1, i, arr, dp);
                maxi = Integer.max(maxi, curr);
            }
        }
        return dp[d][last] = maxi;
    }


}
