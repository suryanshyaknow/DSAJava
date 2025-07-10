package Stack;

import java.util.Stack;

public class NSE {

    public void immediateSmaller(int arr[]) {
        // code here
        int N = arr.length;
        int[] nse = new int[N];
        Stack<Integer> st = new Stack<>();

        for (int i=N-1; i >=0; i--) {
            // Keep popping elements till the stack is empty
            // Or have elements greater than the current ele
            while (!st.isEmpty() && st.peek() >= arr[i]) st.pop();

            int nextSmaller = st.isEmpty() ? -1: st.peek();
            nse[i] = nextSmaller;
            st.push(arr[i]);
        }
    }
}
