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
    // Directions for up, down, left, right movement
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int shortestPath(int[][] A, int X, int Y) {
        int N = A.length;
        int M = A[0].length;

        // Check if start or destination is blocked
        if (A[0][0] == 0 || A[X][Y] == 0) {
            return -1;
        }

        // If we're already at the destination
        if (X == 0 && Y == 0) {
            return 0;
        }

        // BFS setup
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        // Start from (0, 0)
        queue.offer(new int[]{0, 0, 0}); // {row, col, distance}
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            // Check all 4 directions
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M) {
                    // Check if cell is walkable and not visited
                    if (A[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                        // Check if we reached destination
                        if (newRow == X && newCol == Y) {
                            return dist + 1;
                        }

                        // Add to queue for further exploration
                        queue.offer(new int[]{newRow, newCol, dist + 1});
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }

        // No path found
        return -1;
    }

    // Test method with the provided examples
    public static void main(String[] args) {
        // Example 1
        int[][] matrix1 = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 1, 1}
        };
        System.out.println("Example 1 Result: " + shortestPath(matrix1, 2, 3)); // Expected: 5

        // Example 2
        int[][] matrix2 = {
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 1}
        };
        System.out.println("Example 2 Result: " + shortestPath(matrix2, 1, 3)); // Expected: -1

        // Additional test case - destination at start
        int[][] matrix3 = {{1}};
        System.out.println("Start = Destination Result: " + shortestPath(matrix3, 0, 0)); // Expected: 0
    }
}

// Alternative implementation using a custom Cell class (more readable)
class Cell {
    int row, col, distance;

    Cell(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}

class AlternativeShortestPath {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int shortestPath(int[][] matrix, int destX, int destY) {
        int n = matrix.length;
        int m = matrix[0].length;

        if (matrix[0][0] == 0 || matrix[destX][destY] == 0) {
            return -1;
        }

        if (destX == 0 && destY == 0) {
            return 0;
        }

        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        queue.offer(new Cell(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            for (int[] dir : DIRECTIONS) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if (isValid(newRow, newCol, n, m) &&
                        matrix[newRow][newCol] == 1 &&
                        !visited[newRow][newCol]) {

                    if (newRow == destX && newCol == destY) {
                        return current.distance + 1;
                    }

                    queue.offer(new Cell(newRow, newCol, current.distance + 1));
                    visited[newRow][newCol] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}
