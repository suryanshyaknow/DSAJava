package Recursion;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubsequences {

    public List<List<Integer>> subsetsMemoize(int[] nums) {
        // Memoization: An optimization technique where we store the results of
        // subproblems the first time it's computed, so that it doesn't have to
        // be solved repeatedly.
        // In recursive solutions, the same subproblems are often solved repeatedly,
        // leading to exponential time complexity.

        // Can't apply DP in here.
        // A DP problem must have a small, repeatable state.
        // Because curr is having multiple diff values for a given idx.
        // For DP to be implemented, overlapping subproblems must exist.

        return null;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetHelper(0, nums, new ArrayList<>(), res);
        return res;

        // Time complexity: O(2^N * N) {two choices at every recursive call * Copying curr into res at every leaf}
        // Space complexity: O(N * 2^N) {recursive call stack * storing all the subsequences}
    }

    private static void subsetHelper(int idx, int[] nums, List<Integer> curr,
                                     List<List<Integer>> res) {
        int N = nums.length;
        if (idx >= N) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // Pick the ele
        curr.add(nums[idx]);
        subsetHelper(idx+1, nums, curr, res);
        // Remove the added ele while backtracking
        curr.remove(curr.size() - 1);

        // Don't pick the ele
        subsetHelper(idx+1, nums, curr, res);
    }
}
