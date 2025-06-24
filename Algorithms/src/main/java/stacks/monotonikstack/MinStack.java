package stacks.monotonikstack;

import java.util.Stack;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.



Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack {

    /**
     * MinStack implementation with O(1) time complexity for all operations.
     * Uses two approaches: Pair-based and Two-Stack approach.
     */

        // Approach 1: Using a single stack with pairs (value, currentMin)
        private Stack<int[]> stack;

        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int val) {
            // If stack is empty, val is both the value and minimum
            if (stack.isEmpty()) {
                stack.push(new int[]{val, val});
            } else {
                // Current minimum is the smaller of val and previous minimum
                int currentMin = Math.min(val, stack.peek()[1]);
                stack.push(new int[]{val, currentMin});
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }

    /**
     * Alternative implementation using two separate stacks.
     * More intuitive and memory-efficient in some cases.
     */
    class MinStackTwoStacks {
        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;

        public MinStackTwoStacks() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            mainStack.push(val);

            // Push to minStack if it's empty or val is <= current minimum
            // We use <= to handle duplicate minimum values correctly
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int poppedValue = mainStack.pop();

            // If popped value equals current minimum, remove it from minStack too
            if (poppedValue == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return mainStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * Space-optimized approach using difference encoding.
     * Stores only differences from minimum to save space.
     */
    class MinStackOptimized {
        private Stack<Long> stack;
        private long min;

        public MinStackOptimized() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(0L);
                min = val;
            } else {
                // Store difference between val and current min
                long diff = (long) val - min;
                stack.push(diff);

                // If difference is negative, val becomes new minimum
                if (diff < 0) {
                    min = val;
                }
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;

            long diff = stack.pop();

            // If difference was negative, restore previous minimum
            if (diff < 0) {
                min = min - diff;
            }
        }

        public int top() {
            long diff = stack.peek();

            // If difference is negative, top element is current min
            if (diff < 0) {
                return (int) min;
            } else {
                return (int) (min + diff);
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    /**
     * Test class to demonstrate all implementations
     */
    class MinStackTest {
        public static void main(String[] args) {
            System.out.println("=== Testing MinStack (Pair-based approach) ===");
            testMinStackImplementation();

            System.out.println("\n=== Testing MinStackTwoStacks ===");
            testTwoStacksImplementation();

            System.out.println("\n=== Testing MinStackOptimized ===");
            testOptimizedImplementation();
        }

        private static void testMinStackImplementation() {
            MinStack minStack = new MinStack();

            // Example from problem: ["MinStack","push","push","push","getMin","pop","top","getMin"]
            // [[],[-2],[0],[-3],[],[],[],[]]

            minStack.push(-2);
            System.out.println("Pushed -2");

            minStack.push(0);
            System.out.println("Pushed 0");

            minStack.push(-3);
            System.out.println("Pushed -3");

            System.out.println("getMin(): " + minStack.getMin()); // Expected: -3

            minStack.pop();
            System.out.println("Popped top element");

            System.out.println("top(): " + minStack.top()); // Expected: 0
            System.out.println("getMin(): " + minStack.getMin()); // Expected: -2
        }

        private static void testTwoStacksImplementation() {
            MinStackTwoStacks minStack = new MinStackTwoStacks();

            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            System.out.println("getMin(): " + minStack.getMin()); // -3

            minStack.pop();
            System.out.println("top(): " + minStack.top()); // 0
            System.out.println("getMin(): " + minStack.getMin()); // -2
        }

        private static void testOptimizedImplementation() {
            MinStackOptimized minStack = new MinStackOptimized();

            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            System.out.println("getMin(): " + minStack.getMin()); // -3

            minStack.pop();
            System.out.println("top(): " + minStack.top()); // 0
            System.out.println("getMin(): " + minStack.getMin()); // -2

            // Additional test with duplicate minimums
            System.out.println("\n--- Testing duplicate minimums ---");
            MinStackOptimized stack2 = new MinStackOptimized();
            stack2.push(1);
            stack2.push(1);
            stack2.push(1);
            System.out.println("All elements are 1, min: " + stack2.getMin()); // 1
            stack2.pop();
            System.out.println("After pop, min: " + stack2.getMin()); // 1
        }
}
