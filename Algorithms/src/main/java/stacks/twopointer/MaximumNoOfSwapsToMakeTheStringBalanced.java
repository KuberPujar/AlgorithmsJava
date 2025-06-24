package stacks.twopointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.

A string is called balanced if and only if:

It is the empty string, or
It can be written as AB, where both A and B are balanced strings, or
It can be written as [C], where C is a balanced string.
You may swap the brackets at any two indices any number of times.

Return the minimum number of swaps to make s balanced.



Example 1:

Input: s = "][]["
Output: 1
Explanation: You can make the string balanced by swapping index 0 with index 3.
The resulting string is "[[]]".
Example 2:

Input: s = "]]][[["
Output: 2
Explanation: You can do the following to make the string balanced:
- Swap index 0 with index 4. s = "[]][][".
- Swap index 1 with index 5. s = "[[][]]".
The resulting string is "[[][]]".
Example 3:

Input: s = "[]"
Output: 0
Explanation: The string is already balanced.


Constraints:

n == s.length
2 <= n <= 106
n is even.
s[i] is either '[' or ']'.
The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 */
public class MaximumNoOfSwapsToMakeTheStringBalanced {
    /**
     * Solution 1: Stack-based approach
     *
     * Algorithm:
     * 1. Use stack to track unmatched opening brackets
     * 2. For each closing bracket that can't be matched, count as unmatched
     * 3. Each unmatched closing bracket needs to be swapped with an opening bracket
     * 4. Formula: swaps = (unmatched_closing + 1) / 2
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) for stack
     */
    public int minSwapsStack(String s) {
        Stack<Character> stack = new Stack<>();
        int unmatchedClosing = 0;

        for (char c : s.toCharArray()) {
            if (c == '[') {
                // Push opening bracket
                stack.push(c);
            } else { // c == ']'
                if (!stack.isEmpty()) {
                    // Match with opening bracket
                    stack.pop();
                } else {
                    // Unmatched closing bracket
                    unmatchedClosing++;
                }
            }
        }

        // Each swap fixes 2 unmatched closing brackets
        // So we need ceil(unmatchedClosing / 2) swaps
        return (unmatchedClosing + 1) / 2;
    }

    /**
     * Solution 2: Two-pointer approach (Space Optimized)
     *
     * Algorithm:
     * 1. Use a counter instead of stack to track balance
     * 2. Increment for '[', decrement for ']'
     * 3. When counter goes negative, we have unmatched closing brackets
     * 4. Count these unmatched closing brackets
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int minSwapsTwoPointer(String s) {
        int balance = 0;
        int unmatchedClosing = 0;

        for (char c : s.toCharArray()) {
            if (c == '[') {
                balance++;
            } else { // c == ']'
                balance--;
                if (balance < 0) {
                    // We have more closing brackets than opening so far
                    unmatchedClosing++;
                    balance = 0; // Reset balance as we'll need to swap this
                }
            }
        }

        // Each swap fixes 2 unmatched closing brackets
        return (unmatchedClosing + 1) / 2;
    }

    /**
     * Solution 3: Mathematical approach (Most Optimized)
     *
     * Key insight: Since we have equal numbers of '[' and ']',
     * we only need to count unmatched closing brackets.
     * Each swap converts '][' to '[]', fixing 2 mismatches.
     */
    public int minSwapsMath(String s) {
        int unmatchedClosing = 0;
        int openCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '[') {
                openCount++;
            } else {
                if (openCount > 0) {
                    openCount--; // Match with previous opening bracket
                } else {
                    unmatchedClosing++; // Unmatched closing bracket
                }
            }
        }

        return (unmatchedClosing + 1) / 2;
    }

    /**
     * Helper method to demonstrate the swapping process
     */
    public String demonstrateSwaps(String s) {
        char[] chars = s.toCharArray();
        int swaps = 0;
        Stack<Integer> openIndices = new Stack<>();
        List<Integer> unmatchedClosing = new ArrayList<>();

        // Find unmatched closing brackets
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[') {
                openIndices.push(i);
            } else {
                if (!openIndices.isEmpty()) {
                    openIndices.pop();
                } else {
                    unmatchedClosing.add(i);
                }
            }
        }

        // Remaining in openIndices are unmatched opening brackets
        List<Integer> unmatchedOpening = new ArrayList<>(openIndices);
        Collections.reverse(unmatchedOpening); // Reverse to get rightmost first

        // Perform swaps
        StringBuilder result = new StringBuilder();
        result.append("Original: ").append(s).append("\n");
        result.append("Unmatched closing brackets: ").append(unmatchedClosing.size()).append("\n");
        result.append("Unmatched opening brackets: ").append(unmatchedOpening.size()).append("\n");

        // Fix: Only swap up to the minimum of unmatched closing and opening brackets
        int swapsNeeded = Math.min(unmatchedClosing.size(), unmatchedOpening.size());

        if (swapsNeeded == 0) {
            result.append("String is already balanced or no swaps needed.\n");
            return result.toString();
        }

        for (int i = 0; i < swapsNeeded; i++) {
            int closingIndex = unmatchedClosing.get(i);
            int openingIndex = unmatchedOpening.get(i);

            // Swap
            char temp = chars[closingIndex];
            chars[closingIndex] = chars[openingIndex];
            chars[openingIndex] = temp;

            swaps++;
            result.append("Swap ").append(swaps).append(": index ")
                    .append(closingIndex).append(" with index ").append(openingIndex)
                    .append(" -> ").append(new String(chars)).append("\n");
        }

        result.append("Total swaps performed: ").append(swaps).append("\n");
        result.append("Final result: ").append(new String(chars)).append("\n");

        return result.toString();
    }

    public static void main(String[] args) {
        MaximumNoOfSwapsToMakeTheStringBalanced solution = new MaximumNoOfSwapsToMakeTheStringBalanced();

        // Test cases
        String[] testCases = {
                "][][",
                "]]][[[",
                "[]",
                "]][[",
                "][][][",
                "[[[]]]"
        };

        int[] expectedResults = {1, 2, 0, 1, 2, 0};

        System.out.println("=== Minimum Swaps to Balance Brackets ===\n");

        for (int i = 0; i < testCases.length; i++) {
            String s = testCases[i];
            int expected = expectedResults[i];

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: \"" + s + "\"");

            int result1 = solution.minSwapsStack(s);
            int result2 = solution.minSwapsTwoPointer(s);
            int result3 = solution.minSwapsMath(s);

            System.out.println("Stack Approach: " + result1);
            System.out.println("Two Pointer: " + result2);
            System.out.println("Mathematical: " + result3);
            System.out.println("Expected: " + expected);
            System.out.println("All methods match: " +
                    (result1 == result2 && result2 == result3 && result3 == expected));

            if (s.length() <= 10) {
                System.out.println("\nSwapping demonstration:");
                System.out.println(solution.demonstrateSwaps(s));
            }

            System.out.println("─".repeat(50));
        }

        // Performance analysis
        System.out.println("\n=== Algorithm Analysis ===");
        System.out.println("1. Stack Approach:");
        System.out.println("   - Time: O(n), Space: O(n)");
        System.out.println("   - Uses stack to track unmatched brackets");

        System.out.println("\n2. Two Pointer Approach:");
        System.out.println("   - Time: O(n), Space: O(1)");
        System.out.println("   - Uses balance counter instead of stack");

        System.out.println("\n3. Mathematical Approach:");
        System.out.println("   - Time: O(n), Space: O(1)");
        System.out.println("   - Most optimized, directly counts unmatched closing brackets");

        System.out.println("\nKey Insight:");
        System.out.println("Each swap converts '][' to '[]', fixing 2 unmatched brackets.");
        System.out.println("Formula: swaps = ceil(unmatched_closing_brackets / 2)");
        System.out.println("         = (unmatched_closing_brackets + 1) / 2");
    }
}

/*
 * Detailed Walkthrough for Example 1: "][]["
 *
 * Stack Approach:
 * i=0, ']': stack empty, unmatchedClosing = 1
 * i=1, '[': push to stack = ['[']
 * i=2, ']': pop from stack, stack = []
 * i=3, '[': push to stack = ['[']
 * Result: unmatchedClosing = 1, swaps = (1+1)/2 = 1
 *
 * Two Pointer Approach:
 * i=0, ']': balance = -1 < 0, unmatchedClosing = 1, reset balance = 0
 * i=1, '[': balance = 1
 * i=2, ']': balance = 0
 * i=3, '[': balance = 1
 * Result: unmatchedClosing = 1, swaps = (1+1)/2 = 1
 *
 * Why this works:
 * - We need to swap the first ']' with some '[' to balance
 * - One swap: "][" -> "[]" fixes the imbalance
 * - Pattern: "][][][" needs 2 swaps: "][" -> "[]" twice
 */
