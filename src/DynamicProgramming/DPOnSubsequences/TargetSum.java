package DynamicProgramming.DPOnSubsequences;

public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        // Minus could be taken common and this prob would
        // boil down to computing/counting the subsets.
        // S = S1 + S2
        // S1 - S2 = target
        // S- 2S2 = target
        // S2 = (S - target)/2
        int tot = 0;
        int N = nums.length;
        for (int i=0; i < N; i++) tot += nums[i];

        // S2 couldn't be negative and fractional
        if (tot - target < 0 || (tot - target) % 2 != 0 ) return 0;
        int newTarget = (tot - target)/2;

        return helper(N-1, nums, newTarget);
    }

    private static int helper(int idx, int[] nums, int target) {
        if (idx == 0) {
            if (target == 0 && nums[idx] == 0) return 2;
            else if (target == 0 || nums[idx] == target) return 1;
            else return 0;
        }

        int notTake = helper(idx - 1, nums, target);
        int take = 0;
        if (target >= nums[idx]) take = helper(idx - 1, nums, target - nums[idx]);

        return take + notTake;
    }
}
