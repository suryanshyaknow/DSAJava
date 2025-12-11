package BinarySearch;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int N = nums.length;

        if (N == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[N - 1] > nums[N - 2]) return N - 1;

        // So by observation, we realize that if mid
        // ain't the peak, then it'll either lie on the
        // increasing curve (which could be eliminated since
        // the peak would then lie on the right), or it'll
        // lie on the decreasing curve where then it'd lie
        // on the left.

        // We've gotta take care of an extra edge case in case
        // of multiple peaks where the mid might lie on the trough.
        // then we could eliminate either half.

        int low = 1;
        int high = N - 2;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1])
                return mid;
            else if ((nums[mid] < nums[mid + 1] && nums[mid] > nums[mid - 1]) || (nums[mid] < nums[mid + 1] && nums[mid] < nums[mid - 1])) // lies on the increasing halve
                low = mid + 1;
            else // decreasing halve
                high = mid - 1;
        }
        return -1;
    }
}
