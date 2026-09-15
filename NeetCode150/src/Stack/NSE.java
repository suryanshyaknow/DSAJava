package Stack;

import java.util.*;

public class NSE {

    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        // code here

        // Since we have to find the next smaller ele, we'll
        // start from the right and starting puting elements
        // into stack and the moment we encounter an element
        // that's smaller than the ones present in the stack,
        // we'll pop the rest from the stack.
        // Stack would follow monotonically increasing order
        int N = arr.length;

        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < N; i++) res.add(-1);

        for (int i = N - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();

            int ans = st.isEmpty() ? -1 : arr[st.peek()];
            res.set(i, ans);

            st.push(i);
        }

        return res;

        // Time complexity: O(2N)
    }

}
