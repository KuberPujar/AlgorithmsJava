package queues.design;

import java.util.LinkedList;
import java.util.Queue;

/*
mplement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.


Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False


Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.


Follow-up: Can you implement the stack using only one queue?
 */
public class ImplementStackUsingQueue {
    // q1 will always hold the elements in stack order (LIFO).
    // q2 is used as a helper queue during the push operation.
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    /** Initialize your data structure here. */
    public ImplementStackUsingQueue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * Pushes element x onto the top of the stack.
     * To maintain LIFO order in a FIFO queue, we place the new element
     * at the front of the queue.
     *
     * @param x The integer element to be pushed.
     */
    public void push(int x) {
        // 1. Add the new element to the empty helper queue, q2.
        q2.add(x);

        // 2. Move all elements from q1 to q2. This places the old elements
        // after the new element, maintaining stack order.
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // 3. q2 now has the correct stack order. Swap the names of q1 and q2
        // so that q1 always holds the stack elements.
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /**
     * Removes the element on the top of the stack and returns it.
     * @return The element at the top of the stack.
     */
    public int pop() {
        return q1.remove();
    }

    /**
     * Get the top element.
     * @return The element at the top of the stack.
     */
    public int top() {
        return q1.peek();
    }

    /**
     * Returns whether the stack is empty.
     * @return true if the stack is empty, false otherwise.
     */
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Follow-up: Implements a LIFO stack using only ONE queue.
 * The push operation rearranges the queue to keep the last-pushed element at the front.
 */
class MyStackOneQueue {
    private Queue<Integer> q;

    /** Initialize your data structure here. */
    public MyStackOneQueue() {
        q = new LinkedList<>();
    }

    /**
     * Pushes element x onto the top of the stack.
     * @param x The integer element to be pushed.
     */
    public void push(int x) {
        // 1. Add the new element to the back of the queue.
        q.add(x);
        // 2. Rotate the queue until the new element is at the front.
        // The number of rotations is one less than the current size.
        int size = q.size();
        while (size > 1) {
            q.add(q.remove()); // Move the front element to the back.
            size--;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.remove();
    }

    /** Get the top element. */
    public int top() {
        return q.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("--- Testing MyStack (implemented with two queues) ---");
        ImplementStackUsingQueue myStack = new ImplementStackUsingQueue();
        System.out.println("push(1)");
        myStack.push(1);
        System.out.println("push(2)");
        myStack.push(2);
        System.out.println("top(): " + myStack.top());   // returns 2
        System.out.println("pop(): " + myStack.pop());   // returns 2
        System.out.println("empty(): " + myStack.empty()); // returns false

        System.out.println("\n--- Testing MyStackOneQueue (Follow-up with one queue) ---");
        MyStackOneQueue myStackOneQueue = new MyStackOneQueue();
        System.out.println("push(1)");
        myStackOneQueue.push(1);
        System.out.println("push(2)");
        myStackOneQueue.push(2);
        System.out.println("top(): " + myStackOneQueue.top());   // returns 2
        System.out.println("pop(): " + myStackOneQueue.pop());   // returns 2
        System.out.println("empty(): " + myStackOneQueue.empty()); // returns false
    }
}
