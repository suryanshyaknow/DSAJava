package DesignGurusEasy;

import java.util.HashMap;
import java.util.Map;

public class GoodPairs {

    public int numGoodPairsOptimal(int[] nums) {
        int pairCount = 0;
        // TODO: Write your code here
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n: nums) {
            // Store the frequency of each number at a time in the map
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);

            // Add to the pairs
            // If a number has appeared 3 times, that means 4th occurrence could have a pair with each prior occurrence i.e. 3 pairs
            // i.e. 'p - 1' pairs for 'p' occurrences of a number
            pairCount += freqMap.get(n) - 1;
        }
        return pairCount;
    }

    public static int numGoodPairs(int[] nums) {
        int pairCount = 0;
        // TODO: Write your code here
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    pairCount += 1;
            }
        }
        return pairCount;
    }

    public static void main(String[] args) {
        GoodPairs sol = new GoodPairs();

        int[] nums1 = { 1, 2, 3, 1, 1, 3 };
        int result1 = sol.numGoodPairsOptimal(nums1);
        System.out.println("Result 1: " + result1 + " (Expected: 4)");
        System.out.println("Result 1: " + numGoodPairs(nums1) + " (Expected: 4)");
        System.out.println();

        int[] nums2 = { 1, 1, 1, 1 };
        int result2 = sol.numGoodPairsOptimal(nums2);
        System.out.println("Result 2: " + result2 + " (Expected: 6)");
        System.out.println("Result 2: " + numGoodPairs(nums2) + " (Expected: 6)");
        System.out.println();

        int[] nums3 = { 1, 2, 3 };
        int result3 = sol.numGoodPairsOptimal(nums3);
        System.out.println("Result 3: " + result3 + " (Expected: 0)");
        System.out.println("Result 3: " + numGoodPairs(nums3) + " (Expected: 0)");
        System.out.println();
    }
}
