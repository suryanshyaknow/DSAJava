package DynamicProgramming.DPOnStrings;

public class DeleteOperationsForTwoStrings {

    public int minDistance(String word1, String word2) {
        // This one could be approached w two different approaches
        // Approach 1: Compute the LCS, and remaining chars in both are what we need to delete, pretty fks simple.
        // Approach 2: String matching at each character w couple choices, almost a simplified variant of Edit Distance.

        int N1 = word1.length();
        int N2 = word2.length();

//        int lcs = minDistanceHelper1(N1 - 1, N2 - 1, word1, word2);
//        return N1 - lcs + N2 - lcs;
        return minDistanceHelper2(N1 - 1, N2 - 1, word1, word2);
    }

    private int minDistanceHelper1(int i, int j, String s, String t) {
        // Base case
        // If any of the idx falls beyond zero, then there's no sense in continuing
        if (i < 0 || j < 0) return 0;

        // If the chars do match
        if (s.charAt(i) == t.charAt(j)) return 1 + minDistanceHelper1(i - 1, j - 1, s, t);

        // If they fkn don't
        return Math.max(minDistanceHelper1(i - 1, j, s, t), minDistanceHelper1(i, j - 1, s, t));
    }

    private int minDistanceHelper2(int i, int j, String s, String t) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;

        // If the chars do match, shrink both strings
        if (s.charAt(i) == t.charAt(j))
            return minDistanceHelper2(i - 1, j - 1, s, t);

        // If they don't
        int delFromS = 1 + minDistanceHelper2(i - 1, j, s, t);
        int delFromT = 1 + minDistanceHelper2(i, j - 1, s, t);
        return Math.min(delFromS, delFromT);
    }
}
