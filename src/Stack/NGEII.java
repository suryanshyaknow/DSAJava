package Stack;

import java.util.Stack;

public class NGEII {

    public int[] nextGreaterElements(int[] nums) {
        int N = nums.length;
        int[] nge = new int[N];
        Stack<Integer> st = new Stack<>();

        for (int i = 2 * N - 1; i >= 0; i--) {
            int idx = i % N;
            while (!st.isEmpty() && st.peek() <= nums[idx]) {
                st.pop();
            }
            if (i < N) {
                nge[i] = st.isEmpty() ? -1 : st.peek();
            } ;
            st.push(nums[idx]);
        }
        return nge;
    }

}
