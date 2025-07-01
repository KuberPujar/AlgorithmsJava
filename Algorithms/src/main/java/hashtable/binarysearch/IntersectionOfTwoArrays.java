package hashtable.binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // Step 2: Sort nums2 for binary search
        Arrays.sort(nums2);

        // Step 3: For each unique element in nums1, use binary search in nums2
        Set<Integer> resultSet = new HashSet<>();
        for (int num : set1) {
            if (Arrays.binarySearch(nums2, num) >= 0) {
                resultSet.add(num);
            }
        }

        // Step 4: Convert result set to array
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i++] = num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1a = {1,2,2,1};
        int[] nums2a = {2,2};
        System.out.println("Output: " + Arrays.toString(intersection(nums1a, nums2a))); // [2]

        int[] nums1b = {4,9,5};
        int[] nums2b = {9,4,9,8,4};
        System.out.println("Output: " + Arrays.toString(intersection(nums1b, nums2b))); // [4, 9] or [9, 4]
    }
}
