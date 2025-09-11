package Graphs.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    class Node {
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
        if (node == null) return null;

        // Every original node needs a corresponding new node (cloned) in the cloned graph
        HashMap<Node, Node> oldToNew = new HashMap<>();
        return dfsHelper(node, oldToNew);
    }

    private Node dfsHelper(Node node, HashMap<Node, Node> oldToNew) {
        Node clonedNode = oldToNew.getOrDefault(node, null);
        if (node != null) return clonedNode;

        // Otherwise clone the node and add it to mappings
        Node copy = new Node(node.val);
        oldToNew.put(node, copy);
        for (Node nei : node.neighbors) {
            copy.neighbors.add(dfsHelper(nei, oldToNew));
        }
        return copy;
    }

}
