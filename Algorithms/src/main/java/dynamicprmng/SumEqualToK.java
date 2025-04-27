package dynamicprmng;
/*
sum equal to k
You are given an array ‘ARR’ with N positive integers and an integer K. return true if there is a subset whose sum is equal to K.

Example :

Input : N = 4 , K = 5 , arr = [1,4,4,5]

Output : true
Constraints

1<= 'n' <= 100

0 <= 'arr[i]' <= 1000

1 <= 'k' <= 1000

Note: The function should return the result.
 */
public class SumEqualToK {
    public static void main(String[] args) {
        int[] arr = {1, 4, 4, 5};
        int k = 5;
        System.out.println(subsetSum(arr,arr.length, k)); // Output: true

        int[] arr2 = {1, 2, 3};
        int k2 = 7;
        System.out.println(subsetSum(arr2,arr2.length, k2)); // Output: false
    }

    public static boolean subsetSum(int[] arr,int n, int k) {
        boolean[][] dp = new boolean[n + 1][k + 1];

        // Initialize the DP table
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][k];
    }
}
