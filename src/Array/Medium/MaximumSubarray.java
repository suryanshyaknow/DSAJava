package Array.Medium;

public class MaximumSubarray {

    public static int maxSubArrayOptimal(int[] arr) {
        // Kadane's Algorithm
        // Simply states restart the subarray the moment the sum (till that pos) falls under zero.
        int N = arr.length;
        int maxSum = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            maxSum = Integer.max(maxSum, sum);
            // If at any given point the sum falls under zero, reinitialize the sum w 0
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    public static int maxSubArrayOptimalAndPrintArr(int[] arr) {
        // Kadane's Algorithm
        // Simply states restart the subarray the moment the sum (till that pos) falls under zero.
        int N = arr.length;
        int maxSum = Integer.MIN_VALUE;

        int sum = 0;
        int startIdx = 0;
        int endIdx = 0;
        for (int i = 0; i < N; i++) {
            if (sum == 0)
                startIdx = i;

            sum += arr[i];

//            maxSum = Integer.max(maxSum, sum);
            if (sum > maxSum) {
                maxSum = sum;
                endIdx = i;
            }

            // If at any given point the sum falls under zero, reinitialize the sum w 0
            if (sum < 0) {
                sum = 0;
//                startIdx = i + 1;
            }
        }
        System.out.println("startIdx: " + startIdx);
        System.out.println("endIdx: " + endIdx);
        for (int i = startIdx; i <= endIdx; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("maxSum: " + maxSum);
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArrayOptimalAndPrintArr(arr);
    }

    public static int maxSubArrayBruteForce(int[] arr) {
        // Form every possible array by looping through and compute the max sum
        int maxSum = Integer.MIN_VALUE;
        int N = arr.length;

        for (int i = 0; i < N; i++) {
            int subArrSum = 0;
            for (int j = i; j < N; j++) {
                subArrSum += arr[j];
                maxSum = Integer.max(subArrSum, maxSum);
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = i; j < N; j++) {
//                int subArrSum = 0;
//                for (int k = i; k <= j; k++) {
//                    subArrSum += arr[k];
//                }
//                maxSum = Integer.max(subArrSum, maxSum);
//            }
//        }
        return maxSum;
    }

}
