package queues.priorityq;

import java.util.PriorityQueue;

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
    /**
     * Calculates the maximum score to reach the end of the array.
     * This solution uses dynamic programming with a PriorityQueue to optimize the process.
     * The core idea is that the maximum score to reach index `i` (let's call it `dp[i]`)
     * is `nums[i]` plus the maximum score from the previous `k` reachable indices.
     * A PriorityQueue (acting as a max-heap) is used to efficiently find this maximum
     * score within the sliding window of size `k`.
     *
     * Time Complexity: O(n * log k), where n is the number of elements. Each element is added
     * and removed from the priority queue once.
     * Space Complexity: O(k) for storing at most k elements in the priority queue.
     *
     * @param nums An array of integers representing the scores at each position.
     * @param k    The maximum jump distance.
     * @return The maximum possible score.
     */
    public int maxResult(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        // A priority queue to store pairs of [max_score, index].
        // It's configured as a max-heap to keep the highest scores at the top.
        // The lambda (a, b) -> b[0] - a[0] sorts by score in descending order.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // We can use the input array `nums` as our DP table to save space.
        // nums[i] will store the maximum score to reach index `i`.

        // Initialize with the starting position.
        maxHeap.offer(new int[]{nums[0], 0});

        // Iterate from the second element to the end.
        for (int i = 1; i < n; i++) {
            // Remove scores from the heap that are outside the current window [i-k, i-1].
            // An index `j` is out of reach if j < i - k.
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] < i - k) {
                maxHeap.poll();
            }

            // The top of the heap now contains the best score from a reachable previous step.
            // Calculate the max score to reach the current index `i`.
            int currentMax = nums[i] + (maxHeap.isEmpty() ? 0 : maxHeap.peek()[0]);

            // Update the score in our DP table (the nums array).
            nums[i] = currentMax;

            // Add the current score and index to the heap for future steps.
            maxHeap.offer(new int[]{currentMax, i});
        }

        // The last element of the modified nums array holds the max score to reach the end.
        return nums[n - 1];
    }

    /**
     * Main method to test the maxResult function.
     */
    public static void main(String[] args) {
        JumpGame_VI solution = new JumpGame_VI();

        // Example 1
        int[] nums1 = {1, -1, -2, 4, -7, 3};
        int k1 = 2;
        System.out.println("Max score for nums1: " + solution.maxResult(nums1, k1)); // Expected: 7

        // Example 2
        int[] nums2 = {10, -5, -2, 4, 0, 3};
        int k2 = 3;
        System.out.println("Max score for nums2: " + solution.maxResult(nums2, k2)); // Expected: 17

        // Example 3
        int[] nums3 = {1, -5, -20, 4, -1, 3, -6, -3};
        int k3 = 2;
        System.out.println("Max score for nums3: " + solution.maxResult(nums3, k3)); // Expected: 0
    }
}
