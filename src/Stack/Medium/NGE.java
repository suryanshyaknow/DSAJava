package Stack.Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NGE {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        int res[] = new int[N1];

        int nge[] = new int[N2]; // O(N2)

        /*
         * INTUITION:
         * We process nums2 from right → left and use a MONOTONIC DECREASING STACK.
         *
         * Why?
         * For each element, we want the first greater element on its RIGHT.
         *
         * Stack helps us maintain "useful candidates":
         * - We remove all elements <= current because they can never be the NGE
         *   for this element or any element to its left.
         * - After popping, the top of the stack (if exists) is the next greater element.
         *
         * This ensures:
         * - Each element is pushed and popped at most once → O(N)
         */
        Stack<Integer> st = new Stack<>();
        for (int i = N2 - 1; i >= 0; i--) {
            // Empty the stack till it's empty or all the smaller eles are popped out
            while (!st.isEmpty() && st.peek() <= nums2[i]) st.pop();

            if (st.isEmpty()) nge[i] = -1;
            else
                nge[i] = st.peek();

            st.push(nums2[i]);
        }

        Map<Integer, Integer> hashMap = new HashMap<>(); // O(N2)
        for (int i = 0; i < N2; i++) {
            hashMap.put(nums2[i], i);
        }

        // Populate the resultant array
        for (int i = 0; i < N1; i++) {
            res[i] = nge[hashMap.get(nums1[i])];
        } // O(N1)

        return res;
    }
}
