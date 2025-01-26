package Stack.StackArray;

public class Main {

    public static void main(String[] args) {

        StackArray stackArray = new StackArray(10);
        stackArray.push(1999);
        stackArray.push(2004);
        stackArray.push(2011);
        stackArray.push(1997);
        stackArray.push(1972);
        stackArray.push(1976);
        stackArray.push(2024);

        stackArray.display();
        System.out.printf("stackBottom: %d\n", stackArray.stackBottom());
        System.out.printf("stackTop: %d\n", stackArray.stackTop());

    }
}
