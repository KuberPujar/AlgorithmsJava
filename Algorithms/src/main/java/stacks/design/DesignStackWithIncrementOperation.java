package stacks.design;
/*
Design a stack that supports increment operations on its elements.

Implement the CustomStack class:

CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
int pop() Pops and returns the top of the stack or -1 if the stack is empty.
void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, increment all the elements in the stack.


Example 1:

Input
["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
Output
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
Explanation
CustomStack stk = new CustomStack(3); // Stack is Empty []
stk.push(1);                          // stack becomes [1]
stk.push(2);                          // stack becomes [1, 2]
stk.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
stk.push(2);                          // stack becomes [1, 2]
stk.push(3);                          // stack becomes [1, 2, 3]
stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
stk.increment(5, 100);                // stack becomes [101, 102, 103]
stk.increment(2, 100);                // stack becomes [201, 202, 103]
stk.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
stk.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
stk.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
stk.pop();                            // return -1 --> Stack is empty return -1.


Constraints:

1 <= maxSize, x, k <= 1000
0 <= val <= 100
At most 1000 calls will be made to each method of increment, push and pop each separately.
 */
public class DesignStackWithIncrementOperation {
    // The maximum number of elements the stack can hold.
    private final int maxSize;

    // An array to store the stack elements.
    private final int[] stack;

    // Pointer to the top of the stack. -1 indicates an empty stack.
    private int top;

    /**
     * Initializes the object with maxSize which is the maximum number of
     * elements in the stack.
     * @param maxSize The maximum capacity of the stack.
     */
    public DesignStackWithIncrementOperation(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    /**
     * Adds x to the top of the stack if the stack has not reached the maxSize.
     * @param x The integer value to be pushed onto the stack.
     */
    public void push(int x) {
        // Only push if the stack is not full.
        if (top < maxSize - 1) {
            top++;
            stack[top] = x;
        }
    }

    /**
     * Pops and returns the top of the stack or -1 if the stack is empty.
     * @return The integer at the top of the stack, or -1 if empty.
     */
    public int pop() {
        // If the stack is empty, return -1.
        if (top == -1) {
            return -1;
        }
        // Get the top value, then decrement the top pointer.
        int val = stack[top];
        top--;
        return val;
    }

    /**
     * Increments the bottom k elements of the stack by val. If there are less
     * than k elements in the stack, it increments all the elements.
     * @param k The number of bottom elements to increment.
     * @param val The value to add to the elements.
     */
    public void increment(int k, int val) {
        // Determine the number of elements to increment. It's the minimum
        // of k and the current stack size.
        int limit = Math.min(k, top + 1);

        // Iterate from the bottom of the stack and add the value.
        for (int i = 0; i < limit; i++) {
            stack[i] += val;
        }
    }

    /**
     * Main method for demonstrating the CustomStack functionality.
     */
    public static void main(String[] args) {
        // Explanation from the problem description:
        DesignStackWithIncrementOperation stk = new DesignStackWithIncrementOperation(3);
        System.out.println("Initialized CustomStack(3)");

        stk.push(1);
        System.out.println("Pushed 1");

        stk.push(2);
        System.out.println("Pushed 2");

        System.out.println("Popped: " + stk.pop()); // returns 2

        stk.push(2);
        System.out.println("Pushed 2");

        stk.push(3);
        System.out.println("Pushed 3");

        stk.push(4); // This will be ignored as the stack is full
        System.out.println("Pushed 4 (should be ignored)");

        stk.increment(5, 100);
        System.out.println("Increment(5, 100)");

        stk.increment(2, 100);
        System.out.println("Increment(2, 100)");

        System.out.println("Popped: " + stk.pop()); // returns 103
        System.out.println("Popped: " + stk.pop()); // returns 202
        System.out.println("Popped: " + stk.pop()); // returns 201
        System.out.println("Popped: " + stk.pop()); // returns -1
    }
}
