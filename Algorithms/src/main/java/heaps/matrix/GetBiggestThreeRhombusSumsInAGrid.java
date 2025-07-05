package heaps.matrix;

import java.util.*;

/*
You are given an m x n integer matrix grid​​​.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:


Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.



Example 1:


Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
Output: [228,216,211]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 20 + 3 + 200 + 5 = 228
- Red: 200 + 2 + 10 + 4 = 216
- Green: 5 + 200 + 4 + 2 = 211
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: [20,9,8]
Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
- Blue: 4 + 2 + 6 + 8 = 20
- Red: 9 (area 0 rhombus in the bottom right corner)
- Green: 8 (area 0 rhombus in the bottom middle)
Example 3:

Input: grid = [[7,7,7]]
Output: [7]
Explanation: All three possible rhombus sums are the same, so return [7].


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
1 <= grid[i][j] <= 105
 */
public class GetBiggestThreeRhombusSumsInAGrid {
    /**
     * Calculates the three largest distinct rhombus sums from a given grid.
     *
     * @param grid The input m x n integer matrix.
     * @return An array containing the three biggest distinct rhombus sums in descending order.
     * If there are fewer than three distinct values, it returns all of them.
     */
    public int[] getBiggestThree(int[][] grid) {
        // Get the dimensions of the grid.
        int m = grid.length;
        int n = grid[0].length;

        // Use a HashSet to store the distinct rhombus sums.
        // This automatically handles duplicates.
        Set<Integer> sums = new HashSet<>();

        // Iterate through each cell of the grid, treating it as a potential center for a rhombus.
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // A rhombus of size 0 is just the cell itself.
                sums.add(grid[r][c]);

                // Expand outwards to create larger rhombuses centered at (r, c).
                // 'k' represents the radius or size of the rhombus.
                for (int k = 1; ; k++) {
                    // Check if the four corners of the rhombus are within the grid boundaries.
                    // If not, any larger rhombus from this center will also be out of bounds.
                    if (r - k < 0 || r + k >= m || c - k < 0 || c + k >= n) {
                        break;
                    }

                    // Calculate the sum of the elements on the border of the rhombus.
                    int currentSum = 0;

                    // The four corners of the rhombus are:
                    // Top:    (r - k, c)
                    // Right:  (r, c + k)
                    // Bottom: (r + k, c)
                    // Left:   (r, c - k)

                    // We traverse the four sides of the rhombus.
                    for (int i = 0; i < k; i++) {
                        // Top-left side (moving from top towards right)
                        currentSum += grid[r - k + i][c + i];
                        // Top-right side (moving from right towards bottom)
                        currentSum += grid[r + i][c + k - i];
                        // Bottom-right side (moving from bottom towards left)
                        currentSum += grid[r + k - i][c - i];
                        // Bottom-left side (moving from left towards top)
                        currentSum += grid[r - i][c - k + i];
                    }

                    // Add the calculated sum to our set of distinct sums.
                    sums.add(currentSum);
                }
            }
        }

        // Convert the set to a list to allow for sorting.
        List<Integer> sortedSums = new ArrayList<>(sums);

        // Sort the list in descending order.
        Collections.sort(sortedSums, Collections.reverseOrder());

        // Determine how many results to return (up to 3).
        int limit = Math.min(3, sortedSums.size());

        // Create the result array and populate it with the top sums.
        int[] result = new int[limit];
        for (int i = 0; i < limit; i++) {
            result[i] = sortedSums.get(i);
        }

        return result;
    }

    /**
     * Main method for testing the solution with the provided examples.
     */
    public static void main(String[] args) {
        GetBiggestThreeRhombusSumsInAGrid sol = new GetBiggestThreeRhombusSumsInAGrid();

        // Example 1
        int[][] grid1 = {
                {3, 4, 5, 1, 3},
                {3, 3, 4, 2, 3},
                {20, 30, 200, 40, 10},
                {1, 5, 5, 4, 1},
                {4, 3, 2, 2, 5}
        };
        int[] result1 = sol.getBiggestThree(grid1);
        System.out.println("Example 1 Output: " + java.util.Arrays.toString(result1)); // Expected: [228, 216, 211]

        // Example 2
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] result2 = sol.getBiggestThree(grid2);
        System.out.println("Example 2 Output: " + java.util.Arrays.toString(result2)); // Expected: [20, 9, 8]

        // Example 3
        int[][] grid3 = {{7, 7, 7}};
        int[] result3 = sol.getBiggestThree(grid3);
        System.out.println("Example 3 Output: " + java.util.Arrays.toString(result3)); // Expected: [7]
    }
}
