package GreedyAlgorithm.Easy;

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        int N = s.length();

        // The idea is to track the possible range of open brackets.
        int min = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '(') {
                min += 1;
                max += 1;
            } else if (s.charAt(i) == ')') {
                min -= 1;
                max -= 1;
            } else {
                min -= 1;
                max += 1;
            }
            if (min < 0) min = 0;
            if (max < 0) return false;
        }
        return min == 0;
    }

    public boolean checkValidStringBruteForce(String s) {
        // First off, the criteria for a string to be valid is to increment the count for opening parentheses,
        // decrementing it for closing ones. And if at the end of traversal the counter turns outta be zero,
        // then the string is valid.
        // However, since the order of parenthesis also matters, so if the closing one appears before the opening,
        // we decrease the counter and the moment it falls under zero, boom kaboom.

        int count = 0;
        return fn(s, 0, count);

        // Time Complexity: O(3^N) because of branching out and recursion
        // Space Complexity: O(N) => Auxiliary Stack Space
    }

    private static boolean fn(String s, Integer idx, Integer count) {
        int N = s.length();
        // Anywhere count falls under 0, early exit
        if (count < 0)
            return false;

        // If all chars are traversed, and count is zero, return true
        if (idx == N)
            return count == 0;

        // Now, handle the traversal
        if (s.charAt(idx) == '(')
            return fn(s, idx + 1, count + 1);
        else if (s.charAt(idx) == ')')
            return fn(s, idx + 1, count - 1);
        else return fn(s, idx + 1, count + 1) || fn(s, idx + 1, count - 1) || fn(s, idx + 1, count);
    }

}
