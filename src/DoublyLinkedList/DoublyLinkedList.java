package DoublyLinkedList;

public class DoublyLinkedList {

    private Node head;

    // Add nodes to the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.next = newNode;
        newNode.prev = ptr;
    }

    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            add(data);
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void insertAt(int data, int idx) {
        Node newNode = new Node(data);
        if (head == null) {
            add(data);
            return;
        }

        if (idx == 0) {
            insertAtBeginning(data);
            return;
        }
        Node ptr1 = head;
        Node ptr2 = head.next;
        int counter = 0;
        while (ptr1.next != null && counter < idx - 1) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            counter++;
        }

        // newNode's gotta be appended
        if (ptr2 == null) {
            add(data);
            return;
        }

        // Linkage of newNode with the Node that'd be dragged
        newNode.next = ptr2;
        ptr2.prev = newNode;
        // Linkage of newNode with the node before
        ptr1.next = newNode;
        newNode.prev = ptr1;
    }

    void display() {
        Node ptr = head;
        if (head == null) {
            System.out.println("\nEmpty list!");
            return;
        }
        while (ptr.next != null) {
            System.out.print(ptr.data + " -> ");
            ptr = ptr.next;
        }
        System.out.println(ptr.data);
        while (ptr.prev != null) {
            System.out.print(ptr.data + " -> ");
            ptr = ptr.prev;
        }
        System.out.println(ptr.data);
    }

    public void deleteFirst() {
        if (head == null) {
            System.out.print("Empty list, bitch!");
            return;
        }

        if (head.next == null) { // Single element in the list
            head = null;
            return;
        }

        head = head.next;
        head.prev = null;
    }

    public void pop() {
        Node ptr = head;
        if (head == null) {
            System.out.println("Empty list, bitch!");
            return;
        }
        if (head.next == null) { // Single element in the list!
            head = null;
            return;
        }
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        Node prevNode = ptr.prev;
        prevNode.next = null;
    }

    public void deleteAt(int idx) {
        // Empty list
        if (head == null) {
            System.out.println("Empty list, bitch");
            return;
        }
        // Single element in the list
        if (head.next == null) {
            pop();
            return;
        }
        // First element to be deleted
        if (idx == 0) {
            deleteFirst();
            return;
        }

        Node ptr1 = head;
        Node ptr2 = head.next;
        int counter = 0;
        while (ptr2.next != null && counter < idx - 1) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            counter++;
        }
        if (ptr2.next == null) { // Last node's gotta be deleted
            pop();
            return;
        }
        ptr1.next = ptr2.next;
        ptr2.next.prev = ptr1;
    }


}
