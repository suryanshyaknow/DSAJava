package BinarySearch;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        // By observation, we could say that
        // the non-sorted halve would always contain
        // the smallest ele cuz pivot lies in it.
        // But that won't always be true.
        // So before ditching the sorted halve, keep track
        // of the smallest ele from it as well.

        int N = nums.length;

        int low = 0;
        int high = N-1;
        int res = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Identify the sorted halve and pick the smallest ele of it
            if (nums[mid] >= nums[low]) {
                res = Math.min(res, nums[low]);
                low = mid + 1;
            } else {
                res = Math.min(res, nums[mid]);
                high = mid - 1;
            }
        }

        return res;
    }
}
