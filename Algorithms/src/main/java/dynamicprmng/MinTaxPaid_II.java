package dynamicprmng;
/*
Given a n x n grid filled with non-negative numbers, A explorer is at top left cell and he has to find a path from top left to bottom right, but there is a condition he has to pay an amount equal to the cell if he choose to include a particular cell in his path, suggest a falling path which minimizes the money paid by the explorer. The explorer can move down, right or diagonally down.

Example

Input:
3 3
1 2 3
4 5 6
7 8 9
Output:
15

Explanation:
The explorer will start from matrix[0][0] that is from 1 then move to 5 then finally to 9 which is at matrix[n-1][n-1]. The total cost is 16 which is the minimum possible achievable.

Constraints:
n == matrix.length == matrix[i].length

1 <= n <= 100

-100 <= matrix[i][j] <= 100

Note: The function should return the result.
 */
public class MinTaxPaid_II {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(minFallingPathSum(grid)); // Output: 15
    }

    private static int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        // Initialize the starting point
        dp[0][0] = grid[0][0];

        // Fill the first row (can only come from left)
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // Fill the first column (can only come from top)
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // Fill the rest of the table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // Minimum of coming from top, left, or diagonal
                int minPrev = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                dp[i][j] = minPrev + grid[i][j];
            }
        }

        return dp[n-1][n-1];
    }
}
