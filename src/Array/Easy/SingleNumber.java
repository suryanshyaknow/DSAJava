package Array.Easy;

import java.util.TreeMap;

public class SingleNumber {

    public int singleNumberOptimal(int[] arr) {
        // We'll use XOR and all the twice occurring elements would cancel out each other
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        return xor;
    }

    public int singleNumberBetter(int[] arr) {
        int N = arr.length;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        // Constructing the TreeMap → O(N log M) (since insertion in a TreeMap takes O(log N) per element).
        // Where M -> N/2 + 1

        for (Integer key : freqMap.keySet()) {
            if (freqMap.get(key) == 1)
                return key;
        }
        // Iterating over freqMap.keySet() → O(N). O(N/2 + 1) since except for the one element other have their twin dupes.
        return -1;
    }


    public int singleNumber(int[] arr) { // This approach won't work for the cases where there are negative valies in the arr
        int N = arr.length;

        // Maintain a hashArr of size maxElement of the array and update the frequencies
        // For that, first off, we gotta find the max val elements via array traversal

        // Step 1: Find the largest element
        int max = -1;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr[i]);
        } // O(N)

        // Step 2: Construct an hashArr and update the frequencies of arr elements
        int[] hashArr = new int[max + 1]; // Cuz we gotta even update the frequency of the largest element
        for (int i = 0; i < N; i++) {
            hashArr[arr[i]] += 1;
        } // O(N)

        // Step 3: Iterate over the hashArr and return the element with the single frequency
        for (int i = 0; i <= max; i++) {
            if (hashArr[i] == 1) return i;
        } // O(max(N))
        return -1;
    }

}
