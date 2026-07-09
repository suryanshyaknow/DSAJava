package ArraysAndHashing;

import java.util.*;

public class LongestConsecutiveSequence {

    public int longestConsecutiveOptimal(int[] nums) {
        int N = nums.length;

        // Intuition: Now, the optimal solution's idea
        // is pretty clean and smart. What we're gonna
        // do is only look for consecutives if we're sure
        // the number hasn't got a pre-consecutive.

        int maxLen = 0;

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i=0; i < N; i++) hashSet.add(nums[i]); // O(1) lookups

        for (int i=0; i < N; i++) {
            // Check for preconsecutive
            if (hashSet.contains(nums[i] - 1)) continue;

            int num = nums[i];

            int len = 1;
            while (hashSet.contains(num + 1)) {
                len += 1;
                num += 1;
            }
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
        // Time: O(N)
        // Space: O(N)
    }

    public int longestConsecutive(int[] nums) {
        int N = nums.length;

        if (N == 0) return 0;

        // The idea is to sort the array and bring
        // the consecutives together. Track and the
        // last smaller and udpate the count accordingly,
        // and the moment the current elements breaks the
        // flow, update the last smaller.
        Arrays.sort(nums);
        int maxLen = 1;

        int lastSmaller = Integer.MIN_VALUE;
        int count = 1;
        for (int i = 0; i < N; i++) {
            if (nums[i] == lastSmaller) continue;

            if (nums[i] - 1 != lastSmaller) {
                count = 1;
            } else {
                count += 1;
            }
            lastSmaller = nums[i];

            maxLen = Math.max(maxLen, count);
        }

        return maxLen;

        // Time: O(N log N)
        // Space: O(log N)
    }

}
