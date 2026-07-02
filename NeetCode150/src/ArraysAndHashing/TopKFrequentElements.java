package ArraysAndHashing;

import java.util.*;

public class TopKFrequentElements {

    public int[] topKFrequentOptimal(int[] nums, int k) {
        int N = nums.length;

        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        // We already have a freq map. And the max freq of any
        // element is N as an ele can't occur more than the size
        // of the array.
        //
        // Since the frequencies are bound from 1 to N. We could
        // bucket the elements by their frequencies. And simply
        // pick top K elements from the buckets where there are
        // any elements.

        // Create a bucket to accommodate 1 to N frequencies
        List<Integer>[] buckets = new ArrayList[N + 1]; // bucket for each freq

        // Iterate over hashmap entries and put into buckets
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();

            if (buckets[val] == null)
                buckets[val] = new ArrayList<>();
            buckets[val].add(key);

        } // O(M) where M is unique elements

        // Now, all the buckets have been filled
        // Extract the top k elements from the end
        int[] res = new int[k];
        int cnt = 0;
        // Iterate over buckets and fetch the elements in 'em
        for (int i = N; i >= 0; i--) {
            if (cnt >= k)
                break;

            List<Integer> lis = buckets[i];
            if (lis != null && !lis.isEmpty()) {
                for (int j = 0; j < lis.size(); j++) {
                    if (cnt == k)
                        break;

                    res[cnt++] = lis.get(j);
                }
            }
        } // O(N + M) cuz exactly elements lie across all the buckets

        return res;

        // Time: O(N)
        // Space: O(M) + O(N)
    }

    static class Pair {
        private int ele;
        private int freq;

        public Pair(int ele, int freq) {
            this.ele = ele;
            this.freq = freq;
        }
    }

    public int[] topKFrequentUsingMinHeap(int[] nums, int k) {

        // Min Heap: Smallest ele is on the top always
        // Max Heap: Largest ele is on the top always

        // In java, Heap is implemented via PriorityQueue

        // So the intuition is pretty clear.
        // Measure the frequency of each ele and store them
        // both in Max Heap and just extract the first 4 eles.

        int N = nums.length;
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int ele : nums) {
            freqMap.put(ele, freqMap.getOrDefault(ele, 0) + 1);
        } // O(N)

        // Store the (key, val) pair in PQ
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq); // Min Heap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();

            pq.add(new Pair(key, val)); // O(log k)

            // Don't allow entries more than k at any instance
            if (pq.size() > k)
                pq.poll(); // O(log k)

        }

        // Now extract the first k elements from the PQ
        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().ele;
        } // O (k. log k)

        return res;

        // Time: O(N) + O(M log k) + O(k log k) where M -> unique elements in arr
        // ~ O(N + M log k)
        // Space: O(M) + O(k) + O(k)
    }

    public int[] topKFrequentUsingMaxHeap(int[] nums, int k) {

        // Min Heap: Smallest ele is on the top always
        // Max Heap: Largest ele is on the top always

        // In java, Heap is implemented via PriorityQueue

        // So the intuition is pretty clear.
        // Measure the frequency of each ele and store them
        // both in Max Heap and just extract the first 4 eles.

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int ele : nums) {
            freqMap.put(ele, freqMap.getOrDefault(ele, 0) + 1);
        }

        // Store the (key, val) pair in PQ
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq); // Max Heap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();

            pq.add(new Pair(key, val));
        }

        // Now extract the first k elements from the PQ
        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().ele;
        } // O (k. log M)

        return res;

        // Time: O(N) + O(M log M) + O(k log M) where M -> unique elements in arr
        // ~ O(N + (M + k) log M)
        // Space: O(M) + O(M) + O(k)

        // --- Improvement 1: Skip the Pair class ---
        // You can use int[] directly in the PQ instead of a custom Pair:
        //   PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        //   pq.add(new int[]{key, val});
        //   res[i] = pq.poll()[0];

        // --- Improvement 2: Min Heap of size k (better complexity) ---
        // Instead of a max heap over all M unique elements, maintain a min heap
        // capped at size k. Push each entry; if size exceeds k, poll the minimum.
        // This gives O(M log k) instead of O(M log M), which is faster when k << M.
        //   PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // min heap
        //   for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
        //       pq.add(new int[]{entry.getKey(), entry.getValue()});
        //       if (pq.size() > k) pq.poll();
        //   }

        // --- Improvement 3: Bucket Sort — O(N) optimal ---
        // Frequencies are bounded by N, so use an array of lists indexed by freq.
        // Scan from the end (highest freq) and collect k elements.
        //   List<Integer>[] bucket = new List[N + 1];
        //   for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
        //       int freq = e.getValue();
        //       if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
        //       bucket[freq].add(e.getKey());
        //   }
        //   int[] res = new int[k]; int idx = 0;
        //   for (int freq = N; freq >= 1 && idx < k; freq--)
        //       if (bucket[freq] != null)
        //           for (int ele : bucket[freq]) if (idx < k) res[idx++] = ele;
        //   return res;
        // Time: O(N), Space: O(N)
    }

}
