package SlidingWindow;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int N = s.length();

        // The idea is pretty simple. Maintain a window
        // of unique characters and everytime you encounter
        // a dupe, shrink the window.
        // And keep updating the max len dynamically.

        int leftPtr = 0;
        int rightPtr = 0;

        // To achieve this how about we maintain a hash map
        // of unique characters and their last seen indices
        // so that at the time of dupe interval we could
        // directly jump to the char next to the dupe?

        HashMap<Character, Integer> hashMap = new HashMap<>();

        // Sliding window
        int maxLen = 0; // can encounter empty strings
        while (rightPtr < N) {
            char ch = s.charAt(rightPtr);

            // If it's a dupe AND is within the current window, chop off the front
            int lastSeenIdx = hashMap.getOrDefault(ch, -1);
            if (lastSeenIdx != -1 && lastSeenIdx >= leftPtr) {
                // Jump straight to one char after the last seen dupe
                leftPtr = lastSeenIdx + 1;
            }

            // Compute and update the length
            int len = rightPtr - leftPtr + 1;
            maxLen = Math.max(len, maxLen);

            // Store the value along w its idx in hashmap
            hashMap.put(ch, rightPtr);

            // Move the pointer
            rightPtr++;
        }

        return maxLen;
    }

}