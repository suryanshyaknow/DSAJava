package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueue {

    private Queue<Integer> queue;

    public ImplementStackUsingQueue() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        // ✅ Stack: Last In, First Out
        // ✅ Queue: First In, First Out
        // Making Push expensive:
        // - You use one queue.
        // - When pushing, you put the new item in front of the queue ordering.
        // Intuition: "I want the last pushed item to be the first that comes out when I dequeue."

        // How?
        // - Enqueue the new item.
        // - Then dequeue all previous items one by one and enqueue them back.
        // - This puts the new item at the front of the queue.

        int size = queue.size();
        queue.offer(x);

        for (int i = 0; i < size; i++) {
            queue.offer(queue.peek());
            queue.poll();
        }
    }

    public int pop() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack Underflow!");
        }
        return queue.poll();
    }

    public int top() {
        if (queue.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

}
