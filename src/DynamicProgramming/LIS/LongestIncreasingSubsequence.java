package DynamicProgramming.LIS;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int N = nums.length;

        return lengthOfLISHelper(0, nums, -1);
    }

    private int lengthOfLISHelper(int i, int[] nums, int prev) {
        if (i >= nums.length)
            return 0;

        // Do NOT take the ele
        int notTake = lengthOfLISHelper(i + 1, nums, prev); // prev remains the same
        // Take the ele
        int take = 0;
        // We could only take an ele if it's prev idx is -1
        // Or prev ele is less than the curr
        if (prev == -1 || nums[prev] < nums[i])
            take = 1 + lengthOfLISHelper(i + 1, nums, i);
        return Math.max(take, notTake);
    }


}
