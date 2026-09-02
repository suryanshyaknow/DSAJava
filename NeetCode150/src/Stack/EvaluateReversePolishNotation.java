package Stack;

import java.util.*;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        int N = tokens.length;

        // See, first off, observe that each operator
        // is to be applied to its preceding two elements
        // so what we could do is put each operand in a
        // stack and the moment we encounter an operator
        // perform the operation on them. And add the result
        // back to the stack.

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < N; i++) {
            String str = tokens[i];
            if (!str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/"))
                st.push(Integer.parseInt(str));
            else {
                int b = st.pop();
                int a = st.pop();
                if (str.equals("+")) {
                    st.push(a + b);
                } else if (str.equals("-")) {
                    st.push(a - b);
                } else if (str.equals("*")) {
                    st.push(a * b);
                } else if (str.equals("/")) {
                    st.push(a / b);
                }
            }
        }
        // The last ele standing in the stack
        // would be the result of the exp.
        return st.peek();
    }
}
