package Stack.StackLinkedList;

public class StackLinkedList {

    private Node top;
    private Integer size;

    boolean isEmpty() {
        return top == null;
    }

    public void push(int data) {
        try {
            Node newNode = new Node(data);
            if (top == null) { // Empty stack!
                top = newNode;
                return;
            }
            newNode.next = top;
            top = newNode;
            size++;
        } catch (Exception e) {
            System.out.println("Heap memory exhausted! Stack's full.");
        }
    }

    public void pop() {
        if (top == null) {
            System.out.println("Stack Underflow!");
            return;
        }
        top = top.next; // Even if there's a single node in stack, this'd automatically set top to null upon popping
        size--;
    }

    public int stackBottom() {
        if (top == null) {
            System.out.println("Empty stack!");
            return Integer.MIN_VALUE;
        }

        Node ptr = top;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        return ptr.data;
    }

    public int stackTop() {
        if (top == null) {
            System.out.println("Empty stack!");
            return Integer.MIN_VALUE;
        }
        return top.data;
    }

    public void display() {
        if (top == null) {
            System.out.println("Stack Underflow!");
            return;
        }

        Node ptr = top;
        int counter = 1;
        while (ptr.next != null) {
            System.out.printf("The value at position %d is %d\n", counter, ptr.data);
            ptr = ptr.next;
            counter++;
        }
        System.out.printf("The value at position %d is %d\n", counter, ptr.data);
    }

    public int peek(int pos) {
        if (pos <= 0) {
            System.out.println("Invalid index!");
            return Integer.MIN_VALUE;
        }
        if (top == null) {
            System.out.println("Stack is empty for you to peek at any position!");
            return Integer.MIN_VALUE;
        }

        Node ptr = top;
        int counter = 1;
        while (ptr != null && counter < pos) {
//            System.out.printf("Iteration %d\n", counter);
            ptr = ptr.next;
            counter++;
        }
        if (ptr != null) {
            return ptr.data;
        }
        System.out.println("Index outta bounds!");
        return Integer.MIN_VALUE;

    }

}
