package TwoPointers;

public class NonDuplicateNumberInstances {

    public static int moveElements(int[] arr) {
        // TODO: Write your code here
        // Since, it's two pointers, let's have one pointer to iterate the array,
        // and the other to keep track of the open position where non-dupe instance would go.

        int nonDupeInstancePtr = 1;
        // First ele stays where it is cuz it's the first non-dupe.
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[nonDupeInstancePtr - 1]) {
                arr[nonDupeInstancePtr] = arr[i];
                nonDupeInstancePtr++;
            }
        }
        return nonDupeInstancePtr;
    }

    public static void main(String[] args) {
//        int[] arr = {2, 3, 3, 3, 6, 9, 9};
//        System.out.println(moveElements(arr));

        int[] arr = {2, 2, 2, 11};
        System.out.println(moveElements(arr));

    }
}
