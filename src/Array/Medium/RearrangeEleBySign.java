package Array.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeEleBySign {

//    You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
//
//    You should return the array of nums such that the array follows the given conditions:
//
//    Every consecutive pair of integers have opposite signs.
//    For all integers with the same sign, the order in which they were present in nums is preserved.
//    The rearranged array begins with a positive integer.
//    Return the modified array after rearranging the elements to satisfy the aforementioned conditions.

    public static int[] rearrangeArrayUsingBruteForce(int[] arr) {
        if (arr.length == 1) return arr;
        int N = arr.length;

        // We're gonna initialize two arrays each for a diff sign and populate them accordingly
        int[] posEle = new int[N / 2];
        int[] negEle = new int[N / 2];
        int negPtr = 0;
        int posPtr = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] >= 0)
                posEle[posPtr++] = arr[i];
            else
                negEle[negPtr++] = arr[i];
        }

        // Now, we're gonna repopulate the original arrays according to the given statement
        for (int i = 0; i < N / 2; i++) {
            arr[2 * i] = posEle[i];
            arr[2 * i + 1] = negEle[i];
        }

        return arr;
    }

    public static int[] rearrangeArrayUsingBruteForceWhereNegativesAndPositivesAreNotEqual(int[] arr) {
        int N = arr.length;
        int res[] = new int[N];
        // Count positive and negative numbers
        int posNums = 0;
        int negNums = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] >= 0) posNums++;
            else negNums++;
        }

        int requiredIterations = Math.min(posNums, negNums);

        // Implement two pointers to place numbers correctly till one of 'em runs out
        int posPtr = 0;
        int negPtr = 1;
        int i = 0;
        for (; i < N; i++) {
            if (arr[i] < 0 && negNums > 0 && negPtr < 2 * requiredIterations) {
                res[negPtr] = arr[i];
                negPtr += 2;
                negNums--;
            } else if (arr[i] >= 0 && posNums > 0 && posPtr < 2 * requiredIterations) {
                res[posPtr] = arr[i];
                posPtr += 2;
                posNums--;
            }

            // 🚨 Early exit if we've placed all elements that fit in the alternating structure
            if (posPtr >= 2 * requiredIterations || negPtr >= 2 * requiredIterations) {
                i++;  // Move i forward to track where we left off
                break;
            }
        }

        int remainingEleIdx = (posNums > 0) ? posPtr : negPtr;
        for (int j = i; i < N; i++) {
            if (arr[i] >= 0 && posNums > 0) {
                res[remainingEleIdx++] = arr[j];
                posNums--;
            } else {
                res[remainingEleIdx] = arr[j];
                negNums--;
            }
            res[i] = arr[i];
        }

        return res;
    }

    public static int[] rearrangeArrayBetter(int[] arr) {
        // The effort is to do the same in a single pass
        int N = arr.length;
        int[] res = new int[N];
        // Initialize two pointer one for each sign
        // Pos pointer starts off from zero and moves two points after correctly assigning an element
        // Neg pointer starts w 1 and moves two points after correctly assigning an element
        int posPtr = 0;
        int negPtr = 1;

        for (int i = 0; i < N; i++) {
            if (arr[i] < 0) {
                res[negPtr] = arr[i];
                negPtr += 2;
            } else {
                res[posPtr] = arr[i];
                posPtr += 2;
            }
        }
        return res;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, -2, -5, 2, -4, -9, -11, -13};
        System.out.println(Arrays.toString(rearrangeArrayUsingBruteForceWhereNegativesAndPositivesAreNotEqual(arr)));
    }

}
