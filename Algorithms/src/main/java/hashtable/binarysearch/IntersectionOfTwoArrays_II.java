package hashtable.binarysearch;

import java.util.*;

/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000


Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArrays_II {
    public int[] intersect(int[] nums1, int[] nums2) {
        // To optimize for nums1 being smaller, we'll process the smaller array first
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        // Sort both arrays to enable binary search
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();
        int left = 0;

        for (int num : nums1) {
            int index = binarySearch(nums2, left, num);
            if (index != -1) {
                result.add(num);
                left = index + 1;
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private int binarySearch(int[] nums, int left, int target) {
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                index = mid;
                right = mid - 1; // look for first occurrence
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }

    // Alternative solution using Hash Map (for unsorted arrays)
    public int[] intersectWithHashMap(int[] nums1, int[] nums2) {
        // To minimize space usage, process the smaller array first
        if (nums1.length > nums2.length) {
            return intersectWithHashMap(nums2, nums1);
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Count frequencies in nums1
        for (int num : nums1) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Check against nums2
        for (int num : nums2) {
            if (frequencyMap.containsKey(num) && frequencyMap.get(num) > 0) {
                result.add(num);
                frequencyMap.put(num, frequencyMap.get(num) - 1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays_II solution = new IntersectionOfTwoArrays_II();

        // Example 1
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(solution.intersect(nums1, nums2))); // [2, 2]

        // Example 2
        nums1 = new int[]{4, 9, 5};
        nums2 = new int[]{9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(solution.intersect(nums1, nums2))); // [4, 9]
    }
}
