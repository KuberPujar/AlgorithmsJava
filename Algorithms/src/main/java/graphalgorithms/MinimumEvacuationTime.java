package graphalgorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Minimum Evacuation Time
You're a botanist infiltrating a secret warehouse teeming with oranges! But these aren't ordinary oranges; they're prone to a contagious rot that spreads like wildfire! Your mission? Determine the minimum time it takes to rot all oranges before which you will have to act.

You're presented with a grid representing the warehouse (array grid). Each cell holds an integer: 0: Empty space 1: Fresh orange (ripe for the picking) 2: Rotten orange (spreading the contagion) Every minute, any fresh orange adjacent (up, down, left, right) to a rotten one succumbs to the rot itself. Your mission is to find the minimum number of minutes it takes for all fresh oranges to rot. If it is impossible for all the oranges to rot then return -1.

Input/Output Format:

Input:

A nested array grid representing the warehouse layout, where each cell contains an integer (0, 1, or 2).

Output:

A single integer indicating the minimum time it takes to rot all oranges or -1 if it is impossible to rot all fresh oranges.

Examples:

Input:

[[2, 1, 1], [1, 1, 0], [0, 1, 1]] (Warehouse filled with both ripe and rotten oranges)

Output:

4 (It takes 4 minutes for all oranges to rot.)

Input:

[[2, 1, 1], [0, 1, 1], [1, 0, 1]] (Another warehouse layout)

Output:

-1 (The left bottom orange will never rot thus the output is -1)

Input:

[[0, 2]] (A small warehouse with no fresh oranges)

Output:

0 (Since there are no fresh oranges to begin with, the evacuation time is instantly completed.)

Constraints:

1 <= m (number of rows in the warehouse) <= 10
1 <= n (number of columns in the warehouse) <= 10
Each cell in grid can only hold 0, 1, or 2.
 */
public class MinimumEvacuationTime {
    /**
     * Cell class to represent position in the grid
     */
    static class Cell {
        int row, col, time;

        Cell(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,t=%d)", row, col, time);
        }
    }

    /**
     * Main solution using Multi-source BFS
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // Directions for adjacent cells (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<Cell> queue = new LinkedList<>();
        int freshOranges = 0;

        // Initialize queue with all rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Cell(i, j, 0));
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // If no fresh oranges, return 0
        if (freshOranges == 0) {
            return 0;
        }

        int maxTime = 0;

        // BFS to spread rot
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // Check all 4 adjacent directions
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                // Check bounds and if cell contains fresh orange
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {

                    // Rot the fresh orange
                    grid[newRow][newCol] = 2;
                    freshOranges--;

                    // Add to queue for next level
                    Cell newCell = new Cell(newRow, newCol, current.time + 1);
                    queue.offer(newCell);

                    // Update maximum time
                    maxTime = Math.max(maxTime, current.time + 1);
                }
            }
        }

        // If there are still fresh oranges, return -1
        return freshOranges == 0 ? maxTime : -1;
    }

    /**
     * Alternative solution with detailed step-by-step tracking
     */
    public static int orangesRottingDetailed(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        String[] dirNames = {"UP", "DOWN", "LEFT", "RIGHT"};

        Queue<Cell> queue = new LinkedList<>();
        int freshOranges = 0;

        System.out.println("=== Initial Grid ===");
        printGrid(grid);

        // Initialize
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Cell(i, j, 0));
                    System.out.println("Initial rotten orange at: (" + i + "," + j + ")");
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        System.out.println("Initial fresh oranges count: " + freshOranges);

        if (freshOranges == 0) {
            System.out.println("No fresh oranges to rot!");
            return 0;
        }

        int maxTime = 0;
        int minute = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean rotted = false;

            System.out.println("\n=== Minute " + minute + " ===");
            System.out.println("Processing " + levelSize + " rotten oranges");

            // Process all oranges at current time level
            for (int i = 0; i < levelSize; i++) {
                Cell current = queue.poll();
                System.out.println("Processing rotten orange at " + current);

                // Check all 4 directions
                for (int d = 0; d < directions.length; d++) {
                    int newRow = current.row + directions[d][0];
                    int newCol = current.col + directions[d][1];

                    if (newRow >= 0 && newRow < rows &&
                            newCol >= 0 && newCol < cols &&
                            grid[newRow][newCol] == 1) {

                        System.out.println("  -> Rotting fresh orange at (" + newRow + "," + newCol + ") via " + dirNames[d]);

                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        rotted = true;

                        queue.offer(new Cell(newRow, newCol, current.time + 1));
                        maxTime = Math.max(maxTime, current.time + 1);
                    }
                }
            }

            if (rotted) {
                minute++;
                System.out.println("Grid after minute " + minute + ":");
                printGrid(grid);
                System.out.println("Remaining fresh oranges: " + freshOranges);
            }

            if (!rotted) break;
        }

        System.out.println("\n=== Final Result ===");
        System.out.println("Fresh oranges remaining: " + freshOranges);
        System.out.println("Maximum time: " + maxTime);

        return freshOranges == 0 ? maxTime : -1;
    }

    /**
     * Optimized solution using level-by-level BFS
     */
    public static int orangesRottingOptimized(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Initialize queue and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0;

        int minutes = 0;

        // BFS level by level
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();

            // Process all rotten oranges at current level
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                // Check all 4 directions
                for (int[] dir : directions) {
                    int newRow = current[0] + dir[0];
                    int newCol = current[1] + dir[1];

                    if (newRow >= 0 && newRow < rows &&
                            newCol >= 0 && newCol < cols &&
                            grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2;
                        freshCount--;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
            minutes++;
        }

        return freshCount == 0 ? minutes : -1;
    }

    /**
     * Helper method to print grid
     */
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Helper method to create a deep copy of grid
     */
    private static int[][] copyGrid(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    public static void main(String[] args) {
        // Test case 1
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Expected: 4");
        System.out.println("Result: " + orangesRotting(copyGrid(grid1)));
        System.out.println();

        // Test case 2
        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Expected: -1");
        System.out.println("Result: " + orangesRotting(copyGrid(grid2)));
        System.out.println();

        // Test case 3
        int[][] grid3 = {{0, 2}};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Expected: 0");
        System.out.println("Result: " + orangesRotting(copyGrid(grid3)));
        System.out.println();

        // Test case 4 - Detailed explanation
        int[][] grid4 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println("=== Test Case 4 (Detailed) ===");
        orangesRottingDetailed(grid4);
        System.out.println();

        // Test case 5 - Edge cases
        int[][] grid5 = {{1}};
        System.out.println("=== Test Case 5 (No rotten oranges) ===");
        System.out.println("Grid: " + Arrays.deepToString(grid5));
        System.out.println("Expected: -1");
        System.out.println("Result: " + orangesRotting(copyGrid(grid5)));
        System.out.println();

        int[][] grid6 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println("=== Test Case 6 (All empty) ===");
        System.out.println("Grid: " + Arrays.deepToString(grid6));
        System.out.println("Expected: 0");
        System.out.println("Result: " + orangesRotting(copyGrid(grid6)));
        System.out.println();

        // Performance comparison
        int[][] largeGrid = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                largeGrid[i][j] = (int)(Math.random() * 3);
            }
        }

        System.out.println("=== Performance Comparison ===");
        long start = System.nanoTime();
        int result1 = orangesRotting(copyGrid(largeGrid));
        long time1 = System.nanoTime() - start;

        start = System.nanoTime();
        int result2 = orangesRottingOptimized(copyGrid(largeGrid));
        long time2 = System.nanoTime() - start;

        System.out.println("Standard BFS: " + result1 + " (Time: " + time1/1000 + " µs)");
        System.out.println("Optimized BFS: " + result2 + " (Time: " + time2/1000 + " µs)");
    }

    /**
     * Main solution function
     */
    public static int solve(int[][] grid) {
        return orangesRotting(grid);
    }
}
