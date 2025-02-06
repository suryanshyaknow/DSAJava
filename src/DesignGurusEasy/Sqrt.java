package DesignGurusEasy;

public class Sqrt {

    public static int mySqrtBruteForce(int x) {
        // TODO: Write your code here
        int startingNum = 1;
        while (startingNum * startingNum <= x) {
            startingNum++;
        }
        return startingNum - 1;

        // Potential Overflow: When starting number becomes large (e.g. 2^15), multiplying num*num can overflow int.
        // Overflow occurs when a number exceeds the maximum value that can be stored in a given data type.
    }

    public static int mySqrt(int x) {
        // Base cases for 0 and 1
        if (x < 2) return x;
        // Introducing binary search: Reducing the number of iterations by almost half
        int low = 0, high = x;
        int mid;
        int answer = 0;

        while (low <= high) { // When left exceeds right, that means the search space has been exhausted
//            mid = (low + high) / 2; // Would overflow, could exceed Integer.MAX_VALUE
            mid = low + (high - low) / 2; // (high - low) never overflows, because it's always in range
            long midSquare = (long) mid * mid;
            if (midSquare == x)
                return mid;
            else if (midSquare <= x) { // Eliminate the left
                answer = mid;
                low = mid + 1;
            } else { // Eliminate the right half
                high = mid - 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("Sqrt(x): " + mySqrt(4));
        System.out.println("Sqrt(x): " + mySqrt(8));
        System.out.println("Sqrt(x): " + mySqrt(15));
        System.out.println("Sqrt(x): " + mySqrt(2147395600));
    }

}
