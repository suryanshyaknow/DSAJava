package Graphs.Easy;

import java.util.ArrayList;
import java.util.List;

public class NumberOfSubsequences {

    public int numSubseq(int[] nums, int target) {
        int cnt[] = new int[1];
        cnt[0] = 0;
        List<Integer> ds = new ArrayList<>();
        numSubseqHelper(0, nums, target, ds, cnt);
        return cnt[0];
    }

    private static void numSubseqHelper(int idx, int[] nums, int target, List<Integer> ds, int[] cnt) {
        int N = nums.length;
        if (idx >= N) {
            if (!ds.isEmpty()) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < ds.size(); i++) {
                    min = Integer.min(min, ds.get(i));
                    max = Integer.max(max, ds.get(i));
                }
                if (min + max <= target) {
                    cnt[0]++;
                    return;
                }
            }
            return;
        }

        ds.add(nums[idx]);
        // Pick
        numSubseqHelper(idx + 1, nums, target, ds, cnt);

        // Not Pick
        ds.remove(ds.size() - 1);
        numSubseqHelper(idx + 1, nums, target, ds, cnt);
    }
}
