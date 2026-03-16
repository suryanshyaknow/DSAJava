package Graphs;

import java.util.*;

public class CloneGraph {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        // Intuition:

        // 1. We see a node, we create its deep copy
        // 2. Process neighbors
        // 3. Before adding neighbor, ensure its deep copy exists, otherwise recursively process the neighbors first.
        // 4. While creating deep copy we add it to hashMap.

        if (node == null)
            return null;

        // Create a HashMap to store copied nodes
        Map<Node, Node> hashMap = new HashMap<>();
        return dfs(node, hashMap);
    }

    private Node dfs(Node node, Map<Node, Node> hashMap) {
        // See if node already is copied
        if (hashMap.containsKey(node))
            return hashMap.get(node);

        // Otherwise, create the copy and process its neighbors recursively
        Node deep = new Node(node.val);
        hashMap.put(node, deep);
        for (Node nei : node.neighbors) {
            deep.neighbors.add(dfs(nei, hashMap));
        }
        return deep;
    }

}
