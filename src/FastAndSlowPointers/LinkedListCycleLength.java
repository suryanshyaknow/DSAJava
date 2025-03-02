package FastAndSlowPointers;

public class LinkedListCycleLength {

    public static int findCycleLength(ListNode head) {
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next; // Moves twice as fast as the slow pointer
            if (fastPtr == slowPtr)
                return calculateLength(slowPtr); // I.e. they've met somewhere in the middle of the cycle
        }
        return 0;
    }

    private static int calculateLength(ListNode startingPoint) {
        int length = 0;
        ListNode currentPtr = startingPoint;
        do {
            currentPtr = currentPtr.next;
            length += 1;
        } while (currentPtr != startingPoint);
        return length;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next; // Creating a cycle in the linked list
        System.out.println("LinkedList cycle length: "
                + findCycleLength(head));
    }

}
