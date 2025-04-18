package arrays.dpbased;
/*
Given an integer array nums, find the subarray with the largest sum, and return its sum.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubArray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums)); // Output: 6
        nums = new int[]{1};
        System.out.println(maxSubArray(nums)); // Output: 1
        nums = new int[]{5, 4, -1, 7, 8};
        System.out.println(maxSubArray(nums)); // Output: 23
        nums = new int[]{-1, -2, -3, -4};
        System.out.println(maxSubArray(nums)); // Output: -1
    }

    private static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Decide whether to start new subarray or continue current one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // Update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
