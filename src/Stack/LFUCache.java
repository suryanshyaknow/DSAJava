package Stack;

import java.util.HashMap;

public class LFUCache {

    static class Node {

        Node prev, next;
        private int key;
        private int val;
        private int freq;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    static class DoublyLinkedList {
        private Node head;
        private Node tail;

        public DoublyLinkedList() {
//            this.capacity = capacity;
//            this.currentSize = 0;
            // Understand that here we don't need to track the size of DLL.
            // 'Cause there'll be multiple DLLs assigned to diff frequencies, the frequency DLL doesn't have size restriction.

            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        private void delNode(Node node) {
            Node prevNode = node.prev;
            Node frontNode = node.next;

            prevNode.next = frontNode;
            frontNode.prev = prevNode;
        }

        private void insertNodeAtHead(Node node) {
            Node nodeNextToHead = head.next;
            node.prev = head;
            node.next = nodeNextToHead;
            head.next = node;
            nodeNextToHead.prev = node;
        }

        private Boolean isEmpty() {
            return head.next == tail;
        }
    }

    private int capacity;
    private int currentSize;
    private int minFreq; // Gotta keep track of the minimum frequency
    HashMap<Integer, Node> cacheMap;
    HashMap<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        this.minFreq = 0;
        this.cacheMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public void put(int key, int val) {
        if (capacity == 0) return;

        // First off, check if key exists and accordingly update
        // Then its freq needs to be amped up too
        Node currNode = cacheMap.getOrDefault(key, null);
        if (currNode != null) {
            currNode.val = val;
            updateNodeAndFrequency(currNode);
            return;
        }

        // Second, if capacity allows?
        if (currentSize == capacity) {
            // Remove the LFU
            removeLFUNode();
        }
        // Insert this new node
        Node newNode = new Node(key, val);
        DoublyLinkedList freqDll = freqMap.getOrDefault(newNode.freq, new DoublyLinkedList());
        freqDll.insertNodeAtHead(newNode);

        cacheMap.put(key, newNode);
        freqMap.put(newNode.freq, freqDll);

        minFreq = 1;
        currentSize += 1;
    }

    private void removeLFUNode() {
        DoublyLinkedList minFreqDLL = freqMap.get(minFreq);
        Node lfuNode = minFreqDLL.tail.prev;
        minFreqDLL.delNode(lfuNode);

        if (minFreqDLL.isEmpty())
            freqMap.remove(minFreq);
        // Remove this node from the cache map as well
        cacheMap.remove(lfuNode.key);
        currentSize--;
    }

    public int get(int key) {
        // First off check if it even exists or return -1
        Node node = cacheMap.getOrDefault(key, null);
        if (node == null) return -1;

        // Increase its frequency i.e. move it to DLL assigned to updated frequency
        updateNodeAndFrequency(node); // Put the node into amped up freq DLL
        return node.val;
    }

    private void updateNodeAndFrequency(Node node) {
        // Node needs to be deleted from its olf freq
        DoublyLinkedList freqDll = freqMap.get(node.freq);
        freqDll.delNode(node);

        // And if that freqMap gets nullified and it was minFreq, then update the minFreq
        if (freqDll.isEmpty() && node.freq == minFreq)
            minFreq++;

        // Node needs to be added into newFreq DLL
        node.freq += 1;
        DoublyLinkedList newFreqDll = freqMap.getOrDefault(node.freq, new DoublyLinkedList());
        newFreqDll.insertNodeAtHead(node);
        freqMap.put(node.freq, newFreqDll); // Anyway push the DLL into freqMap
    }


}
