package TwoPointers;

import java.util.Arrays;

public class DutchNationalFlagProblem {

    public static int[] sort(int[] arr) {
        // TODO: Write your code here
        /*

         */
        int low = 0, high = arr.length - 1;
        int ptr = 0;
        while (ptr <= high) {
            if (arr[ptr] == 0) {
                // Swap with the low ele and increment the low
                swap(arr, ptr, low);
                low++;
                ptr++; // Because this currentEle (after swap) will get handled (even if it's 2) in future while getting swapped with 0
                // Nope, couldn't be it. Because ptr would've already iterated over it, and it will already be at the end of the array.
            } else if (arr[ptr] == 2) {
                // Swap with the high ele and decrement the low
                swap(arr, ptr, high);
                high--;
            } else {
                ptr++;
            }
        }

//        for (int ptr = 0; ptr <= high; ptr++) {
//            // If the ele pointed by the pointer is 0, swap with the low, and increment the low
////            System.out.println("Iteration " + ptr);
//            int currentEle = arr[ptr];
//            if (currentEle == 0) {
//                // Swap the currentEle with the ele present at low
//                arr[ptr] = arr[low];
//                arr[low] = currentEle;
//                low++;
////                System.out.println("Swapping w low: " + Arrays.toString(arr) + "\n");
//            } else if (currentEle == 2) {
//                // Swap the currentEle w the ele present at high
//                arr[ptr] = arr[high];
//                arr[high] = currentEle;
//                high--;
////                System.out.println("Swapping w high: " + Arrays.toString(arr) + "\n");
//            }
//        }
        return arr;
    }

    private static void swap(int[] arr, int temp, int rightPtr) {
        int tempEle = arr[temp];
        arr[temp] = arr[rightPtr];
        arr[rightPtr] = tempEle;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 0};
        System.out.println(Arrays.toString(sort(arr)));

        int arr1[] = {2, 2, 0, 1, 2, 0};
        System.out.println(Arrays.toString(sort(arr1)));

        int arr3[] = {2, 1, 0, 1, 2, 0, 0};
        System.out.println(Arrays.toString(sort(arr3)));

        int arr2[] = {0, 0, 0, 0, 0};
//        System.out.println(Arrays.toString(sort(arr2)));
    }

}
