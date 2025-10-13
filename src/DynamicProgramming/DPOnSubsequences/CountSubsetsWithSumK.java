package DynamicProgramming.DPOnSubsequences;

public class CountSubsetsWithSumK {

    public int perfectSum(int[] nums, int target) {
        // code here
        int N = nums.length;
        int count[] = new int[1];
        count[0] = 0;

        int cnt = perfectSumCounter(N-1, nums, target);
        return cnt;
    }

    private static int perfectSumCounter(int idx, int[] arr, int target) {
        // if (target == 0) {
        //     return 1;
        // }

        if (idx == 0) {
            if (target == 0 && arr[0] == 0)
                return 2; // zero included
            if (target == 0 || arr[0] == target)
                return 1;
            return 0;

        }

        // Implement the pick/non-pick strategy
        int notPick = perfectSumCounter(idx-1, arr, target);
        int pick = 0;
        if (arr[idx] <= target)
            pick = perfectSumCounter(idx-1, arr, target-arr[idx]);
        return pick + notPick;
    }
}
