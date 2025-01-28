package DesignGurusEasy;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static boolean isAnagramFancy(String s, String t) {
        // Early exit if their lengths ain't the same
        if (s.length() != t.length())
            return false;

        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // Increment the freq of the character in string s
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
            // Decrement the freq of the character in string t
            freqMap.put(t.charAt(i), freqMap.getOrDefault(t.charAt(i), 0) - 1);
        }

        // Check if all frequencies are zeroes
        for (char c : freqMap.keySet()) {
            if (!freqMap.get(c).equals(0))
                return false;
        }
        return true;
    }


    public static boolean isAnagram(String s, String t) {
        // TODO: Write your code here
        // Early exit if their lengths ain't same
        if (s.length() != t.length())
            return false;

        // Construct hash maps for both s and t with their occurrences
        Map<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        // Now compare the occurrences
        for (Character c : sMap.keySet()) {
            if (!sMap.get(c).equals(tMap.getOrDefault(c, 0)))
                return false;
        }
        return true;
    }

    public static void main(String[] arr) {
        String s1 = "hello";
        String t1 = "world";
        String s2 = "listen";
        String t2 = "silent";

        System.out.println(isAnagram(s1, t1));
        System.out.println(isAnagramFancy(s1, t1));
        System.out.println();

        System.out.println(isAnagram(s2, t2));
        System.out.println(isAnagramFancy(s2, t2));
        System.out.println();

    }

}
