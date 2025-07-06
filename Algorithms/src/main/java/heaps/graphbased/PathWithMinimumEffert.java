package heaps.graphbased;

import java.util.*;

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
public class PathWithMinimumEffert {
    // Directions: up, down, left, right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Finds the minimum effort required to travel from top-left to bottom-right.
     * Uses modified Dijkstra's algorithm with min-heap.
     *
     * Time Complexity: O(rows * cols * log(rows * cols))
     * Space Complexity: O(rows * cols)
     */
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Special case: single cell
        if (rows == 1 && cols == 1) {
            return 0;
        }

        // Min-heap to store (effort, row, col)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Track minimum effort to reach each cell
        int[][] minEffort = new int[rows][cols];

        // Initialize with maximum values
        for (int i = 0; i < rows; i++) {
            Arrays.fill(minEffort[i], Integer.MAX_VALUE);
        }

        // Start from top-left corner
        minEffort[0][0] = 0;
        minHeap.offer(new int[]{0, 0, 0}); // {effort, row, col}

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentEffort = current[0];
            int row = current[1];
            int col = current[2];

            // If we reached bottom-right, return the effort
            if (row == rows - 1 && col == cols - 1) {
                return currentEffort;
            }

            // Skip if we've already found a better path to this cell
            if (currentEffort > minEffort[row][col]) {
                continue;
            }

            // Explore all 4 directions
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                // Calculate effort for this move
                int heightDiff = Math.abs(heights[newRow][newCol] - heights[row][col]);
                int newEffort = Math.max(currentEffort, heightDiff);

                // If we found a better path to the neighbor
                if (newEffort < minEffort[newRow][newCol]) {
                    minEffort[newRow][newCol] = newEffort;
                    minHeap.offer(new int[]{newEffort, newRow, newCol});
                }
            }
        }

        return minEffort[rows - 1][cols - 1];
    }

    /**
     * Alternative solution using binary search + BFS/DFS
     * Time Complexity: O(rows * cols * log(max_height))
     */
    public int minimumEffortPathBinarySearch(int[][] heights) {
        int left = 0;
        int right = 1000000; // Maximum possible height difference

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canReachDestination(heights, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Check if we can reach destination with given maximum effort using DFS
     */
    private boolean canReachDestination(int[][] heights, int maxEffort) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] visited = new boolean[rows][cols];

        return dfs(heights, 0, 0, rows - 1, cols - 1, maxEffort, visited);
    }

    private boolean dfs(int[][] heights, int row, int col, int targetRow, int targetCol,
                        int maxEffort, boolean[][] visited) {
        // Base cases
        if (row == targetRow && col == targetCol) {
            return true;
        }

        visited[row][col] = true;

        // Explore all 4 directions
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check bounds and visited
            if (newRow < 0 || newRow >= heights.length ||
                    newCol < 0 || newCol >= heights[0].length ||
                    visited[newRow][newCol]) {
                continue;
            }

            // Check if effort is within limit
            int effort = Math.abs(heights[newRow][newCol] - heights[row][col]);
            if (effort <= maxEffort) {
                if (dfs(heights, newRow, newCol, targetRow, targetCol, maxEffort, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Union-Find approach for comparison
     */
    public int minimumEffortPathUnionFind(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        if (rows == 1 && cols == 1) return 0;

        // Create list of all edges with their weights
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cellId = i * cols + j;

                // Add edge to right neighbor
                if (j + 1 < cols) {
                    int rightId = i * cols + (j + 1);
                    int weight = Math.abs(heights[i][j] - heights[i][j + 1]);
                    edges.add(new int[]{weight, cellId, rightId});
                }

                // Add edge to bottom neighbor
                if (i + 1 < rows) {
                    int bottomId = (i + 1) * cols + j;
                    int weight = Math.abs(heights[i][j] - heights[i + 1][j]);
                    edges.add(new int[]{weight, cellId, bottomId});
                }
            }
        }

        // Sort edges by weight
        edges.sort((a, b) -> a[0] - b[0]);

        // Union-Find
        UnionFind uf = new UnionFind(rows * cols);

        int start = 0;
        int end = (rows - 1) * cols + (cols - 1);

        for (int[] edge : edges) {
            int weight = edge[0];
            int u = edge[1];
            int v = edge[2];

            uf.union(u, v);

            if (uf.connected(start, end)) {
                return weight;
            }
        }

        return 0;
    }

    /**
     * Union-Find data structure
     */
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    /**
     * Test method with all provided examples
     */
    public static void main(String[] args) {
        PathWithMinimumEffert solution = new  PathWithMinimumEffert();

        // Test Case 1
        int[][] heights1 = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println("Test Case 1:");
        System.out.println("Heights: " + Arrays.deepToString(heights1));
        System.out.println("Dijkstra Result: " + solution.minimumEffortPath(heights1));
        System.out.println("Binary Search Result: " + solution.minimumEffortPathBinarySearch(heights1));
        System.out.println("Union-Find Result: " + solution.minimumEffortPathUnionFind(heights1));
        System.out.println("Expected: 2\n");

        // Test Case 2
        int[][] heights2 = {{1,2,3},{3,8,4},{5,3,5}};
        System.out.println("Test Case 2:");
        System.out.println("Heights: " + Arrays.deepToString(heights2));
        System.out.println("Dijkstra Result: " + solution.minimumEffortPath(heights2));
        System.out.println("Binary Search Result: " + solution.minimumEffortPathBinarySearch(heights2));
        System.out.println("Union-Find Result: " + solution.minimumEffortPathUnionFind(heights2));
        System.out.println("Expected: 1\n");

        // Test Case 3
        int[][] heights3 = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        System.out.println("Test Case 3:");
        System.out.println("Heights: " + Arrays.deepToString(heights3));
        System.out.println("Dijkstra Result: " + solution.minimumEffortPath(heights3));
        System.out.println("Binary Search Result: " + solution.minimumEffortPathBinarySearch(heights3));
        System.out.println("Union-Find Result: " + solution.minimumEffortPathUnionFind(heights3));
        System.out.println("Expected: 0\n");

        // Edge case: single cell
        int[][] heights4 = {{5}};
        System.out.println("Edge Case - Single Cell:");
        System.out.println("Heights: " + Arrays.deepToString(heights4));
        System.out.println("Dijkstra Result: " + solution.minimumEffortPath(heights4));
        System.out.println("Expected: 0\n");

        // Performance test
        System.out.println("=== Performance Comparison ===");
        testPerformance(solution);
    }

    /**
     * Performance testing method
     */
    private static void testPerformance(PathWithMinimumEffert solution) {
        // Create a larger test case
        int[][] largeHeights = new int[50][50];
        Random rand = new Random(42); // Fixed seed for reproducibility

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                largeHeights[i][j] = rand.nextInt(1000000) + 1;
            }
        }

        // Test Dijkstra approach
        long start = System.nanoTime();
        int result1 = solution.minimumEffortPath(largeHeights);
        long dijkstraTime = System.nanoTime() - start;

        // Test Binary Search approach
        start = System.nanoTime();
        int result2 = solution.minimumEffortPathBinarySearch(largeHeights);
        long binarySearchTime = System.nanoTime() - start;

        // Test Union-Find approach
        start = System.nanoTime();
        int result3 = solution.minimumEffortPathUnionFind(largeHeights);
        long unionFindTime = System.nanoTime() - start;

        System.out.println("Large test case (50x50 grid):");
        System.out.println("Dijkstra: " + result1 + " (Time: " + dijkstraTime/1000000 + " ms)");
        System.out.println("Binary Search: " + result2 + " (Time: " + binarySearchTime/1000000 + " ms)");
        System.out.println("Union-Find: " + result3 + " (Time: " + unionFindTime/1000000 + " ms)");

        // Verify all approaches give same result
        if (result1 == result2 && result2 == result3) {
            System.out.println("✓ All approaches give consistent results");
        } else {
            System.out.println("✗ Results differ between approaches");
        }
    }
}
