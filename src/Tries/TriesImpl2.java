package Tries;

public class TriesImpl2 {

    static class Node {

        Node[] links = new Node[26];
        int endsWith = 0;
        int countPrefix = 0;

        public Node() {

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

        public void incrementCountPrefix() {
            this.countPrefix++;
        }

        public void reduceCountPrefix() {
            this.countPrefix--;
        }

        public void reduceEndsWithCount() {
            this.endsWith--;
        }

        public void incrementEndsWith() {
            this.endsWith++;
        }

    }

    private Node root;

    public TriesImpl2() {
        root = new Node();
    }

    /*
        Inserts a word into Trie
     */
    public void insert(String word) {
        Node temp = root;

        // Iterate over each char and see if it already exists or not
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!temp.containsKey(ch))
                temp.put(ch, new Node());

            // Get reference Node
            temp = temp.get(ch);
            temp.incrementCountPrefix();
        }
        temp.incrementEndsWith();
    }

    public int countWordsStartingWith(String word) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!temp.containsKey(ch))
                return 0;
            temp = temp.get(ch);
        }
        return temp.countPrefix;
    }

    public int countWordsEqualTo(String word) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!temp.containsKey(ch))
                return 0;
            temp = temp.get(ch);
        }
        return temp.endsWith;
    }

    public void erase(String word) {
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (temp.containsKey(ch)) {
                temp = temp.get(ch);
                temp.reduceCountPrefix();
            } else return;
        }
        temp.reduceEndsWithCount();
    }

}
