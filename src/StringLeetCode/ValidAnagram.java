package StringLeetCode;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        int S = s.length();
        int T = t.length();
        if (S != T)
            return false;

        // Create a hashmap to count the occurrences of each char in s
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < S; i++) {
            Character ch = s.charAt(i);
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }

        // Now verify the occurrences of each ch from t
        for (int i = 0; i < T; i++) {
            Character ch = t.charAt(i);
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) - 1);

            // Can bail early while subtracting if a char count goes below 0 — that means t has more of some char than s, so not an anagram:
            if (hashMap.get(ch) < 0) return false;
        }

        // Now each key should have zero val if t is an anagram
        for (Character ch : hashMap.keySet()) {
            int val = hashMap.getOrDefault(ch, 0);
            if (val != 0)
                return false;
        }

        return true;
    }
}
