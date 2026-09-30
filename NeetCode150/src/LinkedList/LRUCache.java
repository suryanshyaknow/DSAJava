package LinkedList;

import java.util.*;

public class LRUCache {

    // Intuition: Since the get ops are to be done in constant
    // time, nothing better than hashmap there. However, since
    // we're to repeatedly get rid of lru values, we have to
    // sort them in some manner as well in a way that brings the
    // mru values to the top of the queue or some.
    //
    // To achieve that instead of storing values in the hashmap.
    // we're gonna store the nodes. And it's gonna be DLL.
    //
    // To keep track of lru and mru nodes, we're gonna keep two
    // dummy nodes: left (lru) and right (mru)

    private static class Node {
        private int key;
        private int val;
        private Node prev;
        private Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    private Node left; // dummy lru node
    private Node right; // dummy mru node
    private Map<Integer, Node> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<Integer, Node>();
        this.left = new Node(-1, -1);
        this.right = new Node(-1, -1);

        left.next = right;
        right.prev = left;
    }

    private Node delete(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;

        // Gonna be overridden anyway, but for better clarity..
        node.next = null;
        node.prev = null;
        return node;
    }

    private Node insertAtFront(Node node) {
        // Meaning put before our right dummy
        // left linkage
        right.prev.next = node;
        node.prev = right.prev;

        // right linkage
        right.prev = node;
        node.next = right;
        return node;
    }

    public int get(int key) {
        // See if key exists
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        // Since it's now mru
        // So pick it and put it at front of the dll
        delete(node);
        insertAtFront(node);

        return node.val;
    }

    public void put(int key, int value) {
        // See if key exists
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // Update the value in node as well as cache
            node.val = value;

            // Now since it's mru
            delete(node);
            insertAtFront(node);
            return;
        }

        // If it doesnt
        // Insert as a new node
        // delete the lru
        Node newNode = new Node(key, value);

        // Now see if cache exceeds its capacity
        if (cache.size() >= capacity) {
            cache.remove(left.next.key);
            delete(left.next);
        }

        // Put the new node at front
        insertAtFront(newNode);
        cache.put(key, newNode);
    }

}
