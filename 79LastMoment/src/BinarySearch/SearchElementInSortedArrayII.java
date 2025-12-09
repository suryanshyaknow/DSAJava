package BinarySearch;

public class SearchElementInSortedArrayII {

    public boolean search(int[] nums, int target) {
        int N = nums.length;

        // The key idea here is to identify the sorted half
        // and search in it.
        // But since the dupes are employed as well, we've gotta
        // take care of an extea edge case where low, mid, and high
        // happen to be the same.

        int low = 0;
        int high = N-1;

        while (low <= high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) return true;
            // edge case
            if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
                low = low + 1;
                high = high - 1;
                continue;
            }

            // Identify the sorted halves
            if (nums[mid] >= nums[low]) {
                // see if the target lies in this sorted halve
                if (target >= nums[low] && target < nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return false;

        // Time complexity: O(log N)
        // Space complexity: O(1)
    }
}
