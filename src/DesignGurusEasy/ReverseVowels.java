package DesignGurusEasy;

import java.sql.SQLOutput;
import java.util.*;

public class ReverseVowels {

    public static String reverseVowels(String s) {
        // TODO: Write your code here
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

        // Filter out the vowels (and put them in their original cases)
        List<Character> filteredVows = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vowels.contains(Character.toLowerCase(c)))
                filteredVows.add(c);
        }

        // Reverse 'em
        Collections.reverse(filteredVows);

        // Iterate over the original string and replace the vowels with reversed ones
        int reversedVowelPtr = 0;
        StringBuilder stringBuilder = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (filteredVows.contains(stringBuilder.charAt(i))) // Curr char is a vowel
                stringBuilder.setCharAt(i, filteredVows.get(reversedVowelPtr++));
        }

        return stringBuilder.toString();
    }

    public static String reverseVowelsUsingTwoPointers(String s) {
        String vowels = "aeiouAEIOU";
        HashSet<Character> vowelsSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] sArr = s.toCharArray();

        int frontPtr = 0;
        int rearPtr = s.length() - 1;

        while (frontPtr < rearPtr) {
            // Debug: Print the current pointers and characters
            System.out.println("Before Moving: frontPtr = " + frontPtr + ", rearPtr = " + rearPtr +
                    ", frontChar = " + sArr[frontPtr] + ", rearChar = " + sArr[rearPtr]);

            while (frontPtr < rearPtr && !vowelsSet.contains(sArr[frontPtr])) {
                frontPtr++;
            }
            while (frontPtr < rearPtr && !vowelsSet.contains(sArr[rearPtr])) {
                rearPtr--;
            }
            // Debug: Print the found vowels before swapping
            System.out.println("Found Vowels: frontPtr = " + frontPtr + ", rearPtr = " + rearPtr);
            System.out.println("Found Vowels: frontChar = " + sArr[frontPtr] + ", rearChar = " + sArr[rearPtr]);
            System.out.println("\n");

            // Swap vowels
            char temp = sArr[frontPtr];
            sArr[frontPtr] = sArr[rearPtr];
            sArr[rearPtr] = temp;

            frontPtr++;
            rearPtr--;
        }
        return new String(sArr);
    }


    public static void main(String[] arr) {
        String s = "DesignGUrus";
        System.out.println(reverseVowels(s));
        System.out.println(reverseVowelsUsingTwoPointers(s));
    }
}
