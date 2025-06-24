package stacks.recursion;

import java.util.Stack;

/*
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "1 + 1"
Output: 2
Example 2:

Input: s = " 2-1 + 2 "
Output: 3
Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23


Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    // A global index that tracks the current parsing position in the input string.
    // This allows recursive calls to seamlessly continue parsing from where the previous call left off.
    private int currentIdx;

    /**
     * Evaluates a basic mathematical expression string containing digits, '+', '-', '(', ')', and spaces.
     * This solution employs a recursive approach to handle nested parentheses and uses an explicit Stack
     * to manage the state (intermediate result and sign) of the outer expressions when descending into
     * a sub-expression.
     *
     * The algorithm processes the string character by character:
     * - Digits are parsed to form numbers and added to the current result based on the active sign.
     * - '+' and '-' update the sign for the subsequent number or sub-expression.
     * - '(' triggers a recursive call to evaluate the nested sub-expression. Before the call, the
     * current result and sign are pushed onto the stack to be restored later.
     * - ')' signifies the end of the current sub-expression, and its accumulated result is returned.
     *
     * @param s The input expression string representing a valid mathematical expression.
     * @return The integer result of the evaluated expression.
     */
    public int calculate(String s) {
        // Initialize the global index to 0 at the start of each calculation.
        currentIdx = 0;
        // Convert the string to a character array for efficient random access during parsing.
        char[] chars = s.toCharArray();
        // Start the recursive evaluation process.
        return evaluateExpression(chars);
    }

    /**
     * Recursive helper function to evaluate a sub-expression within the main expression.
     * This function processes characters from the 'currentIdx' and accumulates a result
     * until it encounters a closing parenthesis ')' or reaches the end of the input string.
     *
     * @param chars The character array of the input expression.
     * @return The calculated integer value of the sub-expression being evaluated by this recursive call.
     */
    private int evaluateExpression(char[] chars) {
        // Stack to store the accumulated result and the sign active *before* entering a new parenthesis block.
        // When '(' is encountered:
        // - The current 'currentResult' is pushed (the sum accumulated up to that point).
        // - The current 'sign' is pushed (the sign applicable to the sub-expression inside the parentheses).
        // This stack effectively maintains the context of the parent expression.
        Stack<Integer> stack = new Stack<>();

        // 'currentResult' accumulates the sum of numbers and evaluated sub-expressions at the current level.
        int currentResult = 0;
        // 'sign' determines whether the next number or sub-expression should be added (1) or subtracted (-1).
        int sign = 1; // Default sign is positive.

        // Loop through the characters of the expression from the global 'currentIdx'.
        // The loop continues until the end of the string or until a ')' is encountered,
        // which signals the end of the current sub-expression.
        while (currentIdx < chars.length) {
            char c = chars[currentIdx];

            if (Character.isDigit(c)) {
                // If the character is a digit, parse the entire number.
                int num = 0;
                while (currentIdx < chars.length && Character.isDigit(chars[currentIdx])) {
                    num = num * 10 + (chars[currentIdx] - '0');
                    currentIdx++; // Advance currentIdx while parsing the number
                }
                // Add the parsed number to the 'currentResult', applying the current 'sign'.
                currentResult += sign * num;
                // Decrement 'currentIdx' by one. This is crucial because the outer 'while' loop
                // will increment 'currentIdx' again at the end of this iteration. If we didn't
                // decrement, we would skip the character immediately after the number.
                currentIdx--;
            } else if (c == '+') {
                // If it's a plus sign, the next number/sub-expression will be added.
                sign = 1;
            } else if (c == '-') {
                // If it's a minus sign, the next number/sub-expression will be subtracted.
                sign = -1;
            } else if (c == '(') {
                // If an opening parenthesis is encountered, it means a nested sub-expression begins.
                // 1. Save the current state of the outer expression onto the stack.
                //    Push the result accumulated so far in the current level.
                stack.push(currentResult);
                //    Push the sign that was active *before* this parenthesis.
                stack.push(sign);

                // 2. Reset 'currentResult' and 'sign' for the new sub-expression.
                //    The sub-expression starts with its own fresh calculation context.
                currentResult = 0;
                sign = 1;

                // 3. Move past the '(' character and recursively evaluate the sub-expression.
                currentIdx++; // Advance 'currentIdx' to the character immediately after '('
                int subExpressionValue = evaluateExpression(chars); // Recursive call

                // 4. After the recursive call returns (meaning the sub-expression is evaluated):
                //    Restore the state of the outer expression from the stack.
                //    Pop the 'prevSign' (sign before the parenthesis).
                int prevSign = stack.pop();
                //    Pop the 'prevResult' (result accumulated before the parenthesis).
                int prevResult = stack.pop();

                // 5. Integrate the sub-expression's value into the 'prevResult'.
                //    The sub-expression's value is added/subtracted to 'prevResult' based on 'prevSign'.
                currentResult = prevResult + prevSign * subExpressionValue;
                // 'currentIdx' is already pointing at the ')' that terminated the sub-expression
                // when the recursive call returned. The outer loop's increment will handle moving past it.
            } else if (c == ')') {
                // If a closing parenthesis is encountered, the current sub-expression is complete.
                // Return the accumulated 'currentResult' for this sub-expression.
                return currentResult;
            }
            // Move to the next character for the next iteration of the loop.
            currentIdx++;
        }

        // If the loop finishes, it means we have processed the entire string (or the current sub-expression
        // that was not enclosed in outer parentheses).
        // Return the final accumulated result.
        return currentResult;
    }

    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();

        // Example 1
        String expression1 = "1 + 1";
        System.out.println("Input: \"" + expression1 + "\"");
        System.out.println("Output: " + calculator.calculate(expression1)); // Expected: 2

        // Example 2
        String expression2 = " 2-1 + 2 ";
        System.out.println("Input: \"" + expression2 + "\"");
        System.out.println("Output: " + calculator.calculate(expression2)); // Expected: 3

        // Example 3
        String expression3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Input: \"" + expression3 + "\"");
        System.out.println("Output: " + calculator.calculate(expression3)); // Expected: 23
    }
}
