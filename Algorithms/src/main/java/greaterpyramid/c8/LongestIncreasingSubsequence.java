package greaterpyramid.c8;

import java.util.Arrays;

/*
Longest Increasing Subsequence (LIS)

Given an array arr[] of size N, find the length of the Longest Increasing Subsequence (LIS) in the array. The LIS is defined as the longest subsequence where each element is strictly greater than the preceding one.

Input Format:

The first line contains an integer N, the size of the array.
The second line contains Nspace-separated integers representing the elements of the arrayarr[].
Output Format:

Output a single integer, the length of the longest increasing subsequence in the given array.
Constraints:

1 <= N <= 2500
-10^5 <= arr[i] <= 10^5, where arr[i] denotes the i-th element of the array arr[].
Sample Inputs and Outputs:

Input:

5
3 10 2 1 20
Output:

3
Explanation: The longest increasing subsequence is {3, 10, 20}, which has a length of 3.

Input:

2
3 2
Output:

1
Explanation: The longest increasing subsequences are {3}and{2}, both with a length of 1.

Input:

6
50 3 10 7 40 80
Output:

4
Explanation: The longest increasing subsequence is {3, 7, 40, 80}, which has a length of 4.
 */
public class LongestIncreasingSubsequence {
    public static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        // Each element is an increasing subsequence of length 1
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {50, 3, 10, 7, 40, 80};
        System.out.println("Length of Longest Increasing Subsequence: " + longestIncreasingSubsequence(arr));
    }
}
