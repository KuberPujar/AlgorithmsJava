package dynamicprmng;
/*
Grid Puzzle-II
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time. An obstacle and space are marked as 1 or 0 respectively in the grid. A path that the robot takes cannot include any square that is an obstacle. Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

Example

Input : obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]

Output: 2

Explanation:

There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Constraints

2 <= m,n <= 500

grid contains only 0 and 1

Note: The function should return the result.
 */
public class GridePuzzleII {
    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));

        int[][] obstacleGrid2 = {
                {0, 1},
                {0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid2));

        int[][] obstacleGrid3 = {
                {0, 0},
                {1, 0}
        };

        System.out.println(uniquePathsWithObstacles(obstacleGrid3));

        int[][] obstacleGrid4 = {
                {0, 0},
                {0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid4));

        int[][] obstacleGrid5 = {
                {1, 0},
                {0, 0}
        };

        System.out.println(uniquePathsWithObstacles(obstacleGrid5));

        int[][] obstacleGrid6 = {
                {0, 0},
                {1, 1}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid6));

        int[][] obstacleGrid7 = {
                {0, 0},
                {1, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid7));

        int[][] obstacleGrid8 = {
                {0, 0},
                {0, 1}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid8));
        int[][] obstacleGrid9 = {
                {0, 0},
                {1, 1}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid9));

        int[][] obstacleGrid10 = {
                {0, 0,0},
                {0,1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid10));

        int[][] obstacleGrid11 = {
                {0, 1},
                {0, 0},
                {0, 1},
                {0, 0}
        };
        System.out.println("#11"+uniquePathsWithObstacles(obstacleGrid11));
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;//rows
        int n = obstacleGrid[0].length;//columns
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1) return 0; // If start or end is blocked
        int[][] dp=new int[m][n];
        dp[0][0]=1; // Starting point
        // Fill the first column
        for(int i=1;i<m;i++){
            if(obstacleGrid[i][0]==0){
                dp[i][0]=dp[i-1][0];
            }
        }
        // Fill the first row
        for(int i=1;i<n;i++){
            if(obstacleGrid[0][i]==0){
                dp[0][i]=dp[0][i-1];
            }
        }
        // Fill the rest of the dp table
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1]; // Return the number of unique paths to the bottom-right corner
    }
}
