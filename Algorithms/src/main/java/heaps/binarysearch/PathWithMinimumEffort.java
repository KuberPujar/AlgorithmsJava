package heaps.binarysearch;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.



Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.


Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {

    // Custom class to represent a cell with its coordinates and effort
    static class Cell implements Comparable<Cell> {
        int row, col, effort;

        Cell(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }

        // Compare cells based on effort (for min-heap)
        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.effort, other.effort);
        }
    }

    public static int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Priority queue (min-heap) to store cells with their minimum effort
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        // Array to track the minimum effort to reach each cell
        int[][] minEffort = new int[rows][cols];

        // Initialize all cells with maximum effort
        for (int i = 0; i < rows; i++) {
            Arrays.fill(minEffort[i], Integer.MAX_VALUE);
        }

        // Start from top-left cell (0,0) with effort 0
        minEffort[0][0] = 0;
        pq.offer(new Cell(0, 0, 0));

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            int currRow = current.row;
            int currCol = current.col;
            int currEffort = current.effort;

            // If we reached the destination, return the effort
            if (currRow == rows - 1 && currCol == cols - 1) {
                return currEffort;
            }

            // Skip if we've already found a better path to this cell
            if (currEffort > minEffort[currRow][currCol]) {
                continue;
            }

            // Explore all 4 directions
            for (int[] dir : directions) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];

                // Check if the new position is valid
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    // Calculate the effort to move to the new cell
                    int heightDiff = Math.abs(heights[newRow][newCol] - heights[currRow][currCol]);
                    int newEffort = Math.max(currEffort, heightDiff);

                    // If we found a better path to the new cell, update it
                    if (newEffort < minEffort[newRow][newCol]) {
                        minEffort[newRow][newCol] = newEffort;
                        pq.offer(new Cell(newRow, newCol, newEffort));
                    }
                }
            }
        }

        // Return the minimum effort to reach the bottom-right cell
        return minEffort[rows - 1][cols - 1];
    }

    // Helper method to print the path (for debugging)
    public static void printPath(int[][] heights, int[][] minEffort) {
        int rows = heights.length;
        int cols = heights[0].length;

        System.out.println("Heights Grid:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%3d ", heights[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nMinimum Effort Grid:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (minEffort[i][j] == Integer.MAX_VALUE) {
                    System.out.printf("%3s ", "INF");
                } else {
                    System.out.printf("%3d ", minEffort[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test Example 1
        int[][] heights1 = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        System.out.println("Example 1:");
        System.out.println("Input: heights = [[1,2,2],[3,8,2],[5,3,5]]");
        int result1 = minimumEffortPath(heights1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 2");
        System.out.println();

        // Test Example 2
        int[][] heights2 = {
                {1, 2, 3},
                {3, 8, 4},
                {5, 3, 5}
        };

        System.out.println("Example 2:");
        System.out.println("Input: heights = [[1,2,3],[3,8,4],[5,3,5]]");
        int result2 = minimumEffortPath(heights2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 1");
        System.out.println();

        // Test Example 3
        int[][] heights3 = {
                {1, 2, 1, 1, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 2, 1}
        };

        System.out.println("Example 3:");
        System.out.println("Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]");
        int result3 = minimumEffortPath(heights3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: 0");
        System.out.println();

        // Test with a single cell
        int[][] heights4 = {{5}};
        System.out.println("Single cell test:");
        System.out.println("Input: heights = [[5]]");
        int result4 = minimumEffortPath(heights4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: 0");
        System.out.println();

        // Performance test with larger grid
        System.out.println("Performance Analysis:");
        System.out.println("Time Complexity: O(rows * cols * log(rows * cols))");
        System.out.println("Space Complexity: O(rows * cols)");
        System.out.println("Algorithm: Dijkstra's with binary heap (PriorityQueue)");
    }
}
