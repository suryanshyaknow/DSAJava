package LinkedList;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfLinkedList {

    public ListNode getIntersectionNodeOptimal(ListNode headA, ListNode headB) {
        // Traverse w two pointers (one for each LL), and when
        // either of them reaches at the last node, move them to
        // opposite LLs, and do the fkn comparison.
        if (headA == null || headB == null) return null;

        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != tempB) { // In case, the intersection is at the head node itself

            tempA = tempA.next;
            tempB = tempB.next;

            if (tempA == tempB) break;

            // When either of them reaches the last node
            // switch their fkn heads.
            if (tempA == null)
                tempA = headB;
            if (tempB == null)
                tempB = headA;
        }
        // Note: after switching heads once they'll level and eventually become equal,
        // be it any fkn node or they both reach the end simultaneously and become null.

        // At last, anyway, they're equal
        return tempA;

        // Time complexity: O(N1 + N2)
    }

    public ListNode getIntersectionNodeBetter(ListNode headA, ListNode headB) {
        // If we somehow could make the pointers
        // iterate over each LL at the same level, we could
        // directly do the comparison w each iteration and figure
        // out the intersection node.
        // This ensures we don't need any external data structure.

        // So, figure out the length of each LL.
        // Figure out the offset you need to have for the longer
        // LL and move the pointer that much.
        // And then traverse both the pointers simultaneously.

        ListNode tempA = headA;
        int N1 = 0;
        while (tempA != null) {
            tempA = tempA.next;
            N1++;
        }

        ListNode tempB = headB;
        int N2 = 0;
        while (tempB != null) {
            tempB = tempB.next;
            N2++;
        }

        // Reset both the pointers
        tempA = headA;
        tempB = headB;
        // Move the pointer of the shorter LL by offset
        int offset = Math.abs(N1 - N2);
        if (N1 > N2) {
            // Move tempA by offset
            for (int i=0; i < offset; i++) tempA = tempA.next;
        } else {
            for (int i=0; i < offset; i++) tempB = tempB.next;
        }

        // Move both the pointers simultaneously
        while (tempA != null && tempB != null) {
            if (tempA == tempB) return tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return null;

        // Time complexity: O(N1 + N2 + abs(N1 - N2) + ON2) = O(2N1 + N2)
        // Space complexity: O(1)
    }

    public ListNode getIntersectionNodeBruteForce(ListNode headA, ListNode headB) {
        // Using hashing
        // We simply memorize each node of a LL and try to
        // determine where it exists in another LL.
        Set<ListNode> hashSet = new HashSet<>();

        // Traverse through first LL
        ListNode temp = headA;
        while (temp != null) {
            hashSet.add(temp);
            temp = temp.next;
        }

        // Traverse through second LL and check
        // the existence of each node in hashset
        temp = headB;
        while (temp != null) {
            if (hashSet.contains(temp)) return temp;
            temp = temp.next;
        }

        return null;

        // Time complexity: O(N1 + N2)
        // Space complexity: O(N1)
    }

}
