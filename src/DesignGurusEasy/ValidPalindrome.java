package DesignGurusEasy;

import java.util.ArrayList;
import java.util.List;

public class ValidPalindrome {

    public static boolean isPalindrome(String sentence) {
        // TODO: Write your code here
        // Construct an array free of everything other than lowercase letters
        List<Character> sentenceArr = new ArrayList<>();
        for (int i = 0; i < sentence.length(); i++) {
            if (Character.isLetterOrDigit(sentence.charAt(i)))
                sentenceArr.add(Character.toLowerCase(sentence.charAt(i)));
        }

        // Iterate the array using two pointers and determine if it's palindrome
        int frontPtr = 0;
        int rearPtr = sentenceArr.size() - 1;
        while (frontPtr < rearPtr) {
            if (sentenceArr.get(frontPtr) != sentenceArr.get(rearPtr)) {
                return false;
            }
            frontPtr++;
            rearPtr--;
        }
        return true;
        /*
            - Extra Space Complexity: You create a List<Character> to store the filtered version of the string.
            This costs O(n) space in addition to the input string

            - Performance Overhead:
                - You iterate over the string twice:
                    - First, to build sentenceArr.
                    - Second, to check for the palindrome.

            - Time complexity: O(n) for filtering + O(n/2) for checking = O(n) overall, but with extra processing overhead
         */
    }

    public static boolean isPalindromeFancy(String sentence) {
        int frontPtr = 0;
        int rearPtr = sentence.length() - 1;

        while (frontPtr < rearPtr) {
            // Increase the frontPtr till it encounters alpha or digit
            while (frontPtr < rearPtr && !Character.isLetterOrDigit(sentence.charAt(frontPtr))) {
                frontPtr++;
            }
            // Decrease the rearPtr till it encounters alpha or digit
            while (frontPtr < rearPtr && !Character.isLetterOrDigit(sentence.charAt(rearPtr))) {
                rearPtr--;
            }
            // Now check if it validates the condition for it to be a palindrome or early exit
            if (Character.toLowerCase(sentence.charAt(frontPtr++)) != Character.toLowerCase(sentence.charAt(rearPtr--)))
                return false;
        }
        return true;
        // Constant space: The algorithm only uses a few extra variables (i, j) and performs character operations in place.
        // No additional data structures are used that scale with the input size.
    }

    public static void main(String[] arr) {
        String sentence1 = "A man, a plan, a canal, Panama!";
        String sentence2 = "Was it a car or a cat I saw?";
        String sentence3 = "Race car";
        String sentence4 = "12345";

        System.out.println(isPalindrome(sentence1));
        System.out.println(isPalindromeFancy(sentence1));
        System.out.println();

        System.out.println(isPalindrome(sentence2));
        System.out.println(isPalindromeFancy(sentence2));
        System.out.println();

        System.out.println(isPalindrome(sentence3));
        System.out.println(isPalindromeFancy(sentence3));
        System.out.println();

        System.out.println(isPalindrome(sentence4));
        System.out.println(isPalindromeFancy(sentence4));
        System.out.println();
    }

}
