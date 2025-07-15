package Stack.Hard;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class Node {
        Node prev, next;
        int key, val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    int capacity;
    Map<Integer, Node> hashMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
    }

    public int get(int key) {
        // First off, the key should exist in the map
        if (!hashMap.containsKey(key)) return -1;

        // Second, pick this node and put it at the front i.e. new head
        Node node = hashMap.get(key);
        int val = node.val;

        // Redo the linkage
        // Delete the node
        deleteNode(node);

        // Insert the node
        insertNodeAtHead(node);
        return val;
    }

    public void put(int key, int val) {
        // First off, see if map has already this key
        if (hashMap.containsKey(key)) {
            Node node = hashMap.get(key);
            node.val = val;
            hashMap.put(key, node);

            // Now that it has been put to use, it's gotta be placed at head
            deleteNode(node);
            insertNodeAtHead(node);
            return;
        }

        // Now see if hashMap has hit the capacity
        // And if yes, delete the last node and put this new node at head
        if (hashMap.size() >= capacity) {
            // Delete the last node
            Node lruNode = tail.prev;
            deleteNode(lruNode);
            // Remove the node from the map too
            hashMap.remove(lruNode.key);
        }

        // Anyway, put this new node at head
        Node newNode = new Node(key, val);
        hashMap.put(key, newNode);
        insertNodeAtHead(newNode);
    }

    private void deleteNode(Node node) {
        Node prev = node.prev; // Preserve the prev node
        prev.next = node.next; // prev -> tail
        node.next.prev = prev; // prev <- tail
    }

    private void insertNodeAtHead(Node node) {
        Node nextToHead = head.next; // preserve nextToHead
        head.next = node;
        node.prev = head;

        node.next = nextToHead;
        nextToHead.prev = node;
    }

}
