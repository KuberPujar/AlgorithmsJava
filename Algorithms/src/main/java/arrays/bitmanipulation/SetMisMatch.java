package arrays.bitmanipulation;
/*
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.



Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:

Input: nums = [1,1]
Output: [1,2]


Constraints:

2 <= nums.length <= 104
1 <= nums[i] <= 104
 */

import java.util.Arrays;

public class SetMisMatch {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 4};
        System.out.println(Arrays.toString(findErrorNums(nums1))); // Output: [2, 3]

        int[] nums2 = {1, 1};
        System.out.println(Arrays.toString(findErrorNums(nums2))); // Output: [1, 2]
    }

    public static int[] findErrorNums(int[] nums) {
        int xorResult = 0;
        int n = nums.length;

        // XOR all elements in the array and numbers from 1 to n
        for (int i = 0; i < n; i++) {
            xorResult ^= nums[i];
            xorResult ^= (i + 1);
        }

        // Find the rightmost set bit
        int rightmostSetBit = xorResult & -xorResult;

        int xorGroup1 = 0;
        int xorGroup2 = 0;

        // Partition the numbers into two groups based on the rightmost set bit
        for (int i = 0; i < n; i++) {
            if ((nums[i] & rightmostSetBit) != 0) {
                xorGroup1 ^= nums[i];
            } else {
                xorGroup2 ^= nums[i];
            }
            if (((i + 1) & rightmostSetBit) != 0) {
                xorGroup1 ^= (i + 1);
            } else {
                xorGroup2 ^= (i + 1);
            }
        }

        // Determine which one is the duplicate and which is the missing
        for (int num : nums) {
            if (num == xorGroup1) {
                return new int[]{xorGroup1, xorGroup2};
            }
        }

        return new int[]{xorGroup2, xorGroup1};
    }
}
