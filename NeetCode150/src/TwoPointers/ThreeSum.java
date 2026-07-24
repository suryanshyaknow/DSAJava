package TwoPointers;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;

        // Sort the array to apply two pointers
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < N - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip dupes for i

            int target = -nums[i];

            int left = i + 1;
            int right = N - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }

                if (sum < target)
                    left++;
                else
                    right--;
            }
        }
        return res;
    }
}
