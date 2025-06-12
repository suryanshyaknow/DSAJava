package RecursionAndBackTracking;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {

    public static List<List<Integer>> permuteOptimal(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        getPermutationsOptimal(0, nums, res);
        return res;
    }

    static void getPermutationsOptimal(int idx, int[] nums, List<List<Integer>> res) {
        int N = nums.length;
        if (idx == N - 1) {
            List<Integer> perm = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                perm.add(nums[i]);
            }
            res.add(new ArrayList<>(perm));
            return;
        }

        for (int i = idx; i < N; i++) {
            swap(nums, idx, i);
            getPermutationsOptimal(idx + 1, nums, res);
            swap(nums, idx, i);
        }
    }

    static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] freqArr = new boolean[nums.length];
        getPermutations(nums, new ArrayList<>(), res, freqArr);
        return res;

        // Time complexity: O(n! * n) ~ Generating n! permutations and each time looping from 0 to n
        // Space complexity: O(n) + O(n) ~ perm and freqArr
    }

    static void getPermutations(int[] nums, ArrayList<Integer> perm, List<List<Integer>> res, boolean[] freqArr) {
        // Base case
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!freqArr[i]) {
                freqArr[i] = true;
                perm.add(nums[i]);
                getPermutations(nums, perm, res, freqArr);
                perm.removeLast();
                freqArr[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        permute(arr);
    }

}
