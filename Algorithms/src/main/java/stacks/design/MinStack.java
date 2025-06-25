package stacks.design;

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
    // Main stack to store the actual elements.
    private Stack<Integer> stack;

    // Auxiliary stack to store the minimum elements. The top of this stack
    // will always be the minimum element of the entire main stack.
    private Stack<Integer> minStack;

    /**
     * Initializes the stack object.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Pushes the element val onto the stack.
     * @param val The integer value to be pushed onto the stack.
     */
    public void push(int val) {
        // Push the value onto the main stack.
        stack.push(val);

        // Check the minStack. If it's empty or the new value is less than or
        // equal to the current minimum, push the new value onto the minStack.
        // We use '<=' to handle cases where the minimum value appears multiple times.
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * Removes the element on the top of the stack.
     * Note: This method will always be called on non-empty stacks as per the problem constraints.
     */
    public void pop() {
        // Pop the top element from the main stack.
        int poppedValue = stack.pop();

        // If the popped value is the same as the current minimum, it must also
        // be removed from the minStack to expose the previous minimum.
        // We use .equals() for proper Integer object comparison.
        if (poppedValue==minStack.peek()) {
            minStack.pop();
        }
    }

    /**
     * Gets the top element of the stack.
     * Note: This method will always be called on non-empty stacks as per the problem constraints.
     * @return The integer at the top of the stack.
     */
    public int top() {
        // The top of the main stack is the stack's top element.
        return stack.peek();
    }

    /**
     * Retrieves the minimum element in the stack.
     * Note: This method will always be called on non-empty stacks as per the problem constraints.
     * @return The minimum integer in the stack.
     */
    public int getMin() {
        // The top of the minStack is always the current minimum element.
        return minStack.peek();
    }

    /**
     * Main method for demonstrating the MinStack functionality.
     */
    public static void main(String[] args) {
        // Explanation from the problem description:
        MinStack minStack = new MinStack();
        minStack.push(-2);
        System.out.println("Pushed -2. Stack: " + minStack.stack + ", MinStack: " + minStack.minStack);

        minStack.push(0);
        System.out.println("Pushed 0.  Stack: " + minStack.stack + ", MinStack: " + minStack.minStack);

        minStack.push(-3);
        System.out.println("Pushed -3. Stack: " + minStack.stack + ", MinStack: " + minStack.minStack);

        System.out.println("getMin(): " + minStack.getMin()); // return -3

        minStack.pop();
        System.out.println("Popped.    Stack: " + minStack.stack + ", MinStack: " + minStack.minStack);

        System.out.println("top(): " + minStack.top());    // return 0

        System.out.println("getMin(): " + minStack.getMin()); // return -2
    }
}
