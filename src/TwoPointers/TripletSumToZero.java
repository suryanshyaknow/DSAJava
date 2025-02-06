package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumToZero {

    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();
        // TODO: Write your code here
        // First off, sort the array
        Arrays.sort(arr);

        // Iterate with the first pointer being the anchor
        // Now the problem boils down to finding a pair whose sum negate the anchor
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            int first = i + 1;
            int last = arr.length - 1;

            while (first < last) { // NOT '<=' because then arr[first] would be same as arr[last] and the pair won't be unique
                int sum = arr[first] + arr[last];
                if (sum == -arr[i]) { // Triplet found
                    triplets.add(Arrays.asList(arr[i], arr[first], arr[last]));
                    first++;
                    last--;
                } else if (sum < -arr[i]) { // Means sum's gotta up his game
                    first++;
                } else {
                    last--;
                }
            }
        }
        return triplets;
        /*
        - To find triplets, you often use two loops, meaning you’re checking N × N = N² possible pairs.
        - In the worst case, every pair could form a valid triplet, so you end up storing N² triplets.
        - Since storing each triplet takes space, the total space used becomes O(N²).
         */
    }

    public static void main(String[] args) {
        int[] arr = {-3, 0, 1, 2, -1, 1, -2};
        System.out.println(searchTriplets(arr));

        int[] arr1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(searchTriplets(arr1));
    }
}
