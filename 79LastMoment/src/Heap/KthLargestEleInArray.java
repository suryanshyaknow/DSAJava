package Heap;

import java.util.PriorityQueue;

public class KthLargestEleInArray {

    public int findKthLargest(int[] nums, int k) {
        int N = nums.length;

        // In PQ the head would be the smallest ele
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Intuition: Pretty simple, we'd just maintain the
        // k sized PQ till all eles are iterated over
        for (int i = 0; i < N; i++) {
            pq.add(nums[i]);

            // If PQ size exceeds k, poll
            if (pq.size() > k)
                pq.poll();
        }

        return pq.peek();

        // Time complexity: O(N * log k)
    }

    public int findKthLargestBruteForce(int[] nums, int k) {
        int N = nums.length;

        // Put each ele in PQ, the head would be the smallest ele
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(nums[i]);
        }

        // And then remove N-k elements, and the head would be the kth largest
        for (int i = 0; i < N - k; i++) {
            pq.poll();
        }

        return !pq.isEmpty() ? pq.peek() : -1;
    }
}
