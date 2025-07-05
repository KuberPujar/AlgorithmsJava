package heaps.hashing;

import java.util.*;

/*
You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.

Return any such subsequence as an integer array of length k.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: nums = [2,1,3,3], k = 2
Output: [3,3]
Explanation:
The subsequence has the largest sum of 3 + 3 = 6.
Example 2:

Input: nums = [-1,-2,3,4], k = 3
Output: [-1,3,4]
Explanation:
The subsequence has the largest sum of -1 + 3 + 4 = 6.
Example 3:

Input: nums = [3,4,3,3], k = 2
Output: [3,4]
Explanation:
The subsequence has the largest sum of 3 + 4 = 7.
Another possible subsequence is [4, 3].


Constraints:

1 <= nums.length <= 1000
-105 <= nums[i] <= 105
1 <= k <= nums.length
 */
public class FindSubsequenceOfLengthKWithTheLargestSum {
    /**
     * Finds a subsequence of length k with the largest sum from the given array.
     *
     * @param nums The input integer array.
     * @param k    The desired length of the subsequence.
     * @return An integer array of length k representing the subsequence with the largest sum.
     */
    public int[] maxSubsequence(int[] nums, int k) {
        // A min-heap to store pairs of [number, index].
        // The heap is ordered by the number (the first element of the pair).
        // This allows us to efficiently keep track of the k largest elements seen so far.
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Iterate through the input array to find the k largest elements.
        for (int i = 0; i < nums.length; i++) {
            // Add the current number and its index to the heap.
            pq.offer(new int[]{nums[i], i});
            // If the heap size exceeds k, remove the smallest element.
            // This ensures the heap only contains the k largest elements encountered.
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // At this point, the priority queue contains the k largest elements,
        // but they are not in their original order.
        // We extract them into a list to sort them by their original index.
        List<int[]> topKElements = new ArrayList<>(pq);

        // Sort the list of [number, index] pairs based on the original index.
        // This restores the subsequence order.
        Collections.sort(topKElements, Comparator.comparingInt(a -> a[1]));

        // Create the result array and populate it with the numbers from the sorted list.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topKElements.get(i)[0];
        }

        return result;
    }

    /**
     * Main method for testing the solution with the provided examples.
     */
    public static void main(String[] args) {
        FindSubsequenceOfLengthKWithTheLargestSum sol = new FindSubsequenceOfLengthKWithTheLargestSum();

        // Example 1
        int[] nums1 = {2, 1, 3, 3};
        int k1 = 2;
        int[] result1 = sol.maxSubsequence(nums1, k1);
        System.out.println("Example 1 Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Example 1 Output: " + Arrays.toString(result1)); // Expected: [3, 3]

        // Example 2
        int[] nums2 = {-1, -2, 3, 4};
        int k2 = 3;
        int[] result2 = sol.maxSubsequence(nums2, k2);
        System.out.println("Example 2 Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Example 2 Output: " + Arrays.toString(result2)); // Expected: [-1, 3, 4]

        // Example 3
        int[] nums3 = {3, 4, 3, 3};
        int k3 = 2;
        int[] result3 = sol.maxSubsequence(nums3, k3);
        System.out.println("Example 3 Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Example 3 Output: " + Arrays.toString(result3)); // Expected: [3, 4]
    }
}
