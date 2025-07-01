package hashtable.counting;

import java.util.HashMap;

/*
Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.

The value of |x| is defined as:

x if x >= 0.
-x if x < 0.


Example 1:

Input: nums = [1,2,2,1], k = 1
Output: 4
Explanation: The pairs with an absolute difference of 1 are:
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
- [1,2,2,1]
Example 2:

Input: nums = [1,3], k = 3
Output: 0
Explanation: There are no pairs with an absolute difference of 3.
Example 3:

Input: nums = [3,2,1,5,4], k = 2
Output: 3
Explanation: The pairs with an absolute difference of 2 are:
- [3,2,1,5,4]
- [3,2,1,5,4]
- [3,2,1,5,4]


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
1 <= k <= 99
 */
public class CountNumberOfPairsWithObsoluteDiffK {
    public static int countKDifference(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int result = 0;

        // Traverse the array and for each number, check if (num - k) or (num + k) has been seen before
        for (int num : nums) {
            result += countMap.getOrDefault(num - k, 0);
            result += countMap.getOrDefault(num + k, 0);
            // Add current number to the map
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int k1 = 1;
        System.out.println("Output: " + countKDifference(nums1, k1)); // Output: 4

        int[] nums2 = {1,3};
        int k2 = 3;
        System.out.println("Output: " + countKDifference(nums2, k2)); // Output: 0

        int[] nums3 = {3,2,1,5,4};
        int k3 = 2;
        System.out.println("Output: " + countKDifference(nums3, k3)); // Output: 3
    }
}
