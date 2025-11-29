package Tries;

public class LongestStringWithAllPrefixes {

    static class Node {

        private Node[] links = new Node[26];
        private boolean isEnd = false;

        Node() {

        }

        public boolean containsKey(char ch) {
            return this.links[ch - 'a'] != null;
        }

        public void put(char ch, Node node) {
            this.links[ch - 'a'] = node;
        }

        public Node get(char ch) {
            return this.links[ch - 'a'];
        }

        public void setEndPoint() {
            this.isEnd = true;
        }

        public boolean isEndPoint() {
            return isEnd;
        }

    }

    static class Trie {

        private Node root;

        Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node temp = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!temp.containsKey(ch))
                    temp.put(ch, new Node());
                temp = temp.get(ch); // Get the ref node and move to it
            }
            temp.setEndPoint();
        }

        public boolean checkIfPrefixExists(String word) {
            Node temp = root;

            // iterate and check at every instance/char if it's an end of some word
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (temp.containsKey(ch)) {

                    temp = temp.get(ch);

                    if (!temp.isEndPoint()) return false;
                } else return false;
            }
            return true;
        }

    }

    public String longestValidWord(String[] words) {
        // code here
        // First off, we're gonna insert every word into Trie data structure
        // Then we'll reiterate over for each word and determine if it's
        // a prefix for all other words, and consequently find the longest
        // string.

        String res = "";

        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }

        // Reiterate and find the longest prefix
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (trie.checkIfPrefixExists(word)) {
                if (res.length() < word.length())
                    res = word;
                else if (res.length() == word.length())
                    if (word.compareTo(res) < 0) res = word; // retaining lexicographically smallest one
            }
        }

        return res;
    }
}
