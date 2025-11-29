package Tries;

import java.util.HashSet;
import java.util.Set;

public class CountDistinctSubstrings {

    static class Node {

        private Node[] links = new Node[26];
        private boolean isEndpoint = false;

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

    }

    public static int countDistinctSubstring(String st) {
        int N = st.length();
        int count = 1;

        Node root = new Node();
        for (int i = 0; i < N; i++) { // Treat each new char at i as new char in root
            Node temp = root;
            for (int j = i; j < N; j++) {
                char ch = st.charAt(j);
                if (!temp.containsKey(ch)) {
                    count++;
                    temp.put(ch, new Node());
                }
                temp = temp.get(ch); // Move to current char's ref node
            }
        }

        return count;

        // Time complexity: O(N^2)
        // Space complexity:

        // Total substrings = N + (N-1) + (N-2) + ... + 1 = N*(N+1)/2 → ~O(N²) substrings.
        // In the worst case, every substring is unique → every substring creates a new path of nodes.
        // So the number of nodes in the Trie ≈ total number of distinct substrings ≈ O(N²).
    }

    public static int countDistinctSubstringBruteForce(String st) {
        // your code here
        int N = st.length();

        // We'll iterate over each char and count the number of substrings starting off
        // w that char
        Set<String> res = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                res.add(st.substring(i, j));
            }
        }

        return res.size() + 1; // plus one for empty string
    }

}
