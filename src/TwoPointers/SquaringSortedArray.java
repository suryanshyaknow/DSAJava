package TwoPointers;

import java.util.Arrays;

public class SquaringSortedArray {

    public static int[] makeSquares(int[] arr) {
        int n = arr.length;
        int[] squares = new int[n];
        // TODO: Write your code here
        // Largest squares would come from either the left part of the negatives or the rightmost part
        int left = 0;
        int right = arr.length - 1;
        int squaresPtr = n - 1;
        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (leftSquare > rightSquare) { // Swap
                squares[squaresPtr--] = leftSquare;
                left++;
            } else {
                squares[squaresPtr--] = rightSquare;
                right--;
            }
        }
        return squares;
    }

    public static void main(String[] args) {
        int arr[] = {-2, -1, 0, 2, 3};
        System.out.println(Arrays.toString(makeSquares(arr)));
    }

}
