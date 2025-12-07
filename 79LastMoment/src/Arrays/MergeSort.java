package Arrays;

public class MergeSort {

    public int[] sortArray(int[] nums) {
        // First off, recursion is when a function solves a problem by calling
        // itself upon a smaller version of the same problem, until it hits a
        // base case that stops the calls.

        int N = nums.length;

        mergeSortHelper(nums, 0, N - 1);
        // Divide, divide, divide.. and then ultimately merge.

        return nums;

        // Time complexity: N * O(log N), since N sized array is getting divided by 2 at every recursive call
        // and every level of splitting requires O(n) work to merge the pieces back together.
        // Space complexity: O(N) (temp array for merging)
    }

    private static void mergeSortHelper(int[] nums, int low, int high) {
        if (low >= high) return; // bse case

        // Divide into two halves
        int mid = (low + high) / 2;

        mergeSortHelper(nums, low, mid);
        mergeSortHelper(nums, mid + 1, high);

        // by this point, we've got two portion of given array sorted
        // We've gotta merge 'em now
        mergeTwoSortedArrays(nums, low, mid, high);
    }

    private static void mergeTwoSortedArrays(int[] nums, int low, int mid, int high) {
        int leftPtr = low;
        int rightPtr = mid + 1;
        int[] temp = new int[high - low + 1];

        int tempPtr = 0;
        while (leftPtr <= mid && rightPtr <= high) {
            if (nums[leftPtr] <= nums[rightPtr])
                temp[tempPtr++] = nums[leftPtr++];
            else
                temp[tempPtr++] = nums[rightPtr++];
        }

        // If somehow, left array is still left
        while (leftPtr <= mid) temp[tempPtr++] = nums[leftPtr++];
        // If somehow, right array is still left
        while (rightPtr <= high) temp[tempPtr++] = nums[rightPtr++];

        // Copy the sorted elements into og arrays
        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }

}

