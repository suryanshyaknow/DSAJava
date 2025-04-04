package LinkedList.LinkedListMed;

import LinkedList.FastAndSlowPointers.ListNode;

import java.util.HashSet;
import java.util.Set;

public class IntersectionLL {

    // Given the heads of two singly linked-lists headA and headB,
    // return the node at which the two lists intersect. If the two linked lists have no intersection at all,
    // return null.

    public ListNode getIntersectionNodeII(ListNode headA, ListNode headB) { // Most Optimal
        if (headA == null || headB == null) return null;

        // We'll move pointers simultaneously,
        // but with the catch being after one lap the pointer will restart from the opposite linked list.
        // This somehow levels the ground for both the pointers
        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != tempB) { // Cuz we aren't checking the nodes at the very first iteration in case where a single ll is given
            // Pointers shall be moving
            tempA = tempA.next;
            tempB = tempB.next;

            // If they collide during traversal
            if (tempA == tempB)
                return tempA; // This even satisfy the condition where tempA and tempB eventually becomes null simultaneously

            // If they reach their respective ends, start from the opposite list
            if (tempA == null) tempA = headB;
            if (tempB == null) tempB = headA;
        }
        return tempA;
    }

    public ListNode getIntersectionNodeI(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // To find the intersecting node, both the linked lists lengths should be equal,
        // so that the pointers could be moved and compared simultaneously.
        // In other words, the pointer traversing the longer linked list gotta have that head start.
        ListNode tempA = headA;
        int lenA = 0;
        while (tempA != null) { // O(N1)
            lenA++;
            tempA = tempA.next;
        }
        tempA = headA;

        ListNode tempB = headB;
        int lenB = 0;
        while (tempB != null) { // O(N2)
            lenB++;
            tempB = tempB.next;
        }
        tempB = headB;

        int lenDiff = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            // tempA gotta have that head start
            while (lenDiff != 0) {
                tempA = tempA.next;
                lenDiff--;
            }
        } else {
            // tempB gotta have that head start
            while (lenDiff != 0) {
                tempB = tempB.next;
                lenDiff--;
            }
        } // O(N2 - N1)

        // Now move the pointers simultaneously and conduct the matches
        while (tempA != null && tempB != null) { // O(N1) i.e. shorter length
            if (tempA == tempB) return tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;

        // Time Complexity: O(N1) + O(N2) + O(N2 - N1) + O(N1) = O(N1 + 2*N2)
    }

    public ListNode getIntersectionNodeUsingBruteForce(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // Maintain a hash data structure and memorize every node of one linked list,
        // and traverse through the other checking the existence of each node in said data structure.
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            nodeSet.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (nodeSet.contains(temp)) return temp;
            temp = temp.next;
        }
        return null;
    }

}
