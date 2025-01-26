package CircularLinkedList;

public class Main {

    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.append(1999);
        circularLinkedList.append(1997);
        circularLinkedList.append(1972);
        circularLinkedList.append(1976);
        circularLinkedList.append(2004);

        circularLinkedList.display();
//        circularLinkedList.insertAtBeginning(2011);
//        circularLinkedList.insertAtIdx(913, 50);

//        circularLinkedList.deleteFirst();
//        circularLinkedList.deleteLast();
//        circularLinkedList.deleteByValue(2006);

        circularLinkedList.deleteByIdx2(5);
        circularLinkedList.display();
    }

}
