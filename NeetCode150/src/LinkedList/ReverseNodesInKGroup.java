package LinkedList;

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        // Intuition: Pretty simple logic. Just gotta keep
        // track of the prev and next of the group to be
        // reversed.
        // Also, org. head of group to be reverted. Kth node.

        if (head == null)
            return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy; // group prev; group to be reversed
        while (true) {
            ListNode groupPrev = curr;
            ListNode orgHead = groupPrev.next;
            ListNode kth = getKthNode(orgHead, k);
            if (kth == null)
                break;
            ListNode groupNext = kth.next;

            ListNode newHead = reverse(groupPrev.next, kth);

            // Now do the group extremes linkage
            groupPrev.next = newHead;
            orgHead.next = groupNext; // orgHead is the new tail

            // Move curr to the groupPrev of next group to be reversed
            curr = orgHead;
        }

        return dummy.next;

        // Time:
    }

    private ListNode reverse(ListNode head, ListNode kth) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode stop = kth.next;
        while (curr != null && curr != stop) {
            ListNode front = curr.next;
            curr.next = prev;

            // Move the pointers
            prev = curr;
            curr = front;
        }

        return prev;

        // Time: O(N)
    }

    private ListNode getKthNode(ListNode head, int k) {
        ListNode curr = head;
        k -= 1;
        while (curr != null && k != 0) {
            curr = curr.next;
            k -= 1;
        }
        return curr;
    }

}
