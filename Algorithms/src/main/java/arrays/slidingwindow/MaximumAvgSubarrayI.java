package arrays.slidingwindow;
/*
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.



Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000


Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104
 */
public class MaximumAvgSubarrayI {
    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(findMaxAverage(nums, k));
    }

    private static double findMaxAverage(int[] nums, int k) {
        // Calculate the initial sum of the first window
        double windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        double maxSum = windowSum;

        // Slide the window through the array
        for (int i = k; i < nums.length; i++) {
            // Subtract the element going out of the window
            // Add the new element coming into the window
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        // Return the maximum average
        return maxSum / k;
    }
}
