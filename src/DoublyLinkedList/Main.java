package DoublyLinkedList;

public class Main {

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(1999);
        list.add(1972);
        list.add(1976);
        list.add(2004);
        list.add(2011);

        list.display();
//        list.insertAtBeginning(1997);
//        list.insertAt(913, 10);


//        list.deleteFirst();
//        list.pop();

        list.deleteAt(4);
        list.display();

    }

}
