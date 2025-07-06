package heaps.prefixsum;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1], k = 1
Output: 1
Example 2:

Input: nums = [1,2], k = 4
Output: -1
Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3


Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
 */
public class ShortestSubarrayWithSumAtLeastK {
    /**
     * Finds the length of the shortest non-empty subarray with sum at least k.
     * Uses prefix sum and deque for O(n) time complexity.
     *
     * @param nums the input array
     * @param k the target sum
     * @return length of shortest subarray, or -1 if none exists
     */
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        // Prefix sum array - prefixSum[i] = sum of nums[0] to nums[i-1]
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        // Deque to maintain indices of prefix sums in increasing order
        Deque<Integer> deque = new ArrayDeque<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // Check if we can form a subarray with sum >= k
            // Remove indices from front while we can form valid subarrays
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }

            // Maintain deque in increasing order of prefix sums
            // Remove indices from back while current prefix sum is smaller
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * Alternative solution using PriorityQueue (Min-Heap)
     * This approach has O(n log n) time complexity but is more intuitive
     */
    public static int shortestSubarrayUsingHeap(int[] nums, int k) {
        int n = nums.length;

        // Min-heap to store (prefix_sum, index) pairs
        PriorityQueue<long[]> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        long prefixSum = 0;
        int minLength = Integer.MAX_VALUE;

        // Add initial state (0 sum at index -1)
        minHeap.offer(new long[]{0, -1});

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            // Check if we can form subarrays with sum >= k
            while (!minHeap.isEmpty() && prefixSum - minHeap.peek()[0] >= k) {
                long[] pair = minHeap.poll();
                minLength = Math.min(minLength, i - (int)pair[1]);
            }

            minHeap.offer(new long[]{prefixSum, i});
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1},
                {1, 2},
                {2, -1, 2},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {-1, -1, -1},
                {5, -3, 5},
                {1, 2, 3, 4, 5}
        };

        int[] kValues = {1, 4, 3, 8, 1, 4, 15};

        System.out.println("Testing Shortest Subarray with Sum at Least K");
        System.out.println("=" .repeat(50));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int k = kValues[i];

            int result1 = shortestSubarray(nums, k);
            int result2 = shortestSubarrayUsingHeap(nums, k);

            System.out.printf("Test %d: nums = %s, k = %d%n",
                    i + 1, Arrays.toString(nums), k);
            System.out.printf("  Deque solution: %d%n", result1);
            System.out.printf("  Heap solution:  %d%n", result2);
            System.out.printf("  Match: %s%n%n", result1 == result2 ? "✓" : "✗");
        }

        // Performance comparison
        System.out.println("Performance Analysis:");
        System.out.println("- Deque solution: O(n) time, O(n) space");
        System.out.println("- Heap solution:  O(n log n) time, O(n) space");
        System.out.println("- Deque is optimal for this problem");
    }
}

/*
Algorithm Explanation:

1. DEQUE APPROACH (Optimal - O(n)):
   - Use prefix sums to convert subarray sum to difference of prefix sums
   - Maintain a deque of indices with increasing prefix sum values
   - For each position i:
     * Remove indices from front while we can form valid subarrays (sum >= k)
     * Remove indices from back to maintain increasing order
     * Add current index to back

2. HEAP APPROACH (O(n log n)):
   - Use min-heap to store (prefix_sum, index) pairs
   - For each position, check against smallest prefix sums first
   - More intuitive but less efficient than deque approach

Key Insights:
- Negative numbers make this problem complex (can't use sliding window)
- Need to handle prefix sums efficiently to avoid O(n²) brute force
- Deque maintains candidates in optimal order for checking

Time Complexity: O(n) for deque, O(n log n) for heap
Space Complexity: O(n) for both approaches
*/

