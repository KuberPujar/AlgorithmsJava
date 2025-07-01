package hashtable.sorting;

import java.util.Arrays;
import java.util.HashSet;

/*
You are given an array of integers nums. You are also given an integer original which is the first number that needs to be searched for in nums.

You then do the following steps:

If original is found in nums, multiply it by two (i.e., set original = 2 * original).
Otherwise, stop the process.
Repeat this process with the new number as long as you keep finding the number.
Return the final value of original.



Example 1:

Input: nums = [5,3,6,1,12], original = 3
Output: 24
Explanation:
- 3 is found in nums. 3 is multiplied by 2 to obtain 6.
- 6 is found in nums. 6 is multiplied by 2 to obtain 12.
- 12 is found in nums. 12 is multiplied by 2 to obtain 24.
- 24 is not found in nums. Thus, 24 is returned.
Example 2:

Input: nums = [2,7,9], original = 4
Output: 4
Explanation:
- 4 is not found in nums. Thus, 4 is returned.


Constraints:

1 <= nums.length <= 1000
1 <= nums[i], original <= 1000
 */
public class MultiplyingFoundValuesByTwo {
    public static int findFinalValue(int[] nums, int original) {
        // Sort the array (not strictly necessary for hash table lookup, but included as per requirement)
        Arrays.sort(nums);

        // Add all numbers to a hash set for O(1) lookup
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Keep multiplying original by 2 as long as it is found in the set
        while (set.contains(original)) {
            original *= 2;
        }

        return original;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {5, 3, 6, 1, 12};
        int original1 = 3;
        System.out.println("Output: " + findFinalValue(nums1, original1)); // Output: 24

        // Example 2
        int[] nums2 = {2, 7, 9};
        int original2 = 4;
        System.out.println("Output: " + findFinalValue(nums2, original2)); // Output: 4
    }
}
