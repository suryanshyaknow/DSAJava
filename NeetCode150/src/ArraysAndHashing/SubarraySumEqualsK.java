package ArraysAndHashing;

import java.util.*;

public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int N = nums.length;

        // Intuition: Prefix sum + hashing
        // To compute the number of subarrays in a single
        // pas w/o generating all subarrays we can think of
        // prefix sum and hashing.
        // So what Im gonna do is I'll maintain a running sum
        // while traversing the array and I'll see whether K's
        // complement (Sum - K) to give me total sum of Sum exist in
        // my hash.
        // The frequency of the complement can be added to our ans
        // as it implies there are that many number of subarrays w
        // sum k.

        Map<Integer, Integer> hash = new HashMap<>(); // prefix sum : frequency

        int sum = 0; // running sum
        int res = 0; // count of subarrays having sum k
        for (int i = 0; i < N; i++) {
            sum += nums[i];

            if (sum == k) res += 1;

            // Check for K's complement: Sum - k in hash
            int comp = sum - k;

            res += hash.getOrDefault(comp, 0);

            // Store the curr sum as prefix sum
            hash.put(sum, hash.getOrDefault(sum, 0) + 1);
        }

        return res;

        // Time: O(N)
        // Space: O(N)
    }
}
