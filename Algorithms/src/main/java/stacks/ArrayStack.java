package stacks;

public class ArrayStack {
    private final int[] stack;
    private int top;
    private final int capacity;

    // Constructor to initialize the stack
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.top = -1;  // Indicates empty stack
    }

    // Push operation
    public void push(int item) {
        if (top < capacity - 1) {
            top++;
            stack[top] = item;
        } else {
            System.out.println("Stack Overflow");
        }
    }

    // Pop operation
    public int pop() {
        if (top > -1) {
            int item = stack[top];
            top--;
            return item;
        } else {
            System.out.println("Stack Underflow");
            return -1;  // Return a default value indicating error
        }
    }

    // Peek operation
    public int peek() {
        if (top > -1) {
            return stack[top];
        } else {
            System.out.println("Stack is empty");
            return -1;  // Return a default value indicating error
        }
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Get current size of stack
    public int size() {
        return top + 1;
    }

    // Example usage
    public static void main(String[] args) {
        // Initialize a stack with capacity of 5
        ArrayStack stack = new ArrayStack(5);

        // Push elements onto the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Pop an element from the stack
        int popResult = stack.pop();  // Returns 3 (last added element)
        System.out.println("Pop result: " + popResult);

        // Peek the top element of the stack
        int peekResult = stack.peek();  // Returns 2 (top element)
        System.out.println("Peek result: " + peekResult);

        // Check if stack is empty
        boolean isEmptyResult = stack.isEmpty();  // Returns false
        System.out.println("Is empty: " + isEmptyResult);

        // Get the size of the stack
        int sizeResult = stack.size();  // Returns 2
        System.out.println("Size: " + sizeResult);
    }
}
