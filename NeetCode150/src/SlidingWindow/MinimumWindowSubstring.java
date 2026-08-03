package SlidingWindow;

import java.util.*;

public class MinimumWindowSubstring {

    public String minWindowOptimal(String s, String t) {
        // Intuition: So the idea is to find a valid window/ans,
        // store it as a potential answer, and then shrink the
        // window till the window is no longer valid to find even
        // a better and, and then again find a potential ans.
        // And so on..

        int N = s.length();
        int T = t.length();

        // Map out frequencies of string t in a hash map
        HashMap<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < T; i++)
            mapT.put(
                    t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        HashMap<Character, Integer> mapWin = new HashMap<>();

        // Now in here instead of equating the frequncies of both
        // string t and curr window, we're gonna maintian two
        // variables to count the number of matches rather.
        // We'll update the match for current window
        // based on char's freq on each iteration.
        int need = mapT.size();
        int have = 0;

        String res = "";
        int resLen = Integer.MAX_VALUE;

        int leftPtr = 0;
        int rightPtr = 0;
        while (rightPtr < N) {
            // Update the freq in window freq map
            char ch = s.charAt(rightPtr);
            if (mapT.containsKey(ch)) {
                int freq = mapWin.getOrDefault(ch, 0);
                mapWin.put(ch, freq + 1);

                // Now how does 'have' change
                int needFreq = mapT.get(ch);
                // have changes if now they becomes equal
                if (freq + 1 == needFreq) have += 1;
            }

            // Now, if have == need, we have potential ans
            while (have == need) {
                int len = rightPtr - leftPtr + 1;
                if (len < resLen) {
                    resLen = len;
                    res = s.substring(leftPtr, rightPtr + 1);
                }

                // Shrink the window till it no longer remains valid
                char leftCh = s.charAt(leftPtr);
                if (mapT.containsKey(leftCh)) {
                    int leftChFreq = mapWin.getOrDefault(leftCh, 0);
                    mapWin.put(
                            leftCh, leftChFreq != 0 ? leftChFreq - 1 : leftChFreq);

                    // Update the have
                    // If the curr leftChFreq is greater than equal to need, have doesnt change
                    int needLeftChFreq = mapT.get(leftCh);
                    if (leftChFreq == needLeftChFreq)
                        have -= 1;
                }
                leftPtr++;
            }

            rightPtr++;
        }
        return res;

        // Time: O(N) cuz we atmost tocuhing an ele twice once while
        // expanding the window and then shrinking it.
    }


    public String minWindow(String s, String t) {
        // Intuition: So the idea is to find a valid window/ans,
        // store it as a potential answer, and then shrink the
        // window till the window is no longer valid to find even
        // a better and, and then again find a potential ans.
        // And so on..

        int N = s.length();
        int T = t.length();

        HashMap<Character, Integer> mapT = new HashMap<>();
        HashMap<Character, Integer> mapWin = new HashMap<>();
        // Fill mapT w frequncies of string t
        for (int i = 0; i < T; i++) {
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        }

        String res = "";
        int resLen = Integer.MAX_VALUE;
        int leftPtr = 0;
        int rightPtr = 0;
        while (rightPtr < N) {
            // Update the window freq map
            char ch = s.charAt(rightPtr);
            if (mapT.containsKey(ch)) // Make sure to only persist required chars
                mapWin.put(ch, mapWin.getOrDefault(ch, 0) + 1);

            // If frequencies of string t and windows are equal,
            // update the potential ans.
            // Now, keep shrinking the window from left to find
            // even a better ans
            while (isFreqMatch(mapT, mapWin)) {
                // First off, update the ans
                int len = rightPtr - leftPtr + 1;
                if (len < resLen) {
                    resLen = len;
                    res = s.substring(leftPtr, rightPtr + 1);
                }

                // Shrink the window from left
                char leftCh = s.charAt(leftPtr);
                if (mapT.containsKey(leftCh))
                    mapWin.put(leftCh, mapWin.get(leftCh) - 1);
                leftPtr++;
            }

            rightPtr++;
        }

        return res;

        // Time: O(N * T)
    }

    private static boolean isFreqMatch(
            HashMap<Character, Integer> mapT, HashMap<Character, Integer> mapWin) {
        if (mapT.size() != mapWin.size()) return false;

        for (Map.Entry<Character, Integer> entry : mapT.entrySet()) {
            char ch = entry.getKey();
            int need = entry.getValue();

            if (mapWin.getOrDefault(ch, 0) < need)
                return false;
        }
        return true;
    }

}
