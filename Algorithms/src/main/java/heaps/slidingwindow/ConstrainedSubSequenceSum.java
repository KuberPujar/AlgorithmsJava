package heaps.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.



Example 1:

Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].
Example 2:

Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.
Example 3:

Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].


Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 */
public class ConstrainedSubSequenceSum {
    // DP with Deque to maintain the maximum values in the sliding window
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // Remove elements that are out of the current window (i-k to i-1)
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // The maximum sum in the window (or 0 if window is empty)
            int prevMax = deque.isEmpty() ? 0 : dp[deque.peekFirst()];
            dp[i] = Math.max(nums[i], nums[i] + prevMax);
            maxSum = Math.max(maxSum, dp[i]);

            // Maintain the deque in decreasing order of dp values
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return maxSum;
    }

    //Space optimized version
    public int constrainedSubsetSumSpaceOptimized(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // Remove out-of-window indices
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Calculate current value
            int prevMax = deque.isEmpty() ? 0 : nums[deque.peekFirst()];
            nums[i] = Math.max(nums[i], nums[i] + prevMax);
            maxSum = Math.max(maxSum, nums[i]);

            // Maintain deque in decreasing order
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        ConstrainedSubSequenceSum solution = new ConstrainedSubSequenceSum();
        int[] nums = {10, 2, -10, 5, 20};
        int k = 2;
        System.out.println("Maximum sum of constrained subsequence: " + solution.constrainedSubsetSum(nums, k));

        // Test space optimized version
        System.out.println("Maximum sum of constrained subsequence (space optimized): " + solution.constrainedSubsetSumSpaceOptimized(nums, k));
    }
}
