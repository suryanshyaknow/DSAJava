package DynamicProgramming.LIS;

import java.util.ArrayList;
import java.util.List;

public class LISBinarySearch {

    public int lengthOfLIS(int[] nums) {
        // Using BS
        // Instead of generating new subsequence for every smaller number than the prev,
        // we put it at apt pos within the original subsequence saving the space.
        // Now about the BS, it'll help decide where that junction ele should get place.

        // Lower Bound: The first idx where the ele is greater than or equal to a target.
        // So w each ele that doesn't follow LIS, ask what the first ele greater than or equal to
        // that ele, and insert it there, i.e., basically its lower bound.

        int N = nums.length;
        List<Integer> dp = new ArrayList<>();

        dp.add(nums[0]);
        for (int i = 1; i < N; i++) {
            if (nums[i] > dp.get(dp.size() - 1))
                dp.add(nums[i]);
            else {
                int lb = lowerBound(nums[i], dp);
                dp.set(lb, nums[i]);
            }
        }
        return dp.size();
    }

    private int lowerBound(int num, List<Integer> dp) {
        int low = 0;
        int high = dp.size(); // not N - 1, because here we're not tryna find the ele but the insertion point

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (dp.get(mid) < num)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

}
