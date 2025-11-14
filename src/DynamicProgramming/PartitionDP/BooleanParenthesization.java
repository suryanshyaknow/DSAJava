package DynamicProgramming.PartitionDP;

public class BooleanParenthesization {

    static int countWays(String s) {
        // code here
        int N = s.length();

        return countWaysHelper(0, N - 1, 1, s); // Represents the number of ways to evaluate the given exp to true
    }

    private static int countWaysHelper(int i, int j, int isTrue, String s) {
        // Base cases
        if (i > j) return 0; // No partitions left
        if (i == j) {
            if (isTrue == 1) return s.charAt(i) == 'T' ? 1 : 0;
            else return s.charAt(i) == 'F' ? 1 : 0; // If we wanted false only
        }

        // Process the partitions
        int ways = 0;
        for (int k = i + 1; k <= j; k += 2) {
            int lTrue = countWaysHelper(i, k - 1, 1, s); // Number of ways for the left partition to be true
            int lFalse = countWaysHelper(i, k - 1, 0, s);
            int rTrue = countWaysHelper(k + 1, j, 1, s);
            int rFalse = countWaysHelper(k + 1, j, 0, s);

            if (s.charAt(k) == '&') {
                if (isTrue == 1) // Both true
                    ways += lTrue * rTrue;
                else // either false, or both false
                    ways += lFalse * rTrue + lTrue * rFalse + lFalse * rFalse;
            } else if (s.charAt(k) == '|') {
                if (isTrue == 1) // either true, or both true
                    ways += lTrue * rTrue + lTrue * rFalse + lFalse * rTrue;
                else // both false
                    ways += lFalse * rFalse;
            } else if (s.charAt(k) == '^') {
                if (isTrue == 1) // Both diff
                    ways += lTrue * rFalse + lFalse * rTrue;
                else // both same
                    ways += lTrue * rTrue + lFalse * rFalse;
            }
        }
        return ways;
    }


}
