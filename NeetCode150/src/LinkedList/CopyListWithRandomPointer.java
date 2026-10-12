package LinkedList;

import java.util.*;

public class CopyListWithRandomPointer {

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node copyRandomListOptimal(Node head) {
        if (head == null)
            return null;

        // Intuition: We're gonna try to do this w/o using any
        // external additional space

        // Step 1. Create deep copy of each node and insert it bw its
        // parent and parent's next
        Node curr = head;
        while (curr != null) {
            Node dc = new Node(curr.val);
            dc.next = curr.next;
            curr.next = dc;

            // Move the ptr
            curr = curr.next.next;
        }

        /// 2. Now mirror the random linkages in deep copy
        curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;

            // Move the ptr
            curr = curr.next.next;
        }

        // Step 3. Now extract the deep copy while restoring the org ll
        // to its pristine condition
        curr = head;
        Node dcHead = curr.next;
        while (curr != null) {
            Node dc = curr.next;
            if (dc != null) {
                // Restore dc parent linkage
                // Node orgNext = dc.next;
                curr.next = dc.next;

                // Point the dc next to its parent next's next
                if (dc.next != null)
                    dc.next = dc.next.next;
            }

            // Now that cur org next has been restored
            curr = curr.next;
        }

        return dcHead;
        // Time: O(3N)
        // Space: O(1)
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Intuition: We're gonna leverage the hash map.
        // In first pass we're gonna create deep copyof each node
        // and register them in our hash map. And in the seccond
        // pass, we're gonna do the linkages w the help of hash map
        // cuz random pointers can be done easily now, cuz every node
        // exists beforehand.

        HashMap<Node, Node> hashMap = new HashMap<>();
        Node curr = head;

        // First pass
        while (curr != null) {
            hashMap.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Second pass, do the linkages
        curr = head;
        while (curr != null) {
            Node dc = hashMap.get(curr); // deep copy
            dc.next = hashMap.getOrDefault(curr.next, null);
            dc.random = hashMap.getOrDefault(curr.random, null);

            curr = curr.next;
        }

        return hashMap.get(head);

        // Time: O(2N)
        // Space: O(N)
    }
}
