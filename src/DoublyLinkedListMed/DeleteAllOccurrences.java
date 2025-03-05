package DoublyLinkedListMed;

import FastAndSlowPointers.ListNode;

public class DeleteAllOccurrences {

    private static Node deleteAllOccurOfX(Node head, int x) {
        // Write your code here
        Node dummyNode = new Node(-1);
        dummyNode.next = head;
        head.prev = dummyNode;
        Node temp = dummyNode;

        while (temp.next != null) {
            if (temp.next.data == x) {
                Node front = temp.next.next;
                temp.next = front;

                if (front != null)
                    front.prev = temp;
            } else
                temp = temp.next; // Move forward only if no deletion happened
        }
        return dummyNode.next;
    }

    private static Node deleteAllOccurOfXII(Node head, int x) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == x) {
                // Even head could contain the target value
                if (temp == head)
                    head = head.next;

                Node frontNode = temp.next;
                Node prevNode = temp.prev;

                if (prevNode != null)
                    prevNode.next = frontNode;
                if (frontNode != null)
                    frontNode.prev = prevNode;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

}
