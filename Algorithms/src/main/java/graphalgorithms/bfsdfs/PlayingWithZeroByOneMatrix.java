package graphalgorithms.bfsdfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Playing with zero by one matrix
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.

Input/Output Format
Input:
T: Number of test cases.

For each test case:
N: Number of columns.
M: Number of rows .

Output:
For each test case, print the 2D matrix.

Sample Input:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Sample Output:
Output: [[0,0,0],[0,1,0],[0,0,0]]
Sample Input:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Sample Output:
Output: [[0,0,0],[0,1,0],[1,2,1]]
Constraints
m == mat.length
n == mat[i].length
1 <= m, n <= 1e4
1 <= m * n <= 1e4

mat[i][j] is either 0 or 1.


 ***Note:The function should return the result. The driver code will handle printing the output.***
 */
public class PlayingWithZeroByOneMatrix {
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Result matrix to store distances
        int[][] result = new int[m][n];

        // Queue for BFS - stores coordinates as int array [row, col]
        Queue<int[]> queue = new LinkedList<>();

        // Initialize result matrix and add all 0s to queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    result[i][j] = Integer.MAX_VALUE; // Mark as unvisited
                }
            }
        }

        // Directions for 4-connected neighbors (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Multi-source BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // Check all 4 neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                    // If we found a shorter path to this cell
                    if (result[newRow][newCol] > result[row][col] + 1) {
                        result[newRow][newCol] = result[row][col] + 1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return result;
    }

    // Helper method to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1
        int[][] mat1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        System.out.println("Input Matrix 1:");
        printMatrix(mat1);

        int[][] result1 = updateMatrix(mat1);
        System.out.println("Output Matrix 1:");
        printMatrix(result1);

        // Test case 2
        int[][] mat2 = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        System.out.println("Input Matrix 2:");
        printMatrix(mat2);

        int[][] result2 = updateMatrix(mat2);
        System.out.println("Output Matrix 2:");
        printMatrix(result2);

        // Test case 3 - Edge cases
        int[][] mat3 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };

        System.out.println("Input Matrix 3:");
        printMatrix(mat3);

        int[][] result3 = updateMatrix(mat3);
        System.out.println("Output Matrix 3:");
        printMatrix(result3);
    }
}
