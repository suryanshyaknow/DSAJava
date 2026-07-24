package ArraysAndHashing;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;

        Arrays.sort(nums);
        // Note: Dupes are to be ignored.
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < N - 2; i++) {
            // Starting w nums[i] would all have been formed
            // here, so skip dupe nums[i]'s
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            // Apply two pointers in here
            int left = i + 1;
            int right = N - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move the pointers
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;

                    right--;
                    while (right > left && nums[right] == nums[right + 1]) right--;
                } else if (sum < target) left++;
                else right--;
            }
        }

        return res;
    }
}
