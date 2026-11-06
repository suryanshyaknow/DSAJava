package DesignPattern;

import java.util.*;

public class LFUCache {

    private static class Node {
        int key, val;
        Node prev, next;
        int freq; // use freq

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    private static class DLL {
        Node left; // head; LRU pointer, points forward
        Node right; // tail; MRU pointer, points backwards

        DLL() {
            this.left = new Node(-1, -1);
            this.right = new Node(-1, -1);
            this.left.next = right;
            this.right.prev = left;
        }

        /**
         * Oftentimes I'll need to check if given freq list is empty
         */
        private boolean isEmpty() {
            return this.left.next == this.right;
        }

        /**
         * Detach a node from its current pos.
         */
        private void removeNode(Node node) {
            Node prev = node.prev;
            Node front = node.next;

            prev.next = front;
            front.prev = prev;
        }

        /**
         * Attach the node at front -- right before right.
         * This makes it MRU in this list.
         */
        private void insertAtFront(Node node) {
            Node prev = right.prev; // Current MFU
            prev.next = node;

            node.next = right;
            node.prev = prev;

            right.prev = node;
        }
    }

    private Map<Integer, Node> cache; // Key-node map
    private Map<Integer, DLL> freqMap; // freq-DLL map; groups nodes by use frequencies in LRU -> MRU fashion
    int minFreq;
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFreq = 1;
    }

    public int get(int key) {
        // Check the existence of the key in cache
        Node node = cache.getOrDefault(key, null);
        if (node == null)
            return -1;

        // If it does exist: Retrieve -> Detach -> Promote -> Attach
        // Fetch its value
        int val = node.val;

        // Remove it from its current freq dll, increase the use freq
        promoteNode(node);

        // Return the val
        return val;
    }

    /**
     * Remove, update min freq -> Increase freq -> Insert in new freq list
     */
    private void promoteNode(Node node) {
        // Removal from curr freq list
        DLL dll = freqMap.get(node.freq);
        dll.removeNode(node);

        // See if curr freq list is empty and update the min freq
        if (dll.isEmpty()) {
            freqMap.remove(node.freq);

            if (this.minFreq == node.freq)
                this.minFreq++;
        }
        node.freq += 1;

        // Insert it into +1 freq list
        int newFreq = node.freq;
        DLL newDll = freqMap.computeIfAbsent(newFreq, k -> new DLL());
        newDll.insertAtFront(node); // this makes it MRU in its new freq list
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;
        // Capacity Exceed: Evict LFU
        // Create/Fetch -> Detach (if exists) -> Promote -> Attach

        // See if it exists or not
        Node node = cache.getOrDefault(key, null);
        if (node == null) {
            // Key doesn't exist
            if (this.capacity == this.cache.size())
                evictLFU();

            // Create node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);

            // Insert node
            DLL dll = freqMap.computeIfAbsent(1, k -> new DLL());
            dll.insertAtFront(newNode);

            // Update min freq
            this.minFreq = 1;
        } else {
            // Key exists already
            promoteNode(node);

            // Update val
            node.val = value;
        }

    }

    private void evictLFU() {
        // Retrieve the min freq DLL
        DLL dll = freqMap.get(this.minFreq);
        Node lfu = dll.left.next;
        dll.removeNode(lfu);
        if (dll.isEmpty()) {
            freqMap.remove(lfu.freq);
        }
        // Remove from cache as well
        cache.remove(lfu.key);
    }

}
