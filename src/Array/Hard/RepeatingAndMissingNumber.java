package Array.Hard;

import java.util.ArrayList;
import java.util.List;

public class RepeatingAndMissingNumber {

    ArrayList<Integer> findTwoElementOptimal(int arr[]) {
        // code here
        int N = arr.length;
        ArrayList<Integer> res = new ArrayList<>();
        int repeating = -1;
        int missing = -1;

        // Step i. Form the equation 1 from the array sum and sum of the first n natural numbers
        long arrSum = 0;
        long arrSumSquares = 0;
        for (int i = 0; i < N; i++) {
            arrSum += arr[i];
            arrSumSquares += (long) arr[i] * arr[i];
        }
        long nSum = (long) N * (N + 1) / 2;
//        repeating - missing = arrSum - nSum; --> Equation 1
        long diff1 = arrSum - nSum; // r - m

        // Step ii. Form the equation 2 via squares
        long nSumSquares = (long) N * (N + 1) * (2L * N + 1) / 6;
//        for (int i = 1; i <= N; i++) {
//            nSumSquares += (long) i * i;
//        }
        long diff2 = (arrSumSquares - nSumSquares) / diff1; // r + m

        repeating = (int) ((diff1 + diff2) / 2);
        missing = (int) (repeating - diff1);

        res.add(repeating);
        res.add(missing);
        return res;
    }

    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        int N = arr.length;
        ArrayList<Integer> res = new ArrayList<>();

        // Use a freq array and populate it
        int[] freqArr = new int[N];

        for (int i = 0; i < N; i++) {
            freqArr[i] += 1;
        }

        for (int i = 0; i < N; i++) {
            if (freqArr[i] > 1)
                res.addFirst(i);
            else if (freqArr[i] == 0)
                res.addLast(i);
        }
        return res;
    }

}
