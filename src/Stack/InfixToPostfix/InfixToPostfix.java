package Stack.InfixToPostfix;

import Stack.Stack;

import java.util.Arrays;

public class InfixToPostfix {

    public static int getPrecedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        else if (operator == '*' || operator == '/')
            return 2;
        return 0;
    }

    public static String getPostfixFromInfix2(String infix) {
        Stack<Character> stack = new Stack<Character>(infix.length());
        char[] postfixArr = new char[infix.length()];
        char[] infixArr = infix.toCharArray();

        int i = 0; // Track infix traversal
        int j = 0; // Track postfix traversal
        while (i != infix.length()) {
            if (Character.isLetterOrDigit(infixArr[i])) {
                postfixArr[j] = infixArr[i];
                i++;
                j++;
            } else {
                if (stack.isEmpty() || getPrecedence(infixArr[i]) > getPrecedence(stack.stackTop())) {
                    stack.push(infixArr[i]);
                    i++;
                } else {
                    postfixArr[j] = stack.pop();
                    j++;
                }
            }
        }
        while (!stack.isEmpty()) {
            postfixArr[j] = stack.pop();
            j++;
        }
        return Arrays.toString(postfixArr);
    }

    public static String getPostFixFromInfix(String infix) {
        Stack<Character> stack = new Stack<Character>(infix.length());
        StringBuilder postfixBuilder = new StringBuilder();

        for (char c : infix.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                if (stack.isEmpty() || getPrecedence(c) > getPrecedence(stack.peek())) {
                    stack.push(c);
                } else {
                    while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                        char op = stack.pop();
                        postfixBuilder.append(op);
                    }
                    stack.push(c); // Push the current operator after popping
                }
            } else {
                postfixBuilder.append(c);
            }
        }
        while (!stack.isEmpty()) {
            char op = stack.pop();
            postfixBuilder.append(op);
        }
        return postfixBuilder.toString();
    }

    public static void main(String[] args) {
        String infix = "A+B*C-D";

        String postfix = getPostfixFromInfix2(infix);
        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + postfix);

        String infix2 = "x-y/z-k*d";
        String postfix2 = getPostfixFromInfix2(infix2);
        System.out.println("Infix Expression: " + infix2);
        System.out.println("Postfix Expression: " + postfix2);


    }

}
