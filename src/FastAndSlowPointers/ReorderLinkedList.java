package FastAndSlowPointers;

public class ReorderLinkedList {

    public ListNode reorder(ListNode head) {
        // TODO: Write your code here
        if (head == null || head.next == null)
            return head;

        // Step 1: Find the middle
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr.next != null && fastPtr.next.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // *To prevent cycle formation, break the linkage between two halves*
        ListNode nextToMid = slowPtr.next;
        slowPtr.next = null;

        // Step 2: Reverse the second half
        ListNode secondHalfHead = reverse(nextToMid);

        // Step 3: Do the alternate linkage
        ListNode first = head;
        ListNode second = secondHalfHead;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            // Do the alternate linkage
            first.next = second;
            second.next = temp1;
            // Move the pointers
            first = temp1;
            second = temp2;
        }
        return head;
    }

    private ListNode reverse(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;

        while (temp != null) {
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }

    public static void main(String[] args) {

    }

}
