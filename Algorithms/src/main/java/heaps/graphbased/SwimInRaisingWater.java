package heaps.graphbased;

import java.util.PriorityQueue;

/*
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).



Example 1:


Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:


Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
Each value grid[i][j] is unique.
 */
public class SwimInRaisingWater {

    //Java solution that uses a modified Dijkstra's algorithm with a priority queue (min-heap) to find the least time required to swim from the top-left to the bottom-right corner:
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Priority queue: [max elevation so far, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHeap.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int maxElevation = current[0];
            int row = current[1];
            int col = current[2];

            // If we've reached the destination
            if (row == n - 1 && col == n - 1) {
                return maxElevation;
            }

            // Explore all 4 directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check boundaries and if already visited
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    // The new max elevation is the maximum between current path's max and the new cell's elevation
                    int newMax = Math.max(maxElevation, grid[newRow][newCol]);
                    minHeap.offer(new int[]{newMax, newRow, newCol});
                }
            }
        }

        return -1; // Shouldn't reach here for valid inputs
    }

    public static void main(String[] args) {
        SwimInRaisingWater solution = new SwimInRaisingWater();
        int[][] grid1 = {{0, 2}, {1, 3}};
        System.out.println(solution.swimInWater(grid1)); // Output: 3

        int[][] grid2 = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}
        };
        System.out.println(solution.swimInWater(grid2)); // Output: 16
    }
}
