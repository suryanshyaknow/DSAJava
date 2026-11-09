package ArraysAndHashing;

import java.util.*;

public class TopKFrequentElements {

    public int[] topKFrequentOptimal(int[] nums, int k) {
        int N = nums.length;

        // Intuition: Hashing + Bucket sort
        //
        // First off, we have to store the elements w their
        // frequencies in a hashmap.
        // Then we can simply bucket the elements by their
        // frequencies and simply extract the top k frequent
        // elements.
        // Note that we can do this because frequencies are bound
        // to be O to N.

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < N; i++) {
            hash.put(nums[i], hash.getOrDefault(nums[i], 0) + 1);
        } // O(N)

        // Bucket Structure: ArrayList<>[N + 1]; 0 -> N
        List<Integer>[] buckets = new ArrayList[N + 1];
        // Iterate over hash entries
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            int ele = entry.getKey();
            int freq = entry.getValue(); // freq

            List<Integer> candidates = buckets[freq];
            if (candidates == null)
                candidates = new ArrayList<>();
            candidates.add(ele);
            buckets[freq] = candidates;
        } // O(U)

        // Extract top k elements
        // Iterate over buckets from last: decreasing freq
        int[] res = new int[k];
        int cnt = 0;
        for (int i = N; i >= 0; i--) {
            if (cnt >= k)
                break;

            // Fetch the bucket
            List<Integer> bucket = buckets[i];
            if (bucket != null && !bucket.isEmpty()) {
                for (int j = 0; j < bucket.size(); j++) {
                    if (cnt >= k)
                        break;
                    res[cnt++] = bucket.get(j);
                }
            }
        } // O(N + U)

        return res;

        // Time: O(N)
        // Space: O(U) + O(N)
    }

    static class Pair {
        private int ele;
        private int freq;

        public Pair(int ele, int freq) {
            this.ele = ele;
            this.freq = freq;
        }
    }

    private static class Node {
        int ele;
        int freq;

        Node(int ele, int freq) {
            this.ele = ele;
            this.freq = freq;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        int N = nums.length;

        // Intuition: Hash + min. Heap
        // First off, store the frequencies of each element in a
        // hash map and then start storing each element into
        // min heap based on freq of element. The ele w the least
        // number of occurrences will be at top.
        //
        // In java, heap is implemented using PriorityQueue
        Map<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < N; i++) {
            hash.put(nums[i], hash.getOrDefault(nums[i], 0) + 1);
        }

        // Initialize a min heap
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue())); // O(log k)

            // Pop the element w least freq
            if (pq.size() > k) pq.poll(); // O(log k)
        } // O(U log k) where u -> unique elements

        int[] res = new int[k];
        // Iterate over pq for k times and retrive the candidat ele
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().ele;
        } // O(k log k)

        return res;

        // Time: O(N + U log k)
        // Space: O(N)
    }
}
