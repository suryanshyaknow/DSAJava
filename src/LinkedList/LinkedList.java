package LinkedList;

import javax.xml.transform.Source;

public class LinkedList {

    private Node head;

    // Add a node to the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null)
            head = newNode; // If list is empty, the new node becomes the head
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode; // Connection formed
        }
    }

    // Display the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void insertAtBeginning(int data) {
        Node node = new Node(data);
        // Point the new node to the current head
        node.next = head;
        // Update head to the new node
        head = node;
    }

    public void insertAt(int data, int idx) {
        Node newNode = new Node(data);
        if (idx == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        if (head == null && idx > 0) {
            System.out.println("List is empty, and the idx is outta bounds!");
            return;
        }

        // Using two pointers
//        Node ptr1 = head;
//        Node ptr2 = head.next;
//        for (int i = 0; i < idx - 1; i++) { // Cuz ptr2 is one step ahead
//            // Edge case 2: Out-of-bounds index
//            if (ptr1 == null || ptr1.next == null) {
//                System.out.println("Index out of bounds!");
//                return;
//            }
//            ptr1 = ptr1.next;
//            ptr2 = ptr2.next;
//        }
//        ptr1.next = newNode;
//        newNode.next = ptr2;

        // Using a single pointer
        Node ptr = head;
        for (int i = 0; i < idx - 1; i++) {
            if (ptr == null) {
                System.out.println("Index outta bounds!");
                return;
            }
            ptr = ptr.next;
        }
        newNode.next = ptr.next;
        ptr.next = newNode;
    }

    public void append(int data) {
        Node newNode = new Node(data);
        Node ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = newNode;
    }

    public void insertAfter(int data, int after) {
        Node newNode = new Node(data);
        Node ptr = head;
        while (ptr != null && ptr.data != after) {
            ptr = ptr.next;
        }
        if (ptr == null) {
            System.out.println("Value NOT found in the list!");
            return;
        }
        newNode.next = ptr.next;
        ptr.next = newNode;
    }

    // Pop the last element from the list
    public void pop() {
        // Edge case 1: Empty list
        if (head == null) {
            System.out.println("List is already empty!");
            return;
        }

        // Edge case 2: Single-node list
        if (head.next == null) {
            head = null;
            return;
        }

//        Node ptr1 = head;
//        Node ptr2 = ptr1.next;
//        while (ptr2.next != null) {
//            ptr1 = ptr1.next;
//            ptr2 = ptr2.next;
//        }
//        ptr1.next = null;

        Node ptr = head;
        while (ptr.next.next != null) {
            ptr = ptr.next;
        }
        ptr.next = null;
    }

    // Delete a node by value from the list
    public void delete(int data) {
        // Edge Case 1: Empty list
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }

        // Edge case 2: Value is at head
        if (head.data == data) {
            head = head.next;
            return;
        }

        Node ptr1 = head;
        Node ptr2 = head.next;
        while (ptr2 != null && ptr2.data != data) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        if (ptr2 == null) {
            System.out.println("Value NOT found in the list!");
            return;
        }
        ptr1.next = ptr2.next;
    }

    public void deleteByIdx(int idx) {
        // If the list is empty or the index is negative, do nothing
        if (head == null || idx < 0) {
            System.out.println("Invalid index!");
            return;
        }

        if (idx == 0) {
            head = head.next;
            return;
        }

        Node ptr = head;
        Node ptr2 = head.next;
        for (int i = 0; i < idx - 1; i++) {
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }
        ptr.next = ptr2.next; // Now ptr2 is the node to be deleted
    }

}
