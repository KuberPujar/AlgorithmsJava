package dynamicprmng;

import java.util.Arrays;
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
fruits.
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
     * @param dungeon A 2D array of integers representing the jungle.
     * @return The minimum initial exp required.
     */
    public static int minInitialExp(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];

        // Initialize the starting cell
        dp[0][0] = dungeon[0][0];

        // Fill the first row
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j-1] + dungeon[0][j];
        }

        // Fill the first column
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + dungeon[i][0];
        }

        // Fill the rest of the table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + dungeon[i][j];
            }
        }

        int maxSum = dp[n-1][m-1];
        print(dp);
        return 1 - maxSum;
    }

    private static void print(int[][] dp) {
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }
    public static void main(String[] args) {
        int[][] jungle1 = {
                {0, 1},
                {2, 0}
        };
        System.out.println("case1:" + minInitialExp(jungle1)); // Expected Output: -1

        int[][] jungle2 = {
                {-2, -3, 4},
                {-1, -1, 1},
                {3, -2, 0}
        };
        System.out.println("case2:" + minInitialExp(jungle2)); // Expected Output: 5

        int[][] jungle3 = {
                {0, -2, -3, 1},
                {-1, 4, 0, -2},
                {1, -2, -3, 0}
        };
        System.out.println("case3:" + minInitialExp(jungle3)); // Expected Output: 0

        int[][] jungle4 = {
                {0, -1, -1},
                {-2, -3, -4},
                {1, 2, 0}
        };
        System.out.println("case4:" + minInitialExp(jungle4)); // Expected Output: 0

        int[][] jungle5={
                {0,1,-3},
                {1,-2,0}
        };
        System.out.println("case5:" + minInitialExp(jungle5)); // Expected Output: 2
    }
}
