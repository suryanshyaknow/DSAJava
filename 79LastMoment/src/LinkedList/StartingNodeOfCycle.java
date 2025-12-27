package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class StartingNodeOfCycle {

    public ListNode detectCycle(ListNode head) {
        // First off, detect a loop.
        // Where the slow and fast pointers happen to
        // meet, we'll reset one of the pointers to the
        // head, and fkn move 'em simultaneously. And
        // where they happen to meet again is the startig
        // node of the loop.

        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) break; // fast and slow have collided
        } // O(N)
        if (fast == null || fast.next == null) return null; // No cycle

        // Otherwise, they've met on an actual need
        // Reset slow to the head, and move 'em simultaneously
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        } // O(< N)
        return slow;

        // Time complexity: O(N)
        // Space complexity: O(1)
    }

    public ListNode detectCycleBruteForce(ListNode head) {
        if (head == null || head.next == null) return null;

        // Using hashing
        Map<ListNode, Integer> hashMap = new HashMap<>();
        ListNode temp = head;

        while (temp != null) {
            Integer freq = hashMap.getOrDefault(temp, 0);
            if (freq > 0)
                return temp;
            else
                hashMap.put(temp, 1);

            temp = temp.next;
        }

        return null;

        // Time complexity: O(N)
        // Space complexity: O(N)
    }

}
