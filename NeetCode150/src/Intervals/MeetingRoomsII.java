package Intervals;

import java.util.*;

public class MeetingRoomsII {

    public int minMeetingRooms(int[] start, int[] end) {
        // code here

        // start[] and end[]
        // Minimum number of rooms required to attend all meetings.
        // See, think about this, if all meetings are held at different
        // times that a single room can accommodate all of them.
        // Meaning if there are two simultaneous meetings or overlapping
        // meetings then they need different rooms.

        // Intuition: The idea is to have something that can store the
        // rooms w data. And at any given time it can provide us
        // w the room's free to use time.
        // Traverse thru the intervals in chronological order and see if
        // there are any rooms that can be used for a given meeting at a
        // time. If not we'll add another room.

        // First off, I'll club the intervals together and sort them by
        // their start time.
        int N = start.length;
        int[][] intervals = new int[N][2];
        for (int i = 0; i < N; i++) {
            intervals[i] = new int[]{start[i], end[i]};
        }
        // Sort by start times to process in chronological manner
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Initialize a min heap to store the rooms along w their free time
        int roomCnt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        pq.offer(intervals[0][1]);
        roomCnt += 1; // One room has been occupied by the first meeting

        // Traverse over the remaining meetings and see how many rooms can
        // accommodate all of them.
        // Wile traversing the intervals and allocating a room we need to
        // check the room w smallest endtime, and for that we can use PQ.
        for (int i = 1; i < N; i++) {
            int[] curr = intervals[i];
            int currStart = curr[0];
            int currEnd = curr[1];

            // See if the room that's most likely to be free can accommodate
            // this meeting; top of the PQ, room w the earliest end time
            int prevEnd = pq.peek();
            if (currStart >= prevEnd) {
                // Same room can be used
                // Update this room's end time
                pq.poll();
                pq.offer(currEnd);
            } else {
                // We gotta add another room
                roomCnt += 1;
                pq.offer(currEnd);
            }
        } // O(N) + O(k log k) where k being number of ele in PQ

        return roomCnt;
    }
}
