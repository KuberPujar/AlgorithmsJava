package hashtable.bitmanipulation;

import java.util.HashMap;

/*
You are given an integer array nums consisting of 2 * n integers.

You need to divide nums into n pairs such that:

Each element belongs to exactly one pair.
The elements present in a pair are equal.
Return true if nums can be divided into n pairs, otherwise return false.



Example 1:

Input: nums = [3,2,3,2,2,2]
Output: true
Explanation:
There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.
Example 2:

Input: nums = [1,2,3,4]
Output: false
Explanation:
There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.


Constraints:

nums.length == 2 * n
1 <= n <= 500
1 <= nums[i] <= 500
 */
public class DivideArrayIntoEqualParts {
    public static boolean canDivideIntoPairs(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        // Count occurrences of each number
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Check if all counts are even using bit manipulation
        for (int count : countMap.values()) {
            // (count & 1) == 1 means count is odd
            if ((count & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums1 = {3,2,3,2,2,2};
        System.out.println("Output: " + canDivideIntoPairs(nums1)); // true

        int[] nums2 = {1,2,3,4};
        System.out.println("Output: " + canDivideIntoPairs(nums2)); // false
    }
}
