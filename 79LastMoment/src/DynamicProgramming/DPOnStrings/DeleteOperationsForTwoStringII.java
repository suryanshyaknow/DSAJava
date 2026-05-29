package DynamicProgramming.DPOnStrings;

public class DeleteOperationsForTwoStringII {

    public int minDistance(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        // The second approach would be to find the
        // longest common subsequence and delete the
        // rest of the chars in both the strings.

        int lcs = helper(N1 - 1, N2 - 1, word1, word2);
        return N1 + N2 - 2 * lcs;
    }

    private int helper(int idx1, int idx2, String str1, String str2) {
        // Edge cases
        if (idx1 < 0 || idx2 < 0) return 0;

        // Chars match
        if (str1.charAt(idx1) == str2.charAt(idx2))
            return 1 + helper(idx1 - 1, idx2 - 1, str1, str2);

        // Move either pointer
        return Math.max(helper(idx1, idx2 - 1, str1, str2), helper(idx1 - 1, idx2, str1, str2));
    }

}
