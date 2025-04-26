package Array.Medium;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    public static int longestConsecutiveOptimal(int[] nums) {
        int N = nums.length;
        if (N == 0)
            return 0;
        // The idea behind the optimal soln is pretty simple and smart.
        // We're gonna check for the consecutives only if we're sure that
        // it had no pre-consecutive.
        // And to check the presence, no better ds than hash set ;)

        HashSet<Integer> hashSet = new HashSet<>();
        for (int ele : nums) {
            hashSet.add(ele);
        } // O(N) assuming the set is unordered
        int longest = 1;
        for (int ele : hashSet) {
            if (hashSet.contains(ele - 1))
                continue;
            int count = 1;
            while (hashSet.contains(ele + 1)) {
                count++;
                ele++;
            }
            longest = Integer.max(count, longest);
        } // O(N)
        return longest;

        // Time complexity: O(N) + 2O(N)
        // Even though the while loop checks for consecutive elements, each element of nums is processed only once.
        // This is because after processing an element as part of one consecutive sequence, it won't be checked again
        // in subsequent iterations. So, the overall time complexity remains O(N), where N is the number of elements in nums.
    }

    public static int longestConsecutiveBetter(int[] arr) {
        // On seeing 'consecutive', maybe sorting an array avails us some good
        int N = arr.length;
        int maxCount = 0;
        int currentCount = 0;
        int lastSmaller = Integer.MIN_VALUE;

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            if (arr[i] - 1 == lastSmaller) {
                // Then we update the last smaller and increase the count
                lastSmaller = arr[i];
                currentCount += 1;
            } else if (arr[i] != lastSmaller) {
                // Then we update the last smaller and reset the count
                lastSmaller = arr[i];
                currentCount = 1;
            }  // else we do nothing
            maxCount = Integer.max(maxCount, currentCount);
        }

        return maxCount;
    }

    public static int longestConsecutive(int[] arr) {
        // code here
        int N = arr.length;
        int maxLen = 1;

        // Simply put we'll process each element and search for its consecutive on its right,
        // and keep track of the longest length.
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int count = 1;

//            while (linearSearchForConsecutive(arr, num + 1, i + 1)) { // Search for the consecutive from an idx one greater than the current
            // Doing the above will exclude the elements lesser consecutives in a way
            while (linearSearchForConsecutive(arr, num + 1)) {
                num += 1;

                count += 1;
            }
            // Update the max accordingly
            maxLen = Integer.max(count, maxLen);
        }
        return maxLen;
    }

    private static boolean linearSearchForConsecutive(int[] arr, int ele) {
        for (int j : arr) {
            if (j == ele)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 1, 9, 5, 4, 3};
        longestConsecutive(arr);
    }

}
