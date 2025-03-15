package LinkedList.DoublyLinkedList;

public class ConstructDLL {

    public static Node constructDLL(int[] arr) {
        // Write your code here
        if (arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node prev = head;

        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            prev.next = newNode;
            newNode.prev = prev;
            // Move the prev pointer
            prev = newNode;
        }
        return head;
    }

    public static void print(Node head) {
        if (head == null || head.next == null) {
            System.out.println(head);
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static Node pop(Node head) {
        if (head == null || head.next == null) return null;

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        // Now, we're at the second last node
        // Free the last one
        temp.next = null;
        return head;
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) return head;

        Node temp = head;
        Node prev = head.prev;
        while (temp != null) {
            Node tempNext = temp.next;
            temp.next = prev;
            temp.prev = tempNext;
            // Move the prev and temp pointer
            prev = temp;
            temp = tempNext;
        } // Cuz temp is now at null
        head = prev;
        return head;
    }


}

