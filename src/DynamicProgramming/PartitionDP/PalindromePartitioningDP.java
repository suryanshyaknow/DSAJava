package DynamicProgramming.PartitionDP;

import java.util.Arrays;

public class PalindromePartitioningDP {

    public int minCutTabulate(String s) {
        int N = s.length();
        int[] dp = new int[N + 1];

        for (int i = N; i >= 0; i--) {
            if (i == N) {
                dp[i] = 0;
                continue;
            }
            // Process the partitions
            int minCuts = Integer.MAX_VALUE;
            for (int k = i; k <= N - 1; k++) {
                if (isPalindrome(i, k, s))
                    minCuts = Math.min(minCuts, 1 + dp[k + 1]);
            }
            dp[i] = minCuts;
        }
        return dp[0] - 1; // -1 for the partition at the end

        // Time complexity: O(N^2)
        // Space complexity: O(N) + O(N)
    }

    public int minCutMemoize(String s) {
        int N = s.length();
        // First off, a string can always be partitioned to give all palindromes in n-1 ways,
        // i.e., treat every single char as partition.
        // This problem adheres to front partition cuz string doesn't shrink and the front remains fixed.
        // Front Partition: You fix the start at index 0 and iterate over possible end indices for the
        // first substring (i = 0..N-1).

        int dp[] = new int[N];
        Arrays.fill(dp, -1);
        return minCutHelper(0, s, dp) - 1;

        // Time complexity: O(N^2)
        // Space complexity: O(N) + O(N)
    }

    private int minCutHelper(int i, String s, int[] dp) {
        // Minimum number of cuts required for the substring s[i..N-1]
        // assuming nothing about what comes before i.
        int N = s.length();
        if (i == N) return 0; // We partitioned the entire string successfully

        if (dp[i] != -1) return dp[i];

        // Process the partitions
        int minCuts = Integer.MAX_VALUE;
        StringBuilder temp = new StringBuilder();
        for (int k = i; k <= N - 1; k++) {
            temp.append(s.charAt(k));
            // If only temp is palindrome till now, we process the rest of the string
            if (isPalindrome(temp.toString()))
                minCuts = Math.min(minCuts, 1 + minCutHelper(k + 1, s, dp));
        }
        return dp[i] = minCuts;
    }

    private boolean isPalindrome(int left, int right, String string) {
        while (left < right) {
            if (string.charAt(left) != string.charAt(right))
                return false;

            left++;
            right--;
        }
        return true;
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
