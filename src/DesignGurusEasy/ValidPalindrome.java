package DesignGurusEasy;

import java.util.ArrayList;
import java.util.List;

public class ValidPalindrome {

    public static boolean isPalindromeOptimal(String s) {
        char[] sArr = s.toCharArray();
        int rear = s.length() - 1;
        for (int i = 0; i < rear; i++) {
            // Move the pointer until we get to an alphanumeric
            if (Character.isLetterOrDigit(sArr[i])) {
                // Move the rear pointer till we get an alphanumeric
                while (i < rear && !Character.isLetterOrDigit(sArr[rear])) {
                    rear--;
                }
                if (Character.toLowerCase(sArr[i]) != Character.toLowerCase(sArr[rear]))
                    return false;
                rear--;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String s) {
        // TODO: Write your code here
        List<Character> filteredStrArr = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                filteredStrArr.add(Character.toLowerCase(c));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : filteredStrArr) {
            stringBuilder.append(c);
        }
        System.out.println("Filtered String: " + stringBuilder);

        int rear = filteredStrArr.size() - 1;
        for (int i = 0; i < rear; i++) {
            if (filteredStrArr.get(i) != filteredStrArr.get(rear--))
                return false;
        }
        return true;
    }

    public static void main(String args[]) {
//        String s = "Was it a car or a cat I saw?";
//        String s = "Not a palindrome";
        String s = "A man, a plan, a canal, Panama!";
        System.out.println("Is Palindrome? " + isPalindromeOptimal(s));
    }

}
