package Array.Medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithSumK {

    public static int longestSubarrayOptimalForJustPositives(int[] arr, int k) {
        int N = arr.length;
        int leftPtr = 0;
        int rightPtr = 0;

        int maxLen = 0;
        int sum = 0;
        while (rightPtr < N) {
            sum += arr[rightPtr];
            // Trim the left part if sum > K
            while (leftPtr < rightPtr && sum > k) {
                sum -= arr[leftPtr++];
            }
            if (sum == k) {
                maxLen = Math.max(rightPtr - leftPtr + 1, maxLen);
            }
            rightPtr++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1};
        System.out.println(longestSubarrayOptimalForJustPositives(arr, 0));

    }

    public int longestSubarray(int[] arr, int k) {
        // code here
        int N = arr.length;
        Map<Long, Integer> presSumMap = new HashMap<>();

        int maxLen = 0;
        long sumTillNow = 0;
        for (int i = 0; i < N; i++) {
            sumTillNow += arr[i];
            // See if the sum till now equals k
            if (sumTillNow == k) {
                maxLen = Math.max(maxLen, i + 1); // Because of zero based indexing
            }
            long rem = sumTillNow - k;
            // Now, check in the map whether we have remaining sum up till now to compute the len of required sub array
            if (presSumMap.containsKey(rem)) {
                int lenSubArr = i - presSumMap.get(rem);
                maxLen = Math.max(lenSubArr, maxLen);
            }

            // This would fail for the case where there are leading zeroes in intended sub arrays,
            // so apparently, we're to keep rem (x - k) to the left as possible.
            if (!presSumMap.containsKey(sumTillNow))
                presSumMap.put(sumTillNow, i);
        }
        return maxLen;
    }

}
