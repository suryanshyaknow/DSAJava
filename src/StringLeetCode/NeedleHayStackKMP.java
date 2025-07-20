package StringLeetCode;

public class NeedleHayStackKMP {

    public int strStrKMP(String haystack, String needle) {
        if (needle == "") return 0;
        int N = haystack.length();
        int M = needle.length();

        // Formulate the LPS array
        // LPS = longest prefix which is also a suffix
        // Meaning If I’ve matched the first i characters of the pattern, and a mismatch just happened,
        // what’s the longest part of that matched prefix that also exists at the end of what I’ve matched — so I can reuse it?
        // Explanation: We still face the mismatch, but we don’t face it like a dumbass — we face it with memory, pattern, and precision.
        int[] lps = new int[M];
        lps[0] = 0;
        int prevLPS = 0, i = 1; // Pointers to keep track of the LPS
        while (i < M) {
            if (needle.charAt(i) == needle.charAt(prevLPS)) {
                lps[i] = prevLPS + 1;
                prevLPS++;
                i++;
            } else {
                if (prevLPS == 0) {
                    lps[i] = 0;
                    i++;
                } else
                    prevLPS = lps[prevLPS - 1]; // We had a prefix of length prevLPS. Let's now see what was the LPS of that prefix itself — maybe we can retry from there.
            }
        }

        // Second part: Finding the actual match
        i = 0; // Ptr for haystack
        int j = 0; // Ptr for needle
        while (i < N) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i += 1;
                } else {
                    j = lps[j - 1];
                }
            }
            if (j == M) return i - M;
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        int N = haystack.length();
        int M = needle.length();

        if (M == 0)
            return 0;

        // Treat each char as start of the needle
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j < M; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j))
                    break;

                if (j == M - 1)
                    return i;
            }
        }
        return -1;
    }
}
