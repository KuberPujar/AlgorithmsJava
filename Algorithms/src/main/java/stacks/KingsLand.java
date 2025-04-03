package stacks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class KingsLand {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> landValues = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            landValues.add(scanner.nextInt());
        }

        ArrayList<Integer> result = calculateKingProperties(landValues);
        for (int val : result) {
            System.out.print(val + " ");
        }
    }

    public static ArrayList<Integer> calculateKingProperties(ArrayList<Integer> landValues) {
        int n = landValues.size();
        ArrayList<Integer> result = new ArrayList<>();
        int[] leftBound = new int[n];  // Stores the left boundary for each king
        int[] rightBound = new int[n]; // Stores the right boundary for each king
        Stack<Integer> stack = new Stack<>();

        // Calculate left boundaries using stack
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && landValues.get(stack.peek()) <= landValues.get(i)) {
                stack.pop();
            }
            leftBound[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }

        stack.clear();

        // Calculate right boundaries using stack
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && landValues.get(stack.peek()) <= landValues.get(i)) {
                stack.pop();
            }
            rightBound[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
            stack.push(i);
        }

        // Precompute prefix sum for efficient range sum calculation
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + landValues.get(i);
        }

        // Calculate each king's property
        for (int i = 0; i < n; i++) {
            int sum = prefixSum[rightBound[i] + 1] - prefixSum[leftBound[i]];
            result.add(sum);
        }

        return result;
    }
}