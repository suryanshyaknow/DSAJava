package Queue.QueueArray;

public class QueueArray {

    private int[] queue;

    int front, rear, capacity, size;

    public QueueArray(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
        this.front = this.rear = -1;
        this.size = 0;
    }

    public int enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue overflow!.");
            return Integer.MIN_VALUE;
        }
        rear++;
        size++;
        queue[rear] = data;
        return data;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Sadly, there are no elements to dequeue from the queue!");
            return Integer.MIN_VALUE;
        }
        int itemToPop = queue[++front];
//        front++;
        size--;

        // Edge case: Reset queue when last element has been dequeued
        // Somewhat efficient approach to REUSE the queue when it gets virtually empty i.e. front == rear,
        // but still won't suffice cuz now for use to reuse the queue, we'll have to wait till it gets empty.
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        return itemToPop;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Sadly no elements to display. Empty queue!");
            return;
        }
        int ptr = front;
        for (int i = 0; i < size; i++) {
            ptr++;
            System.out.print(queue[ptr] + " ");
        }
        System.out.println();
    }

    public boolean isFull() {
        return rear == capacity - 1;
//        return size == capacity;
    }


    public boolean isEmpty() {
        return front == rear;
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

//        queue.enqueue(400);
//        queue.enqueue(430);
//        queue.enqueue(3500000);
//        queue.display();
//
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
