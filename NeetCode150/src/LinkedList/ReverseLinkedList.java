package LinkedList;

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Pick an arbitrary node and play w its pointers
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            // Shift the linkage
            curr.next = prev;

            // Move the pointers
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode reverseListRecursive(ListNode head) {
        // base case
        if (head == null || head.next == null)
            return head;

        ListNode newHead = reverseList(head.next);
        ListNode nxt = head.next;
        nxt.next = head;
        head.next = null;

        return newHead;

        // Time: O(N)
        // Space: O(N)
    }
}
