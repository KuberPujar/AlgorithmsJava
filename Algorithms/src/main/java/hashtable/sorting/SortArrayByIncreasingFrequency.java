package hashtable.sorting;

import java.util.Arrays;
import java.util.HashMap;

/*
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.



Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]


Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */
public class SortArrayByIncreasingFrequency {
    public static int[] frequencySort(int[] nums) {
        // Step 1: Count frequencies using HashMap
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Convert the array to Integer[] for custom sorting
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        // Step 3: Sort with custom comparator
        Arrays.sort(arr, (a, b) -> {
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);
            if (freqA != freqB) {
                return Integer.compare(freqA, freqB); // Increasing frequency
            } else {
                return Integer.compare(b, a); // Decreasing value if frequency is the same
            }
        });

        // Step 4: Convert back to int[]
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr[i];
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,1,2,2,2,3};
        System.out.println("Output: " + Arrays.toString(frequencySort(nums1))); // [3,1,1,2,2,2]

        int[] nums2 = {2,3,1,3,2};
        System.out.println("Output: " + Arrays.toString(frequencySort(nums2))); // [1,3,3,2,2]

        int[] nums3 = {-1,1,-6,4,5,-6,1,4,1};
        System.out.println("Output: " + Arrays.toString(frequencySort(nums3))); // [5,-1,4,4,-6,-6,1,1,1]
    }
}
