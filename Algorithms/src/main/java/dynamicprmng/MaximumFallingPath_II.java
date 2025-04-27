package dynamicprmng;
/*
Maximum Falling Path Sum II
Given an n×n integer matrix grid, find the maximum sum of a falling path with non-zero shifts.

A falling path with non-zero shifts is a choice of exactly one element from each row of the grid such that no two elements chosen in adjacent rows are in the same column.

Example:

Input:

3
grid = [[1,2,3],
       [4,5,6],
       [7,8,9]
]
Output:

17
Explanation:
The possible falling paths are:

[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the largest sum is [3,5,9], so the answer is 17.

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
-99 <= grid[i][j] <= 99
Note: The function should return the result.
 */
public class MaximumFallingPath_II {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(maxFallingPathSum(grid)); // Output: 17
    }

    public static int maxFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0]; // Edge case: single element

        int[][] dp = new int[n][n];

        // Initialize the first row of dp
        for (int j = 0; j < n; j++) {
            dp[0][j] = grid[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int maxPrev = Integer.MIN_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k != j && dp[i-1][k] > maxPrev) {
                        maxPrev = dp[i-1][k];
                    }
                }
                dp[i][j] = grid[i][j] + maxPrev;
            }
        }

        // Find the maximum in the last row
        int maxSum = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            if (dp[n-1][j] > maxSum) {
                maxSum = dp[n-1][j];
            }
        }

        return maxSum;
    }
}
