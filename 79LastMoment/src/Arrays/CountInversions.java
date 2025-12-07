package Arrays;

public class CountInversions {

    static int inversionCount(int arr[]) {
        // Code Here
        int N = arr.length;
        return mergeSortHelper(arr, 0, N - 1);
    }

    private static int mergeSortHelper(int arr[], int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;

        // Divide...
        int mid = (low + high) / 2;
        cnt += mergeSortHelper(arr, low, mid);
        cnt += mergeSortHelper(arr, mid + 1, high);

        // Merge..
        cnt += merge(arr, low, mid, high);
        return cnt;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        int leftPtr = low;
        int rightPtr = high;
        int cnt = 0;

        int temp[] = new int[high - low + 1];
        int ptr = 0;
        // We wanna track the cnt of the pairs where left is greater than the right
        while (leftPtr <= mid && rightPtr <= high) {
            if (arr[leftPtr] <= arr[rightPtr]) {
                temp[ptr++] = arr[leftPtr++];
            } else {
                temp[ptr++] = arr[rightPtr++];
                cnt += mid - leftPtr + 1;
            }
        }
        while (leftPtr <= mid) {
            temp[ptr++] = arr[leftPtr++];
        }
        while (rightPtr <= high) {
            temp[ptr++] = arr[rightPtr++];
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }

        return cnt;
    }
}
