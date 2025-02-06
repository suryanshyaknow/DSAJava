package TwoPointers;

import java.util.Arrays;

public class TripletsWithSmallerSum {

    public static int searchTriplets(int[] arr, int target) {
        int count = 0;
        // TODO: Write your code here
        // Sort the array to leverage two pointers
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {

            int first = i + 1;
            int last = arr.length - 1;
            while (first < last) {
                int tripletSum = arr[i] + arr[first] + arr[last];

                // Move the pointers based on the triplets sum
                if (tripletSum < target) { // Meaning all the pairs between left (with left being fixed) and right would qualify
                    count += last - first;
                    first++;
                } else { // We need a pair w smaller sum
                    last--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 2, 3};
        System.out.println(searchTriplets(arr, 3));

        int[] arr1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(searchTriplets(arr1, 5));

        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(searchTriplets(arr2, 10));
    }

}
