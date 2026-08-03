package SlidingWindow;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacementOptimal(String s, int k) {
        int N = s.length();

        // Intuition: So the idea is pretty simple if you think about it.
        // You have to employ hashmap anywhichway to keep the count of each
        // character. Now just think about it -- pick a window and keep
        // updating the maxLen dynamically till it satsfies the k constraint.

        // So maintain a sliding window and character frqeuencies. Keep expanding
        // it while the number of characters we'd need to replace stays within k.
        // Otherwise, shrink the window from left. And keep updating the maximum
        // length.
        // To compute a potential ans the given window should accomodate no more
        // than k replacements, i.e., windowSize - mostFreqChar <= k
        // If not the case, shrink the window size.

        int[] freq = new int[26];
        int maxLen = 0;

        int leftPtr = 0;
        int rightPtr = 0;
        int maxFreq = 0;

        while (rightPtr < N) {
            freq[s.charAt(rightPtr) - 'A']++;
            maxFreq = Math.max(freq[s.charAt(rightPtr) - 'A'], maxFreq);

            // Ditch the wrong window by shrinking it
            while ((rightPtr - leftPtr + 1) - maxFreq > k) {
                // Decrement the removed char's freq
                freq[s.charAt(leftPtr) - 'A'] -= 1;

                // And shrink the window
                leftPtr++;

                // Update the max freq as well
                // maxFreq = getMostFrequentCharFreq(freq);
            }

            // Update the maxLen
            maxLen = Math.max(maxLen, rightPtr - leftPtr + 1);
            rightPtr++;
        }
        return maxLen;
    }

    public int characterReplacement(String s, int k) {
        int N = s.length();

        // Intuition: So the idea is pretty simple if you think about it.
        // You have to employ hashmap anywhichway to keep the count of each
        // character. Now just think about it -- pick a window and keep
        // updating the maxLen dynamically till it satsfies the k constraint.

        // So maintain a sliding window and character frqeuencies. Keep expanding
        // it while the number of characters we'd need to replace stays within k.
        // Otherwise, shrink the window from left. And keep updating the maximum
        // length.
        // To compute a potential ans the given window should accomodate no more
        // than k replacements, i.e., windowSize - mostFreqChar <= k
        // If not the case, shrink the window size.

        int[] freq = new int[26];
        int maxLen = 0;

        int leftPtr = 0;
        int rightPtr = 0;

        while (rightPtr < N) {
            freq[s.charAt(rightPtr) - 'A']++;

            int maxFreq = getMostFrequentCharFreq(freq);

            // Ditch the wrong window by shrinking it
            while ((rightPtr - leftPtr + 1) - maxFreq > k) {
                // Decrement the removed char's freq
                freq[s.charAt(leftPtr) - 'A'] -= 1;

                // And shrink the window
                leftPtr++;

//                // Update the max freq as well
//                maxFreq = getMostFrequentCharFreq(freq);
                /**
                 * NOTE: We keep maxFreq stale because it may make us under-shrink the window,
                 * but it can never make us over-shrink it—and since we're only after the maximum length,
                 * that's safe.
                 */
            }

            // Update the maxLen
            maxLen = Math.max(maxLen, (rightPtr - leftPtr + 1));

            rightPtr++;
        }
        return maxLen;
    }

    private static int getMostFrequentCharFreq(int[] freq) {
        int maxFreq = 0;
        for (int i = 0; i < 26; i++) {
            maxFreq = Math.max(maxFreq, freq[i]);
        }
        return maxFreq;
    }

}
