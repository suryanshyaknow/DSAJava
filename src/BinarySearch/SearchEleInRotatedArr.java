package BinarySearch;

public class SearchEleInRotatedArr {

    public int search(int[] nums, int target) {
        // No dupes
        int N = nums.length;
        // The idea here is to identify sorted halve and search the target in it,
        // and discard the non-sorted halve.

        int low = 0;
        int high = N - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
                // Identify the sorted halves
                // left halve
            else if (nums[low] <= nums[mid]) {
                // See if target lies in this halve
                if (target >= nums[low] && target <= nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            } else {
                // See if target lies in this halve
                if (target <= nums[high] && target >= nums[mid])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }

}
