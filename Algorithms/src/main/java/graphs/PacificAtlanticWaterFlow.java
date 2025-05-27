package graphs;

import java.util.*;

/*
Pacific Atlantic Water Flow
Given an m x n integer matrix heights representing the heights above sea level of cells on an island, determine the cells from which water can flow to both the Pacific and Atlantic oceans.
Water can flow to neighbouring cells directly north, south, east, and west if the neighbouring cell's height is less than or equal to the current cell's height. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

Input:

The first line contains two integers, rows and cols, representing the dimensions of the heights matrix.
The following rows lines each contain cols integers, representing the heights of the cells in the matrix.
Output:

A list of coordinates [r, c] where water can flow from cell (r, c) to both the Pacific and Atlantic oceans.
Examples:

Input 1:
4 4
1 6 2 5
3 4 2 1
3 5 6 1
4 5 2 6
Output 1:
0 1
0 2
0 3
1 1
1 2
2 1
2 2
3 0
3 1
Explanation:

In this matrix, the listed cells can flow to both the Pacific Ocean (top and left edges) and the Atlantic Ocean (bottom and right edges). For example, cell (0, 1) can flow to both edges due to its height being higher than its neighbors.

Input 2:

2 2
1 2
3 4
Output 2:

 0 1
 1 0
 1 1
Explanation:

In this matrix, the listed cells can flow to both the Pacific Ocean (top and left edges) and the Atlantic Ocean (bottom and right edges). For example, cell (0, 1) can flow to both edges as it’s high enough compared to its neighbors.
Constraints:

1 <= m, n <= 100
1 <= heights[r][c] <= 10000
The matrix heights represents an island with varying cell heights.
Note:The function should return the result. The driver code will handle printing the output.
 */
public class PacificAtlanticWaterFlow {
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();

        // Initialize Pacific queue with left and top edges
        for (int i = 0; i < rows; i++) {
            pacific[i][0] = true;
            pQueue.add(new int[]{i, 0});
        }
        for (int j = 1; j < cols; j++) {
            pacific[0][j] = true;
            pQueue.add(new int[]{0, j});
        }

        // Initialize Atlantic queue with right and bottom edges
        for (int i = 0; i < rows; i++) {
            atlantic[i][cols-1] = true;
            aQueue.add(new int[]{i, cols-1});
        }
        for (int j = 0; j < cols-1; j++) {
            atlantic[rows-1][j] = true;
            aQueue.add(new int[]{rows-1, j});
        }

        // Perform BFS for both oceans
        bfs(heights, pQueue, pacific);
        bfs(heights, aQueue, atlantic);

        // Collect all cells that can reach both oceans
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private static void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int rows = matrix.length;
        int cols = matrix[0].length;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0], j = cell[1];
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && matrix[x][y] >= matrix[i][j]) {
                    visited[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }
    }

    // Example main method for testing (not part of the submission typically)
    public static void main(String[] args) {
        // Input 1:
        // 4 4
        // 1 6 2 5
        // 3 4 2 1
        // 3 5 6 1
        // 4 5 2 6
        int[][] heights1 = {
                {1, 6, 2, 5},
                {3, 4, 2, 1},
                {3, 5, 6, 1},
                {4, 5, 2, 6}
        };
        List<List<Integer>> result1 = pacificAtlantic(heights1);
        System.out.println("Output 1:");
        for (List<Integer> coord : result1) {
            System.out.println(coord.get(0) + " " + coord.get(1));
        }
        // Expected Output 1:
        // 0 1
        // 0 2
        // 0 3
        // 1 1
        // 1 2
        // 2 1
        // 2 2
        // 3 0
        // 3 1

        System.out.println("\n");

        // Input 2:
        // 2 2
        // 1 2
        // 3 4
        int[][] heights2 = {
                {1, 2},
                {3, 4}
        };
        List<List<Integer>> result2 = pacificAtlantic(heights2);
        System.out.println("Output 2:");
        for (List<Integer> coord : result2) {
            System.out.println(coord.get(0) + " " + coord.get(1));
        }
        // Expected Output 2:
        // 0 1
        // 1 0
        // 1 1
    }

}
