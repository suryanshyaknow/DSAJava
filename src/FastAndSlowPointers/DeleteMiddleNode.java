package FastAndSlowPointers;

public class DeleteMiddleNode {

    public static ListNode deleteMiddle(ListNode head) {
        // Write your code here.
        if (head == null || head.next == null)
            return null;

        // Step 1: Find the ele before mid
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        ListNode prev = null;
        while (fastPtr != null && fastPtr.next != null) { // Even & Odd number of nodes respectively
            fastPtr = fastPtr.next.next;
            prev = slowPtr;
            slowPtr = slowPtr.next;
        } // slowPtr is at middle of the list

        // Step 2: Store the middle's next and do the linkage
        prev.next = slowPtr.next;

        return head;
    }


}
