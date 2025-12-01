package Arrays;

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int N = nums.length;

        // Intuition:
        // i. Since we have to find the number slightly greater than the given
        // among its permutations, we need longer prefix match for that.
        // So we need to find the breaking idx where in lies the smaller
        // ele so that the rest could arranged to make the number greater than it.

        // ii. We swap the breaking idx ele w slightly greater ele than it.

        // iii. Now, we could simply sort the rest to get the next permutation,
        // but since we know that rest were in an increasing order from the left
        // to right, we could just reverse 'em.'

        // Find the breaking idx
        int idx = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idx = i;
                break;
            }
        }

        // No breaking idx implies the given combination is the greatest
        // Return the reversed array
        if (idx == -1) {
            reverseArr(nums, 0, N - 1);
            return;
        }

        // Swap the breaking idx ele w the slightly greater ele
        for (int i = N - 1; i > idx; i--) {
            if (nums[i] > nums[idx]) {
                int temp = nums[i];
                nums[i] = nums[idx];
                nums[idx] = temp;

                break;
            }
        }

        // Now, reverse the rest
        reverseArr(nums, idx + 1, N - 1);
    }

    private static void reverseArr(int[] arr, int leftPtr, int rightPtr) {
        while (leftPtr < rightPtr) {
            int temp = arr[leftPtr];
            arr[leftPtr] = arr[rightPtr];
            arr[rightPtr] = temp;

            leftPtr++;
            rightPtr--;
        }
    }
}
