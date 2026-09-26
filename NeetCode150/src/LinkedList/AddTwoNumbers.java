package LinkedList;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Intuition: Just gotta add them normally, ensuring
        // carry does its deed. Thye're already reversed for
        // us so transition of carry from left to right is natural.

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode l = l1;
        ListNode r = l2;

        // Iterate till the longest list's end
        int carry = 0;
        while (l != null || r != null) {
            int val1 = l != null ? l.val : 0;
            int val2 = r != null ? r.val : 0;

            int sum = val1 + val2 + carry;
            int newVal = sum % 10;
            carry = sum / 10;

            curr.next = new ListNode(newVal);

            // Move the pointers
            curr = curr.next;
            l = l != null ? l.next : null;
            r = r != null ? r.next : null;
        }

        // Now if carry is still left out
        if (carry != 0) {
            curr.next = new ListNode(carry);
            // curr = curr.next;
        }

        return dummy.next;

        // Time: O(max(N1, N2))
        // Space: O(1)
    }

}
