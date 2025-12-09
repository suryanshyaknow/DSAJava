package BinarySearch;

public class SearchElementInSortedArrayI {

    public int search(int[] nums, int target) {
        int N = nums.length;

        // Since the arr is rotated and will not be sorted
        // totally, we've gotta identify the sorted half
        // before actually searching for the ele.

        int low = 0;
        int high = N-1;

        while (low <= high) {
            int mid = low + (high - low)/ 2;

            if (nums[mid] == target) return mid;

            // Identify the sorted half
            if (nums[mid] >= nums[low]) {
                // See if the target lies in this halve
                if (target >= nums[low] && target <= nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {
                // See if the target lies in this halve
                if (target >= nums[mid] && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }
}
