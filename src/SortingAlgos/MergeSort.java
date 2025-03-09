package SortingAlgos;

public class MergeSort {

    void mergeSort(int arr[], int l, int r) {
        // code here
        // Merge Sort: Divide and Merge

        if (l == r) return; // Base case cuz single elements are sorted in themselves

        // Find the middle to divide the arr into two parts
        int mid = (l + r) / 2;

        // Divide the array into further two parts until we are left with single elements in both parts
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        mergeTwoSortedLists(arr, l, mid, r);
    }

    private void mergeTwoSortedLists(int[] arr, int l, int mid, int r) {
        // Construct a temp array that contains sorted parts of both worlds
        int[] temp = new int[r - l + 1];

        // Sort 'em using two pointers
        int leftPtr = l;
        int rightPtr = mid + 1;
        int tempPtr = 0;
        while (leftPtr <= mid && rightPtr <= r) {
            if (arr[leftPtr] <= arr[rightPtr]) {
                temp[tempPtr++] = arr[leftPtr];
                leftPtr++;
            } else {
                temp[tempPtr++] = arr[rightPtr];
                rightPtr++;
            }
        }

        // If the left part got exhausted first, then we just gotta attach the remaining right part
        while (leftPtr <= mid) {
            temp[tempPtr++] = arr[leftPtr];
            leftPtr++;
        }
        while (rightPtr <= r) {
            temp[tempPtr++] = arr[rightPtr];
            rightPtr++;
        }

        // Now, repopulate the arr in between the give range
        for (int i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
    }
}
