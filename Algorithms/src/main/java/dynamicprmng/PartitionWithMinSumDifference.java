package dynamicprmng;
/*
Partition with minimum sum difference
Given an integer array nums, representing the number of chocolates in ith bag where 1<=i<=N and N is the length of Array nums , Alice and Bob are best friends they want to share chocolates in such a way that minimises the absolute difference between the sum of two arrays. Return the minimum possible absolute difference.

For example the given array is [8, 6, 5] and we divide it in arrays [6,5] and [8] then the absolute difference of these arrays would be |8 - (6+5)| = 3. You have to seperate the array in such a way that the absolute difference between the array is minimised.

Example 1 :

Input:  nums = [3,9,7,3]

Output: 2
Example 2 :

Input: nums = [36,36]

Output: 0
Constraints:

1 <= n <= 15

0 <= nums[i] <= 10^5

Note: The function should return the result.
 */
public class PartitionWithMinSumDifference {
    public static void main(String[] args) {
        int[] nums = {3, 9, 7, 3};
        System.out.println(minimumDifference(nums)); // Output: 2

        int[] nums2 = {36, 36};
        System.out.println(minimumDifference(nums2)); // Output: 0
    }

    private static int minimumDifference(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int n = nums.length;
        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = totalSum; j >= num; j--) {
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
        }

        int minDiff = Integer.MAX_VALUE;
        for (int j = totalSum / 2; j >= 0; j--) {
            if (dp[j]) {
                minDiff = totalSum - 2 * j;
                break;
            }
        }

        return minDiff;
    }
}
