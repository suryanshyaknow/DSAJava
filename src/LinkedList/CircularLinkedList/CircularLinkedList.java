package LinkedList.CircularLinkedList;

public class CircularLinkedList {

    Node tail;

    public void append(int data) {
        Node newNode = new Node(data);

        // Edge Case 1: Empty list
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
            return;
        }
        newNode.next = tail.next; // Point the newNode towards head
        tail.next = newNode; // Make the tail point towards the newNode
        tail = newNode; // Update tail to the newNode
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);

        if (tail == null) {
            tail = newNode;
            tail.next = tail;
            return;
        }
        newNode.next = tail.next; // Point newNode to the head
        tail.next = newNode; // Point tail to the new head
    }

    public void insertAtIdx(int data, int idx) {
        Node newNode = new Node(data);

        // Edge Case 1: List in empty
        if (tail == null) {
            System.out.println("Empty List!");
            return;
        }

        // Edge Case 2: Idx ==
        if (idx == 0) {
            newNode.next = tail.next;
            tail.next = newNode;
            return;
        }

        Node ptr = tail.next;
        for (int i = 0; i < idx - 1; i++) {
            ptr = ptr.next;

            // If we complete a lap without finding the position
            if (ptr == tail.next) {
                System.out.println("Index out of bounds!");
                return;
            }
        }
        if (ptr == tail) { // Implying the ptr has completed a lap
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
            return;
        }
        // Now the pointer is at the idx before
        newNode.next = ptr.next;
        ptr.next = newNode;
    }

    // Display the list
    public void display() {
        if (tail == null) {
            System.out.println("Empty list!");
            return;
        }

        Node ptr = tail.next;
        do {
            System.out.print(ptr.data + " -> ");
            ptr = ptr.next;
        } while (ptr != tail.next);
        System.out.println(ptr.data);
    }

    // Delete the first node
    public void deleteFirst() {
        if (tail == null) {
            System.out.println("Empty List!");
            return;
        }
        tail.next = tail.next.next;
    }

    // Delete the end node
    public void deleteLast() {
        if (tail == null) {
            System.out.println("Empty List!");
            return;
        }
        Node ptr = tail.next;
        while (ptr.next != tail) {
            ptr = ptr.next;
        }
        ptr.next = tail.next;
        tail = ptr; // System.out.print(circularLinkedList.tail.data);
    }

    // Delete the Node by idx
    public void deleteByIdx(int idx) {
        if (tail == null) {
            System.out.print("Empty list!");
            return;
        }

        if (idx == 0) {
            deleteFirst();
            return;
        }

        Node ptr = tail.next;
        for (int i = 0; i < idx - 1; i++) {
            ptr = ptr.next;
            if (ptr == tail) {
                System.out.println("Index outta bounds!");
                return;
            }
            System.out.println("Ptr is at: " + ptr.data);
        }

        if (ptr.next == tail) {
            deleteLast();
            return;
        }
        ptr.next = ptr.next.next;
    }

    // Delete the node by idx: Two pointers
    public void deleteByIdx2(int idx) {
        if (tail == null) {
            System.out.print("Empty list!");
            return;
        }

        if (idx == 0) {
            deleteFirst();
            return;
        }

        Node ptr1 = tail.next;
        Node ptr2 = ptr1.next;
        for (int i=0; i < idx -1; i++) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            if (ptr1 == tail) {
                System.out.println("Index outta bounds!");
                return;
            }
        }

        if (ptr2 == tail) {
            deleteLast();
            return;
        }
        ptr1.next = ptr2.next;
    }

    // Delete the Node by value
    public void deleteByValue(int data) {
        if (tail == null) {
            System.out.print("Empty list!");
            return;
        }

        Node ptr = tail.next;
        if (ptr.data == data) { // Head's gotta be removed
            deleteFirst();
            return;
        }

        while (ptr.next.data != data && ptr != tail) { // A node before the one to be deleted and before the tail node
            ptr = ptr.next;
        }

        // Node ain't found
        if (ptr.next.data != data) {
            System.out.println("Node ain't found!");
            return;
        }

        if (ptr.next == tail) { // Tail's gotta be deleted cuz we know the next node is the tail node
            deleteLast();
        } else {
            ptr.next = ptr.next.next;
        }

    }

}
