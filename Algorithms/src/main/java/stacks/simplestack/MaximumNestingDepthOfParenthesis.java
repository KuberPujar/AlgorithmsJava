package stacks.simplestack;
/*
Given a valid parentheses string s, return the nesting depth of s. The nesting depth is the maximum number of nested parentheses.



Example 1:

Input: s = "(1+(2*3)+((8)/4))+1"

Output: 3

Explanation:

Digit 8 is inside of 3 nested parentheses in the string.

Example 2:

Input: s = "(1)+((2))+(((3)))"

Output: 3

Explanation:

Digit 3 is inside of 3 nested parentheses in the string.

Example 3:

Input: s = "()(())((()()))"

Output: 3



Constraints:

1 <= s.length <= 100
s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
It is guaranteed that parentheses expression s is a VPS.
 */
public class MaximumNestingDepthOfParenthesis {
    /**
     * Given a valid parentheses string s, returns the nesting depth of s.
     * The nesting depth is the maximum number of nested parentheses.
     *
     * @param s The input valid parentheses string.
     * @return The maximum nesting depth of the string.
     */
    public int maxDepth(String s) {
        int maxDepth = 0; // Stores the maximum nesting depth found so far.
        int currentDepth = 0; // Tracks the current nesting depth.

        // Iterate through each character of the input string.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If an opening parenthesis is encountered, it means we are entering
                // a new level of nesting. Increment the current depth.
                currentDepth++;
                // Update maxDepth if the currentDepth is greater than the previously recorded max.
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                // If a closing parenthesis is encountered, it means we are exiting
                // a level of nesting. Decrement the current depth.
                currentDepth--;
            }
            // Other characters (digits, operators) do not affect the nesting depth,
            // so we simply ignore them.
        }

        // After iterating through the entire string, maxDepth will hold the maximum
        // nesting depth achieved.
        return maxDepth;
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        MaximumNestingDepthOfParenthesis solver = new MaximumNestingDepthOfParenthesis();

        // Example 1: Input: s = "(1+(2*3)+((8)/4))+1", Output: 3
        String s1 = "(1+(2*3)+((8)/4))+1";
        System.out.println("--- Example 1 ---");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Maximum Nesting Depth: " + solver.maxDepth(s1)); // Expected: 3
        System.out.println();

        // Example 2: Input: s = "(1)+((2))+(((3)))", Output: 3
        String s2 = "(1)+((2))+(((3)))";
        System.out.println("--- Example 2 ---");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Maximum Nesting Depth: " + solver.maxDepth(s2)); // Expected: 3
        System.out.println();

        // Example 3: Input: s = "()(())((()()))", Output: 3
        String s3 = "()(())((()()))";
        System.out.println("--- Example 3 ---");
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Maximum Nesting Depth: " + solver.maxDepth(s3)); // Expected: 3
        System.out.println();

        // Additional Test Case: No parentheses
        String s4 = "1+2*3";
        System.out.println("--- Additional Test Case 1 (No Parentheses) ---");
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("Maximum Nesting Depth: " + solver.maxDepth(s4)); // Expected: 0
        System.out.println();

        // Additional Test Case: Simple nested
        String s5 = "((()))";
        System.out.println("--- Additional Test Case 2 (Simple Nested) ---");
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("Maximum Nesting Depth: " + solver.maxDepth(s5)); // Expected: 3
        System.out.println();

        // Additional Test Case: Single level
        String s6 = "(a+b)";
        System.out.println("--- Additional Test Case 3 (Single Level) ---");
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("Maximum Nesting Depth: " + solver.maxDepth(s6)); // Expected: 1
        System.out.println();
    }
}
