package String;

import java.util.*;

public class DiagonalTraverse {

    // Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

    public static int[] findDiagonalOrderII(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];

        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int row = 0, col = 0;
        int counter = 0; // Tracks the num of elements inserted into res;
        boolean isMovingUpwards = true; // 1 represents down, -1 up

        while (counter < m * n) {
            res[counter++] = mat[row][col];

            if (isMovingUpwards) { // Move upwards
                if (col == n - 1) {
                    // Reached the last column, move downwards and shift row
                    isMovingUpwards = false;
                    row++;
                } else if (row == 0) {
                    // Reached the first row, can't move up, move right, and change direction
                    col += 1;
                    isMovingUpwards = false;
                } else {
                    // Moving diagonally from extreme left to extreme right
                    row--;
                    col++;
                }
            } else { // Move downwards
                if (row == m - 1) {
                    // Reached the last row, change directions and move upwards
                    col += 1;
                    isMovingUpwards = true;
                } else if (col == 0) { // First column: move down
                    row++;
                    isMovingUpwards = true;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return res;
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];
        // First off, maintain a hashmap where keys are sum of row and column indices,
        // Cuz the elements having the same mentioned sum lie on the same diagonal.
        // Process the diagonal groups, and reverse the order if diagonal idx is even.

        int cols = mat[0].length;
        int rows = mat.length;

        // Traverse the matrix and associate diagonal elements
        Map<Integer, List<Integer>> diagonals = new HashMap<>();
        for (int r = 0; r < rows; r++) {
            for (int j = 0; j < cols; j++) {
                List<Integer> diagonalElements = diagonals.getOrDefault(r + j, new ArrayList<Integer>());
                diagonalElements.add(mat[r][j]);
                diagonals.put(r + j, diagonalElements);
            }
        }

        // Process the elements
        List<Integer> res = new ArrayList<>(rows * cols);
        for (int i = 0; i < rows + cols - 1; i++) {
            if (i % 2 == 0) {
                res.addAll(diagonals.get(i).reversed());
            } else {
                res.addAll(diagonals.get(i));
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String args[]) {
        int mat[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }

}
