package Queue;

import java.util.Stack;


public class ImplementQueueUsingStackII {

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public ImplementQueueUsingStackII() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        // If there are more push operations, we'll make other methods expensive rather than 'push'
        s1.push(x);
    }

    public int pop() {
        if (!s2.isEmpty())
            return s2.pop();
        else {
            while (s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        }
    }

    public int peek() {
        // Transfer all the elements from S1 to S2 anb then return its top
        if (!s2.isEmpty()) {
            return s2.peek();
        } else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.peek();
        }
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

