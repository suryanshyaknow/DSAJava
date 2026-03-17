package Graphs.ShortestPath;

import java.util.*;

public class WordLadder {

    private static class Pair {
        String word;
        int k; // conversion

        public Pair(String word, int k) {
            this.word = word;
            this.k = k;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // First off create a hashing ds of the wordlist
        // to check for the presence of words in O(1) time complexity.
        int N = wordList.size();
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        // Also we're gonna use this set as visited array as well
        // in a way/ Word visited once shall be removed from the set
        // in order to treat them visited.

        // Implement BFS to compute the min number of transformations
        // Also, since a word can be transformed into multiple instances
        // from the wordlist, it gives us the idea of simultaneous branching,
        // and hence BFS.
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 1));
        set.remove(beginWord);

        if (!set.contains(endWord)) return 0;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            String word = pair.word;
            int d = pair.k;

            char[] wordArr = word.toCharArray();

            // Now, explore all the transformations
            for (int i = 0; i < word.length(); i++) {
                char org = wordArr[i]; // for restoration
                for (int j = 0; j < 26; j++) {
                    wordArr[i] = (char) ('a' + j);
                    if (wordArr[i] == org) continue; // Skip same iterations
                    String newWord = new String(wordArr);

                    if (set.contains(newWord)) {
                        if (newWord.equals(endWord)) return d + 1;

                        q.offer(new Pair(newWord, d + 1));
                        set.remove(newWord);
                    }
                }
                wordArr[i] = org;
            }
        } // O(N * L * 26) where N is the number of word in wordList, L being the length of each word

        return 0;

        // Time complexity: O(N * L)
        // Space complexity: O(N + L) ~ O(N) where L being the transformation into char array
    }
}
