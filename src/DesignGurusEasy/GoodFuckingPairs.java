package DesignGurusEasy;

import java.util.HashMap;

public class GoodFuckingPairs {

    public static int numGoodPairs(int[] nums) {
        int pairCount = 0;
        // TODO: Write your code here

        // Maintain a hashmap for each entry along with its occurrences
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Apply the formula n.(n-1)/2 and summate 'em all
        for (int freq : freqMap.values()) {
            pairCount += freq * (freq - 1) / 2;
        }

        return pairCount;
    }

    public static void main(String[] args) {

    }

}
