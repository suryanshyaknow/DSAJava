package LinkedList.DoublyLinkedListMed;

public class RemoveDupesFromSortedDLL {

    Node removeDuplicates(Node head) {
        // Code Here.
        if (head == null || head.next == null) return head;

        // Iterate -> Skip dupes -> do the linkage
        Node temp = head;
        while (temp != null) {
            Node firstOccurrence = temp;
            // Skip the dupes
            while (temp.next != null && temp.next.data == firstOccurrence.data) temp = temp.next;
            // temp is at the last dupe so:
            temp = temp.next;

            // Do the linkage
            firstOccurrence.next = temp;
            if (temp != null)
                temp.prev = firstOccurrence;
        }

        return head;
    }

}
