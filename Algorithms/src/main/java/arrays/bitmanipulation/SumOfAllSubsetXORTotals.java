package arrays.bitmanipulation;

/*
The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.

For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums.

Note: Subsets with the same elements should be counted multiple times.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.



Example 1:

Input: nums = [1,3]
Output: 6
Explanation: The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6
Example 2:

Input: nums = [5,1,6]
Output: 28
Explanation: The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
Example 3:

Input: nums = [3,4,5,6,7,8]
Output: 480
Explanation: The sum of all XOR totals for every subset is 480.


Constraints:

1 <= nums.length <= 12
1 <= nums[i] <= 20
 */

public class SumOfAllSubsetXORTotals {
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        System.out.println(calculateXOR(nums1));
        System.out.println(subsetXORSum(nums1)); // Output: 6

        int[] nums2 = {5, 1, 6};
        System.out.println(subsetXORSum(nums2)); // Output: 28
        System.out.println(calculateXOR(nums2));

        int[] nums3 = {3, 4, 5, 6, 7, 8};
        System.out.println(subsetXORSum(nums3)); // Output: 480
        System.out.println(calculateXOR(nums3));
    }

    public static int subsetXORSum(int[] nums) {
        int totalSum = 0;
        int n = nums.length;
        // There are 2^n subsets
        int totalSubsets = 1 << n;
        for (int mask = 0; mask < totalSubsets; mask++) {
            int currentXOR = 0;
            for (int i = 0; i < n; i++) {
                // Check if the i-th bit is set in the mask
                if ((mask & (1 << i)) != 0) {
                    currentXOR ^= nums[i];
                }
            }
            totalSum += currentXOR;
        }
        return totalSum;
    }

    private static int calculateXOR(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total |= num;
        }

        return total << (nums.length - 1);
    }
}