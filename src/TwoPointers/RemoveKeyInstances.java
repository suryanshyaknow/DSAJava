package TwoPointers;

public class RemoveKeyInstances {

    /*
    Given an unsorted array of numbers and a target ‘key’, remove all instances of ‘key’ in-place and return the new length of the array.

    Input: [3, 2, 3, 6, 3, 10, 9, 3], Key=3
    Output: 4
    Explanation: The first four elements after removing every 'Key' will be [2, 6, 10, 9].
     */

    public static int moveElements(int[] arr, int key) {
        // TODO: Write your code here
        int openPosPtr = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) { // The number gets the open position, and the position gets incremented
                arr[openPosPtr] = arr[i];
                openPosPtr++;
            }
        }
        return openPosPtr;
    }

    public static void main(String[] args) {
        int[] arr = {2, 11, 2, 2, 1};
        System.out.println(moveElements(arr, 2));

        int[] arr1 = {3, 2, 3, 6, 3, 10, 9, 3};
        System.out.println(moveElements(arr1, 3));
    }

}
