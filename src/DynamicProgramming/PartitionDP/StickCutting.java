package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class StickCutting {

    public int minCost(int n, int[] cuts) {
        int[] nums = new int[cuts.length + 2];
        Arrays.sort(cuts);
        int N = nums.length;

        nums[0] = 0;
        nums[N - 1] = n;
        for (int i = 1; i < N - 1; i++) {
            nums[i] = cuts[i - 1];
        }

        return minCostHelper(1, N - 2, nums);
    }

    private int minCostHelper(int i, int j, int[] nums) {
        if (i > j) return 0; // nothing left to cut, just a piece left out in bw paddings

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            minCost = Math.min(minCost, nums[j + 1] - nums[i - 1] + minCostHelper(i, k-1, nums) + minCostHelper(k + 1, j, nums));
        }

        return minCost;
    }

}
