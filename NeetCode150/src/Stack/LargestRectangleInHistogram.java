package Stack;

import java.util.*;

public class LargestRectangleInHistogram {

    public int largestRectangleAreaOptimal(int[] heights) {
        int N = heights.length;

        // Intuition: The idea is not to precompute
        // pse and nse arrays which warrant extra
        // passes and extra space. And try to do this
        // in a single pass.
        //
        // Gotta internalize the fact that while computing
        // pse when you're popping the eles cuz they're
        // greater than the current ele being processed is
        // the nse for them.

        Stack<Integer> st = new Stack<>();
        int maxArea = Integer.MIN_VALUE;

        for (int i=0; i < N; i++) {
            int ele = heights[i];
            while (!st.isEmpty()
                    && heights[st.peek()] >= ele) {
                int h = heights[st.pop()];
                int nse = i;
                int pse = st.isEmpty() ? -1 : st.peek(); // as stack is montonically increasing
                int area = h * (nse - pse - 1);
                maxArea = Math.max(area, maxArea);
            }

            // Now also note that for the current idx being processed
            // we don't know the nse yet, that's why we won't compute
            // the area for it as of now.

            st.push(i);
        }

        // Now could be possible some elements are left in stack
        while (!st.isEmpty()) {
            int ele = heights[st.pop()];
            int nse = N;
            int pse = st.isEmpty() ? -1 : st.peek();
            maxArea = Math.max(maxArea, ele * (nse - pse - 1));
        }

        return maxArea;

        // Time: O(2N)
        // Space: O(N)
    }

    public int largestRectangleArea(int[] heights) {
        int N = heights.length;

        // Intuition: So the intuition is pretty simple,
        // we just have to treat each height as a candidate
        // compute the max width by extending towards both
        // ends. Meaning we have to find the nse and pse for
        // each height candidate and that'd give us the width.

        // Gotta precompute the pse and nse arrays to compute
        // the max expandable width at any given idx. Note that
        // the arrays would contain the indices and not actual elements.

        int[] pse = computePse(heights); // O(2N), O(2N)
        int[] nse = computeNse(heights); // O(2N), O(2N)

        int maxArea = Integer.MIN_VALUE;
        // area[i] = nse[i] - pse[i] - 1

        for (int i=0; i < N; i++) {
            maxArea = Math.max(maxArea, heights[i] * (nse[i] - pse[i] - 1));
        } // O(N)

        return maxArea;

        // Time: O(5N)
        // Space: O(4N)
    }

    private static int[] computePse(int[] nums) {
        int N = nums.length;
        int[] res = new int[N];

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i])
                st.pop();

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.add(i);
        }
        return res;
    }

    private static int[] computeNse(int[] nums) {
        int N = nums.length;
        int[] res = new int[N];

        Stack<Integer> st = new Stack<>();
        for (int i = N-1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i])
                st.pop();

            res[i] = st.isEmpty() ? N : st.peek();
            st.add(i);
        }
        return res;
    }
}
