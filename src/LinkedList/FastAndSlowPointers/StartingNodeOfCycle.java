package LinkedList.FastAndSlowPointers;

public class StartingNodeOfCycle {

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

        /*
            Mathematical Intuition:

            a = dist from head to loop start
            b = dist where fast and slow meet
            c = length of loop

            When fast and slow meet:
                - Slow dist travelled = a + b
                - Fast dist travelled = a + b + n * c where n being the laps travelled by fast
                - But, Fast dist travelled also = 2 * (a + b) as it moves twice as fast as slow

                Now, 2(a + b) = a + b + n * c
                a = b - n*c which is exactly the distance from the meeting point to loop start.
         */
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

