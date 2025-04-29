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
        int[] arr = new int[]{1};
        System.out.println(longestSubarrayOptimalForJustPositives(arr, 0));

    }

    public int longestSubarray(int[] arr, int k) {
        int N = arr.length;
        // code here
        // Since, we can't dynamically pick the subarrays from
        // in between having the sum k, we gotta do some reverse
        // engineering to do that.
        // Let's consider a point in the array, and the sum till that
        // ele is 'sum'. Now, if we happen to find a subarray from the start
        // w sum "sum - k" then from the end of that subarray til the chosen
        // point will be a aubarray giving us the sum 'k'.

        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        int longest = 0;

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];

            if (sum == k)
                longest = Integer.max(longest, i + 1);

            int preSum = sum - k;
            if (preSumMap.containsKey(preSum)) {
                int idx = preSumMap.get(preSum);
                int nEle = i - (idx + 1) + 1;
                longest = Integer.max(longest, nEle);
            }

            // the same 'sum' might act as presum, so we gotta store it in map
            if (!preSumMap.containsKey(sum))
                preSumMap.put(sum, i);
        }
        return longest; 
    }

}
