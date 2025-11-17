package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class PalindromePartitioning {

    public int minCut(String s) {
        int N = s.length();
        // First off, a string can always be partitioned to give all palindromes in n-1 ways,
        // i.e., treat every single char as partition.
        // This problem adheres to front partition cuz string doesn't shrink and the front remains fixed.
        // Front Partition: You fix the start at index 0 and iterate over possible end indices for the
        // first substring (i = 0..N-1).

        return minCutHelper(0, s) - 1;
    }

    private int minCutHelper(int i, String s) {
        // Minimum number of cuts required for the substring s[i..N-1]
        // assuming nothing about what comes before i.
        int N = s.length();
        if (i == N) return 0; // We partitioned the entire string successfully

        // Process the partitions
        int minCuts = Integer.MAX_VALUE;
        StringBuilder temp = new StringBuilder();
        for (int k = i; k <= N - 1; k++) {
            temp.append(s.charAt(k));
            // If only temp is palindrome till now, we process the rest of the string
            if (isPalindrome(temp.toString()))
                minCuts = Math.min(minCuts, 1 + minCutHelper(k + 1, s));
        }
        return minCuts;
    }

    private boolean isPalindrome(String string) {
        int N = string.length();
        int left = 0;
        int right = N - 1;
        while (left < right) {
            if (string.charAt(left) != string.charAt(right))
                return false;

            left++;
            right--;
        }
        return true;
    }
}
