package LinkedList;

public class MergeSortLL {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        return mergeSortHelper(head);
    }

    private static ListNode mergeSortHelper(ListNode head) {
        // Base case: Single ele left
        if (head == null || head.next == null)
            return head;

        // Figure the mid of the ll
        ListNode mid = getMidNode(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null; // detach the right ll

        left = mergeSortHelper(left);
        right = mergeSortHelper(right);

        return mergeTwoSortedLL(left, right);
    }

    private static ListNode mergeTwoSortedLL(ListNode list1, ListNode list2) {
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

                ptr1  = ptr1.next;
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
    }

    private static ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Here we want the first mid in case of even num of nodes
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
