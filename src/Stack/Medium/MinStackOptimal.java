package Stack.Medium;

import java.util.Stack;

public class MinStackOptimal {

    Stack<Long> st;
    long min;

    public MinStackOptimal() {
        st = new Stack<>();
        // min = Integer.MIN_VALUE;
    }

    public void push(int val) {
        if (st.isEmpty()) {
            min = val;
            st.push((long) val);
        } else {
            if (val >= min)
                st.push((long) val);
            else {
                // Encode
                st.push(2L * val - min);
                min = val;
            }
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;
        long val = st.pop();

        // Update the min accordingly
        if (val < min) {
            min = 2L * min - val;
        }
    }

    public int top() {
        if (st.isEmpty()) return -1;
        long top = st.peek();
        if (top > min)
            return (int) top;
        else
            return (int) min;
    }

    public int getMin() {
        return (int) min;
    }

}
