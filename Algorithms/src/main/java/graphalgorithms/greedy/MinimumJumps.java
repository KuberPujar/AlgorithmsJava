package graphalgorithms.greedy;

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
public class MinimumJumps {
    /**
     * BRUTE FORCE APPROACH - Dynamic Programming
     * Time: O(n²), Space: O(n)
     */
    public static int minJumpsBruteForce(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        if (arr[0] == 0) return -1;

        // dp[i] represents minimum jumps needed to reach index i
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If we can reach i from j and j is reachable
                if (j + arr[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];
    }

    /**
     * OPTIMIZED GREEDY APPROACH
     * Time: O(n), Space: O(1)
     *
     * Key Insight: At each step, we try to reach as far as possible
     * We keep track of:
     * - currentEnd: farthest we can reach with current number of jumps
     * - farthest: farthest we can reach with one more jump
     */
    public static int minJumpsGreedy(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        if (arr[0] == 0) return -1;

        int jumps = 0;
        int currentEnd = 0;    // Farthest we can reach with current jumps
        int farthest = 0;      // Farthest we can reach with one more jump

        for (int i = 0; i < n - 1; i++) {
            // Update the farthest we can reach
            farthest = Math.max(farthest, i + arr[i]);

            // If we can't move forward from current position
            if (farthest <= i) {
                return -1;
            }

            // If we've reached the end of current jump range
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early termination if we can reach the end
                if (currentEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    /**
     * ALTERNATIVE GREEDY APPROACH - BFS Style
     * Time: O(n), Space: O(1)
     */
    public static int minJumpsBFS(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        if (arr[0] == 0) return -1;

        int jumps = 0;
        int left = 0, right = 0;

        while (right < n - 1) {
            int farthest = 0;

            // Find the farthest we can reach in current level
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + arr[i]);
            }

            // If we can't make progress
            if (farthest <= right) {
                return -1;
            }

            left = right + 1;
            right = farthest;
            jumps++;
        }

        return jumps;
    }

    /**
     * DETAILED SOLUTION WITH STEP-BY-STEP EXPLANATION
     */
    public static int minJumpsDetailed(int[] arr) {
        int n = arr.length;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Length: " + n);

        if (n <= 1) {
            System.out.println("Array length <= 1, no jumps needed");
            return 0;
        }

        if (arr[0] == 0) {
            System.out.println("First element is 0, cannot move");
            return -1;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        System.out.println("\nStep-by-step execution:");
        System.out.println("i\tarr[i]\tfarthest\tcurrentEnd\tjumps");
        System.out.println("-\t-----\t--------\t----------\t-----");

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + arr[i]);

            if (farthest <= i) {
                System.out.println("Cannot make progress from index " + i);
                return -1;
            }

            System.out.printf("%d\t%d\t%d\t\t%d\t\t%d", i, arr[i], farthest, currentEnd, jumps);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                System.out.print(" -> JUMP!");

                if (currentEnd >= n - 1) {
                    System.out.print(" (Can reach end)");
                    break;
                }
            }
            System.out.println();
        }

        System.out.println("\nMinimum jumps needed: " + jumps);
        return jumps;
    }

    /**
     * RECURSIVE SOLUTION WITH MEMOIZATION
     * Time: O(n²), Space: O(n)
     */
    public static int minJumpsRecursive(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        if (arr[0] == 0) return -1;

        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        int result = minJumpsHelper(arr, 0, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int minJumpsHelper(int[] arr, int pos, int[] memo) {
        int n = arr.length;

        // Base case: reached the end
        if (pos == n - 1) return 0;

        // Out of bounds or stuck at 0
        if (pos >= n || arr[pos] == 0) return Integer.MAX_VALUE;

        // Check memoization
        if (memo[pos] != -1) return memo[pos];

        int minJumps = Integer.MAX_VALUE;

        // Try all possible jumps from current position
        for (int i = 1; i <= arr[pos] && pos + i < n; i++) {
            int jumps = minJumpsHelper(arr, pos + i, memo);
            if (jumps != Integer.MAX_VALUE) {
                minJumps = Math.min(minJumps, jumps + 1);
            }
        }

        memo[pos] = minJumps;
        return minJumps;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Expected: 3");
        System.out.println("Brute Force: " + minJumpsBruteForce(arr1));
        System.out.println("Greedy: " + minJumpsGreedy(arr1));
        System.out.println("BFS Style: " + minJumpsBFS(arr1));
        System.out.println("Recursive: " + minJumpsRecursive(arr1));
        System.out.println();

        // Test case 2
        int[] arr2 = {1, 4, 3, 2, 6, 7};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Expected: 2");
        System.out.println("Greedy: " + minJumpsGreedy(arr2));
        System.out.println();

        // Test case 3 - Detailed explanation
        int[] arr3 = {2, 3, 1, 1, 4};
        System.out.println("=== Test Case 3 (Detailed) ===");
        minJumpsDetailed(arr3);
        System.out.println();

        // Test case 4 - Impossible case
        int[] arr4 = {1, 0, 2, 3};
        System.out.println("=== Test Case 4 (Impossible) ===");
        System.out.println("Array: " + Arrays.toString(arr4));
        System.out.println("Result: " + minJumpsGreedy(arr4));
        System.out.println("Expected: -1");
        System.out.println();

        // Performance comparison
        int[] largeArr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArr[i] = (int)(Math.random() * 10) + 1;
        }

        System.out.println("=== Performance Comparison (Array size: 1000) ===");

        long start = System.nanoTime();
        int result1 = minJumpsGreedy(largeArr);
        long time1 = System.nanoTime() - start;

        start = System.nanoTime();
        int result2 = minJumpsBFS(largeArr);
        long time2 = System.nanoTime() - start;

        System.out.println("Greedy result: " + result1 + " (Time: " + time1/1000 + " µs)");
        System.out.println("BFS result: " + result2 + " (Time: " + time2/1000 + " µs)");
    }

    /**
     * Main solution function that matches the expected interface
     */
    public static int solve(int[] arr) {
        return minJumpsGreedy(arr);
    }
}
