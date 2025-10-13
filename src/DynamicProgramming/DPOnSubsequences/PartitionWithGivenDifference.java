package DynamicProgramming.DPOnSubsequences;

public class PartitionWithGivenDifference {

    int countPartitions(int[] arr, int d) {
        // code here
        // S1 >= S2
        // S1 - S2 = d
        // S1 + S2 = S
        // S1 = S - S2
        // S - 2S2 = d
        // S2 = (S - d) / 2

        // First off, compute S
        int S = 0;
        int N = arr.length;
        for (int i=0; i < N; i++) {
            S += arr[i];
        }

        // S2 couldn't be negative, S2 couldn't be fractional
        if (S < d || (S - d) % 2 != 0) return 0;
        int S2 = (S - d) / 2;

        return countPartitionHelper(N-1, arr, S2);
    }

    private static int countPartitionHelper(int idx, int[] arr, int target) {
        // Edge cases

        if (idx == 0) {
            if (arr[idx] == 0 && target == 0) return 2;
            else if (target == 0 || target == arr[idx]) return 1;
            else return 0;
        };

        int notTake = countPartitionHelper(idx - 1, arr, target);
        int take = 0;
        if (arr[idx] <= target) take = countPartitionHelper(idx - 1, arr, target - arr[idx]);
        return take + notTake;
    }
}
