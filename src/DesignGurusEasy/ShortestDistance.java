package DesignGurusEasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ShortestDistance {

    public static int shortestDistance(String[] words, String word1, String word2) {
        // TODO: Write your code here

        // Update the indices dynamically and keep the track of distances
        int word1Idx = -1;
        int word2Idx = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                word1Idx = i;
            else if (words[i].equals(word2))
                word2Idx = i;

            if (word1Idx != -1 && word2Idx != -1) {
                int distance = Math.abs(word1Idx - word2Idx);
                if (distance < minDistance)
                    minDistance = distance;
            }
        }
        return minDistance;

        // The variables require constant space, O(1), since the space used does not scale with the size of the input.
    }

    public static void main(String args[]) {
        String[] words = new String[]{"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};
        String word1 = "fox";
        String word2 = "dog";
        System.out.println(shortestDistance(words, word1, word2));
    }

}
