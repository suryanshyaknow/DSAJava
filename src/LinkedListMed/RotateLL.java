package LinkedListMed;

import FastAndSlowPointers.ListNode;

public class RotateLL {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode temp = head;
        int len = findLen(head);
        // Edge Case: If k is the multiple of len
        if (k % len == 0) return head;

        k = k % len;


        int nIterations = len - k - 1;
        while (nIterations != 0) {
            temp = temp.next;
            nIterations--;
        }
        // temp is the pivot now
        ListNode newHead = temp.next;
        temp.next = null;

        // Do the linkage
        ListNode newTemp = newHead;
        while (newTemp.next != null) {
            newTemp = newTemp.next;
        }

        newTemp.next = head;
        return newHead;
    }

    private int findLen(ListNode head) {
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len += 1;
        }
        return len;
    }
}
