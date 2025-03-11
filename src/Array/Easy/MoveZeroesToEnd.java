package Array.Easy;

import java.util.Arrays;

public class MoveZeroesToEnd {

    public static void moveZeroes(int[] arr) {
        // Shift the elements as soon as a zero is encountered
        // First off, point a pointer towards the first zero
        int ptr = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                ptr = i;
                break;
            }
        }
        if (ptr == -1) return;

        int i = ptr + 1;
        while (i <= arr.length - 1) {
            System.out.println("i: " + i);
            System.out.println("ptr: " + ptr);
            System.out.println(Arrays.toString(arr) + "\n");
            if (arr[i] != 0) {
                swap(arr, ptr, i);
                ptr++; // Always point to zero
                // Either there are consecutive zeroes that make ptr still points to zero even post increment
                // or if even there are not the next digit is automatically gonna be a zero after swap.
            }
            // Else stacking up zeroes
            i++;
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{0, 1, 0, 3, 12};
        moveZeroes(arr);
    }

    private static void swap(int[] arr, int firstPtr, int lastPtr) {
        int temp = arr[firstPtr];
        arr[firstPtr] = arr[lastPtr];
        arr[lastPtr] = temp;
    }

}
