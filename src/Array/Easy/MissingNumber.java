package Array.Easy;

import java.util.Arrays;

public class MissingNumber {

    // Given an array nums containing n distinct numbers in the range [0, n],
    // return the only number in the range that is missing from the array.

    public static int missingNumberXOR(int[] arr) {
        int N = arr.length;
        int xor1 = 0;
        int xor2 = 0;

        // XOR all numbers from 0 to N
        for (int i = 0; i < N; i++) {
            xor1 ^= i;
            xor2 ^= arr[i];
//            System.out.println("xor1 = " + xor1);
        }

        return xor1 ^ xor2 ^ N; // Since the loop for XOR1 ran only N times instead of N+1
    }


    public static int missingNumberOptimal(int[] arr) {
        // It is stated that given array contains 'n' distinct numbers instead of (n+1),
        // cuz zero is inclusive.

        // The missing number would the difference between the sum of first 'n' natural numbers,
        // and the diff between both the sum (first n and the array sum) would be the missing number.
        // But here's the caveat, if the sums happen to be equal then the missing number is 0 cuz it doesn't contribute.

        int N = arr.length;
        int sum = N * (N + 1) / 2;

        int arraySum = 0;
        for (int i = 0; i < N; i++) {
            arraySum += arr[i];
        }

        // Unnecessary check because even if the sums happen to be equal, that means the missing number is 0 anyway,
        // so the subtraction already gives the correct answer.
//        if (sum == arraySum)
//            return 0;

        return Math.abs(sum - arraySum);
    }

    public static int missingNumberBetter(int[] arr) {
        int N = arr.length;
        // Initialize a hash array
        int[] hashArr = new int[N + 1]; // Since the range is [0, N]

        // Mark the matches as 1
        for (int i = 0; i < N; i++) {
            hashArr[arr[i]] = 1;
        }
        System.out.println(Arrays.toString(hashArr));

        for (int i = 0; i <= N; i++) {
            if (hashArr[i] == 0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumberXOR(arr));
    }

    public int missingNumber(int[] arr) {
        int n = arr.length;

        for (int i = 0; i <= n; i++) {
            boolean numFound = false;
            // Linear search for the missing number
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) {
                    numFound = true;
                    break;
                }
            }
            // And if the above loop didn't break at any point, the given number wasn't there in array, so return it
            if (!numFound)
                return i;
        }
        return -1;
    }

}
