package StringLeetCode;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        int N = strs.length;
        StringBuilder res = new StringBuilder();

        // Pick one string as reference and read char by char for others
        for (int i = 0; i < strs[0].length(); i++) {
            Character ch = strs[0].charAt(i);
            for (int j = 1; j < N; j++) {
                if (i > strs[j].length() || strs[j].charAt(i) != ch) {
                    return res.toString();
                }
            }
            res.append(ch);
        }

        return res.toString();
    }
}
