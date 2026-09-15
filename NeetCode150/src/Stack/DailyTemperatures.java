package Stack;

import java.util.*;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temps) {
        int N = temps.length;

        // Since, for each day we're to find the next day
        // where the temp is greater than the given day, this
        // gives me an impression to use monotonic decreasing
        // temperature.

        Stack<Integer> st = new Stack<>(); // will store indices
        int[] res = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            // Enforce the decreasing order
            int ele = temps[i];
            while (!st.isEmpty() && temps[st.peek()] < ele) st.pop();

            res[i] = st.peek() - i;
            st.push(i);
        }

        return res;
    }

    public int[] dailyTemperaturesLeftToRight(int[] temps) {
        int N = temps.length;

        // For each day, we have to find a day warmer than
        // the given day. So what am I gonna do is assume the
        // current day is warmer for some day and store it in
        // greater temperature candidates list. And then when
        // I find a greater temperature, I resolve the lesser
        // ones.
        Stack<Integer> st  = new Stack<>();
        int[] res = new int[N];

        st.push(0);
        for (int i=1; i < N; i++) {
            int ele = temps[i];
            while (!st.isEmpty() && temps[st.peek()] <= ele) {
                int prev = st.pop();
                // now it means we've warmer temp for prev
                res[prev] = i - prev;
            }
            st.push(i);
        }

        return res;
    }

}
