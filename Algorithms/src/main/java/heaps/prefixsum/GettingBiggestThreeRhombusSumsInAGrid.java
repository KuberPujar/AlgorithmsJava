package heaps.prefixsum;

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

https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/description/
 */
public class GettingBiggestThreeRhombusSumsInAGrid {

    // Main solution using geometric calculation and heap
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Use TreeSet to automatically maintain distinct values in descending order
        TreeSet<Integer> rhombusSums = new TreeSet<>(Collections.reverseOrder());

        // Check all possible rhombus centers and sizes
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Try all possible rhombus sizes from this center
                int maxRadius = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));

                for (int radius = 0; radius <= maxRadius; radius++) {
                    int sum = calculateRhombusSum(grid, i, j, radius);
                    rhombusSums.add(sum);
                }
            }
        }

        // Convert to array and return top 3
        int[] result = new int[Math.min(3, rhombusSums.size())];
        Iterator<Integer> iterator = rhombusSums.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = iterator.next();
        }

        return result;
    }

    // Calculate sum of rhombus border with given center and radius
    private int calculateRhombusSum(int[][] grid, int centerRow, int centerCol, int radius) {
        if (radius == 0) {
            return grid[centerRow][centerCol];
        }

        int sum = 0;

        // A rhombus consists of all points where Manhattan distance from center equals radius
        // Manhattan distance: |row - centerRow| + |col - centerCol| = radius

        // We can iterate through all valid row offsets and calculate corresponding column offsets
        for (int rowOffset = -radius; rowOffset <= radius; rowOffset++) {
            int colOffset = radius - Math.abs(rowOffset);

            // Add the point to the right of center
            sum += grid[centerRow + rowOffset][centerCol + colOffset];

            // Add the point to the left of center (if different from the right point)
            if (colOffset != 0) {
                sum += grid[centerRow + rowOffset][centerCol - colOffset];
            }
        }

        return sum;
    }

    // Alternative approach using coordinate transformation
    public int[] getBiggestThreeAlternative(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<Integer> uniqueSums = new HashSet<>();

        // Check all possible rhombus centers and sizes
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Try all possible rhombus sizes from this center
                int maxRadius = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));

                for (int radius = 0; radius <= maxRadius; radius++) {
                    int sum = calculateRhombusSumAlternative(grid, i, j, radius);
                    uniqueSums.add(sum);
                }
            }
        }

        // Convert to sorted array (descending)
        Integer[] sumsArray = uniqueSums.toArray(new Integer[0]);
        Arrays.sort(sumsArray, Collections.reverseOrder());

        // Return top 3 or all if less than 3
        int[] result = new int[Math.min(3, sumsArray.length)];
        for (int i = 0; i < result.length; i++) {
            result[i] = sumsArray[i];
        }

        return result;
    }

    // Alternative calculation method using diamond traversal
    private int calculateRhombusSumAlternative(int[][] grid, int centerRow, int centerCol, int radius) {
        if (radius == 0) {
            return grid[centerRow][centerCol];
        }

        int sum = 0;

        // More intuitive approach: traverse all points at Manhattan distance = radius
        for (int i = 0; i <= radius; i++) {
            int j = radius - i;

            // Four quadrants around the center
            if (i == 0 || j == 0) {
                // On the axes - add each point once
                if (i == 0) {
                    // Top and bottom points
                    if (j > 0) {
                        sum += grid[centerRow - j][centerCol]; // Top
                        sum += grid[centerRow + j][centerCol]; // Bottom
                    }
                }
                if (j == 0) {
                    // Left and right points
                    if (i > 0) {
                        sum += grid[centerRow][centerCol - i]; // Left
                        sum += grid[centerRow][centerCol + i]; // Right
                    }
                }
            } else {
                // Off the axes - add all four symmetric points
                sum += grid[centerRow - i][centerCol - j]; // Top-left
                sum += grid[centerRow - i][centerCol + j]; // Top-right
                sum += grid[centerRow + i][centerCol - j]; // Bottom-left
                sum += grid[centerRow + i][centerCol + j]; // Bottom-right
            }
        }

        return sum;
    }

    // Optimized approach using prefix sums for diagonal calculations
    public int[] getBiggestThreeOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create prefix sum arrays for diagonals
        int[][] prefixDiag1 = new int[m][n]; // top-left to bottom-right
        int[][] prefixDiag2 = new int[m][n]; // top-right to bottom-left

        // Build prefix sum for main diagonal (top-left to bottom-right)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixDiag1[i][j] = grid[i][j];
                if (i > 0 && j > 0) {
                    prefixDiag1[i][j] += prefixDiag1[i-1][j-1];
                }
            }
        }

        // Build prefix sum for anti-diagonal (top-right to bottom-left)
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                prefixDiag2[i][j] = grid[i][j];
                if (i > 0 && j < n - 1) {
                    prefixDiag2[i][j] += prefixDiag2[i-1][j+1];
                }
            }
        }

        TreeSet<Integer> rhombusSums = new TreeSet<>(Collections.reverseOrder());

        // Check all possible rhombus centers and sizes
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxRadius = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));

                for (int radius = 0; radius <= maxRadius; radius++) {
                    int sum = calculateRhombusSumWithPrefix(grid, prefixDiag1, prefixDiag2, i, j, radius);
                    rhombusSums.add(sum);
                }
            }
        }

        // Convert to result array
        int[] result = new int[Math.min(3, rhombusSums.size())];
        Iterator<Integer> iterator = rhombusSums.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = iterator.next();
        }

        return result;
    }

    // Calculate rhombus sum using prefix sums (more complex but potentially faster for large grids)
    private int calculateRhombusSumWithPrefix(int[][] grid, int[][] prefixDiag1, int[][] prefixDiag2,
                                              int centerRow, int centerCol, int radius) {
        if (radius == 0) {
            return grid[centerRow][centerCol];
        }

        // For now, use the simple approach as prefix sum for rhombus is complex
        return calculateRhombusSum(grid, centerRow, centerCol, radius);
    }

    // Helper method to visualize rhombus for debugging
    public void visualizeRhombus(int[][] grid, int centerRow, int centerCol, int radius) {
        int m = grid.length;
        int n = grid[0].length;

        System.out.println("Rhombus at center (" + centerRow + ", " + centerCol + ") with radius " + radius);
        System.out.println("Grid:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isOnRhombusBorder(i, j, centerRow, centerCol, radius)) {
                    System.out.printf("[%3d]", grid[i][j]);
                } else {
                    System.out.printf(" %3d ", grid[i][j]);
                }
            }
            System.out.println();
        }

        int sum = calculateRhombusSum(grid, centerRow, centerCol, radius);
        System.out.println("Sum: " + sum);
        System.out.println();
    }

    // Helper method to check if a point is on rhombus border
    private boolean isOnRhombusBorder(int row, int col, int centerRow, int centerCol, int radius) {
        if (radius == 0) {
            return row == centerRow && col == centerCol;
        }

        int deltaRow = Math.abs(row - centerRow);
        int deltaCol = Math.abs(col - centerCol);

        return deltaRow + deltaCol == radius;
    }

    // Method to demonstrate all rhombuses in a grid
    public void demonstrateAllRhombuses(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        System.out.println("=== All Possible Rhombuses ===");
        System.out.println("Grid:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d ", grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        List<Integer> allSums = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxRadius = Math.min(Math.min(i, m - 1 - i), Math.min(j, n - 1 - j));

                for (int radius = 0; radius <= maxRadius; radius++) {
                    int sum = calculateRhombusSum(grid, i, j, radius);
                    allSums.add(sum);
                    System.out.printf("Center (%d,%d), Radius %d: Sum = %d\n", i, j, radius, sum);
                }
            }
        }

        System.out.println("\nAll sums: " + allSums);

        // Get distinct sums in descending order
        Set<Integer> distinctSums = new TreeSet<>(Collections.reverseOrder());
        distinctSums.addAll(allSums);
        System.out.println("Distinct sums (descending): " + distinctSums);
    }

    public static void main(String[] args) {
        GettingBiggestThreeRhombusSumsInAGrid solution = new GettingBiggestThreeRhombusSumsInAGrid();

        // Test case 1 with manual verification
        int[][] grid1 = {
                {3, 4, 5, 1, 3},
                {3, 3, 4, 2, 3},
                {20, 30, 200, 40, 10},
                {1, 5, 5, 4, 1},
                {4, 3, 2, 2, 5}
        };

        System.out.println("Test Case 1 - Manual verification:");
        System.out.println("Grid:");
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                System.out.printf("%3d ", grid1[i][j]);
            }
            System.out.println();
        }

        // Based on the problem explanation:
        // Blue rhombus: 20 + 3 + 200 + 5 = 228
        // Red rhombus: 200 + 2 + 10 + 4 = 216
        // Green rhombus: 5 + 200 + 4 + 2 = 211

        // Let's manually verify these by finding the right center and radius
        System.out.println("\nManual verification of expected results:");

        // For blue rhombus (228), let's try different centers and radii
        // The sum should include 20, 3, 200, 5 - looking at the grid positions
        // 20 is at (2,0), 3 is at (1,1), 200 is at (2,2), 5 is at (3,1)
        // This suggests center around (2,1) with radius 1? Let's check

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int r = 1; r <= 2; r++) {
                    if (i-r >= 0 && i+r < 5 && j-r >= 0 && j+r < 5) {
                        int sum = solution.calculateRhombusSum(grid1, i, j, r);
                        if (sum == 228 || sum == 216 || sum == 211) {
                            System.out.printf("Found expected sum %d at center (%d,%d) radius %d\n", sum, i, j, r);
                            solution.visualizeRhombus(grid1, i, j, r);
                        }
                    }
                }
            }
        }

        solution.demonstrateAllRhombuses(grid1);
        System.out.println("Result: " + Arrays.toString(solution.getBiggestThree(grid1)));
        System.out.println("Expected: [228, 216, 211]");

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Test case 2
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Test Case 2:");
        // Expected: Blue rhombus 4 + 2 + 6 + 8 = 20
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int r = 1; r <= 1; r++) {
                    if (i-r >= 0 && i+r < 3 && j-r >= 0 && j+r < 3) {
                        int sum = solution.calculateRhombusSum(grid2, i, j, r);
                        if (sum == 20) {
                            System.out.printf("Found expected sum %d at center (%d,%d) radius %d\n", sum, i, j, r);
                            solution.visualizeRhombus(grid2, i, j, r);
                        }
                    }
                }
            }
        }

        solution.demonstrateAllRhombuses(grid2);
        System.out.println("Result: " + Arrays.toString(solution.getBiggestThree(grid2)));
        System.out.println("Expected: [20, 9, 8]");

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Test case 3
        int[][] grid3 = {{7, 7, 7}};

        System.out.println("Test Case 3:");
        solution.demonstrateAllRhombuses(grid3);
        System.out.println("Result: " + Arrays.toString(solution.getBiggestThree(grid3)));
        System.out.println("Expected: [7]");

        System.out.println("\nPerformance Analysis:");
        System.out.println("Time Complexity: O(m * n * min(m, n)) - for each cell, try all possible radii");
        System.out.println("Space Complexity: O(number of distinct sums) - for storing results");
        System.out.println("Each rhombus calculation: O(radius) time");
    }
}
