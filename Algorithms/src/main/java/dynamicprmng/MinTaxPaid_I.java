package dynamicprmng;
/*
MIN TAX PAID_I
Given a m x n grid filled with non-negative numbers, A explorer is at top left cell and he has to find a path from top left to bottom right, but there is a condition he has to pay an amount equal to the cell if he choose to include a particular cell in his path, suggest a path which minimizes the money paid by the explorer

Example

Input: grid = [[1,3,1],[1,5,1],[4,2,1]]

Output: 7
Note: You can only move either down or right at any point in time.

Constraints:

m == grid.length

n == grid[i].length

1 <= m, n <= 200

0 <= grid[i][j] <= 200

Note: The function should return the result.
 */
public class MinTaxPaid_I {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minPathSum(grid)); // Output: 7
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(minPathSum(grid2)); // Output: 21
    }

    private static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Create a DP table to store minimum path sums
        int[][] dp = new int[m][n];

        // Initialize starting point
        dp[0][0] = grid[0][0];

        // Initialize first column (can only come from above)
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // Initialize first row (can only come from left)
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
