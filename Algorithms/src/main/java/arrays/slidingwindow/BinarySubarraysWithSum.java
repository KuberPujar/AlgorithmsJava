package arrays.slidingwindow;

import java.util.HashMap;

/*
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.



Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15


Constraints:

1 <= nums.length <= 3 * 104
nums[i] is either 0 or 1.
0 <= goal <= nums.length
 */
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        System.out.println(numSubarraysWithSum(nums, goal));
    }

    private static int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Base case: sum 0 occurs once before start

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num;
            // Check if (currentSum - goal) exists in prefix sums
            count += prefixSumCount.getOrDefault(currentSum - goal, 0);
            // Update the count of current prefix sum
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
