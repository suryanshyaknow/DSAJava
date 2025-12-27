package LinkedList;

public class OddEvenLL {

    public ListNode oddEvenList(ListNode head) {
        // Just gotta redo the linkages of odd and even nodes
        // and at the end attach the tail of the odd node to
        // the even's head. That'll be all.

        if (head == null || head.next == null) return head;

        ListNode oddPtr = head;
        ListNode evenPtr = head.next;
        ListNode evenHead = head.next;

        // Since evenPtr is leading, it ensures in advance that no null pointers are accessed,
        // which indirectly protects oddPtr from running into null-related issues.
        while (evenPtr != null && evenPtr.next != null) {
            // Point the odd ptr to the next odd node
            // Move the odd ptr
            oddPtr.next = oddPtr.next.next;
            oddPtr = oddPtr.next;

            evenPtr.next = evenPtr.next.next;
            evenPtr = evenPtr.next;
        }
        // Attach odd's tail to the even's head
        oddPtr.next = evenHead;

        return head;
    }

}
