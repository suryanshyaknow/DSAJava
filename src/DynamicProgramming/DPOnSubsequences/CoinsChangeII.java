package DynamicProgramming.DPOnSubsequences;

public class CoinsChangeII {

    public int change(int amount, int[] coins) {
        // Important point here is we could use a certain type
        // of denominations any number of times
        int N = coins.length;

        return changeHelper(N-1, coins, amount);

        // Time complexity: Not exactly 2^N, however exponential in nature
        // Space complexity: >>O(N), we're standing at the same idx, so the depth increases consequently
    }

    private static int changeHelper(int idx, int[] arr, int target) {
        // We won't have any coins w zero denomination
        if (target == 0) return 1;
        if (idx == 0) {
            // Since even last ele could be picked any number of times
            if (target % arr[0] == 0) return 1;
            else return 0;
        }

        // Don't pick the coin
        int notPick = changeHelper(idx-1, arr, target);
        // Pick the coin
        int pick = 0;
        if (arr[idx] <= target) pick = changeHelper(idx, arr, target-arr[idx]);

        return pick + notPick;
    }
}
