package dynamicprmng;
/*
Break the number
Given an integer (n), break it into the sum of 2 or more integers, and maximize the product of those integers. The goal is to find the maximum product that can be obtained from such a breakdown.

Input Format:

A single integer (n).
Output Format:

An integer representing the maximum product that can be obtained by breaking (n).
Sample Input 1:

3
Sample Output 1:

2
Explanation:
You can break ( 3 ) as ( 2 + 1 ), and the product is ( 2 * 1 = 2 ), which is the maximum possible.

Sample Input 2:

10
Sample Output 2:

36
Explanation:
You can break ( 10 ) as ( 3 + 3 + 4 ), and the product is ( 3 * 3 * 4 = 36 ), which is the maximum possible.

Constraints:

( 2 <= n <= 58 )
 */
public class BreakTheNumber {
    public static void main(String[] args) {

        int n = 10;
        System.out.println(maxProduct(n)); // Output: 36
    }

    public static int maxProduct(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
