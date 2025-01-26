package Queue.DEQueue;

public class DEQueue {

    private int[] queue;
    int front, rear, capacity, size;

    public DEQueue(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
        this.front = this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return rear == capacity - 1; // This won't work cuz we've got to account for both ends
//        return size >= capacity;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Sadly, no elements to display!.");
            return;
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < capacity; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println("\n");
    }

    public int enqueueR(int data) {
        if (isFull()) {
            System.out.println("Enqueue at REAR ain't possible for the queue is full!");
            return Integer.MIN_VALUE;
        }
        queue[++rear] = data;
        size++;
        return data;
    }

    public int enqueueF(int data) {
        if (size == capacity) {
            System.out.println("Enqueue at FRONT ain't possible for the queue is full!");
            return Integer.MIN_VALUE;
        }
        // Edge case
        if (isEmpty()) { // i.e. rear = -1
            rear++;
            queue[rear] = data;
            size++;
            return data;
        } else if (front >= 0) { // i.e. there's space to move the front backwards
            queue[front--] = data;
            size++;
            return data;
        } else { // i.e. front == -1
            System.out.println("Enqueue at FRONT ain't possible cuz of no openings!");
            return Integer.MIN_VALUE;
        }
    }

    public int dequeueF() {
        if (isEmpty()) {
            System.out.println("No elements left there to be dequeued from FRONT!");
        }
        int eleToDequeue = queue[++front];
        queue[front] = 0; // Nullify the space
        size--;
        // Reset the queue if it's empty now
        if (isEmpty()) {
            front = rear = -1;
        }
        return eleToDequeue;
    }

    public int dequeueR() {
        if (isEmpty()) {
            System.out.println("Sadly, no elements left to dequeue from either end!.");
            return -1;
        }
        size--;
        int eleToDequeue = queue[rear];
        queue[rear--] = 0; // Nullify the val
        if (isEmpty()) {
            // Reset the front and rear
            front = rear = -1;
        }
        return eleToDequeue;
    }

    public static void main(String[] args) {
        DEQueue deQueue = new DEQueue(5);
//        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
//        deQueue.display();
//
        deQueue.enqueueR(1999);
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

        deQueue.enqueueR(1976);
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();
//
//        System.out.println("Dequeued: " + deQueue.dequeueF());
//        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
//        deQueue.display();
//
        deQueue.enqueueR(1972);
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

        deQueue.enqueueR(2011);
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

        deQueue.enqueueR(2004);
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();
//
//        System.out.println("Dequeued: " + deQueue.dequeueF());
//        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
//        deQueue.display();

        System.out.println("Dequeued: " + deQueue.dequeueF());
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

//        deQueue.enqueueF(26);
//        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
//        deQueue.display();

//        deQueue.enqueueF(27);
//        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
//        deQueue.display();

        System.out.println("Dequeued from Rear: " + deQueue.dequeueR());
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

        System.out.println("Dequeued from Rear: " + deQueue.dequeueR());
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

        System.out.println("Dequeued from Rear: " + deQueue.dequeueR());
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();

        System.out.println("Dequeued from rear: " + deQueue.dequeueR());
        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
        deQueue.display();
//
//        System.out.println("Dequeued from rear: " + deQueue.dequeueR());
//        System.out.println("front: " + deQueue.front + ", rear: " + deQueue.rear);
//        deQueue.display();

    }


}
