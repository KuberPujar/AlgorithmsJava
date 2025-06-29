package queues.design;

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
public class ImplementQueueUsingStack {
    // s1 is used for incoming push operations (newest on top).
    private Stack<Integer> s1;
    // s2 is used for peek/pop operations. It stores elements in reversed,
    // queue-like order (oldest on top).
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public ImplementQueueUsingStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Pushes element x to the back of the queue.
     * This is always an O(1) operation.
     * @param x The integer element to be pushed.
     */
    public void push(int x) {
        s1.push(x);
    }

    /** * Removes the element from the front of the queue and returns it.
     * This is amortized O(1).
     * @return The element at the front of the queue.
     */
    public int pop() {
        // Ensure s2 has the oldest element on top before popping.
        peek();
        return s2.pop();
    }

    /** * Get the front element.
     * This is amortized O(1).
     * @return The element at the front of the queue.
     */
    public int peek() {
        // If s2 is empty, we need to transfer elements from s1.
        // This is the costly part of the operation, but it only happens
        // when s2 is exhausted.
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        // Now the oldest element is at the top of s2.
        return s2.peek();
    }

    /** * Returns whether the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("--- Testing MyQueue (implemented with two stacks) ---");
        ImplementQueueUsingStack myQueue = new ImplementQueueUsingStack();
        System.out.println("push(1)");
        myQueue.push(1); // queue is: [1]
        System.out.println("push(2)");
        myQueue.push(2); // queue is: [1, 2]
        System.out.println("peek(): " + myQueue.peek()); // return 1
        System.out.println("pop(): " + myQueue.pop());   // return 1, queue is [2]
        System.out.println("empty(): " + myQueue.empty()); // return false
    }
}
