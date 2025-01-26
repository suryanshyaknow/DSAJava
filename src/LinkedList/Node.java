package LinkedList;

public class Node {

    int data;
    Node next; // Reference to the next node

    Node(int data) {
        this.data = data;
        this.next = null; // Indicates that this node is not yet connected to another node
    }

}
