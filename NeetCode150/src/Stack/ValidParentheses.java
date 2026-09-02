package Stack;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        int N = s.length();

        // Intuition: Whenever we encounter any open bracket
        // we'll put it into the stack and whenever it's closing
        // we're gonna compare it to the top val of stack and if
        // they do not match, we early exit.

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            char ch = s.charAt(i);
            // Push if an opening bracket
            if (ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
            else {
                char peek = st.peek();
                if (ch == ')' && peek != '(') return false;
                else if (ch == '}' && peek != '{') return false;
                else if (ch == ']' && peek != '[') return false;

                st.pop();
            }
        }

        return false;
    }
}
