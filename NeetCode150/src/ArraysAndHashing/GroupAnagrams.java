package ArraysAndHashing;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        // The idea is pretty straight forward.
        // We just have to group the strings against a common
        // factor -- sorted version of the strings OR simply
        // frequency char count of each str.

        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            // Use the sorted variant of this string as a key
            String keyStr = sortStr(str);

            List<String> anagrams = hashMap.getOrDefault(keyStr, new ArrayList<String>());
            anagrams.add(str);
            hashMap.put(keyStr, anagrams);
        }

        // List<List<String>> res = new ArrayList<>();
        // for (List<String> anagrams : hashMap.values()) {
        //     res.add(anagrams);
        // }

        return new ArrayList<>(hashMap.values());

        // Time: O(N * K log k) where k being the avg length of the strings in strs
        // Space: O(N)
    }

    private String sortStr(String str) {
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }

}
