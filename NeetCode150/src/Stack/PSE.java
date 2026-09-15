package Stack;

import java.util.*;

public class PSE {

    class Solution {
        public static ArrayList<Integer> prevSmaller(int[] arr) {
            // code here

            int N = arr.length;

            ArrayList<Integer> res = new ArrayList<>();
            // for (int i=0; i < N; i++) res.add(-1);

            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < N; i++) {
                // Since in the stack we're looking for the
                // pse we have to ditch all the eles greater
                // than the one whose pse we're looking for
                while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();

                int ans = st.isEmpty() ? -1 : arr[st.peek()];
                // res.set(i, ans);
                res.add(ans);

                st.add(i);
            }

            return res;
        }
    }

}
