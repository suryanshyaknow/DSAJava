package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumQuadruplets {

    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        // TODO: Write your code here
        // Sort the array
        Arrays.sort(arr);

        // Fix two pointers and this problemo boils down to finding two sum
        for (int i = 0; i < arr.length - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) // We wanna skip dupes
                    continue;
                int targetSum = target - arr[i] - arr[j];
                //...
                // Search Pairs.. Two sum
                int first = j + 1;
                int last = arr.length - 1;
                searchPairs(i, j, first, last, arr, targetSum, quadruplets);
            }
        }
        return quadruplets;
        /*
            # Space Complexity:

        - Resultant quadruplets storage: The algorithm uses a list quadruplets to store the resulting quadruplets.
        In the worst case, there can be N^3 (NC3) quadruplets if many valid quadruplets are found.

        - The space complexity for storing the result is O(K), where K is the number of quadruplets, and K can be as
        large as O(N^3). Adding the space for sorting, the total space complexity becomes O(N^3) in the worst case.
         */
    }

    private static void searchPairs(int i, int j, int first, int last, int[] arr, int targetSum, List<List<Integer>> quadruplets) {

        while (first < last) {
            int currentSum = arr[first] + arr[last];
            if (currentSum < targetSum) { // Move the first so that currentSum becomes greater
                first++;
            } else if (currentSum > targetSum) {
                last--;
            } else {
                // Quadruplet found
                List<Integer> quadruplet = Arrays.asList(arr[i], arr[j], arr[first], arr[last]);
                quadruplets.add(quadruplet);
                first++;
                last--;

                // Gotta skip dupes for first and last too
                while (first < last && arr[first] == arr[first + 1]) {
                    first++;
                }
                while (first < last && arr[last] == arr[last - 1]) {
                    last--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 1, 2, -1, 1, -3};
        System.out.println(searchQuadruplets(arr1, 1));
    }

}
