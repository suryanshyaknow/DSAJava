package Array.Medium;

public class FindDuplicate {

    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;

        // Step i. Treat the array as LL and find the intersection point
        int slowPtr = nums[0];
        int fastPtr = nums[0];

        do {
            slowPtr = nums[slowPtr];
            fastPtr = nums[nums[fastPtr]]; // Moves two ndoes at a time
        } while (slowPtr != fastPtr);

        // Step ii. Find the cycle start
        fastPtr = nums[0];
        while (slowPtr != fastPtr) {
            slowPtr = nums[slowPtr];
            fastPtr = nums[fastPtr];
        }

        return slowPtr;
    }

}
