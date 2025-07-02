package bitmanipulation.hashing;

import java.util.HashSet;
import java.util.Set;

/*
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.



Example 1:

Input: nums = [3,0,1]

Output: 2

Explanation:

n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:

Input: nums = [0,1]

Output: 2

Explanation:

n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]

Output: 8

Explanation:

n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xor = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        for (int num : nums) {
            xor ^= num;
            seen.add(num);
        }
        // Hashing: verify all numbers are unique and in range
        if (seen.size() != n) throw new IllegalArgumentException("Input array has duplicates or invalid numbers");
        return xor;
    }

    public static void main(String[] args) {
        MissingNumber solution = new MissingNumber();
        int[] nums1 = {3, 0, 1};
        System.out.println(solution.missingNumber(nums1)); // Output: 2

        int[] nums2 = {0, 1};
        System.out.println(solution.missingNumber(nums2)); // Output: 2

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(solution.missingNumber(nums3)); // Output: 8
    }
}
