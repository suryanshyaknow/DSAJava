package StringLeetCode;

public class ShortestPalindrome {

    public String shortestPalindromeRabinKarp(String s) {
        int N = s.length();

        int prefix = 0;
        int suffix = 0;
        int base = 29; // Coulda taken 26 but 29 is a lil bit bigger and prime and thereby would help in preventing collisions
        int lastIdx = -1; // From where we have pick the rem part and put it at front reversed

        int mod = 1_000_000_007;
        int power = 1;

        for (int i = 0; i < N; i++) {
            int val = s.charAt(i) - 'a' + 1;    // map a-z to 1-26

            prefix = (prefix * base + val) % mod;

            suffix = (suffix + val * power) % mod; // Say the current number is 3 and is at hundredth place, we'd do 3 * 100 + prev val where 100 being 10^2
            power = (power * base) % mod;

            if (prefix == suffix)
                lastIdx = i;
        }

        String remStr = s.substring(lastIdx + 1, N);
        return new StringBuilder(remStr).reverse() + s;
    }

    public String shortestPalindrome(String s) {
        int N = s.length();

        String rem = "";
        // Check for the longest prefixed palindrome
        // Starting from the end cuz we're to append the words at the front which implies
        // chars at the end won't be left out to be paired w for the str to be a palindrome.
        for (int i = N - 1; i >= 0; i--) {
            if (isPalindrome(s, 0, i)) {
                rem = s.substring(i + 1, N);
                break;
            }
        } // O(N^2)

        // Append to the start
        if (!rem.equals(""))
            return new StringBuilder(rem).reverse().toString() + s;
        else
            return s;
    }

    private static Boolean isPalindrome(String s, int leftPtr, int rightPtr) {
        while (leftPtr < rightPtr) {
            if (s.charAt(leftPtr) != s.charAt(rightPtr))
                return false;
            leftPtr++;
            rightPtr--;
        }
        return true;
    }

}
