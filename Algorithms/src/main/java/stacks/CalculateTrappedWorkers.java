package stacks;

import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class CalculateTrappedWorkers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of workers: ");
        int n = scanner.nextInt();
        Vector<Integer> workers = new Vector<>(n);
        System.out.println("Enter the workers entered tunnel:");
        for (int i = 0; i < n; i++) {
            workers.add(scanner.nextInt());
        }

        System.out.println("Enter the workers came out of tunnel:");
        Vector<Integer> workersOut = new Vector<>(n);
        for (int i = 0; i < n; i++) {
            workersOut.add(scanner.nextInt());
        }
        int trappedWorkers = calculateTrappedWorkers(workers, workersOut);
        System.out.println("Number of trapped workers: " + trappedWorkers);
    }

    private static int calculateTrappedWorkers(Vector<Integer> in, Vector<Integer> out) {
        Stack<Integer> stack = new Stack<>();
        int inIndex = 0;
       int n = in.size();
        for (int outValue : out) {
            // If the stack is not empty and the top matches the current outValue, pop it
            if (!stack.isEmpty() && stack.peek() == outValue) {
                stack.pop();
            } else {
                // Push elements from inSequence until we find the outValue
                while (inIndex < n && in.get(inIndex) != outValue) {
                    stack.push(in.get(inIndex));
                    inIndex++;
                }
                // If we reached the end of inSequence without finding outValue, return 0
//                if (inIndex >= n) {
//                    return 0;
//                }
//                // Move past the current outValue in inSequence
//                inIndex++;
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}