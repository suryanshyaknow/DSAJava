package DynamicProgramming.DPOnSubsequences;

public class SubsetSumEqualsK {

    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int N = arr.length - 1;
        return isSubsetSumHelper(N-1, arr, sum);
    }

    private static Boolean isSubsetSumHelper(int idx, int[] arr, int target) {
        // Base cases
        if (target == 0) return true;
        if (idx == 0) return arr[idx] == target;

        // Don't pick the curr ele and move to the next (because we're generating all possible combinations)
        boolean notTake = isSubsetSumHelper(idx - 1, arr, target); // target remains the same

        // Pick the curr ele and move to the next
        boolean take = false;
        // But to pick the curr ele it should be less than or equal to the target
        if (arr[idx] <= target) take = isSubsetSumHelper(idx - 1, arr, target - arr[idx]);

        return take || notTake;
    }

}
