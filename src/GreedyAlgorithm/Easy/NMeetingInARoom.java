package GreedyAlgorithm.Easy;

import java.util.ArrayList;
import java.util.List;

public class NMeetingInARoom {

    private static class Meeting {

        int start;
        int end;
        int idx; // Meeting idx

        Meeting(int start, int end, int idx) {
            this.start = start;
            this.end = end;
            this.idx = idx;
        }

    }

    public static int maxMeetings(int start[], int end[]) {
        // add your code here
        int N = start.length;

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            meetings.add(new Meeting(start[i], end[i], i));
        }

        // Ideally to maximize the number of meetings we'd wanna have ones
        // w faster end timings
        // So first off, sort the meetings w their end times
        meetings.sort((a, b) -> Integer.compare(a.end, b.end)); // O(N Log N)

        int count = 1; // First meeting could already be considered
        int freeEnd = meetings.get(0).end;
        for (int i = 1; i < N; i++) {
            // It's given that the start time of a meeting shpuld not be equal
            // to the end time of the prior meet..
            if (meetings.get(i).start > freeEnd) {
                count += 1;
                freeEnd = meetings.get(i).end;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int start[] = {1, 3, 0, 5, 8, 5};
        int end[] = {2, 4, 6, 7, 9, 9};

        maxMeetings(start, end);
    }

}
