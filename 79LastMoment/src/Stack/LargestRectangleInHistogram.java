package Stack;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public int largestRectangleAreaOptimal(int[] heights) {
        int N = heights.length;

        // We can't afford computing PSE and NSE
        // arrays separately, we'll try to do that in
        // a single go.
        // Remember, while computing PSEs when you're
        // kicking out elements from the stack, realise that
        // the current ele is their NSE.

        int res = 0;
//        int pseArr[] = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            int curEle = heights[i];
            while (!st.isEmpty() && heights[st.peek()] >= curEle) {
                // It's gotta go and the curEle is its nse
                int eleIdx = st.pop();

                int nse = i;
                int pse = !st.isEmpty() ? st.peek() : -1;
                int area = heights[eleIdx] * (nse - pse - 1);
                res = Math.max(area, res);
            }

//            pseArr[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(i);
        }

        // Now if there are still elements left in the stack
        while (!st.isEmpty()) {
            int eleIdx = st.pop();
            int pse = !st.isEmpty() ? st.peek() : -1;
            int nse = N;
            int area = heights[eleIdx] * (nse - pse - 1);
            res = Math.max(area, res);
        }

        return res;

        // Time complexity: O(2N)
        // Space complexity: O(N)
    }

    public int largestRectangleArea(int[] heights) {
        int N = heights.length;

        // Area = heights[i] * (nse[i] - pse[i])
        // i.e., the max length the area could be stretched
        // forwards and backwards.

        int[] nse = getNSE(heights); // O(2N)
        int[] pse = getPSE(heights); // O(2N)
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            int area = heights[i] * (nse[i] - pse[i] - 1);
            maxArea = Math.max(maxArea, area);
        } // O(N)
        return maxArea;

        // Time complexity: O(5N)
        // Space complexity: O(2N)
    }

    private static int[] getPSE(int[] nums) {
        int N = nums.length;

        int[] res = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();

            res[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(i);
        }
        return res;
    }


    private static int[] getNSE(int[] nums) {
        int N = nums.length;

        int[] res = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();

            res[i] = !st.isEmpty() ? st.peek() : N;
            st.push(i);
        }
        return res;
    }

}
