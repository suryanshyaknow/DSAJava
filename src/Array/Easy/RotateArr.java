package Array.Easy;

import java.util.Arrays;

class RotateArr {

    public static void rotateLeft(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;

        k = k % n; // Normalize k

        // Put the first k elements in temp storage
        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        // Shift the rest of them backwards since it's left rotation
        for (int i = 0; i < n - k; i++) {
            arr[i] = arr[k + i];
        }

        // Copy the copied elements from temp to the original array
        for (int i = 0; i < k; i++) {
            arr[n - k + i] = temp[i];
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void rotateUsingReverse(int[] arr, int k) {
        // Normalize k
        int n = arr.length;
        k = k % n;

        // Reverse the last k elements
        reverse(arr, n - k, n - 1);
        System.out.println(Arrays.toString(arr));

        // Reverse the remaining elements
        reverse(arr, 0, n - k - 1);
        System.out.println(Arrays.toString(arr));

        // Reverse the resultant arr
        reverse(arr, 0, n - 1);
        System.out.println(Arrays.toString(arr));

    }

    private static void reverse(int[] arr, int k, int n) {
        int leftPtr = k;
        int rigthPtr = n;
        while (leftPtr < rigthPtr) {
            // Swap the elements
            int temp = arr[leftPtr];
            arr[leftPtr++] = arr[rigthPtr];
            arr[rigthPtr--] = temp;
        }

    }

    public static void rotate(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;

        k = k % n; // Normalize k

        // Put the elements to be rotated in a temp storage
        int[] temp = new int[k];
        for (int i = 0; i <= k - 1; i++) { // O(K)
            temp[i] = arr[n - k + i];
        }
        System.out.println(Arrays.toString(temp));

        // Now shift the remaining elements accordingly
        for (int i = arr.length - k - 1; i >= 0; i--) { // O(N - K)
            arr[i + k] = arr[i];
        }
        System.out.println(Arrays.toString(arr));

        // Now put the rotated elements back in the original array
        for (int i = 0; i < k; i++) { // O(K)
            arr[i] = temp[i];
        }
//        System.out.println(Arrays.toString(arr));
        // O(N + K)
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
//        int[] arr = new int[]{-1, -100, 3, 99};
//        rotate(arr, 3);
        rotateUsingReverse(arr, 3);
    }

}