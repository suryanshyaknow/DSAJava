package SlidingWindow;

import java.util.*;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;

        int[] res = new int[N - k + 1]; // number of windows

        // Intuition: See, we're to keep track of maximums
        // window-wise without having to process each element
        // of window w each successive window. SO this gives us
        // impression that we have to use something monotonically
        // decreasing ds. And lso since we have to ditch the
        // maximums that now falls outside the curr winodw, so we
        // need a ds that allows us to remove elements from both end
        // in O(1) time.

        int l = 0; // leftPtr; it's also can be seen as start of each window
        int r = 0; // rightPtr

        Deque<Integer> dq = new ArrayDeque<>(); // we're gonna store indices

        while (r < N) {
            // Store in deque and enforce monotonically decreasing order
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[r]) dq.pollLast();

            dq.add(r);

            // Ditch the elements in dq that fall outside the current window
            while (!dq.isEmpty() && dq.peekFirst() < l) dq.pollFirst();

            // Start storing the maximums when atleast one window has passed
            if (r >= k - 1) {
                res[l] = nums[dq.peekFirst()];
                l++;
            }

            r++;
        }
        return res;

        // Time: O(N)
    }

}
