package Arrays;

import java.util.*;

public class MissingAndRepeating {

    ArrayList<Integer> findTwoElementOptimal(int arr[]) {
        // code here
        int N = arr.length;

        // Form two equations
        // Form first equation by subtracting sum of first n natural nums from sumArr
        // x - y = arrSum - nSum
        // x = arrSum - nSum + y
        long arrSum = 0;
        for (int i = 0; i < N; i++) {
            arrSum += arr[i];
        }
        // Compute first n natural numbers sum
        long nSum = (long) N * (N + 1) / 2;

        // Form second equation
        // x^2 - y^2 = arrSumSq - nSumSq
        // x + y = (arrSumSQ - nNumSq) / (x - y)
        long nSumSq = (long) N * (N + 1) * (2L * N + 1) / 6;
        long arrSumSq = 0;
        for (int i = 0; i < N; i++) {
            arrSumSq += (long) arr[i] * arr[i];
        }

        long val1 = arrSum - nSum; // x - y
        long val2 = (arrSumSq - nSumSq) / val1; // x + y

        long rNum = (val1 + val2) / 2;
        long mNum = rNum - val1;

        return new ArrayList<>(Arrays.asList((int) rNum, (int) mNum));
    }

    ArrayList<Integer> findTwoElementUsingHashArr(int arr[]) {
        // code here
        int N = arr.length;

        int rNum = -1;
        int mNum = -1;

        // 1 to n, meaning n numbers, but one's missing (n-1), one appears twice (n-1+1)
        int[] hashArr = new int[N + 1]; // to track the count

        for (int i = 0; i < N; i++) {
            hashArr[arr[i]] += 1;
        }

        for (int i = 1; i <= N; i++) {
            if (hashArr[i] == 0)
                mNum = i;
            else if (hashArr[i] == 2)
                rNum = i;
        }

        return new ArrayList<>(Arrays.asList(rNum, mNum));

        // Time Complexity: O(2N)
        // Space Complexity: O(N)
    }

    ArrayList<Integer> findTwoElementBruteForce(int arr[]) {
        // code here
        int N = arr.length;

        int rNum = -1;
        int mNum = -1;

        // 1 to n, meaning n numbers, but one's missing (n-1), one appears twice (n-1+1)

        for (int i = 1; i <= N; i++) {
            int numCount = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j] == i)
                    numCount++;
            }
            if (numCount == 0)
                mNum = i;
            else if (numCount == 2)
                rNum = i;

            if (rNum != -1 && mNum != -1)
                break;
        }

        return new ArrayList<>(Arrays.asList(rNum, mNum));

        // Time Complexity: O(N^2)
        // Space Complexity: O(1)
    }

}
