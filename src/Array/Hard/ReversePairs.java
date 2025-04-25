package Array.Hard;

public class ReversePairs {

    int mergeSort(int arr[], int l, int r) {
        // code here
        // Merge Sort: Divide and Merge
        if (l == r) return 0; // Base case cuz single elements are sorted in themselves

        // Find the middle to divide the arr into two parts
        int mid = (l + r) / 2;

        // Divide the array into further two parts until we are left with single elements in both parts
        int count = 0;
        count += mergeSort(arr, l, mid);
        count += mergeSort(arr, mid + 1, r);
        count += countReversePairs(arr, l, mid, r);
        mergeTwoSortedLists(arr, l, mid, r);
        return count;

        // Time Complexity: O (log N) * (N + N), another N for traversing the sorted arrays to count the pairs
        // Space Complexity: O(N)
        // Mention that you're indeed distorting the org aray
    }

    private int countReversePairs(int[] arr, int low, int mid, int high) {
        int count = 0;
        int right = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) arr[i] > (long) 2 * arr[right]) { // To avoid int overflow
                right++;
            }
            // Update the count
            count = count + (right - (mid + 1));
        }
        return count;
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
