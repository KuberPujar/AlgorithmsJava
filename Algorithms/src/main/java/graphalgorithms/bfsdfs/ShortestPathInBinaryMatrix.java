package graphalgorithms.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Shortest Path in Binary Matrix
Given a square binary matrix, find the length of the shortest path from the top-left cell to the destination cell given as (x, y) moving only in up, down, left, right directions. consider matrix as 0 indexed. You must return -1 in case of no path.

Input Format
N : Number of rows
M : Number of columns
A : (N x M) Square Binary Matrix
X : Destination row
Y : Destination column
Output format
Return an integer representing the length of the shortest path from the top-left cell (0, 0) to the destination cell (x, y). If there is no path, return -1.
Example 1
Input
3 4
1 0 0 0
1 1 0 1
0 1 1 1
2 3
Output
5
Explanation:
we have to go from A[0][0] to A[2][3]. Minimun steps will be 5.

Example 2
Input
4 4
1 1 1 0
0 0 0 0
1 1 0 1
0 0 1 1
1 3
Output
-1
Explanation:
You start at the top-left cell (0, 0) and want to reach the destination cell (1, 3).
In the matrix, 1 represents a cell you can walk on, and 0 represents a blocked cell.
From the top-left, all possible moves to the destination (1, 3) are blocked by cells with 0s.
Since no valid path exists from the start to the destination, the result is -1.
Constraints:
1 <= n,m <= 100
0 <= x < n
0 <= y < m
Note:The function should return the result. The driver code will handle printing the output.
 */
public class ShortestPathInBinaryMatrix {
    public static int shortestPath(int[][] grid, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;

        // Check if the start or destination cell is blocked
        if (grid[0][0] == 1 || grid[x][y] == 1) {
            return -1;
        }

        // Directions for moving in the grid (right, down, left, up)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // BFS queue
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // {row, col, distance}

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            // If we reached the destination
            if (row == x && col == y) {
                return dist;
            }

            // Explore all possible directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds and if the cell is not blocked
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = 1; // Mark as visited
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }

        // If we exhaust the queue without finding the destination
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        System.out.println(shortestPath(grid, x, y));
        scanner.close();
    }
}
