package Stack.StackArray;

public class StackArray {

    private int[] arr;
    private int top; // Holds the idx of the last element
    private int size;

    StackArray(int size) {
        this.size = size;
        this.top = -1; // Initially empty
        this.arr = new int[size];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public void push(int data) {
        if (top == size - 1) {
            System.out.println("Stack overflow!");
            return;
        }

        arr[++top] = data; // Element inserted and top updated
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Empty stack!");
            return -1;
        }
        return arr[--top];
    }

    // Peek operation
    public int peek(int position) { // Assuming the top element is at the 1st position
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        // Edge Case: Wrong position
        if (position - 1 > top) {
            System.out.println("Invalid idx!");
            return -1;
        }
        // (top - i + 1)
        return arr[top - position + 1];
    }

    public void display() {
//        for (int i = top; i > 0; i--) {
//            System.out.print(arr[i] + " -> ");
//        }
//        System.out.print(arr[0]);

        for (int pos = 1; pos <= top + 1; pos++) {
            System.out.printf("The value at position %d: %d\n", pos, peek(pos));
        }
    }

    // Check the size of the stack
    public int size() {
        return top + 1;
    }

    int stackTop() {
        if (top == -1) {
            System.out.println("Empty stack!");
            return -1;
        }
        return arr[top];
    }

    int stackBottom() {
        if (top == -1) {
            System.out.println("Empty stack!");
            return -1;
        }
        return arr[0];
    }

}

