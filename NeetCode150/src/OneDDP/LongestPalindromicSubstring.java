package OneDDP;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int N = s.length();
        if (N == 0) return "";

        // Intuition: If we go brute force way around this prob
        // then we have to first generate all subarray (O(N ^2))
        // and for every sub array generated, we had to check if
        // it's a palindrome -- another O(N) ~ O(N^3).
        // Not at all efficient.
        //
        // So instead we can be smart, and instead of generating
        // all the subarrays we can pick a char and treat it as the
        // center of the palindrome and expand left and right and
        // dynamically update the length of the palindrome.
        // Remember to take care of even length palis as well.

        // Iterate over each char and expand sideways
        String res = s.substring(0, 1); // single char is the smallest len pali
        for (int i = 0; i < N; i++) {
            // Treat i as centre and expand sideways
            int l = i;
            int r = i;
            // Ensure that l and r are in bounds
            while (l >= 0 && r < N && s.charAt(l) == s.charAt(r)) {
                int len = r - l + 1;
                if (len > res.length())
                    res = s.substring(l, r + 1);
                l -= 1;
                r += 1;
            }

            // Even length palis
            l = i;
            r = i + 1;
            while (l >= 0 && r < N && s.charAt(l) == s.charAt(r)) {
                int len = r - l + 1;
                if (len > res.length())
                    res = s.substring(l, r + 1);
                l -= 1;
                r += 1;
            }
        }

        return res;
        // Time: O(N^2)
    }
}
