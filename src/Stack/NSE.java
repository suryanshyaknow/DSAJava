package Stack;

import java.util.Stack;

public class NSE {

    public void immediateSmaller(int arr[]) {
        // code here
        int N = arr.length;
        Stack<Integer> st = new Stack<>();

        for (int i=N-1; i >=0; i--) {
            // Keep popping elements till the stack is empty
            // Or have elements greater than the current ele
            while (!st.isEmpty() && st.peek() >= arr[i]) st.pop();

            st.push(arr[i]);
            if (st.isEmpty()) arr[i] = -1;
            else arr[i] = st.peek();
        }
    }
}
