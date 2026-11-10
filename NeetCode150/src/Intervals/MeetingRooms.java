package Intervals;

import java.util.*;

public class MeetingRooms {

    static boolean canAttend(int[][] arr) {
        // code here

        int N = arr.length; // meetings

        // Intuition: It's only possible for one to
        // attend all the meetings if all of them are
        // at different non-overlapping intervals.

        // First off, sort the intervals by their start times
        // such that meetings are processed chronologically.
        // And also, one can determine whether the next meeting in
        // progression can be attended i.e. it's not overlapping w
        // the current one.
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        // Now in a nutshell, we just need to find any overlaps
        // If there's even one overlap, one can't attend 'em all.
        for (int i = 1; i < N; i++) {
            int[] curr = arr[i];
            int currStart = curr[0];
            int currEnd = curr[1];

            int[] prev = arr[i - 1];
            int prevStart = prev[0];
            int prevEnd = prev[1];

            if (currStart < prevEnd) {
                return false;
            }
        }
        return true;
    }

}
