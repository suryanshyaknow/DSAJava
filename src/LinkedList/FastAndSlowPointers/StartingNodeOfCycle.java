package LinkedList.FastAndSlowPointers;

public class StartingNodeOfCycle {

    public static ListNode findCycleStart(ListNode head) {
        // TODO: Write your code here
        // First off, detect the cycle in linked list and return -1 if there's none
        if (hasCycle(head)) {

            // Second of all, compute the length of the cycle
            int k = getCycleLength(head);

            // Last of all, initialize two pointer at the head of the list.
            ListNode ptr1 = head;
            ListNode ptr2 = head;

            // Move one of the pointer by length of the loop say K nodes.
            for (int i = 0; i < k; i++) {
                ptr2 = ptr2.next;
            }

            // Now, move 'em both one step at a time.
            // The node where they'll meet will be the starting point of the node.
            while (ptr1 != ptr2) {
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }
            return ptr1;
        }
        return null;
    }

    private static int getCycleLength(ListNode head) {
        ListNode fastPtr = head;
        ListNode slowPtr = head;

        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;

            if (fastPtr == slowPtr)
                return computeLengthOfCycle(slowPtr);
        }
        return -1;
    }

    private static int computeLengthOfCycle(ListNode meetingPoint) {
        ListNode ptr = meetingPoint;
        int k = 0;
        do {
            ptr = ptr.next;
            k += 1;
        } while (ptr != meetingPoint);
        return k;
    }

    private static boolean hasCycle(ListNode head) {
        ListNode fastPtr = head;
        ListNode slowPtr = head;

        // Until fastPtr reaches the end (if not a cycle),
        // move the slow by one node and the fast by two.
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;

            // And if at any node they meet, it indicates a cycle
            if (fastPtr == slowPtr)
                return true;
        }
        return false;
    }

    private static ListNode findCycleStartUsingFloydAlgo(ListNode head) {
        // Initialize slow and fast pointers
        // The moment they meet (if in a cycle) in cycle, reset the slow to head
        // Now move 'em both one node at a time, and the point where they meet shall be the starting node

        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
             if (fastPtr == slowPtr)
                 return cycleStart(slowPtr, head);
        }

        return null; // No cycle
    }

    private static ListNode cycleStart(ListNode slowPtr, ListNode head) {
        ListNode anotherPtr = head;

        while (slowPtr != anotherPtr) {
            slowPtr = slowPtr.next;
            anotherPtr = anotherPtr.next;
        }
        return slowPtr; // The meeting point of the slowPtr and anotherPtr
    }


}

