package hashtable.sorting;

import java.util.Arrays;
import java.util.HashMap;

/*
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.



Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation:
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1).
For nums[3]=2 there exist one smaller number than it (1).
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
Example 2:

Input: nums = [6,5,4,8]
Output: [2,1,0,3]
Example 3:

Input: nums = [7,7,7,7]
Output: [0,0,0,0]


Constraints:

2 <= nums.length <= 500
0 <= nums[i] <= 100
 */
public class NumbersLessthanCurrentNumber {
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        // Map to store the number and the count of numbers smaller than it
        HashMap<Integer, Integer> numToCount = new HashMap<>();

        // For each unique number, map it to its first occurrence index (which equals the count of smaller numbers)
        for (int i = 0; i < sorted.length; i++) {
            if (!numToCount.containsKey(sorted[i])) {
                numToCount.put(sorted[i], i);
            }
        }

        // Build the result using the map
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = numToCount.get(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {8,1,2,2,3};
        System.out.println("Output: " + Arrays.toString(smallerNumbersThanCurrent(nums1))); // [4,0,1,1,3]

        // Example 2
        int[] nums2 = {6,5,4,8};
        System.out.println("Output: " + Arrays.toString(smallerNumbersThanCurrent(nums2))); // [2,1,0,3]

        // Example 3
        int[] nums3 = {7,7,7,7};
        System.out.println("Output: " + Arrays.toString(smallerNumbersThanCurrent(nums3))); // [0,0,0,0]
    }
}
