package greaterpyramid.c2;

import java.util.Stack;

/*
Basic Calculator
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note:
You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:

Input: s = "1 + 1"

Output:
2


Example 2:

Input: s = " 2-1 + 2 "
Output:
 3
Example 3:

Input:
s = "(1+(4+5+2)-3)+(6+8)"
Output:
  23
Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '. s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    private static int calculate(String s){
    Stack<Integer> stack = new Stack<>();
    int result = 0;
    int number = 0;
    int sign = 1;

        for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            // Form the number (could have multiple digits)
            number = 10 * number + (c - '0');
        } else if (c == '+') {
            // Add the previous number with the correct sign
            result += sign * number;
            number = 0;
            sign = 1;
        } else if (c == '-') {
            // Add the previous number with the correct sign
            result += sign * number;
            number = 0;
            sign = -1;
        } else if (c == '(') {
            // Push the current result and sign to stack
            stack.push(result);
            stack.push(sign);
            // Reset for new sub-expression
            result = 0;
            sign = 1;
        } else if (c == ')') {
            // Finish current number
            result += sign * number;
            number = 0;
            // Apply the sign before the parenthesis
            result *= stack.pop();
            // Add the result before the parenthesis
            result += stack.pop();
        }
        // Ignore spaces
    }

    // Add the last number
        if (number != 0) {
        result += sign * number;
    }

        return result;
}

public static void main(String[] args) {
    // Test cases BODMAS
    System.out.println(calculate("1 + 1")); // Output: 2
    System.out.println(calculate(" 2-1 + 2 ")); // Output: 3
    System.out.println(calculate("(1+(4+5+2)-3)+(6+8)")); // Output: 23
}
}
