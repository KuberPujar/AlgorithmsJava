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
public class MinimumJumps_II {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minimumJumps(arr));
        int[] arr2 = {1, 4, 3, 2, 6, 7};
        System.out.println(minimumJumps(arr2));
        int[] arr3 = {1, 0, 0, 0, 0};
        System.out.println(minimumJumps(arr3));
        int[] arr4 = {0, 0, 0, 0, 0};
        System.out.println(minimumJumps(arr4));
        int[] arr5 = {1, 2, 3, 4, 5};
        System.out.println(minimumJumps(arr5));
        int[] arr6 = {1, 2, 3, 0, 5};
        System.out.println(minimumJumps(arr6));
        int[] arr7 = {1, 2, 3, 4, 0};
        System.out.println(minimumJumps(arr7));
        int[] arr8 = {1, 2, 3, 4, 5, 0};
        System.out.println(minimumJumps(arr8));
        int[] arr9 = {1, 2, 3, 4, 5, 6};
        System.out.println(minimumJumps(arr9));
    }

    private static int minimumJumps(int[] arr) {
        int n = arr.length;

        // If array is empty or has only one element, no jumps needed
        if (n <= 1) {
            return 0;
        }

        // If first element is 0, can't move anywhere
        if (arr[0] == 0) {
            return -1;
        }

        // Create DP array to store minimum jumps needed to reach each index
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Base case: 0 jumps needed to reach first index

        for (int i = 0; i < n; i++) {
            // If we can't reach this position, skip
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }

            // Maximum distance we can jump from current position
            int maxJump = arr[i];

            // Update all reachable positions from current position
            for (int j = 1; j <= maxJump && i + j < n; j++) {
                int nextPos = i + j;
                dp[nextPos] = Math.min(dp[nextPos], dp[i] + 1);
            }
        }

        // If last position is unreachable
        if (dp[n - 1] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[n - 1];
    }
}
