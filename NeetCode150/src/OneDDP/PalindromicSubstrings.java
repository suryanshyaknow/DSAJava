package OneDDP;

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int N = s.length();

        // To have number of palindromic substrings, we can
        // generate all sub arrays (O(N^2) and then see for
        // each whether one is palindrome or not -- another
        // O(N).
        // O(N ^ 3) ~ not at all efficient.
        //
        // Or rather what we can do is for every char we can
        // expand its left and right and then determine whether
        // the selected range of chars is a palindrome.
        // Expand around centre.

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            // Odd length palindromes
            // Expand around i
            cnt += countPal(i, i, s);

            // Even length palindromes
            cnt += countPal(i, i+1, s);
        }
        return cnt;

        // Time: O(N^2)
    }

    private int countPal(int l, int r, String s) {
        int N = s.length();
        int cnt = 0;

        // Expand around i
        while (l >= 0 && r < N && s.charAt(l) == s.charAt(r)) {
            cnt += 1;
            l -= 1;
            r += 1;
        }
        return cnt;
    }
}
