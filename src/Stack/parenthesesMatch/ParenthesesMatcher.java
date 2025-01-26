package Stack.parenthesesMatch;

import Stack.Stack;

public class ParenthesesMatcher {

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }

    private static boolean isValid(String exp) {
        Stack<Character> stack = new Stack<Character>(exp.length());

        // Iterate through the character in the String
        for (char c : exp.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty())
                    return false;
                char top = (char) stack.pop();
                if (!isMatchingPair(top, c)) // Mismatch found!
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "({[()]})";
        String test2 = "({[)}]";
        String test3 = "(]";

        System.out.println(isValid(test1)); // true
        System.out.println(isValid(test2)); // false
        System.out.println(isValid(test3)); // false
    }

}
