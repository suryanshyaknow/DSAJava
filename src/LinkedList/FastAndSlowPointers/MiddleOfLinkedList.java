package LinkedList.FastAndSlowPointers;

public class MiddleOfLinkedList {

    public ListNode findMiddle(ListNode head) {
        // TODO: Write your code here
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next; // Moves two steps at a time
        }
        // The moment fastPtr approaches the end of the list, the slowPtr is at the middle
        return slowPtr;
    }

}
