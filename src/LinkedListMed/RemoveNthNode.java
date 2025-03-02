package LinkedListMed;

import FastAndSlowPointers.ListNode;

public class RemoveNthNode {

    public ListNode removeNthFromEndOptimal(ListNode head, int n) {
        if (head == null) return head;

        // Using Fast and Slow pointers
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        // Move fast ahead by 'n' nodes
        for (int i = 0; i < n; i++) {
            fastPtr = fastPtr.next;
        }
        // But if the len of list and the nth node are equal then return the head.next
        // i.e. fastPtr reaches null from the back end
        if (fastPtr == null) return head.next;

        // Now, move 'em both simultaneously,
        // and stop when fast reaches the last node
        while (fastPtr.next != null) {
            fastPtr = fastPtr.next;
            slowPtr = slowPtr.next;
        }

        // Now, slow is at the node before the one to be deleted
        // Do the apt linkage
        slowPtr.next = slowPtr.next.next;

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null; // Just assuming the 'n' would be 1 in case of single node

        // Determine the length of the linked list
        int len = 0;
        ListNode temp = head;
        while (temp != null) { // O(N)
            temp = temp.next;
            len++;
        }

        // If we're removing the first node itself
        if (n == len) return head.next;

        // Get to the node before the one to be deleted
        temp = head;
        for (int i = 1; i < len - n; i++) { // O(len - N)
            temp = temp.next;
        }

        // and do the apt linkage
        temp.next = temp.next.next;

        return head;
    }

}
