package Stack.StackLinkedList;

public class Main {

    public static void main(String[] args) {
        StackLinkedList stackLinkedList = new StackLinkedList();
        stackLinkedList.push(1999);
        stackLinkedList.push(1972);
        stackLinkedList.push(1976);
        stackLinkedList.push(2011);
        stackLinkedList.push(2004);

        stackLinkedList.display();
//        stackLinkedList.pop();
//        System.out.println();

//        stackLinkedList.display();
//        System.out.printf("stackTop: %d\n", stackLinkedList.stackTop());
//        System.out.printf("stackBottom: %d\n", stackLinkedList.stackBottom());

//        System.out.println(stackLinkedList.peek(0));
//        System.out.println(stackLinkedList.peek(1));
//        System.out.println(stackLinkedList.peek(2));
//        System.out.println(stackLinkedList.peek(3));
//        System.out.println(stackLinkedList.peek(4));
        System.out.println(stackLinkedList.peek(5));
    }


}
