package stacks.dpbased;

import java.util.Stack;

/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.



Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = [["0"]]
Output: 0
Example 3:

Input: matrix = [["1"]]
Output: 1


Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
 */
public class MaximalRectangle {
    /**
     * Finds the largest rectangle containing only '1's and returns its area.
     * The approach uses dynamic programming to build a histogram for each row,
     * where each bar's height represents the consecutive '1's upwards.
     * Then, for each histogram, it finds the largest rectangle using a stack-based approach.
     * Time Complexity: O(rows * cols) for building histograms + O(rows * cols) for stack-based LRH = O(rows * cols)
     * Space Complexity: O(cols) for the heights array and stack.
     *
     * @param matrix The binary matrix filled with '0's and '1's.
     * @return The maximum area of a rectangle containing only '1's.
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;

        // heights[j] stores the current height of consecutive '1's ending at (current_row, j)
        // treating this as a histogram.
        int[] heights = new int[cols];

        for (int i = 0; i < rows; i++) {
            // Update heights array for the current row
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++; // Increment height if it's a '1'
                } else {
                    heights[j] = 0; // Reset height to 0 if it's a '0'
                }
            }
            // Now, heights array represents a histogram. Find the largest rectangle in this histogram.
            maxArea = Math.max(maxArea, largestRectangleInHistogram(heights));
        }

        return maxArea;
    }

    /**
     * Helper function to find the largest rectangle in a histogram.
     * This uses a monotonic stack to find the nearest smaller elements to the left and right
     * for each bar, allowing efficient calculation of the maximum area.
     * Time Complexity: O(N) where N is the number of bars (cols in this context).
     * Space Complexity: O(N) for the stack.
     *
     * @param heights The array representing histogram bar heights.
     * @return The maximum area of a rectangle in the given histogram.
     */
    private int largestRectangleInHistogram(int[] heights) {
        int n = heights.length;
        if (n == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // Stores indices of bars

        // Iterate through all bars, including a virtual bar of height 0 at the end
        // to ensure all elements in the stack are processed.
        for (int i = 0; i <= n; i++) {
            // Current height being processed. If i == n, it's a virtual 0-height bar.
            int currentHeight = (i == n) ? 0 : heights[i];

            // While stack is not empty and the height of the bar at stack's top
            // is greater than the current height, process the popped bar.
            // This means currentHeight acts as the right boundary for the popped bar.
            while (!stack.isEmpty() && heights[stack.peek()] >= currentHeight) {
                int h = heights[stack.pop()]; // Height of the popped bar (potential minimum)
                // The left boundary for the popped bar is the new stack.peek() (or -1 if stack is empty)
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i); // Push current bar's index onto the stack.
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaximalRectangle solution = new MaximalRectangle();
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println("Maximal Rectangle Area: " + solution.maximalRectangle(matrix1)); // Output: 6

        char[][] matrix2 = {
            {'0'}
        };
        System.out.println("Maximal Rectangle Area: " + solution.maximalRectangle(matrix2)); // Output: 0

        char[][] matrix3 = {
            {'1'}
        };
        System.out.println("Maximal Rectangle Area: " + solution.maximalRectangle(matrix3)); // Output: 1
    }
}
