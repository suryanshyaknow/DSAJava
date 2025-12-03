package Arrays;

import java.util.*;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int N = nums.length;
        Arrays.sort(nums);

        Set<List<Integer>> res = new HashSet<>();
        // Fix two pivots, and implement two pointers
        for (int i = 0; i <= N - 3; i++) {
            // Skip dupes
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j <= N - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                long t = (long) target - (long) nums[i] - (long) nums[j];
                int leftPtr = j + 1;
                int rightPtr = N - 1;

                while (leftPtr < rightPtr) {
                    long sum = nums[leftPtr] + nums[rightPtr];

                    if (sum == t) {
                        List<Integer> lis = Arrays.asList(nums[i], nums[j], nums[leftPtr], nums[rightPtr]);
                        // Collections.sort(lis);
                        res.add(lis);

                        leftPtr++;
                        while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1]) leftPtr++;
                        rightPtr--;
                        while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr + 1]) rightPtr--;
                    } else if (sum < t) {
                        leftPtr++;
                        while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1]) leftPtr++;
                    } else {
                        rightPtr--;
                        while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr + 1]) rightPtr--;
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

}
