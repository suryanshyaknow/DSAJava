package Array.Easy;

import java.util.Arrays;

public class SecondLargest {

    public static int getSecondLargestOptimal(int[] arr) {
        int largest = -1;
        int sLargest = -1;

        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] > largest) {
                sLargest = largest;
                largest = arr[i];
            } else if (arr[i] > sLargest && arr[i] < largest) {
                sLargest = arr[i];
            }

        }
        return sLargest;
    }

    public static int getSecondSmallestOptimal(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int sSmallest = -1;

        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] < smallest) {
                sSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < sSmallest && arr[i] > smallest) {
                sSmallest = arr[i];
            }
        }
        return sSmallest;
    }

    public int getSecondLargest(int[] arr) {
        // Code Here
        // Determine the largest in the first traversal: O(N)
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] > largest)
                largest = arr[i];
        }

        // Determine the second largest in the second traversal: O(N)
        int sLargest = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] > sLargest && arr[i] != largest)
                sLargest = arr[i];
        }
        return sLargest;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, 35, 1, 10, 34, 1};
        System.out.println(getSecondLargestOptimal(arr));
//        System.out.println(getSecondSmallestOptimal(arr));
    }


}
