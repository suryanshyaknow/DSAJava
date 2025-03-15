package LinkedList.LinkedListMed;

import LinkedList.FastAndSlowPointers.ListNode;

import java.util.ArrayList;
import java.util.List;

public class OddEvenLinkedList {

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode oddPtr = head;
        ListNode evenPtr = head.next;
        ListNode evenHead = head.next;

        // Since evenPtr is leading, it ensures in advance that no null pointers are accessed,
        // which indirectly protects oddPtr from running into null-related issues.
        while (evenPtr != null && evenPtr.next != null) {
            oddPtr.next = oddPtr.next.next;
            evenPtr.next = evenPtr.next.next;
            // Move the pointers
            oddPtr = oddPtr.next;
            evenPtr = evenPtr.next;
        }
        // Point last of the odd to evenHead
        oddPtr.next = evenHead;
        return head;
    }

    public static ListNode oddEvenListBruteForce(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // Step 1: Segregate the odd indices vals and put 'em in an array
        ListNode oddTemp = head;
        List<Integer> arr = new ArrayList<>();
        while (oddTemp != null && oddTemp.next != null) {
            arr.add(oddTemp.val);
            oddTemp = oddTemp.next.next; // Skips a node at a time
        }
        if (oddTemp != null) arr.add(oddTemp.val);

        // Step 2: Similarly, the even ones and put 'em too.
        ListNode evenTemp = head.next;
        while (evenTemp != null && evenTemp.next != null) {
            arr.add(evenTemp.val);
            evenTemp = evenTemp.next.next;
        }
        if (evenTemp != null) arr.add(evenTemp.val);

        // Step 3: Traverse and update the values from the array
        ListNode temp = head;
        int i = 0;
        while (temp != null) {
            temp.val = arr.get(i++);
            temp = temp.next;
        }
        return head;
    }
}
