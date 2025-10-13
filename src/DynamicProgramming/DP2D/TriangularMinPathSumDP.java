package DynamicProgramming.DP2D;

import java.util.Arrays;
import java.util.List;

public class TriangularMinPathSumDP {

    public int minimumTotalViaTabulation(List<List<Integer>> triangle) {
        int N = triangle.size();
        int dp[][] = new int[N][N];

        // Populate the base case, i.e., the last row
        // since we started opposite this time
        for (int j = 0; j < N; j++) {
            dp[N - 1][j] = triangle.get(N-1).get(j);
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int dg = triangle.get(i).get(j) + dp[i + 1][j + 1];

                dp[i][j] = Integer.min(down, dg);
            }
        }

        return dp[0][0];
    }

    public int minimumTotalViaMemoization(List<List<Integer>> triangle) {
        // Step i. Express the problem in terms of indices.
        // Step ii. Do what question states in terms of indices. Base case and all.
        // Step iii. Pick the min path sum ;)
        int N = triangle.size();
        int dp[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        return minPathSum(0, 0, triangle, dp);
    }

    private static int minPathSum(int i, int j, List<List<Integer>> arr, int[][] dp) {
        // Base case
        int N = arr.size();
        if (i == N - 1)
            return arr.get(i).get(j);

        if (dp[i][j] != -1) return dp[i][j];

        // Since we could only move down and diagonally, we'll be in bounds that's a given.
        // Triangular structure guarantees that!
        int down = arr.get(i).get(j) + minPathSum(i + 1, j, arr, dp);
        int dg = arr.get(i).get(j) + minPathSum(i + 1, j + 1, arr, dp);
        return dp[i][j] = Integer.min(down, dg);
    }
}
