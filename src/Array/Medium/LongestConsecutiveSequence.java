package Array.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LongestConsecutiveSequence {

    public static int longestConsecutiveOptimal(int[] arr) {
        // So the idea here is pretty simple.
        // We'll only check for the consecutives if we're sure that the given element doesn't have any lesser consecutive
        int N = arr.length;
        int maxCount = 1;
        HashSet<Integer> hashArr = new HashSet<>();

        // Populate the hash array
        for (int ele : arr) {
            hashArr.add(ele);
        }

        // Now iterate through the hash array and only check for the consecutives if the ele is a starting point
        for (int ele : hashArr) {
            if (!hashArr.contains(ele - 1)) {
                // Then we check for further consecutives
                int num = ele + 1;
                int count = 1;
                while (hashArr.contains(num)) {
                    count += 1;
                    num += 1;
                }
                maxCount = Integer.max(maxCount, count);
            }
        }
        return maxCount;
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
        int maxCount = 1;

        // Simply put we'll process each element and search for its consecutive on its right,
        // and keep track of the longest length.
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int count = 1;
            List<Integer> consecutives = new ArrayList<>();
            consecutives.add(arr[i]);

//            while (linearSearchForConsecutive(arr, num + 1, i + 1)) { // Search for the consecutive from an idx one greater than the current
            // Doing the above will exclude the elements lesser consecutives in a way
            while (linearSearchForConsecutive(arr, num + 1)) {
                num += 1;
                consecutives.add(num);

                count += 1;
            }
            System.out.println(consecutives);
            // Update the max accordingly
            maxCount = Integer.max(count, maxCount);
        }
        return maxCount;
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
