package SortingAlgos;

public class QuickSort {

    static void quickSort(int arr[], int low, int high) {
        // code here
        if (low >= high) return;

        int partitionIdx = getPartitionIdx(arr, low, high);
        quickSort(arr, low, partitionIdx - 1); // left partition
        quickSort(arr, partitionIdx + 1, high); // right partition
    }

    private static int getPartitionIdx(int[] arr, int low, int high) {
        // your code here
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            // Move the i and stop the moment the item at ith idx becomes greater than the pivot
            while (arr[i] <= pivot && i <= high-1) i++;

            // Move the j and stop the moment the item at jth idx becomes lesser than the pivot
            while (arr[j] > pivot && j >= low+1) j--;

            // Swap 'em both
            if (i < j) swapArrItems(arr, i, j);

        }
        // Finally swap pivot with j who's low in the left portion
        swapArrItems(arr, low, j);
        return j;
    }

    private static void swapArrItems(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

}
