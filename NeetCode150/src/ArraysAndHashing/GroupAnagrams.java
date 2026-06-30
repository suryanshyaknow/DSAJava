package ArraysAndHashing;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagramsOptimal(String[] strs) {
        int N = strs.length;

        // The idea is pretty straight forward.
        // We just have to group the strings against a common
        // factor -- sorted version of the strings OR simply
        // frequency char count of each str.

        // Now, see sorting the string is working perfectly fine
        // but it's introducing an additional overhead of sorting.
        // Let's see if we could generate unique keys for each group.

        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            // Build freq arr and convert it into unique string key
            String keyStr = getFreqArrKey(str);

            // See if key already exists
            hashMap.computeIfAbsent(keyStr, k -> new ArrayList<>()).add(str);
        }

        // Time: O(N * k) where k being the avg length of the strings in strs
        // Space: O(N)

        return new ArrayList<>(hashMap.values());
    }

    private String getFreqArrKey(String str) {
        // Compute the freq array
        int[] freqArr = new int[26];
        for (char ch : str.toCharArray()) {
            freqArr[ch - 'a']++;
        } // O(k)

        // Now, convert the freq arr into unique string key
        StringBuilder sb = new StringBuilder();
        for (int freq : freqArr) {
            sb.append(freq).append('#');
        } // O(26)

        return sb.toString();
    }

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
