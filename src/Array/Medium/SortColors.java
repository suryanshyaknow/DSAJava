package Array.Medium;

import java.util.Arrays;

public class SortColors {

    public static void sortColors(int[] arr) {
        int N = arr.length;
        int leftPtr = 0;
        int rightPtr = N - 1;
        int temp = 0;

        // If it's two swap it with the last and decrement the rightPtr
        // And if it's 0 swap it with the first and increment the leftPtr
        while (temp <= rightPtr) { // Because rightPtr is the pos between a correctly placed two and could either be 0, 1, or 2
            if (arr[temp] == 0) {
                swap(arr, temp, leftPtr);
                leftPtr++;
                temp++;
                // In any case, 2 won't be there between leftPtr and temp because temp woulda already taken care of it
            } else if (arr[temp] == 1)
                temp++;
            else {
                swap(arr, temp, rightPtr);
                rightPtr--;
                // Can't move temp because even now it could either be zero or one.
            }
        }

        // See, all the buckets before leftPtr are essentially zeroes, that's its purpose to being with.
        // Now, closely notice that leftPtr shall always be pointing to a 0 or 1 and never 2 because by the time
        // we're pass it, two woulda already been swapped and would at be its apt pos. Makes sense?
        // Because the tempPtr always stay ahead of leftPtr and for it to be ahead of the leftPtr it surely woulda put
        // all the twos to their places.
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int[] arr, int temp, int rightPtr) {
        int tempEle = arr[temp];
        arr[temp] = arr[rightPtr];
        arr[rightPtr] = tempEle;
    }


    public static void sortColorsUsingBucketSort(int[] arr) {
        int N = arr.length;
        int zeroCounter = 0;
        int oneCounter = 0;
        int twoCounter = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i] == 0)
                zeroCounter++;
            else if (arr[i] == 1)
                oneCounter++;
            else
                twoCounter++;
        }
        int tempPtr = 0;
        while (zeroCounter > 0) {
            arr[tempPtr++] = 0;
            zeroCounter--;
            System.out.println(tempPtr);
        }
        while (oneCounter > 0) {
            arr[tempPtr++] = 1;
            oneCounter--;
            System.out.println(tempPtr);
        }
        while (twoCounter > 0 && tempPtr <= N - 1) {
            arr[tempPtr++] = 2;
            twoCounter--;
            System.out.println(tempPtr);
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{2, 0, 1};
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        sortColorsUsingBucketSort(arr);
    }

}
