package Stack.Hard;

import java.util.Stack;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        // Just gotta reduce this problem into Largest Rectangle in Histogram.
        // To do that, compute a prefix sum array column wise.

        int[][] prefixSumMatrix = new int[N][M];

        for (int i = 0; i < M; i++) {
            int prefSum = 0;
            for (int j = 0; j < N; j++) {
                prefSum += matrix[j][i] - '0';
                if (matrix[j][i] - '0' == 0) prefSum = 0;
                prefixSumMatrix[j][i] = prefSum;
            }
        }

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxArea = Integer.max(maxArea, computeMaxAreaInRectangle(prefixSumMatrix[i]));
        }

        return maxArea;

        // Time complexity: O(N * M) + O(N * 2M (to compute max area))
        // Space complexity: O(N * M) + O(N) (Stack)
    }

    private int computeMaxAreaInRectangle(int[] nums) {
        int N = nums.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        // Area = heights[idx] * (nse - pse - 1) i.e. number of consecutive bars to be included
        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                // Now, we have an ele whose pse and nse both are known
                int idx = st.peek();
                st.pop();

                int pse = st.isEmpty() ? -1 : st.peek();
                int nse = i;
                maxArea = Integer.max(maxArea, nums[idx] * (nse - pse - 1));
            }
            st.push(i);
        }

        // Iterate ove the stack once again to compute area for left over elements
        while (!st.isEmpty()) {
            int idx = st.peek();
            st.pop();

            int nse = N;
            int pse = st.isEmpty() ? -1: st.peek();
            maxArea = Integer.max(maxArea, nums[idx] * (nse - pse - 1));
        }
        return maxArea;
    }

}
