package LinkedList;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        LinkedList linkedList = new LinkedList();

        linkedList.add(1999);
        linkedList.add(1997);
        linkedList.add(1993);
        linkedList.add(1972);
        linkedList.add(2004);

        linkedList.display();
//        linkedList.delete(20034);
//        linkedList.deleteByIdx(0);
//        linkedList.pop();


//        linkedList.insertAtBeginning(2011);
//        linkedList.insertAt(24, 2);
//        linkedList.append(545);
        linkedList.insertAfter(2024, 19923);
        linkedList.display();

    }

}