package DynamicProgramming.DPOnSubsequences;

import java.util.*;

public class TargetSumEqualsK {

    public int findTargetSumWaysMemo(int[] nums, int target) {
        // See, if the S is the sum of all the integers in arr
        // target is given
        // s1 + s2 = S; because + or - are to be put before each ele
        // s1 - s2 = target
        // 2s1 = S + target
        // s1 = (S + target) / 2

        int S = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            S += nums[i];
        }

        int sumPlusTarget = S + target;
        if (sumPlusTarget < 0 || sumPlusTarget % 2 != 0)
            return 0;
        int newTarget = (S + target) / 2;

        int[][] dp = new int[N][newTarget + 1];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        return helperMemo(N - 1, nums, newTarget, dp);
    }

    private int helperMemo(int i, int[] nums, int t, int[][] dp) {
        // Since we're to find all the subsets, we
        // oughta ensure that we cover all the elements
        // and not early return.

        if (i == 0) {
            if (t == 0 && nums[i] == 0)
                return 2;
            if (t == 0 || nums[i] == t)
                return 1;
            return 0;
        }

        if (dp[i][t] != -1) return dp[i][t];

        int notPick = helperMemo(i - 1, nums, t, dp);
        int pick = 0;
        if (nums[i] <= t)
            pick += helperMemo(i - 1, nums, t - nums[i], dp);

        return dp[i][t] = pick + notPick;
    }

    public int findTargetSumWays(int[] nums, int target) {
        // See, if the S is the sum of all the integers in arr
        // target is given
        // s1 + s2 = S; because + or - are to be put before each ele
        // s1 - s2 = target
        // 2s1 = S + target
        // s1 = (S + target) / 2

        int S = 0;
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            S += nums[i];
        }

        int sumPlusTarget = S + target;
        if (sumPlusTarget < 0 || sumPlusTarget % 2 != 0)
            return 0;
        int newTarget = (S + target) / 2;

        return helper(N - 1, nums, newTarget);
    }

    private int helper(int i, int[] nums, int t) {
        // Since we're to find all the subsets, we
        // oughta ensure that we cover all the elements
        // and not early return.

        if (i == 0) {
            if (t == 0 && nums[i] == 0) return 2;
            if (t == 0 || nums[i] == t) return 1;
            return 0;
        }

        int notPick = helper(i - 1, nums, t);
        int pick = 0;
        if (nums[i] <= t)
            pick += helper(i - 1, nums, t - nums[i]);

        return pick + notPick;
    }
}
