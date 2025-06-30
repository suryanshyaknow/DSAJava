package Queue.QueueLinkedList;

public class QueueLinkedList {

    private static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public QueueLinkedList(int size) {
        this.front = this.rear = null;
        this.size = size;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
            size++;
        }
        return newNode.data;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("No elements left to dequeue!");
            return Integer.MIN_VALUE;
        }
        int dequeuedData = front.data;
        if (rear == front) { // Single ele
            rear = front = null;
            size--;
            return Integer.MIN_VALUE;
        } else {
            front = front.next;
            size--;
            return dequeuedData;
        }
    }

    public void display() {
        Node ptr = front;
        if (ptr == null) {
            System.out.println("Empty queue!.");
            return;
        }
        while (ptr.next != null) {
            System.out.print(ptr.data + " -> ");
            ptr = ptr.next;
        }
        System.out.println(ptr.data);
    }

    public static void main(String[] args) {
        QueueLinkedList queueLinkedList = new QueueLinkedList(5);
        queueLinkedList.display();

        queueLinkedList.dequeue();

        queueLinkedList.enqueue(11);
        queueLinkedList.enqueue(17);
        queueLinkedList.enqueue(15);
        queueLinkedList.enqueue(26);
        queueLinkedList.enqueue(18);
        queueLinkedList.display();

        queueLinkedList.dequeue();
        queueLinkedList.display();

        queueLinkedList.dequeue();
        queueLinkedList.display();

        queueLinkedList.dequeue();
        queueLinkedList.display();

        queueLinkedList.dequeue();
        queueLinkedList.display();

        queueLinkedList.dequeue();
        queueLinkedList.display();

        queueLinkedList.dequeue();
        queueLinkedList.display();
    }

}
