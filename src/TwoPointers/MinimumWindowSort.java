package TwoPointers;

public class MinimumWindowSort {

    public static int sort(int[] arr) {
        // TODO: Write your code here
        int first = 0;
        int last = arr.length - 1;

        // Step 1 & 2. Find the misplaced elements
        while (first < arr.length - 1 && arr[first] <= arr[first + 1]) first++;
        // Early exit if first reaches end implying the array is already sorted
        if (first == last)
            return 0;
        while (last > 0 && arr[last] >= arr[last - 1]) last--;

        // Now that we've got the starting and the ending point of the array to be sorted
        // we need to expand to see if we need to include more elements from the left,
        // provided there's an element in the left greater than the min.
        // Similarly, from the right to see if there's an ele smaller than the max.

        // Find the min and max from the subarray
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        for (int i = first; i <= last; i++) {
            minVal = Integer.min(minVal, arr[i]);
            maxVal = Integer.max(maxVal, arr[i]);
        }

        // Expand the window to the left to find the index holding the element greater than the min
        while (first > 0 && arr[first - 1] > minVal) first--;
        // Expand the window to the right to find the index holding the element smaller than the max
        while (last < arr.length - 1 && arr[last + 1] < maxVal) last++;

        return last - first + 1;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 3, 7, 10, 9, 12};
//        System.out.println(sort(arr));

        int arr1[] = {3, 3, 2, 2};
        System.out.println(sort(arr1));
    }

}
