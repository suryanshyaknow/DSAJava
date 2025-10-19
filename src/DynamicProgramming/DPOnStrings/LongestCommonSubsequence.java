package DynamicProgramming.DPOnStrings;

public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int N1 = text1.length();
        int N2 = text2.length();

        return helper(N1 - 1, N2 - 1, text1, text2);

        // Time complexity: 2^N1 * 2^N2 ~ exponential
        // Space complexity: O(N1 + N2), recursion call stack.
        // In the worst case (when no characters match), recursion can go as deep as N1 + N2.

        // When we say “recursion call stack”, it’s basically the memory your program uses to
        // keep track of function calls that haven’t finished yet.
    }

    private int helper(int i1, int i2, String s1, String s2) {
        // Base case
        // If any of the idx falls beyond zero, then there's no sense in continuing
        if (i1 < 0 || i2 < 0) return 0;

        // If the curr characters are a match
        if (s1.charAt(i1) == s2.charAt(i2))
            return 1 + helper(i1 - 1, i2 - 1, s1, s2);

        // If not we wanna try all combinations
        // Move idx 1, idx 2 stays still
        int mov1 = helper(i1 - 1, i2, s1, s2);
        // Move idx 2, idx 1 stays still
        int mov2 = helper(i1, i2 - 1, s1, s2);
        return Integer.max(mov1, mov2);
    }

}
