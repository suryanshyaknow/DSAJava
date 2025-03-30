package Array.Medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;

        int left = 0;
        int right = N - 1;
        int top = 0;
        int bottom = M - 1;
        List<Integer> res = new ArrayList<>();

        while (left <= right && top <= bottom) { // Loop runs till when we have at least one row and one column left

            // left -> right, top++
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // top -> bottom, right--
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // right -> left, bottom--
            // We gotta ensure there's atleast one row left to traverse
            // If we had a single row left in the matrix, then after moving left → right (first step), top and bottom would be equal.
            // Once we increment top++, top might surpass bottom, which means we would be re-traversing an already added row when moving right → left.
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // bottom -> top, left++
            // We gotta ensure there's atleast one column left to traverse upwards, and hence left <= right
            // If we had a single column left, then after moving top → bottom (second step), left and right would be equal.
            // And once we decrement right--, left might surpass right, which means we would be re-traversing an already added column when moving bottom → top.
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }

}
