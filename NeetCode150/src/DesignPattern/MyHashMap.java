package DesignPattern;

import java.util.Objects;

public class MyHashMap<K, V> {

    private static class Node<K, V> {
        private K key;
        private V val;
        private Node<K, V> next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Internally what a hashmap is?
     * Superficially it's just an array of buckets.
     */
    private Node<K, V>[] buckets;

    private int size;
    private int capacity;
    private final float loadFactor; // Gonna be used for re-sizing

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.buckets = (Node<K, V>[]) new Node[capacity]; // Java doesn't allow generics array creation
    }

    /**
     * Capacity is always powers of 2 because it aids in efficient bucket idx
     * computations using bitwise AND.
     * <p>
     * W capacity of 16, we have 16 buckets (0 - 15), which can be represented
     * using just 4 bits.
     * So despite hash being 32-bit integer, only the lower 4 bits determine the
     * bucket idx, viz. hash & (capacity - 1)
     * <p>
     * Why use hash spreading?
     * Since, for an instance, w a capacity of 16, only 4 lower bits are used in
     * bucket idx computation, the distribution in upper bits might go unnoticed.
     * <p>
     * To address this we XOR the original hash w its right-shifted version,
     * allowing information from the upper 16 bits to influence the lower
     * 16 bits.
     *
     * @param key
     * @return
     */
    private int getHash(K key) {
        int h = key.hashCode(); // 32-bit hash
        return h ^ (h >>> 16); // Hash spreading
    }

    /**
     * Post hash-generation we need to determine the bucket-idx as well to access the node.
     *
     * @param key
     * @return
     */
    private int getBucketIdx(K key) {
        return getHash(key) & (capacity - 1);
    }

    public V get(K key) {
        // Compute the hash and determine the bucket idx
        int idx = getBucketIdx(key);

        // Access the bucket and see if the key exists
        Node<K, V> curr = buckets[idx];
        if (curr == null) return null;

        // Traverse and compare
        while (curr != null) {
            if (Objects.equals(key, curr.key)) return curr.val;
            curr = curr.next;
        }
        return null;
    }

    public void put(K key, V val) {
        // Compute the key hash and determine the bucket idx
        int idx = getBucketIdx(key);

        // Access the bucket and see if node exits already
        Node<K, V> curr = buckets[idx];
        Node<K, V> prev = null;

        // If exists, update the val and return
        // For that, traverse the ll
        while (curr != null) {
            if (Objects.equals(key, curr.key)) {
                curr.val = val;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        // Prev points to the last node
        Node<K, V> newNode = new Node<>(key, val);
        if (prev == null) {
            buckets[idx] = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
        if (size > loadFactor * capacity) resize();
    }

    public V remove(K key) {
        // Compute the key hash and determine the bucket idx
        int idx = getBucketIdx(key);

        // Access the bucket
        Node<K, V> curr = buckets[idx];
        Node<K, V> prev = null;

        // Traverse the ll and see if key exists
        while (curr != null) {
            if (Objects.equals(key, curr.key)) {
                // Node to be removed
                if (prev != null) {
                    prev.next = curr.next;
                } else buckets[idx] = curr.next;
                size--;
                return curr.val;
            }

            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        // Save old buckets
        Node<K, V>[] oldBuckets = buckets;

        // Initialize new buckets twice the size
        capacity *= 2;
        size = 0;
        this.buckets = (Node<K, V>[]) new Node[capacity];

        // Iterate old buckets and put the nodes to new buckets
        for (Node<K, V> head: oldBuckets) {
            Node<K, V> curr =  head;

            // Insert the whole chain
            while (curr != null) {
                put(curr.key, curr.val);
                curr = curr.next;
            }
        }

        // This resize is O(n) but happens only when size doubles, so put is amortized O(1).
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // Basic put/get
        map.put("a", 1);
        map.put("b", 2);
        check(map.get("a") == 1, "get a");
        check(map.get("b") == 2, "get b");
        check(map.get("zzz") == null, "missing key returns null");

        // Update shouldn't change size
        map.put("a", 100);
        check(map.get("a") == 100, "update a");
        check(map.size == 2, "size after update");

        // Same hashCode, same bucket ("Aa" and "BB" both hash to 2112)
        map.put("Aa", 10);
        map.put("BB", 20);
        check(map.get("Aa") == 10, "collision Aa");
        check(map.get("BB") == 20, "collision BB");

        // Remove: tail, head, and missing
        check(map.remove("BB") == 20, "remove BB");
        check(map.get("BB") == null, "BB gone");
        check(map.get("Aa") == 10, "Aa survives");
        check(map.remove("Aa") == 10, "remove Aa");
        check(map.remove("nope") == null, "remove missing");
        check(map.size == 2, "size after removes");

        // Resize: push well past 16 * 0.75 = 12
        MyHashMap<Integer, Integer> big = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) big.put(i, i * 2);
        check(big.size == 1000, "big size");
        check(big.capacity > 16, "capacity grew: " + big.capacity);
        for (int i = 0; i < 1000; i++) {
            check(big.get(i) == i * 2, "big get " + i);
        }

        System.out.println("All tests passed");
    }

    private static void check(boolean condition, String name) {
        if (!condition) throw new AssertionError("FAILED: " + name);
    }


}
