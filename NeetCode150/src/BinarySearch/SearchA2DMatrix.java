package BinarySearch;

public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int N = matrix.length;
        int M = matrix[0].length;

        // Intuition: Since we're to compute this in O(log(M * N))
        // we gotta implement BS smartly.
        // One approach is to first off apply BS on rows and then
        // find the row in which target actuall lies ~ O(log N)
        // Then for the target row, apply BS again to actually
        // find the target ~ O(log M)
        // O(log M) + O(log N) ~ O(log (M * N))

        // 1. Find the target row
        int top = 0;
        int bot = N - 1;
        int targetRow = -1;
        while (top <= bot) {
            targetRow = top + (bot - top) / 2;

            if (target > matrix[targetRow][M - 1]) top = targetRow + 1;
            else if (target < matrix[targetRow][0]) bot = targetRow - 1;
            else {
                // Meaning target is in the mid row
                break;
            }
        }

        // Now if top > bottom, that means target row aint found
        if (top > bot) return false;

        int l = 0;
        int r = M - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int midEle = matrix[targetRow][mid];
            if (midEle == target) return true;
            if (target > midEle) l = mid + 1;
            else r = mid - 1;
        }

        return false;
    }

}
