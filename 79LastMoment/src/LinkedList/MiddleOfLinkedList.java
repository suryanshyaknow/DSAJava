package LinkedList;

public class MiddleOfLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Implementing tortoise and hare algo
        // In case of even number of nodes, fastPtr would reach beyond the last node
        // And for odd number of nodes, fastPtr would reach the last node
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next; // Skip a node
        }

        return slowPtr;
    }

}
