package Stack;

import java.util.HashMap;

public class LRUCache {

    private static class Node {
        private int key;
        private int val;
        private Node next;
        private Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    int capacity;
    HashMap<Integer, Node> hashMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // Check if it even exists
        if (!hashMap.containsKey(key))
            return -1;

        // Get the node if it exists
        Node node = hashMap.get(key);
        // Delete the node and put it at the front
        delNode(node);
        insertAtHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = hashMap.getOrDefault(key, null);

        // There'll be two cases
        // One where node already exists
        // Second where it doesn't and the capacity has been hit

        // Node does exist
        if (node != null) {
            // Allot new value
            node.val = value;
            hashMap.put(key, node);

            // Delete the node and put it at the head
            delNode(node);
            insertAtHead(node);
            return;
        }

        node = new Node(key, value);
        // Node doesn't, and capacity has hit
        if (hashMap.size() == capacity) {
            // Remove the LRU node
            Node lruNode = tail.prev;
            delNode(lruNode);
            hashMap.remove(lruNode.key);
        }

        hashMap.put(key, node);
        insertAtHead(node);
    }

    private void insertAtHead(Node node) {
        Node nextToHead = head.next;

        head.next = node;
        node.prev = head;

        node.next = nextToHead;
        nextToHead.prev = node;
    }

    private void delNode(Node node) {
        Node prev = node.prev;
        Node front = node.next;

        prev.next = front;
        front.prev = prev;
    }

}
