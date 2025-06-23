package stacks.simplestack;

import java.util.Stack;

/*
You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.

You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:

An integer x.
Record a new score of x.
'+'.
Record a new score that is the sum of the previous two scores.
'D'.
Record a new score that is the double of the previous score.
'C'.
Invalidate the previous score, removing it from the record.
Return the sum of all the scores on the record after applying all the operations.

The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that all operations are valid.



Example 1:

Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
Example 2:

Input: ops = ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation:
"5" - Add 5 to the record, record is now [5].
"-2" - Add -2 to the record, record is now [5, -2].
"4" - Add 4 to the record, record is now [5, -2, 4].
"C" - Invalidate and remove the previous score, record is now [5, -2].
"D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
"9" - Add 9 to the record, record is now [5, -2, -4, 9].
"+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
"+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
Example 3:

Input: ops = ["1","C"]
Output: 0
Explanation:
"1" - Add 1 to the record, record is now [1].
"C" - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.


Constraints:

1 <= operations.length <= 1000
operations[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 104, 3 * 104].
For operation "+", there will always be at least two previous scores on the record.
For operations "C" and "D", there will always be at least one previous score on the record.
 */
public class BaseBallGame {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (String op : operations) {
            switch (op) {
                case "+":
                    // Sum of previous two scores
                    int top = stack.pop();
                    int secondTop = stack.peek();
                    stack.push(top); // Put back the first score
                    stack.push(top + secondTop); // Add the sum
                    break;

                case "D":
                    // Double the previous score
                    stack.push(2 * stack.peek());
                    break;

                case "C":
                    // Cancel/remove the previous score
                    stack.pop();
                    break;

                default:
                    // It's an integer, add it to the record
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }

        // Calculate the sum of all scores in the stack
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }

    // Alternative implementation with more explicit handling
    public int calPointsAlternative(String[] operations) {
        Stack<Integer> record = new Stack<>();

        for (String operation : operations) {
            if (operation.equals("+")) {
                // Get the last two scores and add their sum
                int lastScore = record.get(record.size() - 1);
                int secondLastScore = record.get(record.size() - 2);
                record.push(lastScore + secondLastScore);

            } else if (operation.equals("D")) {
                // Double the last score
                int lastScore = record.peek();
                record.push(lastScore * 2);

            } else if (operation.equals("C")) {
                // Remove the last score
                record.pop();

            } else {
                // It's a number, convert and add to record
                int score = Integer.parseInt(operation);
                record.push(score);
            }
        }

        // Sum all remaining scores
        int totalSum = 0;
        for (int score : record) {
            totalSum += score;
        }

        return totalSum;
    }

    // Test method with step-by-step trace
    public static void main(String[] args) {
        BaseBallGame solution = new BaseBallGame();

        // Test case 1
        String[] ops1 = {"5", "2", "C", "D", "+"};
        System.out.println("Test 1:");
        System.out.println("Operations: " + java.util.Arrays.toString(ops1));
        System.out.println("Result: " + solution.calPoints(ops1));
        System.out.println("Expected: 30");
        traceExecution(ops1);
        System.out.println();

        // Test case 2
        String[] ops2 = {"5", "-2", "4", "C", "D", "9", "+", "+"};
        System.out.println("Test 2:");
        System.out.println("Operations: " + java.util.Arrays.toString(ops2));
        System.out.println("Result: " + solution.calPoints(ops2));
        System.out.println("Expected: 27");
        System.out.println();

        // Test case 3
        String[] ops3 = {"1", "C"};
        System.out.println("Test 3:");
        System.out.println("Operations: " + java.util.Arrays.toString(ops3));
        System.out.println("Result: " + solution.calPoints(ops3));
        System.out.println("Expected: 0");
        System.out.println();

        // Additional test case
        String[] ops4 = {"1", "2", "+", "D", "C", "+"};
        System.out.println("Test 4:");
        System.out.println("Operations: " + java.util.Arrays.toString(ops4));
        System.out.println("Result: " + solution.calPoints(ops4));
        traceExecution(ops4);
    }

    // Helper method to trace execution step by step
    public static void traceExecution(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("\nStep-by-step execution:");
        System.out.println("Operation | Action | Stack After");
        System.out.println("----------|--------|------------");

        for (String op : operations) {
            String action = "";

            switch (op) {
                case "+":
                    int top = stack.pop();
                    int secondTop = stack.peek();
                    stack.push(top);
                    int sum = top + secondTop;
                    stack.push(sum);
                    action = "Add " + secondTop + " + " + top + " = " + sum;
                    break;

                case "D":
                    int lastScore = stack.peek();
                    int doubled = 2 * lastScore;
                    stack.push(doubled);
                    action = "Double " + lastScore + " = " + doubled;
                    break;

                case "C":
                    int removed = stack.pop();
                    action = "Cancel " + removed;
                    break;

                default:
                    int score = Integer.parseInt(op);
                    stack.push(score);
                    action = "Add score " + score;
                    break;
            }

            System.out.printf("    %-5s | %-20s | %s%n", op, action, stack.toString());
        }

        // Calculate final sum
        int sum = 0;
        Stack<Integer> temp = new Stack<>();
        temp.addAll(stack);
        for (int score : temp) {
            sum += score;
        }
        System.out.println("Final sum: " + sum);
    }
}
