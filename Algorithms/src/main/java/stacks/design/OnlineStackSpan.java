package stacks.design;

import java.util.Stack;

/*
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.


Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6


Constraints:

1 <= price <= 105
At most 104 calls will be made to next.
 */
public class OnlineStackSpan {
    // A stack to store pairs of [price, span]. Using a monotonic stack
    // ensures that elements are kept in a specific order (in this case,
    // decreasing prices), which helps in efficiently calculating the span.
    private final Stack<int[]> stack;

    /**
     * Initializes the object of the class.
     */
    public OnlineStackSpan() {
        // We use a Stack of int arrays, where each array holds a price and its span.
        stack = new Stack<>();
    }

    /**
     * Calculates and returns the span of the stock's price for the current day.
     * @param price The stock price for the current day.
     * @return The span of the stock for the current day.
     */
    public int next(int price) {
        // Every stock price has a span of at least 1 (for itself).
        int span = 1;

        // The core of the monotonic stack approach:
        // While the stack is not empty and the price at the top of the stack is
        // less than or equal to the current price, we can absorb its span.
        // This is because the consecutive streak of lower-or-equal prices
        // continues over the period covered by the smaller price on the stack.
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            // Add the span of the popped element to the current span.
            span += stack.pop()[1];
        }

        // Push the current price and its calculated span onto the stack.
        // The first element of the array is the price, the second is the span.
        stack.push(new int[]{price, span});

        // Return the final calculated span.
        return span;
    }

    /**
     * Main method for demonstrating the StockSpanner functionality.
     */
    public static void main(String[] args) {
        // Example from the problem description
        OnlineStackSpan stockSpanner = new OnlineStackSpan();
        System.out.println("next(100): " + stockSpanner.next(100)); // returns 1
        System.out.println("next(80):  " + stockSpanner.next(80));  // returns 1
        System.out.println("next(60):  " + stockSpanner.next(60));  // returns 1
        System.out.println("next(70):  " + stockSpanner.next(70));  // returns 2
        System.out.println("next(60):  " + stockSpanner.next(60));  // returns 1
        System.out.println("next(75):  " + stockSpanner.next(75));  // returns 4
        System.out.println("next(85):  " + stockSpanner.next(85));  // returns 6
    }
}
