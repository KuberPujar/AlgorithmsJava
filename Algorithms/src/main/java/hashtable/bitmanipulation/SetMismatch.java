package hashtable.bitmanipulation;

import java.util.HashSet;

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
public class SetMismatch {
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        HashSet<Integer> seen = new HashSet<>();
        int duplicate = -1;
        int xorAll = 0;
        int xorNums = 0;

        // Find the duplicate using a hash set, and calculate XOR of all nums and 1..n
        for (int i = 0; i < n; i++) {
            if (!seen.add(nums[i])) {
                duplicate = nums[i];
            }
            xorNums ^= nums[i];
            xorAll ^= (i + 1);
        }

        // The missing number is found by XORing the duplicate out
        int missing = duplicate ^ (xorNums ^ xorAll);

        return new int[]{duplicate, missing};
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,4};
        int[] result1 = findErrorNums(nums1);
        System.out.println("Output: [" + result1[0] + "," + result1[1] + "]"); // [2,3]

        int[] nums2 = {1,1};
        int[] result2 = findErrorNums(nums2);
        System.out.println("Output: [" + result2[0] + "," + result2[1] + "]"); // [1,2]
    }
}
