package Strings.Medium;

import java.util.HashMap;

public class LongestSubStringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int N = s.length();
        int maxLen = 1;

        // Using the sliding window tech
        int leftPtr = 0;
        int rightPtr = 0;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        while (rightPtr < N) {
            Character ch = s.charAt(rightPtr);

            // But but.. then we gotta trim the window
            // and before that update the maxLen
            if (freqMap.containsKey(ch) && leftPtr <= freqMap.get(ch)) {

                // Reset the leftPtr to latest idx of the repeating character
                leftPtr = freqMap.get(ch) + 1;
            }

            // Anyways, gotta put the char in freq map and update its idx,
            // and move the rightPtr
            freqMap.put(ch, rightPtr);

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
