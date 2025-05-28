package GreedyAlgorithm.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSequencing {

    private static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        // code here
        int N = deadline.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            jobs.add(new Job(i, deadline[i], profit[i]));
        }
        // Sort this list by max profit
        jobs.sort((a, b) -> Integer.compare(b.profit, a.profit));

        // We oughta maintain an array of max deadline to track what and all days
        // are already booked
        // We'll tryna assign the max deadline job to the last available day so that
        // the rest of the days could be free
        // Find the max deadline to size the slot array
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        boolean[] hashArr = new boolean[maxDeadline+1];
        int maxProfit = 0;
        int nJobs = 0;

        for (int i = 0; i < N; i++) {
            for (int j = maxDeadline; j > 0; j--) {
                if (!hashArr[j]) {
                    nJobs += 1;
                    maxProfit += jobs.get(i).profit;
                    hashArr[j] = true;
                    break;
                }
            }
        }
        return new ArrayList<>(Arrays.asList(nJobs, maxProfit));
    }
}
