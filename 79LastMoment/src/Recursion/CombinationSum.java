package Recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(0, candidates, target, new ArrayList<>(), res);
        return res;

        // Time complexity: O(2^t * k)
        // - First off, cuz here we don't have two choices for a given idx. We could choose it as many times as the target allows.
        // And average number of times an idx would be picked is 'target' times.
        // - Second, the operation to insert current into res ain't constant time. K being the average length of current.

        // Space complexity: Recursive call stack + totally dependent on number of combinations generated (we can't predict here).
    }

    private static void helper(int idx, int[] arr, int target, List<Integer> curr,
                               List<List<Integer>> res) {
        int N = arr.length;
        if (target == 0) {
            // Valid combination
            res.add(new ArrayList<>(curr));
            return; // No point in further continuing
        }

        if (idx >= N) return;

        // Pick the ele: multiple times if target allows
        if (arr[idx] <= target) {
            curr.add(arr[idx]);
            helper(idx, arr, target-arr[idx], curr, res); // idx doesn't change, imperative
            // Backtrack: remove the ele
            curr.remove(curr.size() - 1);
        }

        // Don't pick the ele
        helper(idx+1, arr, target, curr, res);
    }
}
