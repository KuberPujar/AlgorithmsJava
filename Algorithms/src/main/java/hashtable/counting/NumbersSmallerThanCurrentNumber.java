package hashtable.counting;

import java.util.*;

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
public class NumbersSmallerThanCurrentNumber {
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        // Step 1: Count the frequency of each number
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Sort the unique numbers
        List<Integer> sortedKeys = new ArrayList<>(countMap.keySet());
        Collections.sort(sortedKeys);

        // Step 3: For each unique number, compute how many numbers are smaller than it
        HashMap<Integer, Integer> smallerCount = new HashMap<>();
        int cumulative = 0;
        for (int key : sortedKeys) {
            smallerCount.put(key, cumulative);
            cumulative += countMap.get(key);
        }

        // Step 4: Build the result array
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = smallerCount.get(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {8,1,2,2,3};
        System.out.println("Output: " + Arrays.toString(smallerNumbersThanCurrent(nums1))); // [4,0,1,1,3]

        int[] nums2 = {6,5,4,8};
        System.out.println("Output: " + Arrays.toString(smallerNumbersThanCurrent(nums2))); // [2,1,0,3]

        int[] nums3 = {7,7,7,7};
        System.out.println("Output: " + Arrays.toString(smallerNumbersThanCurrent(nums3))); // [0,0,0,0]
    }
}
