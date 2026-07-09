package TwoPointers;

public class ValidPalindrome {

    public boolean isPalindromeOptimal(String s) {
        // Preprocess the string to retain only alphanumeric
        // character and then use two pointers to determine if
        // it's a palindrome.

        int leftPtr = 0;
        int rightPtr = s.length() - 1;

        while (leftPtr < rightPtr) {
            // If char is not alphanumeric skip
            char leftCh = s.charAt(leftPtr);
            char rightCh = s.charAt(rightPtr);
            if (!Character.isLetterOrDigit(leftCh)) {
                leftPtr++;
                continue;
            }
            if (!Character.isLetterOrDigit(rightCh)) {
                rightPtr--;
                continue;
            }

            if (Character.toLowerCase(leftCh) != Character.toLowerCase(rightCh))
                return false;
            leftPtr++;
            rightPtr--;
        }

        return true;
    }

    public boolean isPalindrome(String s) {
        // Preprocess the string to retain only alphanumeric
        // character and then use two pointers to determine if
        // it's a palindrome

        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch))
                sb.append(Character.toLowerCase(ch));
        }

        int leftPtr = 0;
        int rightPtr = sb.length() - 1;

        while (leftPtr < rightPtr) {
            if (sb.charAt(leftPtr) != sb.charAt(rightPtr))
                return false;
            leftPtr++;
            rightPtr--;
        }

        return true;

        // Time: O(N)
        // Space: O(N)
    }

}
