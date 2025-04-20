package math.geometry;
/*
You are given an n x n grid where we place some 1 x 1 x 1 cubes that are axis-aligned with the x, y, and z axes.

Each value v = grid[i][j] represents a tower of v cubes placed on top of the cell (i, j).

We view the projection of these cubes onto the xy, yz, and zx planes.

A projection is like a shadow, that maps our 3-dimensional figure to a 2-dimensional plane. We are viewing the "shadow" when looking at the cubes from the top, the front, and the side.

Return the total area of all three projections.



Example 1:


Input: grid = [[1,2],[3,4]]
Output: 17
Explanation: Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
Example 2:

Input: grid = [[2]]
Output: 5
Example 3:

Input: grid = [[1,0],[0,2]]
Output: 8


Constraints:

n == grid.length == grid[i].length
1 <= n <= 50
0 <= grid[i][j] <= 50
 */
public class ProjectionAreaOf3DShapes {
    public static void main(String[] args) {
        int[][] grid1 = {{1, 2}, {3, 4}};
        System.out.println(projectionArea(grid1)); // Output: 17

        int[][] grid2 = {{2}};
        System.out.println(projectionArea(grid2)); // Output: 5

        int[][] grid3 = {{1, 0}, {0, 2}};
        System.out.println(projectionArea(grid3)); // Output: 8
    }

    private static int projectionArea(int[][] grid) {
        int n = grid.length;
        int xy = 0; // Top view projection
        int yz = 0;  // Front view projection
        int zx = 0;  // Side view projection

        for (int i = 0; i < n; i++) {
            int maxRow = 0; // Max in row (for zx projection)
            int maxCol = 0; // Max in column (for yz projection)

            for (int j = 0; j < n; j++) {
                // XY projection - count non-zero cells
                if (grid[i][j] > 0) {
                    xy++;
                }

                // Track max in current row (for zx projection)
                maxRow = Math.max(maxRow, grid[i][j]);

                // Track max in current column (for yz projection)
                maxCol = Math.max(maxCol, grid[j][i]);
            }

            zx += maxRow;
            yz += maxCol;
        }

        return xy + yz + zx;
    }
}
