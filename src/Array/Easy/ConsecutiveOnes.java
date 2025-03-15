package Array.Easy;

public class ConsecutiveOnes {

    public static int findMaxConsecutiveOnes(int[] arr) {
        int maxConsecutiveOnes = 0;

        int N = arr.length;
        int numConsecutiveOnes = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 0) {
                if (numConsecutiveOnes > maxConsecutiveOnes)
                    maxConsecutiveOnes = numConsecutiveOnes;
                numConsecutiveOnes = 0;
            } else
                numConsecutiveOnes += 1;

            System.out.println("numConsecutiveOnes: " + numConsecutiveOnes);
            System.out.println("maxConsecutiveOnes: " + maxConsecutiveOnes + "\n");
        }
        // Edge case: If the last ele wasn't zero the if condition never executed, so gotta make sure it does
        if (arr[N - 1] != 0 && numConsecutiveOnes > maxConsecutiveOnes) {
            maxConsecutiveOnes = numConsecutiveOnes;
        }
        return maxConsecutiveOnes;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 0, 1, 1, 1};
        findMaxConsecutiveOnes(arr);
    }

}
