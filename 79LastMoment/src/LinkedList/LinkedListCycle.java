package LinkedList;

import java.util.HashMap;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;

            if (slowPtr == fastPtr)
                return true; // If slowPtr and fastPtr ever meet at any node, it implies there's a loop
        }

        return false;
    }

    public boolean hasCycleBruteForceUsingHashing(ListNode head) {
        // We'll iterate over each node at a time
        // and hash 'em. And at any time if we
        // happen to encounter a node for the second
        // time, we assert it's a loop.
        if (head == null || head.next == null) return false;

        ListNode temp = head;
        HashMap<ListNode, Integer> hashMap = new HashMap<>();

        while (temp.next != null) { // Iterating till the last node
            int count = hashMap.getOrDefault(temp, 0);
            if (count == 0)
                hashMap.put(temp, 1);
            else
                return true; // cycle exits

            temp = temp.next;
        }

        return false;

        // Time complexity: O(N)
        // Space complexity: O(N) for hashing ds
    }

}
