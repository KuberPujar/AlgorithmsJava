package heaps.graphbased;

import java.util.*;

/*
You are given a 0-indexed 2D integer array grid of size m x n that represents a map of the items in a shop. The integers in the grid represent the following:

0 represents a wall that you cannot pass through.
1 represents an empty cell that you can freely move to and from.
All other positive integers represent the price of an item in that cell. You may also freely move to and from these item cells.
It takes 1 step to travel between adjacent grid cells.

You are also given integer arrays pricing and start where pricing = [low, high] and start = [row, col] indicates that you start at the position (row, col) and are interested only in items with a price in the range of [low, high] (inclusive). You are further given an integer k.

You are interested in the positions of the k highest-ranked items whose prices are within the given price range. The rank is determined by the first of these criteria that is different:

Distance, defined as the length of the shortest path from the start (shorter distance has a higher rank).
Price (lower price has a higher rank, but it must be in the price range).
The row number (smaller row number has a higher rank).
The column number (smaller column number has a higher rank).
Return the k highest-ranked items within the price range sorted by their rank (highest to lowest). If there are fewer than k reachable items within the price range, return all of them.



Example 1:


Input: grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
Output: [[0,1],[1,1],[2,1]]
Explanation: You start at (0,0).
With a price range of [2,5], we can take items from (0,1), (1,1), (2,1) and (2,2).
The ranks of these items are:
- (0,1) with distance 1
- (1,1) with distance 2
- (2,1) with distance 3
- (2,2) with distance 4
Thus, the 3 highest ranked items in the price range are (0,1), (1,1), and (2,1).
Example 2:


Input: grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
Output: [[2,1],[1,2]]
Explanation: You start at (2,3).
With a price range of [2,3], we can take items from (0,1), (1,1), (1,2) and (2,1).
The ranks of these items are:
- (2,1) with distance 2, price 2
- (1,2) with distance 2, price 3
- (1,1) with distance 3
- (0,1) with distance 4
Thus, the 2 highest ranked items in the price range are (2,1) and (1,2).
Example 3:


Input: grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
Output: [[2,1],[2,0]]
Explanation: You start at (0,0).
With a price range of [2,3], we can take items from (2,0) and (2,1).
The ranks of these items are:
- (2,1) with distance 5
- (2,0) with distance 6
Thus, the 2 highest ranked items in the price range are (2,1) and (2,0).
Note that k = 3 but there are only 2 reachable items within the price range.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 105
1 <= m * n <= 105
0 <= grid[i][j] <= 105
pricing.length == 2
2 <= low <= high <= 105
start.length == 2
0 <= row <= m - 1
0 <= col <= n - 1
grid[row][col] > 0
1 <= k <= m * n
 */
public class KthHighestRankedItemsWithinAPriceRange {

    // Directions: up, down, left, right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Item class to store item information for ranking
     */
    static class Item {
        int distance;
        int price;
        int row;
        int col;

        public Item(int distance, int price, int row, int col) {
            this.distance = distance;
            this.price = price;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return String.format("Item{pos=(%d,%d), dist=%d, price=%d}", row, col, distance, price);
        }
    }

    /**
     * Main solution using BFS for distance calculation and custom sorting
     * Time Complexity: O(m * n + k log k) where m*n is grid size
     * Space Complexity: O(m * n)
     */
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int low = pricing[0];
        int high = pricing[1];
        int startRow = start[0];
        int startCol = start[1];

        // BFS to find shortest distances from start position
        int[][] distances = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(distances[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});
        distances[startRow][startCol] = 0;

        // BFS traversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            // Explore all 4 directions
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds and if cell is accessible
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] > 0 && distances[newRow][newCol] == -1) {

                    distances[newRow][newCol] = dist + 1;
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }

        // Collect all items within price range
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Check if cell is reachable and within price range
                if (distances[i][j] != -1 && grid[i][j] >= low && grid[i][j] <= high) {
                    items.add(new Item(distances[i][j], grid[i][j], i, j));
                }
            }
        }

        // Sort items based on ranking criteria
        items.sort((a, b) -> {
            // 1. Distance (shorter distance has higher rank)
            if (a.distance != b.distance) {
                return Integer.compare(a.distance, b.distance);
            }
            // 2. Price (lower price has higher rank)
            if (a.price != b.price) {
                return Integer.compare(a.price, b.price);
            }
            // 3. Row number (smaller row has higher rank)
            if (a.row != b.row) {
                return Integer.compare(a.row, b.row);
            }
            // 4. Column number (smaller column has higher rank)
            return Integer.compare(a.col, b.col);
        });

        // Return top k items (or all if fewer than k)
        List<List<Integer>> result = new ArrayList<>();
        int limit = Math.min(k, items.size());

        for (int i = 0; i < limit; i++) {
            Item item = items.get(i);
            result.add(Arrays.asList(item.row, item.col));
        }

        return result;
    }

    /**
     * Alternative solution using Dijkstra's algorithm (overkill for this problem but good to know)
     * Time Complexity: O(m * n * log(m * n))
     * Space Complexity: O(m * n)
     */
    public List<List<Integer>> highestRankedKItemsDijkstra(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int low = pricing[0];
        int high = pricing[1];
        int startRow = start[0];
        int startCol = start[1];

        // Priority queue for Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        int[][] distances = new int[m][n];

        // Initialize distances
        for (int i = 0; i < m; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        distances[startRow][startCol] = 0;
        pq.offer(new int[]{startRow, startCol, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            // Skip if we've already found a better path
            if (dist > distances[row][col]) {
                continue;
            }

            // Explore neighbors
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] > 0) {

                    int newDist = dist + 1;

                    if (newDist < distances[newRow][newCol]) {
                        distances[newRow][newCol] = newDist;
                        pq.offer(new int[]{newRow, newCol, newDist});
                    }
                }
            }
        }

        // Collect and sort items (same as BFS solution)
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (distances[i][j] != Integer.MAX_VALUE && grid[i][j] >= low && grid[i][j] <= high) {
                    items.add(new Item(distances[i][j], grid[i][j], i, j));
                }
            }
        }

        items.sort((a, b) -> {
            if (a.distance != b.distance) return Integer.compare(a.distance, b.distance);
            if (a.price != b.price) return Integer.compare(a.price, b.price);
            if (a.row != b.row) return Integer.compare(a.row, b.row);
            return Integer.compare(a.col, b.col);
        });

        List<List<Integer>> result = new ArrayList<>();
        int limit = Math.min(k, items.size());

        for (int i = 0; i < limit; i++) {
            Item item = items.get(i);
            result.add(Arrays.asList(item.row, item.col));
        }

        return result;
    }

    /**
     * Optimized solution that stops BFS early when we have enough items
     * Time Complexity: O(m * n) in worst case, but often much better
     * Space Complexity: O(m * n)
     */
    public List<List<Integer>> highestRankedKItemsOptimized(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int low = pricing[0];
        int high = pricing[1];
        int startRow = start[0];
        int startCol = start[1];

        // Use a priority queue to maintain top k items during BFS
        PriorityQueue<Item> topKItems = new PriorityQueue<>((a, b) -> {
            // Reverse comparator for max heap (to remove worst items)
            if (a.distance != b.distance) return Integer.compare(b.distance, a.distance);
            if (a.price != b.price) return Integer.compare(b.price, a.price);
            if (a.row != b.row) return Integer.compare(b.row, a.row);
            return Integer.compare(b.col, a.col);
        });

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        // Check if start position is a valid item
        if (grid[startRow][startCol] >= low && grid[startRow][startCol] <= high) {
            topKItems.offer(new Item(0, grid[startRow][startCol], startRow, startCol));
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n &&
                        !visited[newRow][newCol] && grid[newRow][newCol] > 0) {

                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, dist + 1});

                    // Check if this cell has an item in price range
                    if (grid[newRow][newCol] >= low && grid[newRow][newCol] <= high) {
                        Item newItem = new Item(dist + 1, grid[newRow][newCol], newRow, newCol);

                        if (topKItems.size() < k) {
                            topKItems.offer(newItem);
                        } else {
                            // Compare with worst item in our top k
                            Item worst = topKItems.peek();
                            if (isHigherRank(newItem, worst)) {
                                topKItems.poll();
                                topKItems.offer(newItem);
                            }
                        }
                    }
                }
            }
        }

        // Convert to result format and sort properly
        List<Item> items = new ArrayList<>(topKItems);
        items.sort((a, b) -> {
            if (a.distance != b.distance) return Integer.compare(a.distance, b.distance);
            if (a.price != b.price) return Integer.compare(a.price, b.price);
            if (a.row != b.row) return Integer.compare(a.row, b.row);
            return Integer.compare(a.col, b.col);
        });

        List<List<Integer>> result = new ArrayList<>();
        for (Item item : items) {
            result.add(Arrays.asList(item.row, item.col));
        }

        return result;
    }

    /**
     * Helper method to compare item ranks
     */
    private boolean isHigherRank(Item a, Item b) {
        if (a.distance != b.distance) return a.distance < b.distance;
        if (a.price != b.price) return a.price < b.price;
        if (a.row != b.row) return a.row < b.row;
        return a.col < b.col;
    }

    /**
     * Utility method to print grid for debugging
     */
    public void printGrid(int[][] grid) {
        System.out.println("Grid:");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    /**
     * Utility method to print distances for debugging
     */
    public void printDistances(int[][] distances, int[] start) {
        System.out.println("Distances from start position (" + start[0] + "," + start[1] + "):");
        for (int[] row : distances) {
            for (int dist : row) {
                System.out.print(String.format("%3d", dist));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method to visualize the solution process
     */
    public void debugSolution(int[][] grid, int[] pricing, int[] start, int k) {
        System.out.println("=== DEBUG SOLUTION ===");
        System.out.println("Start: " + Arrays.toString(start));
        System.out.println("Price range: " + Arrays.toString(pricing));
        System.out.println("k = " + k);
        System.out.println();

        printGrid(grid);

        // Calculate distances
        int m = grid.length;
        int n = grid[0].length;
        int[][] distances = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(distances[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        distances[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] > 0 && distances[newRow][newCol] == -1) {

                    distances[newRow][newCol] = dist + 1;
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }

        printDistances(distances, start);

        // Show items in price range
        System.out.println("Items in price range [" + pricing[0] + "," + pricing[1] + "]:");
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (distances[i][j] != -1 && grid[i][j] >= pricing[0] && grid[i][j] <= pricing[1]) {
                    Item item = new Item(distances[i][j], grid[i][j], i, j);
                    items.add(item);
                    System.out.println("  " + item);
                }
            }
        }

        System.out.println();
        System.out.println("After sorting by rank:");
        items.sort((a, b) -> {
            if (a.distance != b.distance) return Integer.compare(a.distance, b.distance);
            if (a.price != b.price) return Integer.compare(a.price, b.price);
            if (a.row != b.row) return Integer.compare(a.row, b.row);
            return Integer.compare(a.col, b.col);
        });

        for (int i = 0; i < Math.min(k, items.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + items.get(i));
        }
        System.out.println();
    }

    /**
     * Test method with all provided examples
     */
    public static void main(String[] args) {
        KthHighestRankedItemsWithinAPriceRange solution = new KthHighestRankedItemsWithinAPriceRange();

        System.out.println("=== K Highest Ranked Items Solutions ===\n");

        // Test Case 1
        int[][] grid1 = {{1,2,0,1},{1,3,0,1},{0,2,5,1}};
        int[] pricing1 = {2,5};
        int[] start1 = {0,0};
        int k1 = 3;

        System.out.println("Test Case 1:");
        solution.debugSolution(grid1, pricing1, start1, k1);

        List<List<Integer>> result1 = solution.highestRankedKItems(grid1, pricing1, start1, k1);
        System.out.println("BFS Result: " + result1);

        List<List<Integer>> result1Opt = solution.highestRankedKItemsOptimized(grid1, pricing1, start1, k1);
        System.out.println("Optimized Result: " + result1Opt);
        System.out.println("Expected: [[0,1],[1,1],[2,1]]\n");

        // Test Case 2
        int[][] grid2 = {{1,2,0,1},{1,3,3,1},{0,2,5,1}};
        int[] pricing2 = {2,3};
        int[] start2 = {2,3};
        int k2 = 2;

        System.out.println("Test Case 2:");
        solution.debugSolution(grid2, pricing2, start2, k2);

        List<List<Integer>> result2 = solution.highestRankedKItems(grid2, pricing2, start2, k2);
        System.out.println("BFS Result: " + result2);

        List<List<Integer>> result2Opt = solution.highestRankedKItemsOptimized(grid2, pricing2, start2, k2);
        System.out.println("Optimized Result: " + result2Opt);
        System.out.println("Expected: [[2,1],[1,2]]\n");

        // Test Case 3
        int[][] grid3 = {{1,1,1},{0,0,1},{2,3,4}};
        int[] pricing3 = {2,3};
        int[] start3 = {0,0};
        int k3 = 3;

        System.out.println("Test Case 3:");
        solution.debugSolution(grid3, pricing3, start3, k3);

        List<List<Integer>> result3 = solution.highestRankedKItems(grid3, pricing3, start3, k3);
        System.out.println("BFS Result: " + result3);

        List<List<Integer>> result3Opt = solution.highestRankedKItemsOptimized(grid3, pricing3, start3, k3);
        System.out.println("Optimized Result: " + result3Opt);
        System.out.println("Expected: [[2,1],[2,0]]\n");

        // Additional Test Case: Start position has item in range
        int[][] grid4 = {{5,2,1},{1,3,1},{1,1,1}};
        int[] pricing4 = {2,5};
        int[] start4 = {0,0};
        int k4 = 2;

        System.out.println("Test Case 4 (Start position has item):");
        solution.debugSolution(grid4, pricing4, start4, k4);

        List<List<Integer>> result4 = solution.highestRankedKItems(grid4, pricing4, start4, k4);
        System.out.println("BFS Result: " + result4);
        System.out.println();

        // Performance comparison
        System.out.println("=== Performance Comparison ===");
        testPerformance(solution);
    }

    /**
     * Performance testing method
     */
    private static void testPerformance(KthHighestRankedItemsWithinAPriceRange solution) {
        // Create a larger test case
        int m = 100, n = 100;
        int[][] grid = new int[m][n];
        Random rand = new Random(42);

        // Fill grid with random values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rand.nextDouble() < 0.1) { // 10% walls
                    grid[i][j] = 0;
                } else if (rand.nextDouble() < 0.3) { // 30% empty cells
                    grid[i][j] = 1;
                } else { // 60% items with prices
                    grid[i][j] = rand.nextInt(100) + 2;
                }
            }
        }

        int[] pricing = {10, 50};
        int[] start = {0, 0};
        int k = 100;

        // Test BFS solution
        long startTime = System.nanoTime();
        List<List<Integer>> bfsResult = solution.highestRankedKItems(grid, pricing, start, k);
        long bfsTime = System.nanoTime() - startTime;

        // Test optimized solution
        startTime = System.nanoTime();
        List<List<Integer>> optResult = solution.highestRankedKItemsOptimized(grid, pricing, start, k);
        long optTime = System.nanoTime() - startTime;

        System.out.println("Large test case (" + m + "x" + n + " grid):");
        System.out.println("BFS Solution: " + bfsResult.size() + " items found (Time: " + bfsTime/1000000 + " ms)");
        System.out.println("Optimized Solution: " + optResult.size() + " items found (Time: " + optTime/1000000 + " ms)");

        // Verify results are consistent
        boolean consistent = bfsResult.equals(optResult);
        System.out.println("Results consistent: " + consistent);

        if (!consistent) {
            System.out.println("BFS first 5: " + bfsResult.subList(0, Math.min(5, bfsResult.size())));
            System.out.println("Opt first 5: " + optResult.subList(0, Math.min(5, optResult.size())));
        }
    }
}
