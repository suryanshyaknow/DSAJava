package Array.Medium;

public class PairWithMaxSum {

    // Given an array arr[], with 0-based indexing, select any two indexes, i and j such that i < j.
    // From the subarray arr[i...j], select the smallest and second smallest numbers and add them,
    // you will get the score for that subarray. Return the maximum possible score across all the
    // subarrays of array arr[].

    /*
        Input : arr[] = [4, 3, 1, 5, 6]
        Output : 11
        Explanation : Subarrays with smallest and second smallest are:- [4, 3] smallest = 3,second smallest = 4
        [4, 3, 1] smallest = 1, second smallest = 3
        [4, 3, 1, 5] smallest = 1, second smallest = 3
        [4, 3, 1, 5, 6] smallest = 1, second smallest = 3
        [3, 1] smallest = 1, second smallest = 3
        [3, 1, 5] smallest = 1, second smallest = 3
        [3, 1, 5, 6] smallest = 1, second smallest = 3
        [1, 5] smallest = 1, second smallest = 5
        [1, 5, 6] smallest = 1, second smallest = 5
        [5, 6] smallest = 5, second smallest = 6

        Maximum sum among all above choices is, 5 + 6 = 11
     */

    public static int pairWithMaxSumBruteForceBetter(int arr[]) {
        // Your code goes here
        int N = arr.length;
        int maxScore = Integer.MIN_VALUE;

        for (int i = 0; i < N - 1; i++) { // For at least two elements to have 'i' can go to second last ele max
            int smallest = Integer.MAX_VALUE;
            int sSmallest = Integer.MAX_VALUE;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < smallest) {
                    sSmallest = smallest;
                    smallest = arr[j];
                } else if (arr[j] != smallest && arr[j] > sSmallest) {
                    sSmallest = arr[j];
                }
                int runningScore = Integer.sum(smallest, sSmallest);
                maxScore = Integer.max(runningScore, maxScore);
            }
        }
        return maxScore;
    }

    public static int pairWithMaxSumBruteForce(int arr[]) {
        // Your code goes here
        int N = arr.length;
        int maxScore = Integer.MIN_VALUE;

        for (int i = 0; i < N - 1; i++) { // For at least two elements to have 'i' can go to second last ele max
            for (int j = i + 1; j < N; j++) {
                int runningScore = getArrayScore(arr, i, j); // Score being the sum of the largest and the second-largest element
                maxScore = Integer.max(maxScore, runningScore);
                System.out.println("ongoing maxScore: " + maxScore);
                System.out.println();
            }
        }
        return maxScore;
    }

    private static int getArrayScore(int[] arr, int startingIdx, int endingIdx) {
        int smallest = Integer.MAX_VALUE;
        int sSmallest = Integer.MAX_VALUE;
        for (int i = startingIdx; i <= endingIdx; i++) {
            if (arr[i] < smallest) {
                sSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < sSmallest && arr[i] > smallest) {
                sSmallest = arr[i];
            }
        }
        System.out.println("smallest: " + smallest);
        System.out.println("second smallest: " + sSmallest);
        return Integer.sum(smallest, sSmallest);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{642, 216, 187, 135, 28, 706, 661, 353, 890, 89};
        pairWithMaxSumBruteForce(arr);
    }

}
