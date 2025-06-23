package stacks.simplestack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
You are given an integer array target and an integer n.

You have an empty stack with the two following operations:

"Push": pushes an integer to the top of the stack.
"Pop": removes the integer on the top of the stack.
You also have a stream of the integers in the range [1, n].

Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to target. You should follow the following rules:

If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the stack.
If the stack is not empty, pop the integer at the top of the stack.
If, at any moment, the elements in the stack (from the bottom to the top) are equal to target, do not read new integers from the stream and do not do more operations on the stack.
Return the stack operations needed to build target following the mentioned rules. If there are multiple valid answers, return any of them.



Example 1:

Input: target = [1,3], n = 3
Output: ["Push","Push","Pop","Push"]
Explanation: Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Pop the integer on the top of the stack. s = [1].
Read 3 from the stream and push it to the stack. s = [1,3].
Example 2:

Input: target = [1,2,3], n = 3
Output: ["Push","Push","Push"]
Explanation: Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Read 3 from the stream and push it to the stack. s = [1,2,3].
Example 3:

Input: target = [1,2], n = 4
Output: ["Push","Push"]
Explanation: Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
The answers that read integer 3 from the stream are not accepted.


Constraints:

1 <= target.length <= 100
1 <= n <= 100
1 <= target[i] <= n
target is strictly increasing.
 */
public class BuildAnArrayWithStackOperations {
    public List<String> buildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int streamValue = 1; // Current value from stream [1, n]
        int targetIndex = 0; // Index in target array

        while (targetIndex < target.length && streamValue <= n) {
            // Always push the current stream value
            stack.push(streamValue);
            operations.add("Push");

            // Check if the pushed value matches the current target
            if (stack.peek() == target[targetIndex]) {
                // Perfect match, move to next target element
                targetIndex++;
            } else {
                // Not a match, pop it immediately
                stack.pop();
                operations.add("Pop");
            }

            // Move to next stream value
            streamValue++;
        }

        return operations;
    }

    // Alternative implementation with more explicit logic
    public List<String> buildArrayAlternative(int[] target, int n) {
        List<String> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int current = 1; // Current number from stream
        int targetIdx = 0; // Index in target array

        while (targetIdx < target.length) {
            if (current == target[targetIdx]) {
                // Found the target number, push it
                stack.push(current);
                result.add("Push");
                targetIdx++;
                current++;
            } else {
                // Not the target number, push then pop
                stack.push(current);
                result.add("Push");
                stack.pop();
                result.add("Pop");
                current++;
            }
        }

        return result;
    }

    // Optimized approach without actually using stack (since we only care about operations)
    public List<String> buildArrayOptimized(int[] target, int n) {
        List<String> operations = new ArrayList<>();
        int streamValue = 1;

        for (int targetValue : target) {
            // Push and pop until we reach the target value
            while (streamValue < targetValue) {
                operations.add("Push");
                operations.add("Pop");
                streamValue++;
            }

            // Push the target value
            operations.add("Push");
            streamValue++;
        }

        return operations;
    }

    public static void main(String[] args) {
        BuildAnArrayWithStackOperations solution = new BuildAnArrayWithStackOperations();

        // Test case 1
        int[] target1 = {1, 3};
        int n1 = 3;
        System.out.println("Test 1:");
        System.out.println("Target: " + Arrays.toString(target1));
        System.out.println("n: " + n1);
        List<String> result1 = solution.buildArray(target1, n1);
        System.out.println("Operations: " + result1);
        System.out.println("Expected: [Push, Push, Pop, Push]");
        simulateOperations(result1, n1);
        System.out.println();

        // Test case 2
        int[] target2 = {1, 2, 3};
        int n2 = 3;
        System.out.println("Test 2:");
        System.out.println("Target: " + Arrays.toString(target2));
        System.out.println("n: " + n2);
        List<String> result2 = solution.buildArray(target2, n2);
        System.out.println("Operations: " + result2);
        System.out.println("Expected: [Push, Push, Push]");
        simulateOperations(result2, n2);
        System.out.println();

        // Test case 3
        int[] target3 = {1, 2};
        int n3 = 4;
        System.out.println("Test 3:");
        System.out.println("Target: " + Arrays.toString(target3));
        System.out.println("n: " + n3);
        List<String> result3 = solution.buildArray(target3, n3);
        System.out.println("Operations: " + result3);
        System.out.println("Expected: [Push, Push]");
        simulateOperations(result3, n3);
        System.out.println();

        // Additional test case
        int[] target4 = {2, 4, 6};
        int n4 = 6;
        System.out.println("Test 4:");
        System.out.println("Target: " + Arrays.toString(target4));
        System.out.println("n: " + n4);
        List<String> result4 = solution.buildArray(target4, n4);
        System.out.println("Operations: " + result4);
        simulateOperations(result4, n4);
        System.out.println();

        // Compare different approaches
        System.out.println("Comparing approaches for target [1,3], n=3:");
        System.out.println("Main: " + solution.buildArray(target1, n1));
        System.out.println("Alternative: " + solution.buildArrayAlternative(target1, n1));
        System.out.println("Optimized: " + solution.buildArrayOptimized(target1, n1));
    }

    // Helper method to simulate and trace the operations
    public static void simulateOperations(List<String> operations, int n) {
        Stack<Integer> stack = new Stack<>();
        int streamValue = 1;

        System.out.println("\nSimulation trace:");
        System.out.println("Operation | Stream | Action | Stack After");
        System.out.println("----------|--------|--------|------------");

        for (String op : operations) {
            String action = "";

            if (op.equals("Push")) {
                stack.push(streamValue);
                action = "Push " + streamValue + " from stream";
                streamValue++;
            } else if (op.equals("Pop")) {
                int popped = stack.pop();
                action = "Pop " + popped;
            }

            System.out.printf("   %-7s|   %-4d | %-18s | %s%n",
                    op, streamValue - 1, action, stack.toString());
        }

        System.out.println("Final stack: " + stack);
    }

    // Method to trace the algorithm logic step by step
    public static void traceAlgorithm(int[] target, int n) {
        System.out.println("\nAlgorithm trace for target " + Arrays.toString(target) + ", n=" + n);
        System.out.println("Stream Value | Target Value | Match? | Action");
        System.out.println("-------------|--------------|--------|--------");

        List<String> operations = new ArrayList<>();
        int streamValue = 1;
        int targetIndex = 0;

        while (targetIndex < target.length && streamValue <= n) {
            boolean match = (streamValue == target[targetIndex]);
            String action;

            if (match) {
                operations.add("Push");
                action = "Push (keep)";
                targetIndex++;
            } else {
                operations.add("Push");
                operations.add("Pop");
                action = "Push & Pop (discard)";
            }

            System.out.printf("     %-7d |      %-7d | %-6s | %s%n",
                    streamValue, target[targetIndex - (match ? 1 : 0)], match, action);

            streamValue++;
        }

        System.out.println("Operations: " + operations);
    }
}
