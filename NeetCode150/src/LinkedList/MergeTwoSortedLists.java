package LinkedList;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode ptr1 = list1;
        ListNode ptr2 = list2;
        ListNode dummy = new ListNode(-1); // dummy node to avoid head edge cases
        ListNode curr = dummy;

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val <= ptr2.val) {
                curr.next = ptr1;
                curr = ptr1;
                ptr1 = ptr1.next;
            } else {
                curr.next = ptr2;
                curr = ptr2;
                ptr2 = ptr2.next;
            }
        }

        // Now if either LL is left untraversed
        if (ptr1 != null) {
            curr.next = ptr1;
        }

        if (ptr2 != null) {
            curr.next = ptr2;
        }

        return dummy.next;

        // Time: O(N + M)
    }
}
