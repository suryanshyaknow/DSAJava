package DynamicProgramming.DPOnSquares;

public class CountSquareSubMatrices {

    public int countSquaresClean(int[][] matrix) {
        // The idea is pretty simple
        // Treat (i, j) as the rightmost end of a potenial square
        // dp[i][j] = 1 + minmal of its neighbors

        int count = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int dp[][] = new int[N][M];
        int[][] arr = matrix;

        // Populate 0th row and 0th col
        for (int i = 0; i < N; i++) { // 0th col
            dp[i][0] = arr[i][0];
            if (arr[i][0] == 1) count += 1;
        }
        for (int j = 0; j < M; j++) { // 0th row
            dp[0][j] = arr[0][j];
            if (arr[0][j] == 1) count += 1;
        }
        if (arr[0][0] == 1) count -= 1; // avoid double count

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == 0)
                    dp[i][j] = 0;
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
                count += dp[i][j];
            }
        }
        return count;
    }

    public int countSquares(int[][] matrix) {
        // The idea is pretty simple
        // Treat (i, j) as the rightmost end of a potenial square
        // dp[i][j] = 1 + minmal of its neighbors

        int count = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int dp[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int up = 0; // i-1, j
                if (i - 1 >= 0 && j >= 0) up = dp[i - 1][j];

                int upLeft = 0; // i-1, j-1
                if (i - 1 >= 0 && j - 1 >= 0) up = dp[i - 1][j - 1];

                int left = 0; // i, j-1
                if (i >= 0 && j - 1 >= 0) up = dp[i][j - 1];
                dp[i][j] = 1 + Math.min(up, Math.min(upLeft, left));
                count += dp[i][j];
            }
        }
        return count;
    }
}
