package Tries;

public class TriesImpl {

    private static class Node {

        Node[] links = new Node[26]; // 26 characters
        boolean isEnd = false;

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

        public void setEndpoint() {
            this.isEnd = true;
        }

        public boolean isEnd() {
            return this.isEnd;
        }

    }

    private Node root;

    public TriesImpl() {
        root = new Node(); // Root node initialization
    }

    public void insert(String word) {
        int N = word.length();

        Node temp = root;

        // Iterate over each char and give/assign them nodes and reference nodes
        for (int i = 0; i < N; i++) {
            char ch = word.charAt(i);

            // See if the node contains the key
            if (!temp.containsKey(ch)) {
                // if it doesn't then create one
                temp.put(ch, new Node());
            }

            // Move temp to the reference node
            temp = temp.get(ch);
        }
        // At the last char's reference node
        // Set flag to true
        temp.setEndpoint();
    }

    public boolean search(String word) {
        // We've gotta ensure the whole word as a 'word' exists
        Node temp = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!temp.containsKey(ch))
                return false;
            temp = temp.get(ch);
        }
        return temp.isEnd();
    }

    public boolean startsWith(String prefix) {
        // We don't necessarily need to ensure that prefix exists as complete word
        // inside the root, hence to check if the last char is endpoitn won't be
        // necessary.
        Node temp = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!temp.containsKey(ch))
                return false;
            temp = temp.get(ch);
        }
        return true; // Provided all chars in the word have been traversed
    }

}
