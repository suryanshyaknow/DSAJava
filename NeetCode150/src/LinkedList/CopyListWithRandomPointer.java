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

        // Step 1. Create deep copy of each node and put them in between
        // their parent and parent's next.
        Node curr = head;
        while (curr != null) {
            Node dc = new Node(curr.val);
            dc.next = curr.next;
            curr.next = dc;

            // Move the pointer
            curr = curr.next.next;
        }

        // Step 2. Mirror the random linkage from their parent
        curr = head;
        while (curr != null) {
            Node currRandom = curr.random;
            if (currRandom != null) {
                Node dc = curr.next;
                dc.random = currRandom.next;
            }

            // Move the pointer
            curr = curr.next.next;
        }

        // Step 3. Now extract the deep copy while restoring the org ll
        // to its pristine condition
        curr = head;
        Node dummy = new Node(-1);
        dummy.next = curr.next; // Point dummy to head of clone list
        Node newCurr = curr.next;
        while (curr != null) {
            // Restore the orginal linkage of parent node
            curr.next = curr.next.next;
            if (curr.next != null)
                newCurr.next = curr.next.next; // parent's next's clone

            // Move the pointers
            curr = curr.next;
            newCurr = newCurr.next;
        }

        return dummy.next;
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
