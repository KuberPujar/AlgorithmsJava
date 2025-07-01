package hashtable.sorting;

import java.util.Arrays;
import java.util.HashSet;

/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 */
public class IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        // Step 1: Store unique elements of nums1 in a HashSet
        HashSet<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // Step 2: Find intersection with nums2
        HashSet<Integer> intersection = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersection.add(num);
            }
        }

        // Step 3: Convert to array and sort
        int[] result = new int[intersection.size()];
        int i = 0;
        for (int num : intersection) {
            result[i++] = num;
        }
        Arrays.sort(result); // Sorting the result array

        return result;
    }

    public static void main(String[] args) {
        int[] nums1a = {1,2,2,1};
        int[] nums2a = {2,2};
        System.out.println("Output: " + Arrays.toString(intersection(nums1a, nums2a))); // [2]

        int[] nums1b = {4,9,5};
        int[] nums2b = {9,4,9,8,4};
        System.out.println("Output: " + Arrays.toString(intersection(nums1b, nums2b))); // [4, 9]
    }
}
