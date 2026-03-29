package DynamicProgramming;

public class HouseRobberII {

    public int rob(int[] nums) {
        // Since the first and the last house can't be robbed
        // together. So we break the problem into two and
        // solve them separately.
        // One in which, the 0th house is robbed, and the last is
        // excluded. And in the other, vice versa.

        int N = nums.length;
        return Math.max(robHelper(0, N - 2, nums), robHelper(1, N - 1, nums));
    }

    private int robHelper(int i, int end, int[] nums) {
        int N = nums.length;
        // If we're at the last idx that means,
        // it's chosen
        if (i == end)
            return nums[i];
        if (i >= end)
            return 0;

        int pick = nums[i] + robHelper(i + 2, end, nums);
        int notPick = robHelper(i + 1, end, nums);

        return Math.max(pick, notPick);
    }

    public int robTabulation(int[] nums) {
        // Since the first and the last house can't be robbed
        // together. So we break the problem into two and
        // solve them separately.
        // One in whicn, the 0th nei is robbed, and the last is
        // excluded. And in the other, vice-versa.

        int N = nums.length;
        if (N == 1) return nums[0];

        return Math.max(robHelperTabulation(nums, 0, N - 2), robHelperTabulation(nums, 1, N - 1));
    }

    private int robHelperTabulation(int[] nums, int start, int end) {
        int N = end - start + 1;

        int[] dp = new int[N + 2]; // extra 2 for i+2 safety

        dp[N] = 0;
        dp[N + 1] = 0;
        for (int i = N - 1; i >= 0; i--) {
            int pick = nums[i + start] + dp[i + 2]; // nums mapped to sliced arr
            int notPick = dp[i + 1];

            dp[i] = Math.max(pick, notPick);
        }
        return dp[0];
    }
}
