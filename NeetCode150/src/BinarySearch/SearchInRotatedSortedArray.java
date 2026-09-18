package BinarySearch;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        int N = nums.length;

        // Intuition: First off internalize that rotated
        // arr just means the array after rotation is gonna
        // have two sorted portion.
        // To find the target we just gotta identify the sorted
        // portion and apply BS over than range.

        int l = 0;
        int r = N - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[mid] >= nums[l]) {
                // left portion is sorted
                // See if target lies in here
                if (target >= nums[l] && target < nums[mid]) r = mid - 1;
                else  l = mid + 1; // Switch the portion
            } else {
                // right portion is sorted
                if (target > nums[mid] && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            }
        }

        return -1;
    }

}
