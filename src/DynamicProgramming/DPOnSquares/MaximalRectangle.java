package DynamicProgramming.DPOnSquares;

import java.util.Stack;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        // First off, we gotta reduce this problem to maximum area rectangle of histogram
        // And we could accordingly feed that function an array at a time
        int[] heights = new int[M];
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] - '0' == '1')
                    heights[j]++;
                else
                    heights[j] = 0;
            }
            maxArea = Math.max(maxArea, computeMaxAreaRectangle(heights)); // O(M)
        }

        return maxArea;

        // Time complexity: O(N * (M + M))
        // Space complexity: O(N)
    }

    private int computeMaxAreaRectangle(int[] heights) {
        int N = heights.length;

        // You're given the heights array, you've gotta find the
        // maximum area of the histogram
        // area = heights[i] * (nse - pse - 1)

        // Pse could be computed on the fly since we'd anyway be moving
        // from the front
        // And while the removal of elements from the st, it could be said that
        // the curr ele is the nse of the ele to be removed.
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            int currEle = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= currEle) {
                int ele = st.pop();
                int nse = i;
                int pse = !st.isEmpty() ? st.peek() : -1;

                maxArea = Math.max(maxArea, heights[ele] * (nse - pse - 1));
            }
            st.push(i);
        }

        // Process the untouched elements
        while (!st.isEmpty()) {
            int ele = st.pop();
            int pse = !st.isEmpty() ? st.peek() : -1;
            int nse = N;

            maxArea = Math.max(maxArea, heights[ele] * (nse - pse - 1));
        }

        return maxArea;
    }
}
