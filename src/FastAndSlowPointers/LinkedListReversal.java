package FastAndSlowPointers;

public class LinkedListReversal {

    public static ListNode reverseIteratively(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        while (temp != null) {
            ListNode front = temp.next;
            temp.next = prev;
            // Moving the pointers forward
            prev = temp;
            temp = front;
        }
        return prev; // Head of the reversed list
    }

    public static ListNode reverseRecursively(ListNode head) {
        // Base case
        if (head == null || head.next == null)
            return head; // Single node list is reversed in itself if you think about it

        // Iterate recursively
        ListNode newHead = reverseRecursively(head.next);
        ListNode front = head.next;
        // Now since front would be pointing towards null and head be pointing towards front,
        // we oughta do the apt linkage
        front.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;

        // Create a dummy node to handle edge cases cleanly
        ListNode dummy = new ListNode(0);
        dummy.next = head; // Make it point towards the head

        // Step 1: Get a hold on left-th node
        ListNode leftPrev = dummy;
        ListNode leftNode = head;
        // Number of nodes to be left before reaching the left-th node: left - 1
        for (int i = 1; i < left; i++) {
            leftPrev = leftPrev.next;
            leftNode = leftNode.next;
        }

        // Step 2: Reverse the intended nodes
        // Number of nodes to be reversed: right - left + 1
        ListNode current = leftNode;
        ListNode prev = null;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode front = current.next;
            current.next = prev;
            // Move the pointers
            prev = current;
            current = front;
        }

        // Step 3: Redo the linkage of reversed sub list with remaining portion of original list
        // Now, prev is the new head of the reversed sub linked list
        leftPrev.next = prev;
        leftNode.next = current; // Make the tail of reversed linked list point towards the right hanging part of the original list

        return dummy.next;
    }

}
