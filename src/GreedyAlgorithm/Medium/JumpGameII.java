package GreedyAlgorithm.Medium;

public class JumpGameII {

    public int jumpOptimalUsingGreedy(int[] nums) {
        int N = nums.length;

        // The idea is to keep a range as to farthest we could go
        // instead of trying every possible path.
        int left = 0;
        int right = 0;
        int jumps = 0;

        while (right < N - 1) {
            int farthest = 0;
            for (int i = left; i <= right; i++) {
                farthest = Integer.max(farthest, i + nums[i]);
            }
            left = right + 1;
            right = farthest;
            jumps += 1;
        }
        return jumps;
    }

    public int jumpBruteForceViaRecursion(int[] nums) {
        return computeMinJumps(nums, 0, 0);
    }

    private static int computeMinJumps(int[] nums, int idx, int jumps) {
        int N = nums.length;
        if (idx >= N - 1)
            return jumps;

        int minJumps = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[idx]; i++) {
            minJumps = Integer.min(minJumps, computeMinJumps(nums, idx + i, jumps + 1));
        }
        return minJumps;
    }

}
