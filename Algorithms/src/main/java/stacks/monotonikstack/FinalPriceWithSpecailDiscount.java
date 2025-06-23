package stacks.monotonikstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
You are given an integer array prices where prices[i] is the price of the ith item in a shop.

There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.

Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.



Example 1:

Input: prices = [8,4,6,2,3]
Output: [4,2,4,2,3]
Explanation:
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
For items 3 and 4 you will not receive any discount at all.
Example 2:

Input: prices = [1,2,3,4,5]
Output: [1,2,3,4,5]
Explanation: In this case, for all items, you will not receive any discount at all.
Example 3:

Input: prices = [10,1,1,6]
Output: [9,0,1,6]


Constraints:

1 <= prices.length <= 500
1 <= prices[i] <= 1000
 */
public class FinalPriceWithSpecailDiscount {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] result = prices.clone(); // Initialize with original prices
        Stack<Integer> stack = new Stack<>(); // Monotonic stack to store indices

        for (int i = 0; i < n; i++) {
            // Process all items in stack that can get discount from current item
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int index = stack.pop();
                result[index] = prices[index] - prices[i]; // Apply discount
            }

            // Add current index to stack
            stack.push(i);
        }

        // Items remaining in stack don't get any discount (already have original prices)
        return result;
    }

    // Alternative approach with detailed explanation
    public int[] finalPricesDetailed(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> monotonicStack = new Stack<>(); // Stores indices in decreasing order of prices

        // Initialize answer with original prices (no discount case)
        for (int i = 0; i < n; i++) {
            answer[i] = prices[i];
        }

        for (int currentIndex = 0; currentIndex < n; currentIndex++) {
            int currentPrice = prices[currentIndex];

            // Current price can provide discount to all items in stack with price >= currentPrice
            while (!monotonicStack.isEmpty()) {
                int waitingIndex = monotonicStack.peek();
                int waitingPrice = prices[waitingIndex];

                if (waitingPrice >= currentPrice) {
                    // Found discount for waiting item
                    monotonicStack.pop();
                    answer[waitingIndex] = waitingPrice - currentPrice;
                } else {
                    // Stack maintains decreasing order, so no more discounts possible
                    break;
                }
            }

            // Add current index to stack (waiting for future discount)
            monotonicStack.push(currentIndex);
        }

        return answer;
    }

    // Brute force approach for comparison
    public int[] finalPricesBruteForce(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = prices[i]; // Default: no discount

            // Find first item to the right with price <= prices[i]
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
       FinalPriceWithSpecailDiscount solution = new FinalPriceWithSpecailDiscount();

        // Test case 1
        int[] prices1 = {8, 4, 6, 2, 3};
        System.out.println("Test 1:");
        System.out.println("Input: " + Arrays.toString(prices1));
        int[] result1 = solution.finalPrices(prices1);
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: [4, 2, 4, 2, 3]");
        traceMonotonicStack(prices1);
        System.out.println();

        // Test case 2
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2:");
        System.out.println("Input: " + Arrays.toString(prices2));
        int[] result2 = solution.finalPrices(prices2);
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: [1, 2, 3, 4, 5]");
        System.out.println();

        // Test case 3
        int[] prices3 = {10, 1, 1, 6};
        System.out.println("Test 3:");
        System.out.println("Input: " + Arrays.toString(prices3));
        int[] result3 = solution.finalPrices(prices3);
        System.out.println("Output: " + Arrays.toString(result3));
        System.out.println("Expected: [9, 0, 1, 6]");
        traceMonotonicStack(prices3);
        System.out.println();

        // Performance comparison
        int[] largePrices = new int[500];
        for (int i = 0; i < 500; i++) {
            largePrices[i] = 500 - i; // Decreasing prices
        }

        long start1 = System.nanoTime();
        solution.finalPrices(largePrices);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        solution.finalPricesBruteForce(largePrices);
        long end2 = System.nanoTime();

        System.out.println("Performance comparison (500 items):");
        System.out.println("Monotonic Stack: " + (end1 - start1) / 1000000.0 + " ms");
        System.out.println("Brute Force: " + (end2 - start2) / 1000000.0 + " ms");
    }

    // Helper method to trace monotonic stack execution
    public static void traceMonotonicStack(int[] prices) {
        System.out.println("\nMonotonic Stack Trace:");
        System.out.println("Index | Price | Stack (indices) | Action | Discounts Applied");
        System.out.println("------|-------|-----------------|--------|------------------");

        int n = prices.length;
        int[] result = prices.clone();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            List<String> discountsApplied = new ArrayList<>();

            // Process discounts
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int index = stack.pop();
                result[index] = prices[index] - prices[i];
                discountsApplied.add("Item[" + index + "]: " + prices[index] + "-" + prices[i] + "=" + result[index]);
            }

            // Add current to stack
            stack.push(i);

            String action = "Push " + i;
            String discounts = discountsApplied.isEmpty() ? "None" : String.join(", ", discountsApplied);

            System.out.printf("  %-3d |   %-3d | %-14s | %-6s | %s%n",
                    i, prices[i], stackToString(stack), action, discounts);
        }

        System.out.println("Final result: " + Arrays.toString(result));
    }

    // Helper method to convert stack to string
    private static String stackToString(Stack<Integer> stack) {
        if (stack.isEmpty()) return "[]";
        return stack.toString();
    }

    // Method to explain the monotonic stack concept
    public static void explainMonotonicStack() {
        System.out.println("MONOTONIC STACK EXPLANATION:");
        System.out.println("============================");
        System.out.println("1. A monotonic stack maintains elements in a specific order (increasing/decreasing)");
        System.out.println("2. For this problem, we use a DECREASING monotonic stack:");
        System.out.println("   - Stack stores indices of items waiting for discount");
        System.out.println("   - Stack maintains decreasing order of prices (top to bottom)");
        System.out.println("3. When we encounter a price that can provide discount:");
        System.out.println("   - Pop all items from stack that can get this discount");
        System.out.println("   - Apply discount to those items");
        System.out.println("4. Key insight: If prices[j] can discount prices[i], and prices[k] can discount prices[j],");
        System.out.println("   then prices[k] can also discount prices[i] (but we want the FIRST discount)");
        System.out.println("5. The monotonic property ensures we find the NEAREST smaller element to the right");
        System.out.println();

        // Example walkthrough
        int[] example = {8, 4, 6, 2, 3};
        System.out.println("Example walkthrough with " + Arrays.toString(example) + ":");
        System.out.println("i=0, price=8: stack=[] → push 0 → stack=[0]");
        System.out.println("i=1, price=4: price[0]=8 >= 4 → discount item 0: 8-4=4 → stack=[1]");
        System.out.println("i=2, price=6: price[1]=4 < 6 → no discount → push 2 → stack=[1,2]");
        System.out.println("i=3, price=2: price[2]=6 >= 2 → discount item 2: 6-2=4");
        System.out.println("                price[1]=4 >= 2 → discount item 1: 4-2=2 → stack=[3]");
        System.out.println("i=4, price=3: price[3]=2 < 3 → no discount → push 4 → stack=[3,4]");
        System.out.println("Result: [4, 2, 4, 2, 3]");
    }
}
