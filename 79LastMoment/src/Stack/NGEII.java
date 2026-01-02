package Stack;

import java.util.Stack;

public class NGEII {

    public int[] nextGreaterElements(int[] nums) {
        int N = nums.length;

        // Since it's a circular array
        // Just hypothetically double the array
        int[] nge = new int[N];
        Stack<Integer> st = new Stack<>();
        for (int i = 2 * N - 1; i >= 0; i--) { // O(2N)
            int ele = nums[i % N];
            while (!st.isEmpty() && st.peek() <= ele) st.pop(); // Can pop 2N elements from the stack

            // Only store the nge if idx < N
            if (i < N) nge[i] = !st.isEmpty() ? st.peek() : -1;
            st.add(ele);
        }

        return nge;

        // Time complexity: O(4N)
        // Space complexity: O(2N)
    }
}
