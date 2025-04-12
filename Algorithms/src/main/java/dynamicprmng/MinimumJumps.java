package dynamicprmng;

import java.util.Arrays;

/*
Minimum Jumps
Given an array of ( N ) integers arr[] where each element represents the maximum length of the jump that can be made forward from that element. This means if arr[i] = x, then we can jump any distance ( y ) such that ( y <= x ).
Find the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then you cannot move through that element.

Try to see if you can solve using brute force first then optimize your solution

Note: Return -1 if you can't reach the end of the array.
Input Format:

The first line contains a single integer ( T ) denoting the number of test cases. For each test case:
The first line contains a single integer ( N ) denoting the number of elements in the array.
The second line contains ( N ) space-separated integers denoting the elements of the array.
Output Format:

Return the minimum number of jumps to reach the end of the array. If not possible, return -1.
Sample Input 1:

1
11
1 3 5 8 9 2 6 7 6 8 9
Sample Output 1:

3
Explanation 1:

First jump from the 1st element to the 2nd element with value 3. Now, from here we jump to the 5th element with value 9, and from here we will jump to the last.

Sample Input 2:

1
6
1 4 3 2 6 7
Sample Output 2:

2
Explanation 2:

First we jump from the 1st to the 2nd element and then jump to the last element.

Constraints:

( 1 <= N <= 10^7 )
( 0 <= arr[i] <= 10^7 )
Note: The function should return the result.
 */
/*
brute force solution
public class MinimumJumps {

    // Brute force recursive solution
    public static int minJumpsRecursive(int[] arr) {
        return helper(arr, 0);
    }

    private static int helper(int[] arr, int position) {
        // Base case: reached the end
        if (position >= arr.length - 1) {
            return 0;
        }

        // Can't move from current position
        if (arr[position] == 0) {
            return Integer.MAX_VALUE;
        }

        int minJumps = Integer.MAX_VALUE;
        // Try all possible jumps from current position
        for (int jump = 1; jump <= arr[position]; jump++) {
            int nextPos = position + jump;
            if (nextPos < arr.length) {
                int jumps = helper(arr, nextPos);
                if (jumps != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, 1 + jumps);
                }
            }
        }

        return minJumps;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumpsRecursive(arr1)); // Output: 3

        int[] arr2 = {1, 4, 3, 2, 6, 7};
        System.out.println(minJumpsRecursive(arr2)); // Output: 2
    }
}

//Greedy solution
public class MinimumJumps {

    // Optimal greedy solution with O(n) time and O(1) space
    public static int minJumpsOptimal(int[] arr) {
        if (arr.length <= 1) return 0;
        if (arr[0] == 0) return -1;

        int maxReach = arr[0];
        int steps = arr[0];
        int jumps = 1;

        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                return jumps;
            }

            maxReach = Math.max(maxReach, i + arr[i]);
            steps--;

            if (steps == 0) {
                jumps++;
                if (i >= maxReach) {
                    return -1;
                }
                steps = maxReach - i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumpsOptimal(arr1)); // Output: 3

        int[] arr2 = {1, 4, 3, 2, 6, 7};
        System.out.println(minJumpsOptimal(arr2)); // Output: 2

        int[] arr3 = {0, 1, 1, 1, 1};
        System.out.println(minJumpsOptimal(arr3)); // Output: -1
    }
}
 */
public class MinimumJumps {

    // DP solution with O(n^2) time and O(n) space
    public static int minJumpsDP(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Starting position

        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;

            for (int j = 1; j <= arr[i] && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumpsDP(arr1)); // Output: 3

        int[] arr2 = {1, 4, 3, 2, 6, 7};
        System.out.println(minJumpsDP(arr2)); // Output: 2
    }
}
