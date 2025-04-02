package stacks;

import java.util.Scanner;
import java.util.Stack;

public class CountOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        System.out.println(countReversals(S));
    }

    public static int countReversals(String S) {
        if (S.length() % 2 != 0) {
            return -1;
        }

        Stack<Character> stack = new Stack<>();
        int reversals = 0;

        for (char c : S.toCharArray()) {
            if (c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        int open = 0;
        int close = 0;
        while (!stack.isEmpty()) {
            if (stack.pop() == '{') {
                open++;
            } else {
                close++;
            }
        }

        reversals = (open + 1) / 2 + (close + 1) / 2;
        return reversals;
    }
}
