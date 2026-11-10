package Intervals;

import java.util.*;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return null;
        int N = intervals.length;

        // Intuition: The idea is pretty simple.
        // Sort all the intervals by their starts so that
        // potential overlapping intervals are adjacent and
        // can be processed together.
        // Then compare the curr interval w the last one to
        // determine whether they are overlapping.

        // Sort the intervals by their start
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // O(N log N)

        List<int[]> res = new ArrayList<>();
        // Append the first interval
        res.add(intervals[0]);

        // Iterate over intervals, merge, and store
        for (int i = 1; i < N; i++) {
            int[] prev = res.get(res.size() - 1);
            int prevStart = prev[0];
            int prevEnd = prev[1];

            // Curr interval
            int[] curr = intervals[i];
            int currStart = curr[0];
            int currEnd = curr[1];

            // See if start of curr is >= prev end
            if (currStart <= prevEnd) {
                // Means there's an overlap
                prev[1] = Math.max(prevEnd, currEnd);
                // No insertion because the prev interval is being overlapped
            } else {
                res.add(curr);
            }
        } // O(N)

        // Populate the res int[]
        int[][] resArr = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++)
            resArr[i] = res.get(i);

        return resArr;
    }
}
