package DynamicProgramming.DPOnStrings;

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int N1 = s.length();
        int N2 = t.length();

        return numDistinctHelper(N1 - 1, N2 - 1, s, t);
    }

    private static int numDistinctHelper(int i, int j, String s, String t) {
        // Base cases
        if (j < 0) return 1; // If t ran out implying the subsequence has been recorded
        if (i < 0) return 0; // If i ran out w/o the match

        // If the chars do match: Pick the char, and still not pick the character
        // despite the match.
        if (s.charAt(i) == t.charAt(j))
            return numDistinctHelper(i - 1, j - 1, s, t) + numDistinctHelper(i - 1, j, s, t);

        // No match
        return numDistinctHelper(i - 1, j, s, t);
    }
}
