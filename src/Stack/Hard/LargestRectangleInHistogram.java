package Stack.Hard;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public int largestRectangleAreaOptimal(int[] heights) {
        // The idea is not to precompute PSE and NSE arrays which take 2N each
        // We anyway would be computing PSE because we'd be traversing from the start, i.e., via maintaining a stack,
        // However, when we pop the elements from the stack as they won't be PSE for the current idx ele,
        // we could say that the current idx ele is the NSE, and hence we also get the NSE for the ele to be popped.
        // Thereby, we could compute the area for this ele.
        int N = heights.length;
        int maxArea = Integer.MIN_VALUE;

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                int ele = st.peek();
                st.pop();

                int nse = heights[i]; // NSE for ele
                int pse = st.peek(); // PSE for ele
                maxArea = Integer.max(maxArea, heights[ele] * (nse - pse - 1));
            }

            st.push(i); // Remember we're pushing idx to the stack
        }

        while (!st.isEmpty()) {
            int ele = st.peek();
            st.pop();

            int nse = N;
            int pse = st.isEmpty() ? -1 : st.peek();
            maxArea = Integer.max(maxArea, heights[ele] * (nse - pse - 1));
        }

        // Upon traversing all the eles, if there are still some elements left in the stack,
        // we'd manually backtrack and compute the area for 'em by presuming the NSE as N for 'em.
        // And PSE as -1 if it doesn't exist.

        return maxArea;
    }


    public int largestRectangleArea(int[] heights) {
        int N = heights.length;
        // The max area would be the area that could be extended on either end of a current element,
        // i.e. area = heights[i] * (nse (max the area could be extended towards left) - pse (max the area could be extended towards left) - 1)

        int nse[] = getNSE(heights);
        int pse[] = getPSE(heights);

        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            maxArea = Integer.max(maxArea, heights[i] * (nse[i] - pse[i] - 1));
        }

        return maxArea;
    }

    private int[] getPSE(int[] arr) {
        int N = arr.length;
        int pse[] = new int[N];
        Stack<Integer> st = new Stack<Integer>();

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && st.peek() >= arr[i]) st.pop();

            int prevSmaller = st.isEmpty() ? -1 : st.peek();
            pse[i] = prevSmaller;

            st.push(i);
        }
        return pse;
    }

    private int[] getNSE(int[] arr) {
        int N = arr.length;
        int nse[] = new int[N];
        Stack<Integer> st = new Stack<Integer>();

        for (int i = N - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() >= arr[i]) st.pop();

            int nextSmaller = st.isEmpty() ? N : st.peek();
            nse[i] = nextSmaller;

            st.push(i); // Gotta push idx in stack rather cuz we wanna compute the width
        }
        return nse;
    }

}
