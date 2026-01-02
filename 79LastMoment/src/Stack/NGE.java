package Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NGE {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;

        int nge[] = new int[N2];
        // Kick all the elements if they're smaller than the curr
        // maintain a hashMap simultaneously
        Stack<Integer> st = new Stack<>();
        Map<Integer,Integer> hashMap = new HashMap<>();
        for (int i = N2 - 1; i >= 0; i--) {
            int ele = nums2[i];
            while (!st.isEmpty() && st.peek() <= ele) st.pop();

            nge[i] = !st.isEmpty() ? st.peek() : -1;

            st.add(ele);
            hashMap.put(ele, nge[i]);
        }

        int[] res = new int[N1];
        for (int i=0; i < N1; i++) {
            res[i] = hashMap.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
