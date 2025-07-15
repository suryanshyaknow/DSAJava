package Stack.Medium;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        if (s.length() == 1)
            return false;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (st.isEmpty()) return false;
                Character op = st.peek();
                if (op == '(' && ch == ')' )
                    st.pop();
                else if (op == '{' && ch == '}')
                    st.pop();
                else if (op == '[' && ch == ']')
                    st.pop();
                else return false;
            }
        }
        return st.isEmpty();
    }
}
