package stacks.design;

import java.util.Stack;

/*
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.


Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false


Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.


Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
 */
public class ImplementQueueUsingStacks {
    // stack1: Used for pushing new elements. Acts as the "input" stack.
    private Stack<Integer> stack1;
    // stack2: Used for popping and peeking elements. Acts as the "output" stack.
    private Stack<Integer> stack2;

    /**
     * Constructs a new MyQueue object.
     * Initializes the two internal stacks.
     */
    public ImplementQueueUsingStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Pushes element x to the back of the queue.
     * This operation always adds the new element to stack1.
     * Time Complexity: O(1)
     * @param x The element to be pushed.
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * Removes the element from the front of the queue and returns it.
     * If stack2 is empty, it first transfers all elements from stack1 to stack2
     * to reverse their order, ensuring FIFO. Then it pops from stack2.
     * Time Complexity: Amortized O(1). In the worst case (when stack2 is empty
     * and stack1 has 'n' elements), it's O(n) for transfer, but this cost is
     * spread out over 'n' push operations.
     * @return The element removed from the front of the queue.
     */
    public int pop() {
        // If stack2 is empty, transfer elements from stack1 to stack2.
        // This ensures the oldest element from stack1 ends up on top of stack2.
        if (stack2.empty()) {
            transferElements();
        }
        // Pop the top element from stack2, which is the front of the queue.
        return stack2.pop();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * Similar to pop(), it ensures stack2 has elements by transferring if needed.
     * Time Complexity: Amortized O(1).
     * @return The element at the front of the queue.
     */
    public int peek() {
        // If stack2 is empty, transfer elements from stack1 to stack2.
        if (stack2.empty()) {
            transferElements();
        }
        // Peek at the top element from stack2, which is the front of the queue.
        return stack2.peek();
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     * The queue is empty if and only if both internal stacks are empty.
     * Time Complexity: O(1)
     * @return True if the queue is empty, false otherwise.
     */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    /**
     * Helper method to transfer all elements from stack1 to stack2.
     * This method is called when stack2 is empty and a pop or peek operation is requested.
     * Elements are popped from stack1 and pushed onto stack2, effectively reversing their order.
     * Time Complexity: O(k) where k is the number of elements in stack1.
     */
    private void transferElements() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
    }

    public static void main(String[] args) {
        ImplementQueueUsingStacks myQueue = new ImplementQueueUsingStacks();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // returns 1
        System.out.println(myQueue.pop());   // returns 1, queue is [2]
        System.out.println(myQueue.empty()); // returns false
    }
}

/**
 * Example Usage (for demonstration, not part of the class itself):
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2]
 * myQueue.peek();  // returns 1
 * myQueue.pop();   // returns 1, queue is: [2]
 * myQueue.empty(); // returns false
 */

