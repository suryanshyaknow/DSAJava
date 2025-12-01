package Array.Medium;

import java.util.Arrays;

public class NextPermutation {

    public void nextPermutation(int[] nums) {

        // i. Find the longest prefix match so that we could find the next permutation in lexicographically order by
        // arranging the remaining elements. Find the breaking idx.
        // ii. Find the shortest greater number than the ele at breaking idx. And swap 'em.
        // iii. Ideally we'd wanna sort the remaining elements but since they're increasing till we get the breaking idx,
        // so just reversing the arr would do the deed.

        int N = nums.length;

        // Find the breaking idx
        int breakingIdx = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                breakingIdx = i;
                break;
            }
        }

        // Now if there was no breaking idx, then that means the given permutation is the last
        if (breakingIdx == -1) {
            Arrays.sort(nums);
            return;
        }

        // Swap the ele at breaking idx w its shortest greater idx
        for (int i = N - 1; i > breakingIdx; i--) {
            if (nums[i] > nums[breakingIdx]) {
                swapEle(nums, i, breakingIdx);
                break;
            }
        }

        // Reverse the rest of the array
        reverseArr(nums, breakingIdx + 1);
    }

    private void reverseArr(int[] arr, int i) {
        int leftPtr = i;
        int rightPtr = arr.length - 1;
        while (leftPtr < rightPtr) {
            swapEle(arr, leftPtr, rightPtr);
            leftPtr++;
            rightPtr--;
        }
    }

    private void swapEle(int[] arr, int i, int breakingIdx) {
        int temp = arr[i];
        arr[i] = arr[breakingIdx];
        arr[breakingIdx] = temp;
    }


}
