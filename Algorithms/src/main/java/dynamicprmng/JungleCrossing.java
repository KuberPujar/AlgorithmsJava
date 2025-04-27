package dynamicprmng;

import java.util.Scanner;

/*
Crossing the jungle
You are in a jungle which can be seen as a matrix of size n x m. At each of the cell there is a
special type of environment like there could be a quick sand, a pack of wolves or a tree of
berries and fruits.
So each cell could either make you lose "exp" or help you gain "exp" (exp stands for experience).
You can either move right or down. Your goal is to reach the cell at nth row and mth column.
Initially let us say you start with exp "X".

Return the minimum possible value of X required to cross the jungle and come out with a
positive exp.

Input Format:
Two space-separated integers 'n' and 'm' denoting the number of rows and columns.

Next 'n' lines contains 'm' number of integers each denoting the impact on your exp.

Output Format:
Return the minimum possible value of X required to cross the jungle come out with a positive exp.
Sample Input:
2 2

0 1

2 0
Sample Output:
-1
Explanation:
The player can take the route (0,0) -> (1, 0) -> (1,1) and the minimum health that he can start with would be -1.
Here the minimum energy required to cross the jungle is -1 because -1 + 2>0 (remember that 0 is not positive).
1<=n,m<=100
Elements of the matrix could range from -100 to 100.
Note: The function should return the result.
 */
public class JungleCrossing {
    /**
     * Given a jungle represented as a matrix, where each cell has an impact on your experience (exp),
     * find the minimum initial exp required to reach the bottom-right cell with a positive exp.
     * You can only move right or down.
     *
     * @param points A 2D array of integers representing the jungle.
     * @return The minimum initial exp required.
     */
    public static int minInitialExp(int[][] points) {
        int m = points.length, n = points[0].length;
        int[][] dp = new int[m][n];

        // Base case
        if (points[m - 1][n - 1] > 0) {
            dp[m - 1][n - 1] = 1;
        }
        else {
            dp[m - 1][n - 1]
                    = Math.abs(points[m - 1][n - 1]) + 1;
        }

        // Fill last row and last column as base to fill
        // entire table
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(
                    dp[i + 1][n - 1] - points[i][n - 1], 1);
        }
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(
                    dp[m - 1][j + 1] - points[m - 1][j], 1);
        }

        // fill the table in bottom-up fashion
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int minExitPoints
                        = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(
                        minExitPoints - points[i][j], 1);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {

        int[][] jungle1 = {
                {0, 1},
                {2, 0}
        };
        System.out.println(minInitialExp(jungle1)); // Expected Output: -1

        int[][] jungle2 = {
                {-2, -3, 4},
                {-1, -1, 1},
                {3, -2, 0}
        };
        System.out.println(minInitialExp(jungle2)); // Expected Output: 5

        int[][] jungle3 = {
                {0 ,-2 ,-3 ,1},
                {-1, 4 ,0 ,-2},
                {1 ,-2 ,-3 ,0}
        };
        System.out.println(minInitialExp(jungle3)); // 0
    }
}
