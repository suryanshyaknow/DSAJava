package LinkedList.DoublyLinkedListMed;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoSumInSortedDLL {

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        // code here
        if (head == null || head.next == null) return new ArrayList<>();

        Node leftPtr = head;
        Node rightPtr = findTail(head);
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

        while (leftPtr != null && rightPtr != null && rightPtr.data > leftPtr.data) { // Cuz the list is said to be sorted
            int sum = rightPtr.data + leftPtr.data;

            if (sum == target) {
                pairs.add(new ArrayList<>(Arrays.asList(leftPtr.data, rightPtr.data)));
                leftPtr = leftPtr.next;
                rightPtr = rightPtr.prev;

                // Gotta skip the dupes
                while (leftPtr != null && leftPtr.data == leftPtr.prev.data) leftPtr = leftPtr.next;
                while (rightPtr != null && rightPtr.data == rightPtr.next.data) rightPtr = rightPtr.prev;
            } else if (sum > target)
                rightPtr = rightPtr.prev;
            else
                leftPtr = leftPtr.next;
        }
        return pairs;
    }

    private static Node findTail(Node head) {
        if (head == null || head.next == null) return null;
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }
}
