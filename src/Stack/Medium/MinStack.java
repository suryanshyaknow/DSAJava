package Stack.Medium;

import java.util.Stack;

public class MinStack {

    private static class Pair {
        private Integer first;
        private Integer second;

        Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }
    }

    private Stack<Pair> st;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {
        if (st.isEmpty()) {
            st.push(new Pair(val, val));
        } else {
            st.push(new Pair(val, Integer.min(val, st.peek().second)));
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().first;
    }

    public int getMin() {
        return st.peek().second;
    }
}
