package Arrays;

public class KadanesAlgorithm {

    public int maxSubArray(int[] nums) {
        int N = nums.length;

        // We can implement Kadane's algorithm
        // We'll carry a variable sum and track the sum
        // w each ele traversal. And reset it to zero the
        // moment it falls under zero.
        // Cuz makes no sense to carry a negative sum forward.

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            maxSum = Math.max(sum, maxSum);

            if (sum < 0)
                sum = 0;
        }
        return maxSum;

        // Time complexity: O(N)
        // Space complexity: O(1)
    }

    int printMaxSubarraySum(int[] arr) {
        // Code here
        int N = arr.length;

        int startIdx = -1;
        int endIdx = -1;
        int tempStart = -1;

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += arr[i];

            if (sum > maxSum) {
                maxSum = sum;
                endIdx = i;

                // Update both start and end idx
                startIdx = tempStart;
                endIdx = i;

            }

            if (sum < 0) { // No sense to carry it forward
                sum = 0;
                tempStart = i + 1;
            }

        }

        if (startIdx != -1 && endIdx != -1) {
            maxSum = 0;
            for (int i = startIdx; i <= endIdx; i++) {
                maxSum += arr[i];
            }
        }

        // Why do we even need tempStart?
        // It won’t work because there are cases where startIdx would already be updated early
        // (because sum went negative), but the run that ACTUALLY gives the global max starts
        // after another reset, and you’d overwrite the start incorrectly or too early.

        return maxSum;
    }

}
