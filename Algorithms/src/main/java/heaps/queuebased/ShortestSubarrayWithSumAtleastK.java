package heaps.queuebased;

import java.util.ArrayDeque;
import java.util.Deque;

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
public class ShortestSubarrayWithSumAtleastK {

    // Java solution using a deque to find the shortest subarray with sum at least k
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];

        // Compute prefix sums
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // Remove elements from the end that are larger than current prefix sum
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }

            // Check for valid subarrays from the front
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }

            deque.offerLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    //Space optimized version using a single prefix sum array
    public int shortestSubarray1(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        Deque<long[]> deque = new ArrayDeque<>(); // [index, prefixSum]
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            // Check if current sum alone satisfies the condition
            if (sum >= k) {
                minLength = Math.min(minLength, i + 1);
            }

            // Remove elements from the end that have larger prefix sums
            while (!deque.isEmpty() && sum <= deque.peekLast()[1]) {
                deque.pollLast();
            }

            // Check for valid subarrays from the front
            while (!deque.isEmpty() && sum - deque.peekFirst()[1] >= k) {
                minLength = Math.min(minLength, i - (int)deque.pollFirst()[0]);
            }

            deque.offerLast(new long[]{i, sum});
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {
        ShortestSubarrayWithSumAtleastK solution = new ShortestSubarrayWithSumAtleastK();
        int[] nums1 = {1};
        int k1 = 1;
        System.out.println(solution.shortestSubarray(nums1, k1)); // Output: 1

        int[] nums2 = {1, 2};
        int k2 = 4;
        System.out.println(solution.shortestSubarray(nums2, k2)); // Output: -1

        int[] nums3 = {2, -1, 2};
        int k3 = 3;
        System.out.println(solution.shortestSubarray(nums3, k3)); // Output: 3
    }
}
