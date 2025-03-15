package LinkedList.LinkedListMed;

import LinkedList.FastAndSlowPointers.ListNode;

public class AddTwoNumbersLL {

    public static ListNode addTwoNumbersOptimal(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        int carry = 0;

        while (temp1 != null || temp2 != null) {
            int sum = carry;
            if (temp1 != null && temp2 != null) {
                sum += temp1.val + temp2.val;
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else if (temp1 != null) {
                sum += temp1.val;
                temp1 = temp1.next;
            } else {
                sum += temp2.val;
                temp2 = temp2.next;
            }

            if (sum >= 10) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }

            temp.next = new ListNode(sum);
            temp = temp.next;
        }

        if (carry > 0) {
            // New node is to be created
            temp.next = new ListNode(carry);
        }

        return dummyNode.next;

        // Time complexity: O(max(N1, N2))
        // Space complexity: O(max(N1, N2))
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        if (head2 == null) return head1;
        if (head1 == null) return head2;

        // First off, reverse them and figure out the lengths of them both
//        int len1 = findLen(head1);
//        int len2 = findLen(head2);
//        int lenNewList = Math.max(len2, len1);

        // Constructing new sum list
        ListNode dummyNode = new ListNode(-1);
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        ListNode temp = dummyNode;
        int carry = 0;
        while (temp1 != null || temp2 != null) {
            int sum = 0;
            if (temp1 != null && temp2 != null) {
                sum = temp1.val + temp2.val + carry;
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else if (temp2 == null) {
                sum = temp1.val + carry;
                temp1 = temp1.next;
            } else {
                sum = temp2.val + carry;
                temp2 = temp2.next;
            }

            if (sum < 10) {
                carry = 0;
            } else {
                carry = 1;
                sum -= 10;
            }

            temp.next = new ListNode(sum);
            temp = temp.next;
        }

        ListNode newHead = dummyNode.next;
        if (carry != 0) {
            temp.next = new ListNode(carry);
        }
        return newHead;
    }

    public static void main(String[] args) {
    }

}
