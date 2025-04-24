package Array.Medium;

public class SearchIn2DArray {

    public boolean searchMatrixOptimal(int[][] matrix, int target) {
        if (matrix == null)
            return false;

        int M = matrix.length;
        int N = matrix[0].length;


        // The idea is to assume the matrix to be a 1D array and
        // and apply BS accordingly.
        // Leading to a time complexity of O(log(M * N))
        int low = 0;
        int high = N * M - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int rowIdx = mid / N;
            int colIdx = mid % N;
            if (matrix[rowIdx][colIdx] == target) return true;
            else if (matrix[rowIdx][colIdx] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    public boolean searchMatrixBetter(int[][] matrix, int target) {
        if (matrix == null)
            return false;

        int M = matrix.length;
        int N = matrix[0].length;

        // Step i. Iterate thru each row and check if the target lies
        // within th extremes
        // Step ii. Would be to perform BS if it does lie.

        for (int i = 0; i < M; i++) {
            if (matrix[i][0] <= target && target <= matrix[i][N - 1]) {
                return binarySearch(matrix[i], target);
            }
        } // O(M) + O(log N)
        return false;
    }

    private static boolean binarySearch(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return true;
            else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

}
