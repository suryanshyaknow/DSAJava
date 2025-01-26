package DesignGurusEasy;

import java.util.*;

public class Pangram {

    public static boolean checkIfPangram(String sentence) {
        // TODO: Write your code here
        String alphabets = "abcdefghijklmnopqrstuvwxyz";

        // Convert both to sets
        HashSet<Character> alphabetsSet = new HashSet<>();
        for (Character c : alphabets.toLowerCase().toCharArray()) {
            if (Character.isLetter(c))
                alphabetsSet.add(c);
        }
        HashSet<Character> sentenceSet = new HashSet<>();
        for (Character c : sentence.toLowerCase().toCharArray()) {
            if (Character.isLetter(c))
                sentenceSet.add(c);
        }

        // Iterate over alphabets set and early exit if a char ain't there in sentence
        for (char c : alphabetsSet) {
            if (!sentenceSet.contains(c))
                return false;
        }
        return true;
    }

    public static boolean checkIfPangramUsingSize(String sentence) {
        // TODO: Write your code here

        // Convert sentence to sets
        HashSet<Character> sentenceSet = new HashSet<>();
        for (Character c : sentence.toLowerCase().toCharArray()) {
            if (Character.isLetter(c))
                sentenceSet.add(c);
        }

        // Now, just check the size of set if it's 26
        return sentenceSet.size() == 26;
    }

    public static void main(String[] args) {
        String sentence = "TheQuickBrownFoxJumpsOverTheLazyDog";
        String sentence1 = "This is not a pangram";
        System.out.println(checkIfPangram(sentence));
        System.out.println(checkIfPangram(sentence1));
    }
}
