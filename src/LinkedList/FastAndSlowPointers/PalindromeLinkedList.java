package LinkedList.FastAndSlowPointers;

import java.util.Stack;

public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        // Edge Case: No nodes or a single node
        if (head == null || head.next == null)
            return true;

        // Step 1: Find the middle of the linked list
        // Using fast and slow pointers
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr.next != null && fastPtr.next.next != null) { // Cuz we gotta stop at the first mid in case of even nodes
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        ListNode mid = slowPtr;

        // Step 2: Reverse the second half
        ListNode secondHead = reverse(mid.next);

        // Step 3: Compare the first half to the second half to determine the palindromeness
        ListNode first = head;
        ListNode second = secondHead;
//        while (first != mid.next) {
        while (second != null) { // Cuz the second half would get exhausted first
            if (first.val != second.val) {
                reverse(secondHead); // Revert the list to its original condition
                return false;
            }
            first = first.next;
            second = second.next;
        }

        reverse(secondHead); // Revert the list to its original condition
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode front = current.next;
            current.next = prev;
            // Move the pointers
            prev = current;
            current = front;
        }
        return prev;
    }


    public boolean isPalindromeBruteForce(ListNode head) {
        // TODO: Write your code here

        Stack<Integer> stack = new Stack<>();
        // Parse the linked list and populate the stack
        ListNode ptr = head;
        while (ptr != null) {
            stack.add(ptr.val);
            ptr = ptr.next;
        }

        // Now since stack is LIFO, we're to match the elements from it to the list and determine if given linked list is palindrome
        ListNode ptr2 = head;
        while (ptr2 != null) {
            if (ptr2.val != stack.pop())
                return false;
            ptr2 = ptr2.next;
        }
        return true;
    }

}
