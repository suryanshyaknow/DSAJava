package GreedyAlgorithm.Medium;

public class JumpGame {

    public boolean canJump(int[] nums) {
        int N = nums.length;

        int maxIdx = nums[0]; // The idx on max leap at a given idx

        for (int i = 0; i < N; i++) {
            if (i > maxIdx)
                return false;

            // Update max idx w each iteration
            maxIdx = Integer.max(maxIdx, i + nums[i]);
        }
        return maxIdx >= N - 1;
    }

}
