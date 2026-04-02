package DynamicProgramming.DPOnGrids;

import java.util.*;

public class Triangle {

    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[][] dp = new int[N][N];

        // bottom up
        for (int i = N - 1; i >= 0; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                // Edge case
                int curr = triangle.get(i).get(j);
                if (i == N - 1) {
                    dp[i][j] = curr;
                    continue;
                }
                int down = curr + dp[i + 1][j];
                int dg = curr + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, dg);
            }
        }

        return dp[0][0];

        // Time: O(~N^2)
        // Space: O(N^2)
    }

    public int minimumTotalMemo(List<List<Integer>> triangle) {
        int N = triangle.size();

        int[][] dp = new int[N][N];
        for (int i=0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        return minPathSumMemo(0, 0, triangle, dp);

        // Time: O(~N^2)
        // Space: O(N) + O(N^2)
    }

    private int minPathSumMemo(int i, int j, List<List<Integer>> triangle, int[][] dp) {
        int N = triangle.size();

        // Base case: Is when we reach at any idx of the last row
        if (i == N - 1)
            return triangle.get(i).get(j);

        if (dp[i][j] != -1) return dp[i][j];

        // Now at each idx we could either go down or diagonally
        int down = triangle.get(i).get(j) + minPathSumMemo(i + 1, j, triangle, dp);
        int dg = triangle.get(i).get(j) + minPathSumMemo(i + 1, j + 1, triangle, dp);

        return dp[i][j] = Math.min(down, dg);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();

        return minPathSum(0, 0, triangle);

        // Time: 2^(1 + 2 + 3 + ... + N-1)
        // Space complexity: O(N) here we only N number of steps
    }

    private int minPathSum(int i, int j, List<List<Integer>> triangle) {
        int N = triangle.size();

        // Base case: Is when we reach at any idx of the last row
        if (i == N - 1)
            return triangle.get(i).get(j);

        // Now at each idx we could either go down or diagonally
        int down = triangle.get(i).get(j) + minPathSum(i + 1, j, triangle);
        int dg = triangle.get(i).get(j) + minPathSum(i + 1, j + 1, triangle);

        return Math.min(down, dg);
    }
}
