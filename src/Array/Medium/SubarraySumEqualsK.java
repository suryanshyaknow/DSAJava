package Array.Medium;

import java.util.HashMap;

public class SubarraySumEqualsK {

    public static int subarraySumOptimal(int[] arr, int k) {
        int count = 0;
        int N = arr.length;

        long currentSum = 0; // x
        HashMap<Long, Integer> prefixSumFreqMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            currentSum += arr[i]; // That's gonna act as prefixSum for forthcoming arrays
            // See if sum till now equals k
            if (currentSum == k) count += 1;

            long remSum = currentSum - k; // X - k
            if (prefixSumFreqMap.containsKey(remSum)) {
                count += prefixSumFreqMap.get(remSum); // How many arrays do we have w prefixSum as their sum?
            }
            prefixSumFreqMap.put(currentSum, prefixSumFreqMap.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static int subarraySum(int[] arr, int k) { // Shall only work if it's ensured that array contains only positive numbers
        int count = 0;
        int N = arr.length;

        int leftPtr = 0; // this ptr is for shrinking the window size
        int rightPtr = 0; // for expanding the window
        int currentSum = 0;
        while (rightPtr < N) {
            currentSum += arr[rightPtr];
            // Now, what if the currentSum is greater than the k, we gotta shrink the window from the left
            while (leftPtr <= rightPtr && currentSum > k) {
                currentSum -= arr[leftPtr++];
            }

            if (currentSum == k) { // We gotta handle zeroes explicitly
                count++;
                int temp = leftPtr;
                while (temp < rightPtr && arr[temp] == 0) {
                    count++;
                    temp++;
                }
            }
            rightPtr++;
        }

        // Special Case: If k = 0, check if array even contains 0
        if (k == 0) {
            boolean hasZero = false;
            for (int num : arr) {
                if (num == 0) {
                    hasZero = true;
                    break;
                }
            }
            return hasZero ? count : 0;  // If no zero exists, return 0.
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1};
        subarraySumOptimal(arr, 2);
    }

}
