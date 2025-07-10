package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;

        // Idea: Decreasing Monotonic Stack
        // We'd need a dequeue to do pop from front in order to discard past window maxes,
        // And to do push from the back to record new maxes.

        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[N - k + 1];
        int resIdx = 0;

        for (int i = 0; i < N; i++) {
            // Dequeue ain't empty and stores a max outta current window
            // Removes element from the FRONT
            // Reason: Expired from the window
            // If the oldest idx is outside the current window's left boundary, it gotta go
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.poll();

            // Maintain the decreasing monotonic order. Remove all the elements if they're lesser the one to be added.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            deque.offer(i);

            // Store maxes if the window has started
            if (i >= k - 1) res[resIdx++] = nums[deque.peek()];
        }
        return res;
    }

}
