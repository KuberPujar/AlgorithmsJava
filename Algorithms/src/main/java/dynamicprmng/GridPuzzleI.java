package dynamicprmng;
/*
Grid Puzzle-I
There is a robot on an ( m * n ) grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time. Given the two integers ( m ) and ( n ), return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to ( 2 * 10^9 ).

Input Format:

The first line contains two integers ( m ) and ( n ) denoting the dimensions of the grid.
Output Format:

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
Sample Input 1:

3 2
Sample Output 1:

3
Explanation 1:

From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:

Right -> Down -> Down
Down -> Down -> Right
Down -> Right -> Down
Constraints:

( 1 <= m, n <= 100 )
Note: The function should return the result.
 */
public class GridPuzzleI {
    public static void main(String[] args) {
        int m = 3, n = 2;
        System.out.println(uniquePaths(m, n));
    }

    private static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
