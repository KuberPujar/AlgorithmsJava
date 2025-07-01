package hashtable.twopointer;

import java.util.*;

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
public class IntersectionOfArrays {
    public int[] intersectionHashSet(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();

        // Add all elements of nums1 to set1
        for (int num : nums1) {
            set1.add(num);
        }

        // Check which elements of nums2 exist in set1
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }

        // Convert set to array
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i++] = num;
        }

        return result;
    }

    // Solution 2: Using Two Pointers (for sorted arrays)
    public int[] intersectionTwoPointers(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> resultList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // Add to result if it's not a duplicate
                if (resultList.isEmpty() || nums1[i] != resultList.get(resultList.size() - 1)) {
                    resultList.add(nums1[i]);
                }
                i++;
                j++;
            }
        }

        // Convert list to array
        int[] result = new int[resultList.size()];
        for (int k = 0; k < resultList.size(); k++) {
            result[k] = resultList.get(k);
        }

        return result;
    }

    public static void main(String[] args) {
        IntersectionOfArrays solution = new IntersectionOfArrays();

        // Example 1
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(solution.intersectionHashSet(nums1, nums2))); // Output: [2]
        System.out.println(Arrays.toString(solution.intersectionTwoPointers(nums1, nums2))); // Output: [2]

        // Example 2
        nums1 = new int[]{4, 9, 5};
        nums2 = new int[]{9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(solution.intersectionHashSet(nums1, nums2))); // Output: [4, 9] or [9, 4]
        System.out.println(Arrays.toString(solution.intersectionTwoPointers(nums1, nums2))); // Output: [4, 9]
    }
}
