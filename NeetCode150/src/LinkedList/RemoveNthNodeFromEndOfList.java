package LinkedList;

public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        // Intuition: Fixed gap tech using fast and slow pointes.
        // Maintain a fixed gap of the n bw the both nodes and the
        // moment fastPtr reaches the last node, slow is sitting
        // at the one node before the one to be removed.

        ListNode s = head;
        ListNode f = head;

        // Move f n steps forward before s
        for (int i = 0; i < n; i++) {
            f = f.next;
        }
        // Now if f becomes null, meaning it traversed all nodes
        // then that means head is to be removed
        if (f == null) return head.next;

        // Now move both s and f till the f reaches the last node
        while (f.next != null) {
            s = s.next;
            f = f.next;
        }
        // f is now at the last node meaning s is at one node
        // before the to be deleted node
        s.next = s.next.next;

        return head;
    }
    
}
