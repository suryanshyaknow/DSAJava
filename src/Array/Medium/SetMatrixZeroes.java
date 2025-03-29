package Array.Medium;

public class SetMatrixZeroes {

    public static void setZeroesBest(int[][] matrix) {
        // The best we could do is to eliminate the extra space we're using
        // And use the first row and first column as trackers to mark a rwo and a col zero.
        int M = matrix.length;
        int N = matrix[0].length;

//        int[] rowTracker = matrix[..][0];
//        int[] colTracker = matrix[0][..];
        int col0 = 1;
        // Step 1: Iterate through the matrix and use the first row and first col as trackers and mark 'em zero.
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    // Mark the ith row
                    matrix[i][0] = 0;
                    // Mark the jth column
                    if (j == 0)
                        col0 = 0;
                    else
                        matrix[0][j] = 0;
                }
            }
        }

        // Now, leave the first row and the first column,
        // and mark the rest of 'em as zeroes based on trackers
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != 0 && (matrix[i][0] == 0 || matrix[0][j] == 0))
                    matrix[i][j] = 0;
            }
        }
        // Now process the first row
        if (matrix[0][0] == 0) {
            for (int j = 0; j < N; j++) {
                matrix[0][j] = 0;
            }
        }
        // Now process the first column
        if (col0 == 0) {
            for (int i = 0; i < M; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void setZeroesBetter(int[][] matrix) {
        // So we kinda need to eliminate the extra steps being taken to mark
        // a whole row and column as -1

        // What we could rather do is to iterate and keep track of rows and columns that're to be marked zero.
        int M = matrix.length;
        int N = matrix[0].length;

        int[] colTracker = new int[N];
        int[] rowTracker = new int[M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    colTracker[j] = 1;
                    rowTracker[i] = 1;
                }
            }
        }
        // Final pass to mark all the elements 0 whose row or col is marked
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (rowTracker[i] == 1 || colTracker[j] == 1)
                    matrix[i][j] = 0;
            }
        }
        // O(2 * M * N)
        // O(M + N)
    }


    public static void setZeroesBruteForce(int[][] matrix) {
        // First off, iterate through the matrix
        // On encountering a zero, mark its respective row and columns as -1
        // One final pass, marking all -1s as zeroes

        int m = matrix.length; // number of rows
        int n = matrix[0].length; // number of columns

        for (int i = 0; i < m; i++) { // Iterating through rows first
            for (int j = 0; j < n; j++) { // Iterating through cols within that row
                if (matrix[i][j] == 0) {
                    markRowNegative(matrix, i);
                    markColumnNegative(matrix, j);
                }
            }
        } // O(m * n) * (m + n)

        // One final pass
        for (int i = 0; i < m; i++) { // Iterating through rows first
            for (int j = 0; j < n; j++) { // Iterating through cols within that row
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        } // O(m * n)

    }

    private static void markColumnNegative(int[][] matrix, int n) {
        // To mark column negative, we gotta downwards row-wise, so column idx remains intact
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][n] != 0)
                matrix[i][n] = -1;
        }
    }

    private static void markRowNegative(int[][] matrix, int m) {
        // To mark row negative, we gotta move forward column-wise
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[m][i] != 0)
                matrix[m][i] = -1;
        }
    }

    public static void main(String[] args) {

    }

}
