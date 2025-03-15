package LinkedList.LinkedListMed;

import LinkedList.FastAndSlowPointers.ListNode;

public class SegregateLL {

    // Given a linked list where nodes can contain values 0s, 1s, and 2s only. The task is to segregate 0s, 1s,
    // and 2s linked list such that all zeros segregate to the head side, 2s at the end of the linked list, and
    // 1s in the middle of 0s and 2s.

    static ListNode segregate(ListNode head) {
        if (head == null || head.next == null) return head;

        // We just gotta segregate the LL into three parts of 0s, 1s, and 2s,
        // and do the apt linkage
        // Create three dummy nodes one for each val
        ListNode zeroHead = new ListNode(-1);
        ListNode zeroPtr = zeroHead;

        ListNode oneHead = new ListNode(-1);
        ListNode onePtr = oneHead;

        ListNode twoHead = new ListNode(-1);
        ListNode twoPtr = twoHead;

        ListNode temp = head;
        // Now traverse and segregate
        while (temp != null) {
            if (temp.val == 0) {
                zeroPtr.next = temp;
                zeroPtr = temp;
            } else if (temp.val == 1) {
                onePtr.next = temp;
                onePtr = temp;
            } else {
                twoPtr.next = temp;
                twoPtr = temp;
            }
            temp = temp.next;
        }
        // Connect the tail of zeroes part w the head of ones
        zeroPtr.next = oneHead.next; // Note that this i.e. oneHead.next could also be null

        // Now, connect the tail of ones to the head of two's
        if (oneHead.next != null)
            onePtr.next = twoHead.next;
        else
            zeroPtr.next = twoHead.next; // Connect the tail of the zeroes w twos directly

        // Two's tail must always point to null
        twoPtr.next = null;

        return zeroHead.next;
    }


    static ListNode segregateUsingBruteForce(ListNode head) {
        if (head == null || head.next == null) return head;

        // Maintain the count of each value and re-populate the LL accordingly
        int zeroCounter = 0;
        int oneCounter = 0;
        int twoCounter = 0;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val == 0) {
                zeroCounter++;
            } else if (temp.val == 1) {
                oneCounter++;
            } else {
                twoCounter++;
            }
            temp = temp.next;
        }

        temp = head;
        while (temp != null && zeroCounter != 0) {
            temp.val = 0;
            temp = temp.next;
            zeroCounter--;
        }
        while (temp != null && oneCounter != 0) {
            temp.val = 1;
            temp = temp.next;
            oneCounter--;
        }
        while (temp != null && twoCounter != 0) {
            temp.val = 2;
            temp = temp.next;
            twoCounter--;
        }
        return head;
    }

}
