package hashtable.bitmanipulation;

import java.util.HashSet;

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









Constraints:

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.


Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int xor = 0;
        HashSet<Integer> set = new HashSet<>();

        // Compute XOR of all numbers in nums and add them to the set
        for (int num : nums) {
            xor ^= num;
            set.add(num);
        }

        // Compute XOR with all numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        // At this point, xor holds the missing number.
        // For demonstration, let's verify using the hash set as well:
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) {
                // Both xor and this value should be the same
                assert xor == i : "Mismatch between XOR and HashSet result!";
                break;
            }
        }

        return xor;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};
        System.out.println("Output: " + missingNumber(nums1)); // 2

        int[] nums2 = {0, 1};
        System.out.println("Output: " + missingNumber(nums2)); // 2

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Output: " + missingNumber(nums3)); // 8
    }
}
