package TwoPointers;

import java.util.Arrays;

public class PairWithTargetSum {

    public static int[] search(int[] arr, int targetSum) {
        // TODO: Write your code here
        int firstIdx = -1, secondIdx = -1;

        int leftPtr = 0, rightPtr = arr.length - 1;
        while (leftPtr < rightPtr) {
            int sum = arr[leftPtr] + arr[rightPtr];
            if (sum == targetSum) {
                firstIdx = leftPtr;
                secondIdx = rightPtr;
                break;
            } else if (sum <= targetSum) { // Means we need a pair with larger sum, implying gotta increment the right ptr
                leftPtr++;
            } else { // Means we need a pair with smaller sum, one of the digit's val should decrease
                rightPtr--;
            }
        }
        return new int[]{firstIdx, secondIdx};
    }

    public static int[] searchUsingBruteForce(int[] arr, int targetSum) {
        // Taking one pointer at a time,
        // and finding the other via binary search (since the array is sorted)
        for (int i = 0; i < arr.length - 1; i++) {
            int low = i + 1;
            int high = arr.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int currSum = arr[i] + arr[mid];

                if (currSum == targetSum) { // Found the pair
                    return new int[]{i, mid};
                } else if (currSum < targetSum) { // Means mid ain't sufficing, so we gotta pick a higher mid i.e. discarding the left half
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        } // O(N log N)
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
//        int arr[] = {0, 1, 2, 3, 4};
//        int targetSum = 0;

        int arr[] = {1, 2, 3, 4, 6};
        int targetSum = 6;
        System.out.println(Arrays.toString(search(arr, targetSum)));
        System.out.println(Arrays.toString(searchUsingBruteForce(arr, targetSum)));
    }

}
