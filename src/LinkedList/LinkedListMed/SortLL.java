package LinkedList.LinkedListMed;

import LinkedList.FastAndSlowPointers.ListNode;

public class SortLL {

    public ListNode sortList(ListNode head) {
        // Edge case
        if (head == null || head.next == null) return head;

        // Find the middle of the linked list
        ListNode mid = findMiddle(head);
        ListNode leftHead = head;
        ListNode rightHead = mid.next;
        // To separate out the lists
        mid.next = null;

        leftHead = sortList(leftHead); // Left sorted part
        rightHead = sortList(rightHead); // Right sorted part

        // Now, merge the sorted parts
        return mergeTwoSortedLists(leftHead, rightHead);
    }

    private ListNode mergeTwoSortedLists(ListNode leftHead, ListNode rightHead) {
        if (leftHead == null) return rightHead;
        if (rightHead == null) return leftHead;

        // Create a dummy node and point it towards leftHead
        ListNode dummyNode = new ListNode(-1);

        // Traverse through both nodes, compare, and do the linkage aptly
        ListNode leftPtr = leftHead;
        ListNode rightPtr = rightHead;
        ListNode temp = dummyNode;

        while (leftPtr != null && rightPtr != null) {
            if (leftPtr.val < rightPtr.val) {
                temp.next = leftPtr;
                temp = leftPtr;
                leftPtr = leftPtr.next;
            } else {
                temp.next = rightPtr;
                temp = rightPtr;
                rightPtr = rightPtr.next;
            }
        }
        // Now, one of them might be left, so handle those cases accordingly
        // Join with either left out sorted part
        if (leftPtr == null) {
            temp.next = rightPtr;
        }
        if (rightPtr == null) {
            temp.next = leftPtr;
        }
        return dummyNode.next;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (fastPtr.next != null && fastPtr.next.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }

}
