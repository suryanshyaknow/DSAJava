package Recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        genCombinations(0, target, candidates, new ArrayList<>(), res);
        return res;

        // Time Complexity: 2^t * k
        // - First off, cuz here we don't have two choices for a given idx. We could choose it as many times as the target allows.
        // And average number of times an idx would be picked is 'target' times.
        // - Second, the operation to insert current into res ain't constant time. K being the average length of current.

        // Space complexity: k * x where x is number of combinations.
        // Actually, it's unpredictable in here, because we don't know how many combinations would be generated.
    }

    private void genCombinations(int idx, int target, int[] arr, List<Integer> current, List<List<Integer>> res) {
        // Base case
        int N = arr.length;
        if (idx == N) {
            if (target == 0)
                res.add(new ArrayList<>(current)); // Not a constant operation. Takes linear time.
            return;
        }

        if (arr[idx] <= target) { // We'll check if the ele we're picking is less than the target for it to be picked.
            current.add(arr[idx]);
            genCombinations(idx, target - arr[idx], arr, current, res);
            current.removeLast();
        }
        genCombinations(idx + 1, target, arr, current, res);
    }

}
