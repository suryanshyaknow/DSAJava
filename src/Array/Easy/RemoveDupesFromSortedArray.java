package Array.Easy;

import java.util.Arrays;

public class RemoveDupesFromSortedArray {

    public static int removeDuplicates(int[] arr) {
        // Maintain a pointer to keep track of unique elements
        int k = 0;
        int temp = 0;

        while (temp <= arr.length - 1) {
            System.out.println("Iteration: " + temp);
            if (arr[temp] != arr[k]) {
                arr[++k] = arr[temp];
                System.out.println(Arrays.toString(arr));
            }
            temp++;
        }
        return k + 1; // Since k is zero-based
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] arr = new int[]{1, 1, 2};
        removeDuplicates(arr);
    }

}
