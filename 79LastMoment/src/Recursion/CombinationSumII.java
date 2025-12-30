package Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;

public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // So that dupe ele can be clubbed together
        // and easily be skipped.

        List<List<Integer>> res = new ArrayList<>();
        helper(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    private static void helper(int idx, int target, int[] arr, List<Integer> curr,
                               List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // Iterate over the whole arr from idx till last to choose the picking ele
        // At every index, I decide whether to include that element once
        for (int i = idx; i < arr.length; i++) {
            // Skip the dupes
            if (i > idx && arr[i] == arr[i - 1]) continue;
            if (arr[i] > target) break;

            curr.add(arr[i]);
            helper(i + 1, target - arr[i], arr, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2BruteForce(int[] candidates, int target) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(candidates);
        helper(0, candidates, target, new ArrayList<>(), res);
        return new ArrayList<>(res);
    }

    private static void helper(int idx, int[] arr, int target, List<Integer> curr,
                               Set<List<Integer>> res) {
        int N = arr.length;
        if (target == 0) {
            // Valid combination
            // Collections.sort(curr);
            res.add(new ArrayList<>(curr));
            return; // No point in further continuing
        }

        if (idx >= N) return;

        // Pick the ele
        if (arr[idx] <= target) {
            curr.add(arr[idx]);
            helper(idx + 1, arr, target - arr[idx], curr, res);

            // Backtrack: remove the ele
            curr.remove(curr.size() - 1);
        }

        // Don't pick the ele
        helper(idx + 1, arr, target, curr, res);
    }
}
