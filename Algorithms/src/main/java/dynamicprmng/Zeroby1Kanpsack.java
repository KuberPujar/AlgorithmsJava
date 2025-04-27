package dynamicprmng;
/*
0 by 1 kanpsack
You are given weights and values of (N) items. Your task is to maximize the total value in a knapsack of capacity (W). Each item can either be picked completely or left out entirely (0-1 property).

Given two integer arrays val[0..N-1] and wt[0..N-1] that represent the values and weights of (N) items, find the maximum value subset of val[] such that the sum of the weights of the chosen subset is less than or equal to (W).

Input Format

The first line contains two integers ( N ) (number of items) and ( W ) (capacity of the knapsack).
The second line contains ( N ) space-separated integers representing the weights of the items.
The third line contains ( N ) space-separated integers representing the values of the items.
Output Format

Return the maximum value that can be achieved in the knapsack.
Sample Input 1

3 4
4 5 1
1 2 3
Sample Output 1

3
Explanation 1
The best way to achieve the maximum value is by selecting the third item (value = 3, weight = 1), as the knapsack capacity is 4.

Constraints

( 1 <= N <= 1000 )
( 1 <= W <= 1000 )
( 1 <= wt[i] <= 1000 )
( 1 <= val[i] <= 1000 )
 */
public class Zeroby1Kanpsack {
    public static void main(String[] args) {
        int[] wt = {4, 5, 1};
        int[] val = {1, 2, 3};
        int W = 4;
        System.out.println(knapsack(wt, val, W,wt.length)); // Output: 3
    }

    public static int knapsack( int[] wt,int[] val, int W, int N) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < N; i++) {
            for (int w = W; w >= wt[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
            }
        }
        return dp[W];
    }
}
