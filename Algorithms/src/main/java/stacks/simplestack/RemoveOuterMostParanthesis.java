package stacks.simplestack;

import java.util.Stack;

/*
A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.

For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.

Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.



Example 1:

Input: s = "(()())(())"
Output: "()()()"
Explanation:
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
Example 2:

Input: s = "(()())(())(()(()))"
Output: "()()()()(())"
Explanation:
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
Example 3:

Input: s = "()()"
Output: ""
Explanation:
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".


Constraints:

1 <= s.length <= 105
s[i] is either '(' or ')'.
s is a valid parentheses string.
 */
public class RemoveOuterMostParanthesis {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Only add '(' to result if stack is not empty
                // (meaning this is not the outermost opening parenthesis)
                if (!stack.isEmpty()) {
                    result.append(c);
                }
                stack.push(c);
            } else { // c == ')'
                stack.pop();
                // Only add ')' to result if stack is not empty after popping
                // (meaning this is not the outermost closing parenthesis)
                if (!stack.isEmpty()) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    // Alternative stack-based approach: Collect primitives first, then process
    public String removeOuterParenthesesAlternative(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else { // c == ')'
                stack.pop();

                // When stack becomes empty, we found a complete primitive
                if (stack.isEmpty()) {
                    // Extract the primitive substring and remove outer parentheses
                    String primitive = s.substring(start, i + 1);
                    String inner = primitive.substring(1, primitive.length() - 1);
                    result.append(inner);
                    start = i + 1; // Start of next primitive
                }
            }
        }

        return result.toString();
    }

    // Test method
    public static void main(String[] args) {
        RemoveOuterMostParanthesis solution = new RemoveOuterMostParanthesis();

        // Test cases
        String test1 = "(()())(())";
        String test2 = "(()())(())(()(()))";
        String test3 = "()()";

        System.out.println("Stack-based approach:");
        System.out.println("Test 1:");
        System.out.println("Input: " + test1);
        System.out.println("Output: " + solution.removeOuterParentheses(test1));
        System.out.println("Expected: ()()()");
        System.out.println();

        System.out.println("Test 2:");
        System.out.println("Input: " + test2);
        System.out.println("Output: " + solution.removeOuterParentheses(test2));
        System.out.println("Expected: ()()()()(())");
        System.out.println();

        System.out.println("Test 3:");
        System.out.println("Input: " + test3);
        System.out.println("Output: " + solution.removeOuterParentheses(test3));
        System.out.println("Expected: ");
        System.out.println();

        // Test alternative stack approach
        System.out.println("Alternative stack approach:");
        System.out.println("Test 1: " + solution.removeOuterParenthesesAlternative(test1));
        System.out.println("Test 2: " + solution.removeOuterParenthesesAlternative(test2));
        System.out.println("Test 3: " + solution.removeOuterParenthesesAlternative(test3));

        // Demonstrate stack behavior with step-by-step trace
        System.out.println("\nStep-by-step trace for \"(()())(())\":");
        traceExecution("(()())(())");
    }

    // Helper method to demonstrate stack behavior
    public static void traceExecution(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        System.out.println("Char | Stack | Action | Result");
        System.out.println("-----|-------|--------|--------");

        for (char c : s.toCharArray()) {
            String action = "";
            if (c == '(') {
                if (!stack.isEmpty()) {
                    result.append(c);
                    action = "Add '(' to result";
                } else {
                    action = "Skip outermost '('";
                }
                stack.push(c);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    result.append(c);
                    action = "Add ')' to result";
                } else {
                    action = "Skip outermost ')'";
                }
            }

            System.out.printf("  %c  | %s | %s | %s%n",
                    c, stack.toString(), action, result.toString());
        }
    }
}
