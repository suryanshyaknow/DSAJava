package Array.Medium;

public class RotateImage {

    public static void rotateUsingOptimal(int[][] matrix) {
        int N = matrix[0].length;

        // First off, transpose the Matrix
        // And to do that we've just gotta process the first half excluding the diagonal
        for (int i = 0; i <= N - 2; i++) { // Go till the second last row cuz the last one would just be the diagonal ele
            for (int j = i + 1; j < N; j++) {
                swap(matrix, i, j);
            }
        } // O(N^2/ 2) since only halve the matrix is being traversed!

        // OR
        /*
        // Step 1: Complement the matrix
        for (int i=0; i < N; i++) {
            for (int j=0; j < i; j++) {
                swap(matrix, i, j);
            }
        }
        */

        // Reverse the rows
        for (int i = 0; i < N; i++) {
            reverseArr(matrix[i]);
        } // O(N * N/2) => O(N^2/2)

        // Overall: O(N^2)
    }

    private static void reverseArr(int[] arr) {
        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr < rightPtr) {
            swapArrElements(arr, leftPtr, rightPtr);
            leftPtr++;
            rightPtr--;
        }
    }

    private static void swapArrElements(int[] arr, int leftPtr, int rightPtr) {
        int temp = arr[rightPtr];
        arr[rightPtr] = arr[leftPtr];
        arr[leftPtr] = temp;
    }

    private static void swap(int[][] matrix, int i, int j) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    public static void rotateUsingBruteForce(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int res[][] = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                res[j][M - i - 1] = matrix[i][j];
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        int arr[][] = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotateUsingBruteForce(arr);
    }


}
