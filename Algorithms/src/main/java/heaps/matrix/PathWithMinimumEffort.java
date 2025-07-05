package heaps.matrix;

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
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Create a min-heap based on effort
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minHeap.offer(new int[]{0, 0, 0});

        // Create a 2D array to store the minimum effort to reach each cell
        int[][] effort = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                effort[i][j] = Integer.MAX_VALUE;
            }
        }
        effort[0][0] = 0;

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int row = current[0];
            int col = current[1];
            int currentEffort = current[2];

            // If we've reached the destination
            if (row == rows - 1 && col == cols - 1) {
                return currentEffort;
            }

            // If we already found a better path to this cell
            if (currentEffort > effort[row][col]) {
                continue;
            }

            // Explore all four directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new position is within bounds
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    // Calculate the new effort
                    int newEffort = Math.max(currentEffort,
                            Math.abs(heights[newRow][newCol] - heights[row][col]));

                    // If we found a better path to the neighbor
                    if (newEffort < effort[newRow][newCol]) {
                        effort[newRow][newCol] = newEffort;
                        minHeap.offer(new int[]{newRow, newCol, newEffort});
                    }
                }
            }
        }

        return 0; // Should never reach here if the grid is valid
    }

    public static void main(String[] args) {
        PathWithMinimumEffort solution = new PathWithMinimumEffort();
        int[][] heights1 = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(solution.minimumEffortPath(heights1)); // Output: 2

        int[][] heights2 = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        System.out.println(solution.minimumEffortPath(heights2)); // Output: 1

        int[][] heights3 = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
        System.out.println(solution.minimumEffortPath(heights3)); // Output: 0
    }
}
