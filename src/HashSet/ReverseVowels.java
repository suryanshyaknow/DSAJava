package HashSet;

import java.util.*;

public class ReverseVowels {

    public String reverseVowelsUsingTwoPointers(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        char[] sArr = s.toCharArray();
        int rear = s.length() - 1;

        // Iterate via two pointers and replace 'em if they're both vowels
        for (int i = 0; i < rear; i++) { // Swapping the character with itself is redundant and unnecessary. That's why i < rear
            if (vowels.contains(sArr[i])) {
                // Move rear pointer until it points to a vowel
                while (i < rear && !vowels.contains(sArr[rear])) {
                    rear--;
                }
                System.out.println("Rear before swap: " + rear);

                // Swap vowels at i and rear
                char temp = sArr[rear];
                sArr[rear] = sArr[i];
                sArr[i] = temp;

                // Move rear pointer
                rear--;
                System.out.println("Rear following swap: " + rear + "\n");
            }

            // The total work done by the two pointers (i and rear) is linear, the inner while does not lead to a nested loop effect where each character is revisited multiple times.
        }

        // Construct the resultant string of the char array..
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : sArr) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void main(String args[]) {
        ReverseVowels reverseVowels = new ReverseVowels();
//        String s = "DesignGurus";
        String s = "DesignGUrus";
        System.out.println("Original String: " + s);
        System.out.println("Reversed Vowels String: " + reverseVowels.reverseVowelsUsingTwoPointers(s));
    }


    public String reverseVowels(String s) {
        // TODO: Write your code here

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        List<Character> existingVowels = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (vowels.contains(c))
                existingVowels.add(c);
        }
        Collections.reverse(existingVowels);

        int ptr = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (existingVowels.contains(c))
                stringBuilder.append(existingVowels.get(ptr++));
            else
                stringBuilder.append(c);
        }
        s = stringBuilder.toString();
        return s;
    }
}