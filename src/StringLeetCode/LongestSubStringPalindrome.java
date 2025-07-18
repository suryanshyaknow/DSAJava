package StringLeetCode;

public class LongestSubStringPalindrome {

    public String longestPalindrome(String s) {
        int N = s.length();
        // Big idea: Treat every ele as the centre of the palindrome and extend both ends.

        String res = "";
        int maxLen = 0;

        for (int i = 0; i < N; i++) {
            // Odd length palindrome
            int leftPtr = i, rightPtr = i;
            while (leftPtr >= 0 && rightPtr < N && s.charAt(leftPtr) == s.charAt(rightPtr)) {
                if (rightPtr - leftPtr + 1 > maxLen) {
                    res = s.substring(leftPtr, rightPtr + 1);
                }
                maxLen = Integer.max(maxLen, res.length());

                leftPtr -= 1;
                rightPtr += 1;
            }

            // Even length palindrome
            leftPtr = i;
            rightPtr = i + 1;
            while (leftPtr >= 0 && rightPtr < N && s.charAt(leftPtr) == s.charAt(rightPtr)) {
                if (rightPtr - leftPtr + 1 > maxLen) {
                    res = s.substring(leftPtr, rightPtr + 1);
                }
                maxLen = Integer.max(maxLen, res.length());

                leftPtr -= 1;
                rightPtr += 1;
            }
        }

        return res;
    }

}
