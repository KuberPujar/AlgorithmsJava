package heaps.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/*
You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.



Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0


Constraints:

1 <= nums.length, k <= 105
-104 <= nums[i] <= 104
 */
public class JumpGame_VI {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        // Deque stores indices of potential maximum values in the sliding window
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);

        for (int i = 1; i < n; i++) {
            // Remove indices that are out of the current window (i-k to i-1)
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // The maximum in the window is at the front of the deque
            dp[i] = dp[deque.peekFirst()] + nums[i];

            // Maintain the deque in decreasing order of dp values
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return dp[n - 1];
    }

    // Alternative solution using the same logic but modifying the input array directly space complexity O(1)
    public int maxResultSpaceOptimized(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);

        for (int i = 1; i < n; i++) {
            // Remove out-of-window indices
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Calculate current value
            nums[i] += nums[deque.peekFirst()];

            // Maintain deque in decreasing order
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return nums[n - 1];
    }

    public static void main(String[] args) {
        JumpGame_VI solution = new JumpGame_VI();
        int[] nums1 = {1, -1, -2, 4, -7, 3};
        int k1 = 2;
        System.out.println(solution.maxResult(nums1, k1)); // Output: 7

        int[] nums2 = {10, -5, -2, 4, 0, 3};
        int k2 = 3;
        System.out.println(solution.maxResult(nums2, k2)); // Output: 17

        int[] nums3 = {1, -5, -20, 4, -1, 3, -6, -3};
        int k3 = 2;
        System.out.println(solution.maxResult(nums3, k3)); // Output: 0
    }
}
