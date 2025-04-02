package stacks;

import java.util.Scanner;
import java.util.Stack;

public class CalculateDifferences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(maxAbsoluteDifference(arr));
    }

    public static int maxAbsoluteDifference(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;

        int[] leftSmaller = new int[n];
        int[] rightSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Find nearest smaller element on the left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            leftSmaller[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(arr[i]);
        }

        stack.clear();

        // Find nearest smaller element on the right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            rightSmaller[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(arr[i]);
        }

        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(leftSmaller[i] - rightSmaller[i]);
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }

        return maxDiff;
    }
}
