package LinkedList.FastAndSlowPointers;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public static boolean find(int num) {
        // TODO: Write your code here
        // We'll use slow and fast pointers to detect the cycle in sum of squares of digits.
        // And then we oughta find the starting node of the cycle, and it it's 1, boom, the
        // number is happy. Otherwise the fucker is unhappy.

        int slowPtr = num;
        int fastPtr = num;

        // Find the meeting point
        do {
            slowPtr = getDigitSquares(slowPtr); // Moves one step
            fastPtr = getDigitSquares(getDigitSquares(fastPtr)); // Moves two steps

            // However, if slowPtr or fastPtr reaches 1, the number is definitely fucking happy
            if (slowPtr == 1 || fastPtr == 1) return true;
        } while (slowPtr != fastPtr);

        return false;

        /*
            2️⃣ the Cycle Detection Phase (O(1))

            - The sum of squares process always leads to a small number relatively quickly.
            - The worst case occurs for numbers in the range of 1 to 999, as beyond this, the sum of squares reduces
            the number’s magnitude significantly.
            - All numbers above 999 shrink fast, since:
                - The largest sum of squares for a 3-digit number (999) is 243 (9² + 9² + 9²).
                - Once we drop below 243, the numbers stay within the range [1, 243].

            - Once N is ≤ 243, it can either:
                - Reach 1 (happy number ✅)
                - Get stuck in a known cycle (unhappy number ❌)
            - The largest possible cycle is at most 8 numbers long (4 → 16 → … → 4).
            - Floyd’s cycle detection runs in O(k) time, but since k ≤ 8, this is O(1).

         */
    }

    public static boolean findViaBruteForce(int num) {
        // TODO: Write your code here
        // We're gonna run a loop until the desired square gets 1
        // Or the cycle starts repeating

        Set<Integer> squares = new HashSet<>();
        while (num != 1) {
            int square = getDigitSquares(num);
            if (!squares.contains(square))
                squares.add(square);
            else
                return false;
            System.out.println(squares);
            System.out.println();
            num = square;
        }
        return true;

        /*
            Time Complexity:

            - Computing the sum of squares of digits: A number 𝑛 has log(n) (base 10) digits. Thus, extracting and
                squaring each digit takes O(log n) time.

            - Detecting cycles (set operations in brute force): The max value we can have for a sum of squares is 729
                (for 999999999, i.e., 9² * 9), meaning we won't ever have more than 729 unique values in the cycle.
                Thus, the cycle detection process takes at most O(729) ≈ O(1) operations.

            - Each number transformation takes O(log n), and since we can have at most O(729) ≈ O(1) transformations,
                the total time complexity is O(log n).

           Space Complexity:

           - The maximum unique values stored in set is at most 729 (based on experimental results).
            Hence, the worst-case space complexity is O(1).
         */
    }

    private static int getDigitSquares(int num) {
        int digitSquares = 0;
        while (num != 0) {
            int digit = num % 10;
            digitSquares += digit * digit;

            num = num / 10;
        }
        return digitSquares;
    }

    public static void main(String[] args) {
        System.out.println(findViaBruteForce(19));
//        System.out.println(findViaBruteForce(12));
    }

}
