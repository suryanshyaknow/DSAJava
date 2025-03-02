package LinkedListMed;

import FastAndSlowPointers.ListNode;

public class PlusOneLinkedList {

    public static ListNode addOneUsingRecursion(ListNode head) {
        if (head == null) return null;

        int carry = helper(head);
        if (carry == 1) { // New Node is to be created
            ListNode newNode = new ListNode(1);
            newNode.next = head;
            return newNode;
        }
        return head;
    }

    private static int helper(ListNode temp) {
        // Breaking the recursion and starting the backtracking
        // i.e. executing the base case
        if (temp == null)
            return 1; // carry
        // Go deep till temp reaches the last node
        int carry = helper(temp.next);

        temp.val = temp.val + carry; // Critical step cuz backtracking regardless of carry processes each node and carry could either be 0 or 1
        if (temp.val < 10) {
            return 0; // No further additions
        } else {
            temp.val = 0;
            return 1;
        }
    }

    public static ListNode addOneII(ListNode head) {
        if (head == null) return null;
        // For our convenience, to be able to alter the nodes from the beginning during traversal,
        // we'd reverse the LL and based on the carry we'd the addition aptly.

        head = reverseLL(head); // O(N)

        ListNode temp = head;
        int carry = 1;
        while (temp != null) {
            int newVal = temp.val + carry;
            if (newVal < 10) {
                carry = 0; // No further additions
                temp.val = newVal;
                break;
            } else {
                temp.val = 0;
            }
            temp = temp.next;
        } // O(N)

        head = reverseLL(head); // O(N)

        if (carry == 1) { // New digit is to be appended
            // Create a new node having val 1 and point it towards head
            ListNode newDigitNode = new ListNode(1);
            newDigitNode.next = head;
            return newDigitNode;
        }

        return head;
    }

    public static ListNode addOneI(ListNode head) { // Amateur, boohoo!
        if (head == null) return null;
        // For our convenience, to be able to alter the nodes from the beginning during traversal,
        // we'd reverse the LL and based on the carry we'd the addition aptly.

        head = reverseLL(head); // O(N)

        ListNode temp = head;
        ListNode prev = null;
        int carry = 0;
        while (temp != null) {
            int newVal = temp.val + 1;
            if (newVal < 10) {
                carry = 0;
                temp.val = newVal;
                break;
            } else {
                carry = 1;
                temp.val = 0;
            }
            prev = temp;
            temp = temp.next;
        } // O(N)
        if (carry > 0) {
            prev.next = new ListNode(1);
        }

        // Re reverse the LL
        return reverseLL(head); // O(N)
    }

    private static ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode front = curr.next;
            curr.next = prev;
            // Move the pointers
            prev = curr;
            curr = front;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        addOneI(head);
    }

}
