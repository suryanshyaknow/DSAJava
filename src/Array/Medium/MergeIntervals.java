package Array.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public static int[][] mergeOptimal(int[][] intervals) {
        int N = intervals.length;
        // First off, sort the intervals to club 'em together
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // O(N log N)

//        List<int[]> res = new ArrayList<>();
        List<int[]> res = new ArrayList<>();
        // Iterate and make the first sub-array as starting interval
        // and update it accordingly
        for (int i = 0; i < N; i++) {
            if (res.isEmpty() || res.getLast()[1] < intervals[i][0]) {  // We need to anyhow form the first interval and also switch 'em
                res.add(intervals[i]);
            } else {
                res.getLast()[1] = Integer.max(intervals[i][1], res.getLast()[1]);
            }
        } // O(N)

//        int[][] ans = new int[res.size()][2];
//        for (int i = 0; i < res.size(); i++) {
//            ans[i] = res.get(i);
//        }
        return res.toArray(new int[res.size()][]);
    }

    public static int[][] merge(int[][] intervals) {
        int N = intervals.length;
        // First off, sort the intervals to club 'em together
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // O(N log N)

//        List<int[]> res = new ArrayList<>();
        List<int[]> res = new ArrayList<>();
        // Iterate and make the first sub-array as starting interval
        // and update it accordingly
        for (int i = 0; i < N; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            // Given there's an interval in the res and the current interval is a part of that interval,
            // we do nothing and skip to the next iteration
            if (!res.isEmpty() && end <= res.get(res.size() - 1)[1])
                continue;

            for (int j = i + 1; j < N; j++) {
                if (intervals[j][0] <= end) // It's an interval
                    end = Integer.max(end, intervals[j][1]);
                else // As it's a sorted array
                    break;
            }
            res.add(new int[]{start, end});
        } // O(2N)

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

}
