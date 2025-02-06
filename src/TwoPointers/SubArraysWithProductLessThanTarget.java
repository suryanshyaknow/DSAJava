package TwoPointers;

import java.util.ArrayList;
import java.util.List;

public class SubArraysWithProductLessThanTarget {

    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // TODO: Write your code here

        /*
         # Sliding Window Algorithm:

         - Initialize a window.
         - Expand it repeatedly till the product stays less than the target, and fetch the subarrays.
         - Contract, the moment the prod gets greater than or equal to the target and do it till it becomes less than the target.
         */
        int left = 0, right = 0;
        double ongoingProd = 1;

        // Handle edge case early on
        if (target <= 1)
            return new ArrayList<>();

        while (right <= arr.length - 1) {
            ongoingProd *= arr[right];

            while (ongoingProd >= target) {
                // Contract the window by moving the left
                ongoingProd /= arr[left];
                left++;
            }
            // Now that we have a valid window, fetch all the possible subarrays
            List<Integer> tempSubarr = new ArrayList<>();
            for (int i = right; i >= left; i--) {
                tempSubarr.addFirst(arr[i]);
                result.add(new ArrayList<>(tempSubarr));
            }
            // Expand the window
            right++;
        }
        return result;

        /*
            # Space Complexity

            1. When do we actually store sub-arrays?
            - Whenever we find a valid window (ongoingProd < target), we extract all contiguous subarrays ending at
            right and starting from any index between left and right.
            - For every right we have (right - left + 1) subarrays in the worst case scenario.

            2. Sub-arrays generated in total?
            - N(right - left + 1) where right -> (N - 1)
            - i.e. O(N^2)

            3. Temp List (tempSubArr)
            - The maximum size of tempSubarr at any time is O(N), as in the worst case, the entire array could be valid
            for a single iteration.
            - (Key Point) However, this list is cleared for every right index, meaning it does not grow cumulatively over iterations.
            - Space used: O(N) (temporary, not cumulative).
            - So, even though tempSubarr might take O(N) space at one point, it does not contribute to the overall
            space complexity. The space complexity is dominated by the output list (result) and the subarrays it stores.

            4. Total Space Complexity
            - The total number of subarrays generated is O(N²).
            - Each subarray can take up O(N) space, but this only occurs during the final iteration when right = N-1,
            and not cumulatively throughout the algorithm. Hence, the total space complexity still remains O(N³).
         */
    }

    public static void main(String[] args) {

        int arr[] = {2, 5, 3, 10};
        System.out.println(findSubarrays(arr, 30));

    }

}
