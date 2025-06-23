package stacks.simplestack;

import java.util.Stack;

/*
You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.



Example 1:

Input: s = "abbaca"
Output: "ca"
Explanation:
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
Example 2:

Input: s = "azxxzy"
Output: "ay"


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
 */
public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // If stack is not empty and top character equals current character
            if (!stack.isEmpty() && stack.peek() == c) {
                // Remove the duplicate pair
                stack.pop();
            } else {
                // Add current character to stack
                stack.push(c);
            }
        }

        // Build result string from stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        // Since we popped from stack, string is reversed, so reverse it back
        return result.reverse().toString();
    }

    // Alternative approach: Build result string in correct order
    public String removeDuplicatesAlternative(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop(); // Remove duplicate
            } else {
                stack.push(c); // Add character
            }
        }

        // Convert stack to string in correct order
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    // Using StringBuilder as stack (more efficient)
    public String removeDuplicatesOptimized(String s) {
        StringBuilder stack = new StringBuilder();

        for (char c : s.toCharArray()) {
            // Check if last character in StringBuilder equals current character
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == c) {
                // Remove last character (equivalent to pop)
                stack.deleteCharAt(stack.length() - 1);
            } else {
                // Add current character (equivalent to push)
                stack.append(c);
            }
        }

        return stack.toString();
    }

    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesInString solution = new RemoveAllAdjacentDuplicatesInString();

        // Test case 1
        String test1 = "abbaca";
        System.out.println("Test 1:");
        System.out.println("Input: " + test1);
        System.out.println("Output: " + solution.removeDuplicates(test1));
        System.out.println("Expected: ca");
        traceExecution(test1);
        System.out.println();

        // Test case 2
        String test2 = "azxxzy";
        System.out.println("Test 2:");
        System.out.println("Input: " + test2);
        System.out.println("Output: " + solution.removeDuplicates(test2));
        System.out.println("Expected: ay");
        traceExecution(test2);
        System.out.println();

        // Additional test cases
        String test3 = "aabbcc";
        System.out.println("Test 3:");
        System.out.println("Input: " + test3);
        System.out.println("Output: " + solution.removeDuplicates(test3));
        System.out.println("Expected: (empty string)");
        System.out.println();

        String test4 = "abccba";
        System.out.println("Test 4:");
        System.out.println("Input: " + test4);
        System.out.println("Output: " + solution.removeDuplicates(test4));
        traceExecution(test4);
        System.out.println();

        // Compare performance of different approaches
        System.out.println("Performance comparison:");
        String largeTest = "a".repeat(1000) + "b".repeat(1000);

        long start1 = System.nanoTime();
        String result1 = solution.removeDuplicates(largeTest);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        String result2 = solution.removeDuplicatesOptimized(largeTest);
        long end2 = System.nanoTime();

        System.out.println("Stack approach: " + (end1 - start1) / 1000000.0 + " ms");
        System.out.println("StringBuilder approach: " + (end2 - start2) / 1000000.0 + " ms");
        System.out.println("Results match: " + result1.equals(result2));
    }

    // Helper method to trace execution step by step
    public static void traceExecution(String s) {
        Stack<Character> stack = new Stack<>();

        System.out.println("\nStep-by-step execution:");
        System.out.println("Char | Action | Stack After");
        System.out.println("-----|--------|------------");

        for (char c : s.toCharArray()) {
            String action;

            if (!stack.isEmpty() && stack.peek() == c) {
                char removed = stack.pop();
                action = "Remove pair (" + removed + "," + c + ")";
            } else {
                stack.push(c);
                action = "Push '" + c + "'";
            }

            System.out.printf("  %c  | %-20s | %s%n", c, action, stackToString(stack));
        }

        // Build final result
        StringBuilder result = new StringBuilder();
        Stack<Character> temp = new Stack<>();
        temp.addAll(stack);
        for (char c : temp) {
            result.append(c);
        }

        System.out.println("Final result: \"" + result.toString() + "\"");
    }

    // Helper method to convert stack to string representation
    private static String stackToString(Stack<Character> stack) {
        if (stack.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < stack.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append("'").append(stack.get(i)).append("'");
        }
        sb.append("]");
        return sb.toString();
    }
}
