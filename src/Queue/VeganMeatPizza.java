package Queue;

import java.util.Arrays;

public class VeganMeatPizza {

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
        System.out.println(Arrays.toString(firstNegativesBruteForce(orderPlaced, 3)));
    }

}
