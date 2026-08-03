package SlidingWindow;

import java.util.*;

public class PermutationInString {

    public boolean checkInclusionOptimal(String s1, String s2) {
        int N1 = s1.length();
        int N2 = s2.length();

        if (N1 > N2)
            return false;

        // Instead of comparing the two frequency arrays on every window shift,
        // we're only going to account for the two characters whose frequencies
        // actually change.
        //
        // So beforehand, we track how many of the 26 characters have matching
        // frequencies between s1 and the current window. As the window slides,
        // only the outgoing and incoming characters can affect this count.
        //
        // If matches == 26, all character frequencies match → permutation found.

        int[] hash1 = new int[26];
        int[] hash2 = new int[26];

        // Precomopute hash1 and hash2 as well but for very first window
        for (int i = 0; i < N1; i++) {
            hash1[s1.charAt(i) - 'a']++;
            hash2[s2.charAt(i) - 'a']++;
        }

        // Count the number of frequencies match
        int match = 0;
        for (int i = 0; i < 26; i++) {
            if (hash1[i] == hash2[i])
                match += 1;
        }

        int leftPtr = 0;
        int rightPtr = N1 - 1;

        while (rightPtr < N2) {
            if (match == 26) return true;

            // Outgoing char
            int idx1 = s2.charAt(leftPtr) - 'a';
            hash2[idx1]--;
            leftPtr++;
            // Update the match
            // You only get to decrement the match
            // if you're sure they were equal before
            if (hash1[idx1] == hash2[idx1]) match += 1;
            else if (hash1[idx1] == hash2[idx1] + 1) match -= 1;

            // Incoming char
            rightPtr++;
            if (rightPtr < N2) {
                int idx2 = s2.charAt(rightPtr) - 'a';
                hash2[idx2]++;
                // Update the match
                if (hash1[idx2] == hash2[idx2]) match += 1;
                else if (hash1[idx2] + 1 ==  hash2[idx2]) match -= 1;
            }
        }

        return false;

        // Time: O(26) + O(N)
        // Unlike before: O(26.N)
    }

    public boolean checkInclusion(String s1, String s2) {
        int N1 = s1.length();
        int N2 = s2.length();

        if (N1 > N2)
            return false;

        // Prep a reference hash table for s1
        int[] hash1 = new int[26];
        for (int i = 0; i < N1; i++) {
            hash1[s1.charAt(i) - 'a']++;
        }

        int leftPtr = 0;
        int rightPtr = N1 - 1;

        int[] hash2 = new int[26];
        // Iterate the first window
        for (int i = leftPtr; i <= rightPtr; i++) {
            hash2[s2.charAt(i) - 'a']++;
        }

        while (rightPtr < N2) {
            // See if current window is a match
            if (Arrays.equals(hash1, hash2))
                return true;

            // Else move the window
            // Remove the outdated char
            hash2[s2.charAt(leftPtr) - 'a']--;
            leftPtr++;

            // Add the incoming char
            rightPtr++;
            if (rightPtr < N2)
                hash2[s2.charAt(rightPtr) - 'a']++;
        }

        return false;
    }

}
