package DesignGurusEasy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Anagram {

    public static boolean isAnagramOptimal(String s, String t) {
        // TODO: Write your code here
        if (s.length() != t.length())
            return false;

        // Create a hash map to store the occurrences of each character in both strings 's' and 't'
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // Increment the frequency for the chars of 's'
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            // Decrement the frequency for the chars of 's'
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        // Check if the frequency of each char in the map is 0
        for (char c : map.keySet()) {
            if (map.get(c) != 0)
                return false;
        }
        // If all characters have a frequency of 0, this means that 't' is an anagram of 's'.
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        // TODO: Write your code here
        if (s.length() != t.length())
            return false;

        // Create a map to store the occurrences of each character in string 's'
        Map<Character, Integer> mapS = new HashMap<>();
        for (char c : s.toCharArray()) {
            mapS.put(c, mapS.getOrDefault(c, 0) + 1);
        }

        // Create a map to store the occurrences of each character in string 't'
        Map<Character, Integer> mapT = new HashMap<>();
        for (char c : t.toCharArray()) {
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        }

        // Iterate over 't' to match the occurrences with chars in 's'
        for (Map.Entry<Character, Integer> entry : mapT.entrySet()) {
            if (!Objects.equals(entry.getValue(), mapS.getOrDefault(entry.getKey(), 0)))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "hello";
        String t = "holla";
        System.out.println("Is t an anagram of s: " + isAnagramOptimal(s, t));
    }

}
