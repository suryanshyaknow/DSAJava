package LinkedList.LinkedListMed;

import LinkedList.FastAndSlowPointers.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeTwoSortedLists {

    public static ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode t1 = list1;
        ListNode t2 = list2;
        ListNode temp = dummy;
        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                temp.next = t1;
                temp = t1;
                t1 = t1.next;
            } else {
                temp.next = t2;
                temp = t2;
                t2 = t2.next;
            }
        }
        // If t1 is exhausted first off
        if (t1 == null) {
            temp.next = t2;
        }
        if (t2 == null) {
            temp.next = t1;
        }
        // If t2 is exhausted
        return dummy.next;
    }


    public static ListNode mergeTwoSortedListsUsingBruteForce(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Traverse list 1 and populate an array
        List<Integer> arr = new ArrayList<>();
        ListNode temp1 = list1;
        while (temp1 != null) {
            arr.add(temp1.val);
            temp1 = temp1.next;
        }

        // Traverse list 2 and populate the same array
        ListNode temp2 = list2;
        while (temp2 != null) {
            arr.add(temp2.val);
            temp2 = temp2.next;
        }

        // Sort the array and convert into linked list
        ListNode beforeHead = new ListNode(-1);
        ListNode temp = beforeHead;
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            temp.next = new ListNode(arr.get(i));
            temp = temp.next;
        }
        return beforeHead.next;
    }
}
