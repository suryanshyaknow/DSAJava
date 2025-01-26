package Stack;

public class Stack<T> {

    private T[] stack;
    private int top;

    public Stack(int size) {
//        Type parameter 'T' cannot be instantiated directly!!!
//        this.stack = new T[size];
        this.stack = (T[]) new Object[size];
        top = -1;
    }

    public void push(T val) {
        if (top == stack.length - 1) {
            System.out.println("Stack overflow!");
            return;
        }
        stack[++top] = val;
    }

    public T pop() {
        if (top == -1) {
            throw new RuntimeException("Stack underflow!");
        }
//        char value = stackArray[top];
//        top--;
//        return value
        return stack[top--]; // Retrieves the value and decrement the top
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[top];
    }

    public T stackTop() {
        return stack[top];
    }


}
