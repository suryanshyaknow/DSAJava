package Stack.Medium;

import java.util.Stack;

public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        int N = num.length();
        if (k == N) return "0";

        // The idea is pretty simple. We're gonna remove the 'k' largest digits from the start.
        // But we gotta take care of some edge cases:
        // - If k == N, return a string containing 0, i.e., "0".
        // - Gotta take care of leading zeroes.
        // - If even after traversal k still remains, then we're gonna manually pop off the remaining digits.

        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < N; i++) {
            while (!st.isEmpty() && k > 0 && st.peek() - '0' > num.charAt(i) - '0') {
                st.pop();
                k--;
            }
            st.push(num.charAt(i));
        } // O(N)

        // If k still remains, manually pop
        while (k > 0) {
            st.pop();
            k--;
        } // O(K)

        // Build result
        while (!st.isEmpty()) {
            res.append(st.pop());
        } // O(N)

        // Take care of leading zeroes
        while (!res.isEmpty() && res.charAt(res.length() - 1) - '0' == 0) res.deleteCharAt(res.length() - 1);
        res.reverse();
        // O(N)

        String resStr = res.toString();
        return resStr.isEmpty() ? "0" : resStr;

        // Time complexity: O(3N + K)
        // Space complexity: O(N) (stack) + O(N) (String Builder)
    }

}
