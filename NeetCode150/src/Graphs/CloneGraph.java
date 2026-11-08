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
        if (node == null)
            return null;

        // Intuition:
        // Whenever I encounter a node whose clone
        // doesn't exist yet, I create its clone.
        // And iterate over its neighbors and process
        // its neighbors recursively.
        //
        // If its clone already exist, then I simply return
        // its clone.
        //
        // Why HashMap? This is an undirected graph, so cycles
        // are possible. A given node can be encountered thru
        // multiple paths.
        // I need a mapping bw old node and its cloned node.
        Map<Node, Node> oldToNew = new HashMap<>();

        return cloneDfs(node, oldToNew);
    }

    /**
     * What if the graph is very deep?
     * Recursion depth can reach V, so a 10⁵-node chain risks a stack overflow in Java.
     * The BFS version I gave earlier is the answer to that.
     *
     * @param node
     * @param oldToNew
     * @return
     */
    private Node cloneDfs(Node node, Map<Node, Node> oldToNew) {
        if (oldToNew.containsKey(node)) return oldToNew.get(node);

        // Create the clone
        Node clone = new Node(node.val);
        oldToNew.put(node, clone); // Mapping formed; ready to use whenever

        // Now iterate over neighbors of the node and recurse
        List<Node> neighbors = node.neighbors;
        for (Node nei : neighbors) {
            clone.neighbors.add(cloneDfs(nei, oldToNew));
        }

        return clone;
    }

    private Node bfs(Node node, Map<Node, Node> oldToNew) {
        // Intuition:
        // Start off w the init config of the starting node
        // by creating its clone and inserting it into queue.
        // Now, while iterating the queue till its empty, poll
        // the node, iterate over its neighbors. Create neighbors
        // clone and register them in hash, and insert them into
        // cloned node neighbors.

        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        Node clone = new Node(node.val);
        oldToNew.put(node, clone); // Register the clone

        while (!q.isEmpty()) {
            Node curr = q.poll();
            Node currClone = oldToNew.get(curr);

            // Iterate over neighbors and register and form the mapping w
            // original nodes of the unregistered nodes
            for (Node nei : curr.neighbors) {
                Node cloneNei = oldToNew.getOrDefault(nei, null);
                if (cloneNei == null) { // Doesn't exist yet
                    cloneNei = new Node(nei.val); // Create clone
                    oldToNew.put(nei, cloneNei); // Register into hash
                    q.offer(nei); // Only enqueue on first discovery
                }
                currClone.neighbors.add(cloneNei);
            }
        }

        return clone;
    }
}
