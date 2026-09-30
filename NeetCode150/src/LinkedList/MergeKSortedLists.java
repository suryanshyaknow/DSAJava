package LinkedList;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // Intuition: Just keep merging every two lists
        // and their resultant w the next list
        //
        // New: Instead of iteratively merging nodes with an
        // already-merged list (with larger nodes) on each
        // iteration, we'll pick multiple pairs and merge
        // them in each iteration making out run time log k
        // times rather k times the total number of nodes.
        //
        // // At each level, every node participates in exactly one
        // merge, and there are log(k) levels.
        // Time: O(N log k), where N = total number of nodes.

        int k = lists.length;
        if (k == 0)
            return null;

        while (k > 1) { // Meaning till one merged list is left
            int mergedListIdx = 0;

            for (int i = 0; i < k; i += 2) {
                ListNode mergedList = (i + 1 < k) ? mergeLists(lists[i], lists[i + 1]) : lists[i];
                lists[mergedListIdx] = mergedList;
                mergedListIdx++;
            }
            k = mergedListIdx;
        }

        return lists[0];

        // Time: O(log k * N) provided each list has got N nodes roughly
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode l = l1;
        ListNode r = l2;

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                curr.next = l;
                curr = l;
                l = l.next;
            } else {
                curr.next = r;
                curr = r;
                r = r.next;
            }
        }

        if (l != null)
            curr.next = l;
        if (r != null)
            curr.next = r;

        return dummy.next;
    }
}
