package Strings.Medium;

import java.util.HashMap;

public class LongestSubStringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int N = s.length();

        // Edge case: empty string
        if (N == 0) return 0;

        int maxLen = 1;

        // If I already know a substring is valid, why would I restart from the scratch?

        // Using the sliding window tech
        int leftPtr = 0;
        int rightPtr = 0;

        // Map to store the last seen index of each character
        HashMap<Character, Integer> freqMap = new HashMap<>();

        while (rightPtr < N) {
            Character ch = s.charAt(rightPtr);

            // If current character was seen before AND is inside the current window
            // Trim the window
            if (freqMap.containsKey(ch) && leftPtr <= freqMap.get(ch)) {

                // Move left pointer just after the last occurrence of dupe
                // This ensures the window remains valid (no duplicates)
                leftPtr = freqMap.get(ch) + 1;
            }

            // Update the last seen index of current character
            freqMap.put(ch, rightPtr);

            // Current window size = right - left + 1
            int len = rightPtr - leftPtr + 1;

            maxLen = Integer.max(maxLen, len); // Important, update maxLen on every iteration
            
            rightPtr++;
        }

        return maxLen;
    }

    public static int lengthOfLongestSubstringBruteForce(String s) {
        int N = s.length();
        int maxLen = 0;

        // Generate all the possible substrings and keep updating the maxLen dynamically
        for (int i = 0; i < N; i++) {
            // Need a hash array to track the count
            int[] hashArr = new int[256]; // to track all 256 ASCII chars
            int runningLen = 0;
            for (int j = i; j < N; j++) {
                char ch = s.charAt(j);
                // If the curr char have existed before, there's no point in traversing forward, break
                if (hashArr[ch] == 1) break;

                // Update the frq in hash array
                hashArr[ch] = 1;
                runningLen += 1;
            }
            // Update the max count
            maxLen = Integer.max(maxLen, runningLen);
        }

        return maxLen;
    }

}
