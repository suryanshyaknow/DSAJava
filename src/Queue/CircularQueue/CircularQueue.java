package Queue.CircularQueue;

public class CircularQueue {

    int capacity;
    int size;
    int[] queue;
    int front;
    int rear;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.front = this.rear = -1; // Cuz -1 ain't gonna cut the isFull condition in here
        this.size = 0;
        this.queue = new int[capacity];
    }

    public int enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue overflow!");
            return Integer.MIN_VALUE;
        }
        if (isEmpty()) { // Either de-facto or after a full dequeue
            front = 0;
        }
        rear = (rear + 1) % capacity;
        System.out.println("The ele to be enqueued at position " + rear + " is: " + data);
        queue[rear] = data;
        size++;
        return data;
    }

    private boolean isFull() {
        return (rear + 1) % capacity == front; // i.e. the next element to the last item is the front itself
        // If adding one more element would overwrite the front of the queue, it's full.
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("\nSadly no elements left to dequeue!.");
            return -1;
        }

        int dequeuedVal = queue[front];
        if (front == rear) { // i.e. at the last ele
            front = rear = -1;
        } else { // circular increment
            queue[front] = Integer.MIN_VALUE;
            front = (front + 1) % capacity;
            size--;
        }
        return dequeuedVal;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Sadly no elements to display!");
            return;
        }

//        for (int i = 0; i < capacity && i != rear; i = (i + 1) % capacity) {
//            int circularEle = queue[i];
//            System.out.print(circularEle + " ");
//        }
        int i = front;
        while (i != rear) {
            System.out.print(queue[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.print(queue[rear] + " ");
        System.out.println("\n");
    }


    private boolean isEmpty() {
        // No good cuz front now is the first ele itself and not the idx before it.
//        return front == rear; // Effectively implying that there's no element present at the back idx
        return front == -1;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(5);
//        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
//        circularQueue.display();
//        System.out.println();

        circularQueue.enqueue(1999); // back: 0
//        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
//        circularQueue.display();

        circularQueue.enqueue(1972); // back: 1
//        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
//        circularQueue.display();

//        int dequeuedEle = circularQueue.dequeue();
//        System.out.println("Element dequeued: " + dequeuedEle);
//        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
//        circularQueue.display();
//        System.out.println();

        circularQueue.enqueue(1976); // back: 2
//        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
//        circularQueue.display();

        circularQueue.enqueue(2004); // back: 3
//        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
//        circularQueue.display();

        circularQueue.enqueue(2011); // back: 4
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        int dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display(); // No elements to display

        dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();
        System.out.println();

        circularQueue.enqueue(26);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        circularQueue.enqueue(27);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        circularQueue.enqueue(28);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        circularQueue.enqueue(39);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        circularQueue.enqueue(49);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        circularQueue.enqueue(29);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();

        dequeuedEle = circularQueue.dequeue();
        System.out.println("Element dequeued: " + dequeuedEle);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();
        System.out.println();

        circularQueue.enqueue(29);
        System.out.println("front: " + circularQueue.front + ", rear: " + circularQueue.rear);
        circularQueue.display();
    }


}
