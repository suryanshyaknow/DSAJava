package DesignGurusEasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestWordDistance {

    public static int shortestDistanceOptimal(String[] words, String word1, String word2) {
        // TODO: Write your code here
        int word1Idx = -1, word2Idx = -1;

        int counter = 0;
        int minDistance = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.equals(word1)) word1Idx = counter;
            else if (word.equals(word2)) word2Idx = counter;

            // Compare the old distance with the current dist and pick the shorter one
            if (word1Idx != -1 && word2Idx != -1) {
                minDistance = Math.min(minDistance, Math.abs(word2Idx - word1Idx));
            }
            counter++;
        }

        return minDistance;
    }

    public static int shortestDistance(String[] words, String word1, String word2) {
        // TODO: Write your code here
        int word1Idx = -1, word2Idx = -1;

        int counter = 0;
        List<Integer> distances = new ArrayList<>();
        for (String word : words) {
            if (word.equals(word1)) word1Idx = counter;
            else if (word.equals(word2)) word2Idx = counter;

            if (word1Idx != -1 && word2Idx != -1) {
                distances.add(Math.abs(word2Idx - word1Idx));
            }
            counter++;
        }

        Collections.sort(distances);
        return distances.getFirst();
    }

    public static void main(String[] args) {
        String[] words = new String[]{"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        String word1 = "fox";
        String word2 = "dog";

        String[] words_2 = new String[]{"a", "c", "d", "b", "a"};
        String word1_2 = "a";
        String word2_2 = "b";

        System.out.println("Shortest distance: " + shortestDistance(words_2, word1_2, word2_2));
    }


}
