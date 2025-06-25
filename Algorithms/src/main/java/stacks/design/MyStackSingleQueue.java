package stacks.design;

import java.util.LinkedList;
import java.util.Queue;

public class MyStackSingleQueue {
    private Queue<Integer> q;

    /** Initialize your data structure here. */
    public MyStackSingleQueue() {
        q = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        // Add the new element to the back
        q.add(x);
        // Rotate the queue to move the new element to the front
        int size = q.size();
        while (size > 1) {
            q.add(q.remove());
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
        MyStackSingleQueue myStack = new MyStackSingleQueue();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return false
    }
}
