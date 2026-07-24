package TwoPointers;

public class TwoSumII {

    public int[] twoSum(int[] nums, int target) {
        int N = nums.length;

        int leftPtr = 0;
        int rightPtr = N - 1;

        int[] res = new int[2];

        while (leftPtr < rightPtr) {
            int sum = nums[leftPtr] + nums[rightPtr];
            if (sum == target) {
                return new int[]{leftPtr + 1, rightPtr + 1};
            }

            if (sum < target) leftPtr++;
            else rightPtr--;
        }

        return new int[]{-1, -1};
    }

}
