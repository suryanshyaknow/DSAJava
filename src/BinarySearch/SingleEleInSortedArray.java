package BinarySearch;

public class SingleEleInSortedArray {

    public int singleNonDuplicateOptimal(int[] nums) {
        int N = nums.length;
        // On the left of the single item, there'd always be (even, odd) duple pair
        // Similarly on the right, there'd be (odd, even) pair.
        // Based on this observation, we could nuke the either halve accordingly

        // Handle the edge case
        if (N == 1) return nums[0];

        // Handle the extremities
        if (nums[0] != nums[1]) return nums[0];
        if (nums[N - 1] != nums[N - 2]) return nums[N - 1];

        // Begin the BS
        int low = 0;
        int high = N - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) return nums[mid];
            else if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
                // Where there might be dupes
                low = low + 1;
                high = high - 1;
            } else if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                // Nuke the left halve
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int singleNonDuplicate(int[] nums) {
        // Idea: At any given idx there should be a dupe of ele
        // either on the left or right, and if not, then it's the
        // single non duplicate.
        int N = nums.length;

        for (int i = 0; i < N; i++) {
            if (i == 0 && nums[i + 1] != nums[i]) {
                return nums[i];
            } else if (i == N - 1 && nums[i] != nums[i - 1])
                return nums[i];
            else {
                if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }


}
