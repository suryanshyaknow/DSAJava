package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SubsetSumsII {

    public List<List<Integer>> subsetsWithDupOptimal(int[] arr) {
        // Sort the array to club the dupes together
        Arrays.sort(arr);
        List<List<Integer>> resSubsets = new ArrayList<>();
        generateSubsetsOptimal(0, arr, new ArrayList<>(), resSubsets);
        return resSubsets;
    }

    private void generateSubsetsOptimal(int idx, int[] arr, ArrayList<Integer> current, List<List<Integer>> resSubsets) {
        resSubsets.add(new ArrayList<>(current));
        int N = arr.length;
        for (int i = idx; i < N; i++) {
            if (i != idx && arr[i] == arr[i - 1]) continue; // Skip the dupes
            current.add(arr[i]);
            generateSubsetsOptimal(i+1, arr, current, resSubsets);
            current.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] arr) {
        // Code here. This approach ain't working correctly but good for grasping the idea of the recursion.
        /*
            Key Idea: Every subset is formed by:

            - Picking some elements,
            - And not picking others.
         */
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generateSubsets(0, arr, current, subsets);
        return new ArrayList<>(new HashSet<>(subsets));

    }

    void generateSubsets(int idx, int[] arr, List<Integer> current, List<List<Integer>> subsets) {
        int N = arr.length;

        if (idx == N) {
            // We've made on full subset, add a copy of it to result
            subsets.add(new ArrayList<>(current));
            return;
        }

        // Pick the fking ele
        current.add(arr[idx]);
        generateSubsets(idx + 1, arr, current, subsets);

        // Don't pick the fking ele
        current.removeLast();
        generateSubsets(idx + 1, arr, current, subsets);
    }

}
