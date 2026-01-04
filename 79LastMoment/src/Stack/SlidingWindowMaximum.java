package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindowOptimal(int[] nums, int k) {
        int N = nums.length;

        // Intuition: To not process each new window every time, we oughta
        // keep track of maximum window-wise, and from that we get the idea
        // that we'll be using a monotonic decreasing stack, but we'll also
        // be needed to ditch the elements falling outside the current window
        // from the stack.
        // Keeping that in mind, we'll be using DQ to control both front as well
        // as the back.

        int[] res = new int[N - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        // Step i. First off, check if dq is containing any elements outside the current window.
        // Step ii. Maintain the dq in monotonic decreasing order.
        // Step iii. And then pick the maximum element for the current window.

        int resPtr = 0;
        for (int i = 0; i < N; i++) {
            // Step i. Ditch elements outta curr window from the front
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();


            // Step ii. Maintain the monotonic decreasing order
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.poll();
            dq.push(i);

            // Step iii. Pick the maximum element for the current window
            if (i >= k - 1) res[resPtr++] = nums[dq.peekFirst()];
        }

        return res;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;

        int[] res = new int[N - k + 1]; // There'll N - k + 1 windows
        int ptr = 0;
        for (int i = 0; i <= N - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[ptr++] = max;
        }

        return res;

        // Time complexity: O(N * k)
        // Space complexity: O(N - k + 1)
    }

}
