package SortingAlgos;

public class SelectionSort {

    void selectionSort(int[] arr) {
        // code here
        // Iterate -> Find Minimum -> Swap

        for (int i = 0; i <= arr.length - 2; i++) { // Cuz we oughta move n -2 steps to get the whole list sorted
            // Find minimum
            int minIdx = i;
            for (int j = i; j <= arr.length - 1; j++) {
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }

            // Swap the minimum with the current pos
            swapTwo(arr, minIdx, i);
        }
        // n + (n - 1) + (n - 2) + ... + 3 + 2 + 1 ...The number of times the inner loop runs w each iteration.
        // n (n + 1)/2
        // O(n (n + 1)/2) => O(n^2/2)

        // Note: Unlike Bubble Sort or Insertion Sort, Selection Sort does NOT improve if the array is already sorted.
        // It still does O(n²) comparisons.
    }

    private static void swapTwo(int[] arr, int minIdx, int currentPos) {
        int temp = arr[minIdx];
        arr[minIdx] = arr[currentPos];
        arr[currentPos] = temp;
    }

    public static void bubbleSort(int arr[]) {
        // code here
        // Push the max to the last via adjacent swaps
        for (int i = arr.length - 1; i >= 1; i--) { // It's not i >= 0 cuz there have to elements left
            // Adjacent swaps
            int didSwap = 0;
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    didSwap = 1;
                }
            }
            if (didSwap == 0)
                break;
        }
    }

    public static void insertionSort(int arr[]) {
        // The loop's gonna run for 'n' times
        for (int i = 0; i <= arr.length - 1; i++) {
            int j = i;
            // Move towards left and put the current item in its correct pos
            while (j > 0 && arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

}
