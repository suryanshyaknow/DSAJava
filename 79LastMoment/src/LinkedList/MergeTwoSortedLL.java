package LinkedList;

public class MergeTwoSortedLL {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // We just gotta play w the linkages

        ListNode dummyNode = new ListNode(-1);
        ListNode ptr1 = list1;
        ListNode ptr2 = list2;
        ListNode tempPtr = dummyNode;

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val <= ptr2.val) {
                // Point the tempPtr to the smaller node
                tempPtr.next = ptr1;
                tempPtr = tempPtr.next; // Move the tempPtr

                ptr1 = ptr1.next;
            } else {
                tempPtr.next = ptr2;
                tempPtr = tempPtr.next;

                ptr2 = ptr2.next;
            }
        }

        // If the ptr1 exhausts first
        if (ptr1 == null) tempPtr.next = ptr2;
        // If the ptr2 exhausts first
        if (ptr2 == null) tempPtr.next = ptr1;

        return dummyNode.next;

        // Time complexity: O(N1 + N2)
    }
}
