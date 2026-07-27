package TwoPointers;

public class TrappingRainWater {

    public int trap(int[] height) {
        int N = height.length;

        // Intuition: To compute the area at a given idx
        // we need to know the max height at the left and
        // max height at the right and pick the minimum of the
        // both so as not to spill any water.
        // area = min(leftMax, rightMax) - height[idx] * 1

        // Seems we need to precompute the left and right maxes for
        // each idx beforehand.
        int[] leftMax = new int[N];
        int[] rightMax = new int[N];

        leftMax[0] = 0;
        int leftMaxSeen = height[0];
        for (int i=1; i < N; i++) {
            leftMax[i] = leftMaxSeen;
            leftMaxSeen = Math.max(height[i], leftMaxSeen);
        }

        rightMax[N - 1] = 0;
        int rightMaxSeen = 0;
        for (int i=N-2; i >= 0; i--) {
            rightMax[i] = rightMaxSeen;
            rightMaxSeen = Math.max(height[i], rightMaxSeen);
        }

        // Now compute the trapped rain water
        int res = height[N - 1];
        for (int i=0; i < N; i++) {
            int currArea = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (currArea > 0)
                res += currArea;
        }
        return res;

        // Time: O(N)
        // Space: O(N)
    }

}
