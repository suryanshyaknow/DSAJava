package DynamicProgramming.DPOnStrings;

public class EditDistance {

    public int minDistance(String s, String t) {
        // We basically have to convert str s into t,
        // and we've got practically three choices to accomplish that.
        // Replacing a matching char, inserting a matching char, and deleting a char.

        int N1 = s.length();
        int N2 = t.length();

        return minDistanceHelper(N1 - 1, N2 - 1, s, t);
    }

    private int minDistanceHelper(int i, int j, String s, String t) {
        // Base case
        // If t runs out, meaning there are still characters left in 's' to match w empty string 't'.
        if (j < 0) return i + 1; // delete left chars in i

        // If s runs out, meaning i is an empty string, we've gotta insert that many chars in it
        if (i < 0) return j + 1; // insert remaining chars in i


        // It all practically boils down to string matching
        // If the chars match
        if (s.charAt(i) == t.charAt(j))
            return 0 + minDistanceHelper(i - 1, j - 1, s, t); // Since the chars matched, shrink both strings

        // If they don't, you exercise your options
        // Insert a matching char
        int insert = 1 + minDistanceHelper(i, j - 1, s, t);

        // Replace 'i' w a matching char
        int replace = 1 + minDistanceHelper(i - 1, j - 1, s, t);

        // Delete a char
        int delete = 1 + minDistanceHelper(i - 1, j, s, t);

        return Math.min(insert, Math.min(replace, delete));
    }

}
