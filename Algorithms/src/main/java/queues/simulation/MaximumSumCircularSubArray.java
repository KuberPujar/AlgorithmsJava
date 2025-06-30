package queues.simulation;

import java.util.*;

/*
Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.



Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
Example 3:

Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.


Constraints:

n == nums.length
1 <= n <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
 */
public class MaximumSumCircularSubArray {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        // Case 1: Maximum subarray is non-circular (standard Kadane's algorithm)
        int maxKadane = kadane(nums);

        // Case 2: Maximum subarray is circular
        // This means we need to find minimum subarray in the middle
        // and subtract it from total sum
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Find minimum subarray sum using modified Kadane's
        int minKadane = kadaneMin(nums);
        int maxCircular = totalSum - minKadane;

        // Edge case: if all elements are negative, maxCircular would be 0
        // but we need at least one element, so return maxKadane
        if (maxCircular == 0) {
            return maxKadane;
        }

        return Math.max(maxKadane, maxCircular);
    }

    // Standard Kadane's algorithm for maximum subarray
    private int kadane(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Kadane's algorithm for minimum subarray
    private int kadaneMin(int[] nums) {
        int minSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.min(nums[i], currentSum + nums[i]);
            minSum = Math.min(minSum, currentSum);
        }

        return minSum;
    }

// Alternative solution using Deque for sliding window maximum approach
    public int maxSubarraySumCircularDq(int[] nums) {
        int n = nums.length;

        // Case 1: Non-circular maximum subarray
        int maxNonCircular = kadaneDq(nums);

        // Case 2: Circular maximum subarray using deque-based approach
        int maxCircular = findMaxCircularWithDeque(nums);

        return Math.max(maxNonCircular, maxCircular);
    }

    private int kadaneDq(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    private int findMaxCircularWithDeque(int[] nums) {
        int n = nums.length;

        // Create extended array for circular consideration
        int[] extended = new int[2 * n];
        for (int i = 0; i < n; i++) {
            extended[i] = nums[i];
            extended[i + n] = nums[i];
        }

        // Calculate prefix sums
        int[] prefixSum = new int[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) {
            prefixSum[i + 1] = prefixSum[i] + extended[i];
        }

        // Use deque to maintain minimum prefix sum in sliding window
        Deque<Integer> deque = new ArrayDeque<>();
        int maxSum = Integer.MIN_VALUE;

        deque.offer(0); // Add initial prefix sum index

        for (int i = 1; i <= 2 * n; i++) {
            // Remove elements outside window of size n
            while (!deque.isEmpty() && deque.peekFirst() < i - n) {
                deque.pollFirst();
            }

            // Current subarray sum = prefixSum[i] - min(prefixSum in valid range)
            if (!deque.isEmpty()) {
                maxSum = Math.max(maxSum, prefixSum[i] - prefixSum[deque.peekFirst()]);
            }

            // Maintain deque in ascending order of prefix sums
            while (!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]) {
                deque.pollLast();
            }

            deque.offer(i);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSumCircularSubArray solution = new MaximumSumCircularSubArray();

        // Example 1: nums = [1,-2,3,-2]
        int[] nums1 = {1, -2, 3, -2};
        System.out.println("Example 1: " + Arrays.toString(nums1));
        System.out.println("Standard Solution: " + solution.maxSubarraySumCircular(nums1));
        System.out.println("Deque Solution: " + solution.maxSubarraySumCircularDq(nums1));
        System.out.println("Expected: 3");
        System.out.println("Explanation: Subarray [3] has maximum sum 3\n");

        // Example 2: nums = [5,-3,5]
        int[] nums2 = {5, -3, 5};
        System.out.println("Example 2: " + Arrays.toString(nums2));
        System.out.println("Standard Solution: " + solution.maxSubarraySumCircular(nums2));
        System.out.println("Deque Solution: " + solution.maxSubarraySumCircularDq(nums2));
        System.out.println("Expected: 10");
        System.out.println("Explanation: Circular subarray [5,5] has maximum sum 10\n");

        // Example 3: nums = [-3,-2,-3]
        int[] nums3 = {-3, -2, -3};
        System.out.println("Example 3: " + Arrays.toString(nums3));
        System.out.println("Standard Solution: " + solution.maxSubarraySumCircular(nums3));
        System.out.println("Deque Solution: " + solution.maxSubarraySumCircularDq(nums3));
        System.out.println("Expected: -2");
        System.out.println("Explanation: Subarray [-2] has maximum sum -2\n");

        // Additional test cases
        int[] nums4 = {3, -1, 2, -1};
        System.out.println("Additional Test 1: " + Arrays.toString(nums4));
        System.out.println("Standard Solution: " + solution.maxSubarraySumCircular(nums4));
        System.out.println("Deque Solution: " + solution.maxSubarraySumCircularDq(nums4));
        System.out.println("Expected: 4");
        System.out.println("Explanation: Circular subarray [2,-1,3] has sum 4\n");

        int[] nums5 = {3, -2, 2, -3};
        System.out.println("Additional Test 2: " + Arrays.toString(nums5));
        System.out.println("Standard Solution: " + solution.maxSubarraySumCircular(nums5));
        System.out.println("Deque Solution: " + solution.maxSubarraySumCircularDq(nums5));
        System.out.println("Expected: 3");
        System.out.println("Explanation: Non-circular subarray [3] has maximum sum\n");

        // Edge case: single element
        int[] nums6 = {5};
        System.out.println("Edge Case - Single Element: " + Arrays.toString(nums6));
        System.out.println("Standard Solution: " + solution.maxSubarraySumCircular(nums6));
        System.out.println("Deque Solution: " + solution.maxSubarraySumCircularDq(nums6));
        System.out.println("Expected: 5\n");

        // Performance comparison
        System.out.println("Performance Test:");
        int[] largePerfArray = generateLargeArray(10000);

        long start = System.currentTimeMillis();
        int result1 = solution.maxSubarraySumCircular(largePerfArray);
        long time1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        int result2 = solution.maxSubarraySumCircularDq(largePerfArray);
        long time2 = System.currentTimeMillis() - start;

        System.out.println("Array size: " + largePerfArray.length);
        System.out.println("Standard solution: " + result1 + " (Time: " + time1 + "ms)");
        System.out.println("Deque solution: " + result2 + " (Time: " + time2 + "ms)");
    }

    private static int[] generateLargeArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducibility
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(200) - 100; // Random numbers between -100 and 99
        }
        return arr;
    }
}
