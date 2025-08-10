package Queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class VeganMeatPizza {

    public static int[] firstNegatives(int[] orderPlaced, int size) {
        int N = orderPlaced.length;
        int k = size;
        int res[] = new int[N - k + 1];
        int counter = 0;

        // - Validate the current window, i.e., maintain the deque.
        // - Insert the current -ve ele idx into deque for the current window.
        // - Record the current window -ve ele into res.

        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            // Validate the deque
            if (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.pollFirst();

            // Insert the current window -ve ele into dq
            if (orderPlaced[i] < 0) dq.offer(i);

            // If the window has started, start recording -ve eles into res
            if (i >= k - 1)
                res[counter++] = dq.isEmpty() ? 0 : orderPlaced[dq.peekFirst()];
        }
        return res;
    }

    public static int[] firstNegativesBruteForce(int[] orderPlaced, int size) {
        int N = orderPlaced.length;
        int k = size;
        int[] ans = new int[N - k + 1];
        int counter = 0;

        for (int i = 0; i <= N - k; i++) {
            boolean isMeat = false;
            for (int j = i; j < i + k; j++) {
                if (orderPlaced[j] < 0) {
                    isMeat = true;
                    // Break the window since meat pizza is encountered
                    ans[counter++] = orderPlaced[j];
                    break;
                }
            }
            // If meat pizza ain't found then add zero to ans
            if (!isMeat)
                ans[counter++] = 0;

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] orderPlaced = {-11, -2, 19, 37, 64, -18};
        System.out.println(Arrays.toString(firstNegatives(orderPlaced, 3)));
    }

}
