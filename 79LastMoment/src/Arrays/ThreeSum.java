package Arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        // Two pointers approach
        // What we could is - give the array is sorted - fix
        // a pivot and find the duplet whose sum is the pivot
        // via two pointers.

        int N = nums.length;
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>(); // To skip dupes

        for (int i = 0; i < N - 2; i++) { // pivot

            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip dupes for i

            int target = -nums[i]; // j + k

            int leftPtr = i + 1;
            int rightPtr = N - 1;

            while (leftPtr < rightPtr) {
                int sum = nums[leftPtr] + nums[rightPtr];

                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[leftPtr], nums[rightPtr]));

                    // Move both pointers
                    leftPtr++;
                    while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr - 1]) leftPtr++;

                    rightPtr--;
                    while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr + 1]) rightPtr--;
                } else if (sum < target) {
                    leftPtr++;
                } else {
                    rightPtr--;
                }
            }
        }

        return new ArrayList<>(res);

        // Time complexity: O(N log N) + O(N^2)
        // Space complexity: O(N) and that too for storing ans
    }
}
