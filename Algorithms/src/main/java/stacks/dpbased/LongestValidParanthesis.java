package stacks.dpbased;

import java.util.Stack;

/*
Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.



Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0


Constraints:

0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
 */
public class LongestValidParanthesis {
    /**
     * Calculates the length of the longest valid parentheses substring.
     *
     * @param s The input string containing only '(' and ')'.
     * @return The length of the longest valid parentheses substring.
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0; // If string is too short, no valid parentheses.
        }

        // dp[i] will store the length of the longest valid parentheses substring ending at index i.
        int[] dp = new int[n];
        int maxLength = 0; // Stores the overall maximum length found.

        // Iterate through the string starting from the second character.
        // A valid parentheses substring needs at least two characters.
        for (int i = 1; i < n; i++) {
            // If the current character is ')'
            if (s.charAt(i) == ')') {
                // Case 1: The immediately preceding character is '('.
                // Example: "...()"
                if (s.charAt(i - 1) == '(') {
                    // Current pair "()" contributes 2 to the length.
                    // If there was a valid substring ending at i-2, add its length.
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // Case 2: The immediately preceding character is ')'
                // Example: "...))" or "...())"
                else { // s.charAt(i-1) == ')'
                    // We look for an opening parenthesis that can match the current ')'
                    // This opening parenthesis would be `dp[i-1]` positions before `i-1`.
                    // So, the index of the potential matching '(' is `i - dp[i-1] - 1`.
                    int prevOpenParenIdx = i - dp[i - 1] - 1;

                    // Check if this index is valid (within bounds) and if the character at that index is '('.
                    if (prevOpenParenIdx >= 0 && s.charAt(prevOpenParenIdx) == '(') {
                        // The length is:
                        // dp[i-1] (length of valid substring ending at i-1)
                        // + 2 (for the current '()' pair)
                        // + (if applicable) dp[prevOpenParenIdx - 1] (length of valid substring before the found '(')
                        dp[i] = dp[i - 1] + 2 + (prevOpenParenIdx > 0 ? dp[prevOpenParenIdx - 1] : 0);
                    }
                }
            }
            // Update the overall maximum length.
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    /**
     * Calculates the length of the longest valid parentheses substring using a stack-based approach.
     * This approach keeps track of the indices of opening parentheses and unmatched closing parentheses.
     *
     * @param s The input string containing only '(' and ')'.
     * @return The length of the longest valid parentheses substring.
     */
    public int longestValidParenthesesWithStack(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        // Initialize stack with -1. This acts as a base for calculating lengths
        // and handles cases where the first character is ')' or when valid
        // parentheses start from index 0.
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '(') {
                stack.push(i); // Push index of opening parenthesis.
            } else { // currentChar == ')'
                stack.pop(); // Pop the top element (potential matching '(' or base -1).

                if (stack.isEmpty()) {
                    // If stack becomes empty, it means the current ')' does not have a matching '(',
                    // or it marks the end of a valid sequence. Push current index as new base.
                    stack.push(i);
                } else {
                    // If stack is not empty, the top of the stack is the index of the last unmatched '('.
                    // The length of the current valid substring is `i - stack.peek()`.
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }


    /**
     * Main method for testing the LongestValidParentheses solution.
     * Includes examples from the problem description.
     */
    public static void main(String[] args) {
        LongestValidParanthesis solution = new LongestValidParanthesis();

        // Example 1
        String s1 = "(()";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("--- DP Solution ---");
        System.out.println("Output: " + solution.longestValidParentheses(s1)); // Expected: 2
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.longestValidParenthesesWithStack(s1)); // Expected: 2
        System.out.println("------------------------------------");

        // Example 2
        String s2 = ")()())";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("--- DP Solution ---");
        System.out.println("Output: " + solution.longestValidParentheses(s2)); // Expected: 4
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.longestValidParenthesesWithStack(s2)); // Expected: 4
        System.out.println("------------------------------------");

        // Example 3
        String s3 = "";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("--- DP Solution ---");
        System.out.println("Output: " + solution.longestValidParentheses(s3)); // Expected: 0
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.longestValidParenthesesWithStack(s3)); // Expected: 0
        System.out.println("------------------------------------");

        // Additional test case: "()(()"
        String s4 = "()(()";
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("--- DP Solution ---");
        System.out.println("Output: " + solution.longestValidParentheses(s4)); // Expected: 2
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.longestValidParenthesesWithStack(s4)); // Expected: 2
        System.out.println("------------------------------------");

        // Additional test case: "(()())"
        String s5 = "(()())";
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("--- DP Solution ---");
        System.out.println("Output: " + solution.longestValidParentheses(s5)); // Expected: 6
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.longestValidParenthesesWithStack(s5)); // Expected: 6
        System.out.println("------------------------------------");

        // Additional test case: "()(())"
        String s6 = "()(())";
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("--- DP Solution ---");
        System.out.println("Output: " + solution.longestValidParentheses(s6)); // Expected: 6
        System.out.println("--- Stack Solution ---");
        System.out.println("Output: " + solution.longestValidParenthesesWithStack(s6)); // Expected: 6
        System.out.println("------------------------------------");
    }
}
