package Queue.QueueArray;

public class QueueArray {

    private int[] queue;

    int front, rear, capacity, currentSize;

    public QueueArray(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
        this.front = this.rear = -1;
        this.currentSize = 0;
    }

    public int enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue overflow!.");
            return Integer.MIN_VALUE;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        queue[rear] = data;
        currentSize += 1;
        return data;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Sadly, there are no elements to dequeue from the queue!");
            return Integer.MIN_VALUE;
        }
        int itemToPop = queue[front];
        if (currentSize == 1) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        currentSize--;
        return itemToPop;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Sadly no elements to display. Empty queue!");
            return;
        }
        int ptr = front;
        for (int i = 0; i < currentSize; i++) {
            System.out.print(queue[ptr] + " ");
            ptr = (ptr + 1) % capacity;
        }
        System.out.println();
    }

    public boolean isFull() {
        return currentSize == capacity;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public static void main(String[] args) {
        QueueArray queue = new QueueArray(5);
        queue.enqueue(5);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(410);
//        queue.enqueue(490);
        queue.display();

        queue.enqueue(400);
        queue.enqueue(430);
        queue.enqueue(3500000);
        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        queue.display();
        System.out.println();

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        queue.display();
        System.out.println();

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        queue.display();
        System.out.println();

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        queue.display();
        System.out.println();

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("front: " + queue.front + ", rear: " + queue.rear);
        queue.display();
        System.out.println();


//        queue.dequeue();
        queue.enqueue(5600);
        queue.enqueue(5600);
        queue.enqueue(5600);
        queue.enqueue(5600);
        queue.enqueue(5600);
        queue.display(); // No elements to display, but have got vacancies, i.e. the disadvantage of linear queues

    }

}
