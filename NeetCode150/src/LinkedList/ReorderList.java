package LinkedList;

public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Intuition: See, we can observe that if we split the list
        // into two halves, then we can simply iterate the first half
        // and connect each node at a time w the last node from the
        // second halve.
        // But see since we have to connect the right halve from the
        // it makes sense to reverse it for the natural transition of
        // the pointers.

        // 1. Spot the mid using fast and slow pointers
        ListNode s = head; // slowPtr
        ListNode f = head; // fastPtr
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        ListNode mid = s;

        // 2. Decouple the both halves
        ListNode splitHead = mid.next;
        mid.next = null; // two halves have been decoupled

        // 3. Reverse the splitHead halve
        splitHead = reverseLL(splitHead);

        // 4. Implement the intuition
        ListNode curr = head;
        while (splitHead != null) {
            // Take splitHead and connect it w curr
            // point the curr to its org next
            // point the splitHead to curr's next
            // Move the splithead to next in split halve
            ListNode front = curr.next;
            curr.next = splitHead;
            ListNode splitFront = splitHead.next;
            splitHead.next = front;

            curr = front;
            splitHead = splitFront;
        }

        // Time: O(N + N/2 + N) ~ O(N) -- spot mid, reverse halve, iterate
        // Space: O(1)
    }

    private static ListNode reverseLL(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }

        return prev;
    }
}
