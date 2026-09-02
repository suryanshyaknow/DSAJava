package Stack;

import java.util.*;

public class MinStack {

    // Intuition: Since we want to return the min encountered
    // so far w/o iterating all the elements in the stack,
    // we could simply track the min so far w each new ele
    // addition.

    private static class Node {
        private Integer nodeVal;
        private Integer min;

        Node(Integer nodeVal, Integer min) {
            this.nodeVal = nodeVal;
            this.min = min;
        }
    }

    private Stack<Node> st;

    public MinStack() {
        st = new Stack<Node>();
    }

    public void push(int value) {
        // Whenever we push we're gonna keep track
        // of min so far
        if (st.isEmpty())
            st.push(new Node(value, value));
        else {
            int min = Math.min(value, st.peek().min);
            st.push(new Node(value, min));
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().nodeVal;
    }

    public int getMin() {
        return st.peek().min;
    }

}
