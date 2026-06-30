package DynamicProgramming.DPOnLIS;

import java.util.*;

public class LISBinarySearch {

    public int lengthOfLIS(int[] nums) {
        // See here we're trying to hypothetically generate all subsequences but
        // w/o using new space for each new subsequence.
        //
        // So instead of creating new subsequences, whenever we find
        // any drifing (smaller) number that's engage in new subsequence,
        // we try to place it in the same space at it's lower bound so as
        // to reuse the same space.
        //
        // And this way even if that drifting number doesnt end up generating
        // the LIS, the old subseuqence woulda given the right answer anyways.
        //
        // Note: We try to place a given number at it's lower bound, i.e., at
        // the idx essentially where the smallest ele of the array is greater
        // than the number on hand.

        int N = nums.length;
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);

        for (int i = 1; i < N; i++) {
            int S = temp.size();
            if (nums[i] > temp.get(S - 1)) {
                temp.add(nums[i]);
            } else {
                // Find the lower bound to put the curr ele in temp
                int lbIdx = getLowerBound(temp, nums[i]);
                temp.set(lbIdx, nums[i]);
            }
        }

        // Note: Also note that temp is def not LIS but it indeed is IS.

        return temp.size();

        // Time: O(N * log N)
        // Space: O(N)

    }

    private int getLowerBound(List<Integer> temp, int ele) {
        int N = temp.size();

        int low = 0;
        int high = N - 1;

        // Find the mid
        // See if its greater than the ele, ditch the right sub-array
        // Cuz we need the smallest greater element than ele
        // Else, ditch the left sub-arr.

        int ans = N; // Fall back in case there's no lower bound
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (temp.get(mid) >= ele) {
                ans = mid; // potential ans
                high = mid - 1; // As we need the smallest greater ele
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

}
