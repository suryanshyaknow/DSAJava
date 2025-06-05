package Recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        genPartitions(0, s, new ArrayList<>(), res);
        return res;
    }

    void genPartitions(int idx, String s, List<String> partition, List<List<String>> res) {
        // Base case
        if (idx == s.length()) {
            res.add(new ArrayList<>(partition));
            return;
        }

        for (int i=idx; i < s.length(); i++) {
            if (isPalindrome(s, idx, i)) {
                partition.add(s.substring(idx, i+1));
                genPartitions(i+1, s, partition, res);
                partition.removeLast();
            }
        }

    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start <= end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}
