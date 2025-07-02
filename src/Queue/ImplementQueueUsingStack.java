package Queue;

import java.util.Stack;

public class ImplementQueueUsingStack {

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public ImplementQueueUsingStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        // - S1 -> S2, push all the elements from S1 to S2
        // - Push x into S1
        // - Push all elements back from S2 to S1

        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s1.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        // Time complexity: O(2N)
    }

    public int pop() {
        return s1.pop();
    }

    public int peek() {
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty();
    }
}
