package hashtable.counting;

import java.util.HashMap;

/*
Given an array of integers nums, return the number of good pairs.

A pair (i, j) is called good if nums[i] == nums[j] and i < j.



Example 1:

Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Example 2:

Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:

Input: nums = [1,2,3]
Output: 0


Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
 */
public class NumberOfGoodPairs {
    public static int numIdenticalPairs(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int goodPairs = 0;

        for (int num : nums) {
            // If the number has appeared before, all previous occurrences form good pairs with this one
            if (countMap.containsKey(num)) {
                goodPairs += countMap.get(num);
                countMap.put(num, countMap.get(num) + 1);
            } else {
                countMap.put(num, 1);
            }
        }

        return goodPairs;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1, 1, 3};
        System.out.println("Output: " + numIdenticalPairs(nums1)); // Output: 4

        int[] nums2 = {1, 1, 1, 1};
        System.out.println("Output: " + numIdenticalPairs(nums2)); // Output: 6

        int[] nums3 = {1, 2, 3};
        System.out.println("Output: " + numIdenticalPairs(nums3)); // Output: 0
    }
}
