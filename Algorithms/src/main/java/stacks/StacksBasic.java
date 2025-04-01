package stacks;

import java.util.Stack;

public class StacksBasic {
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Stack: "+stack);
        stack.pop();
        System.out.println("Stack after pop: "+stack);
        System.out.println("Top element: "+stack.peek());
        System.out.println("Is stack empty? "+stack.isEmpty());
        System.out.println("Size of stack: "+stack.size());
        System.out.println("Searching for element 3: "+stack.search(3));
        System.out.println("Searching for element 6: "+stack.search(6));
        System.out.println("Stack after searching: "+stack);
        System.out.println("Iterating through stack:");
        for (Integer element : stack) {
            System.out.println(element);
        }
        System.out.println("Clearing stack...");
        stack.clear();
        System.out.println("Stack after clearing: "+stack);
        System.out.println("Is stack empty? "+stack.isEmpty());
        System.out.println("Size of stack: "+stack.size());
        System.out.println("Iterating through stack after clearing:");
        for (Integer element : stack) {
            System.out.println(element);
        }
        System.out.println("Stack after clearing: "+stack);
        System.out.println("Is stack empty? "+stack.isEmpty());
    }
}
