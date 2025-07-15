package Stack.Medium;

import java.util.Stack;

public class SumOfSubarrayMinimums {

    public int sumSubarrayMinsOptimal(int[] arr) {
        int N = arr.length;
        int mod = (int) (1e9 + 7);
        int sum = 0;

        // Idea is to simply figure out how much a given ele is contributing to the sum
        // via finding there pse and nse
        int[] psee = getPSEE(arr);
        int[] nse = getNSE(arr);

        for (int i = 0; i < N; i++) {
            int starts = i - psee[i];
            int ends = nse[i] - i;
            sum = (int) (sum + (((long) starts * ends * arr[i]) % mod) % mod) % mod;
        }
        return sum;
    }

    private int[] getPSEE(int[] arr) {
        int N = arr.length;
        int[] pse = new int[N];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i])
                st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pse;
    }

    private int[] getNSE(int[] arr) {
        int N = arr.length;
        int[] nse = new int[N];
        Stack<Integer> st = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i])
                st.pop();
            nse[i] = st.isEmpty() ? N : st.peek();
            st.push(i);
        }
        return nse;
    }

    public int sumSubarrayMins(int[] arr) {
        int N = arr.length;

        int mod = (int) (1e9 + 7);
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                min = Integer.min(min, arr[j]);
                sum = (sum + min) % mod;
            }
        }
        return sum;
    }

}
