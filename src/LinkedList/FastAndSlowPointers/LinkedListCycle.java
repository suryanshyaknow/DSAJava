package LinkedList.FastAndSlowPointers;

import java.util.HashMap;
import java.util.Map;

public class LinkedListCycle {

//    Given head, the head of a linked list, determine if the linked list has a cycle in it.
//
//    There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
//    following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
//    is connected to. Note that pos is not passed as a parameter.
//
//    Return true if there is a cycle in the linked list. Otherwise, return false.

    public static boolean hasCycle(ListNode head) {
        // TODO: Write your code here
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next; // Slow ptr moves one step at a time
            fastPtr = fastPtr.next.next; // Fast ptr moves two steps at a time
            if (slowPtr == fastPtr) // Check if they meet at some point indicating a cycle
                return true;
        }
        return false; // No cycle found
    }

    public static boolean hasCycleUsingBruteForce(ListNode head) {
        /*
            - We'll iterate through the linked list and store the nodes as keys in a hash map
            - And the moment we encounter a node (as in whole) already stored in the map, it implies the list has a cycle.
         */
        ListNode ptr = head;
        Map<ListNode, Boolean> nodesMap = new HashMap<>();
        while (ptr != null) {
            if (!nodesMap.containsKey(ptr)) {
                nodesMap.put(ptr, true);
                ptr = ptr.next; // Move the pointer forward
            } else
                return true;
        }
        return false;
    }

    public static void display(ListNode head) {
        // Assuming it's a singly linked list
        if (head == null) return;

        ListNode ptr = head;
        do {
            System.out.print(ptr.val + " -> ");
            ptr = ptr.next;
        } while (ptr != head);
        System.out.println("null");
    }

    public static void displayCyclic(ListNode head) {
        if (head == null) return;

        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " -> ");
            ptr = ptr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
//        display(head);
//        System.out.println("LinkedList has cycle: " + hasCycle(head));

        // Creating a cycle by connecting the last node to the third node.
        head.next.next.next.next.next.next = head.next.next;
        displayCyclic(head);
        System.out.println("LinkedList has cycle: " + hasCycle(head));

    }

}
