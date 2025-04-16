package arrays.binarysearch;
/*
You are given a 0-indexed integer array nums and a target element target.

A target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.



Example 1:

Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]
Explanation: After sorting, nums is [1,2,2,3,5].
The indices where nums[i] == 2 are 1 and 2.
Example 2:

Input: nums = [1,2,5,2,3], target = 3
Output: [3]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 3 is 3.
Example 3:

Input: nums = [1,2,5,2,3], target = 5
Output: [4]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 5 is 4.


Constraints:

1 <= nums.length <= 100
1 <= nums[i], target <= 100
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTargetIndicesAfterSorting {
    public static List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);

        // Find first and last occurrence of target using binary search
        int first = findFirst(nums, target);
        if (first == -1) return result; // target not found

        int last = findLast(nums, target);

        // Add all indices from first to last
        for (int i = first; i <= last; i++) {
            result.add(i);
        }

        return result;
    }

    private static int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int first = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if (nums[mid] == target) {
                first = mid;
            }
        }

        return first;
    }

    private static int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int last = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

            if (nums[mid] == target) {
                last = mid;
            }
        }

        return last;
    }

    public static void main(String[] args) {

        // Test cases
        System.out.println(targetIndices(new int[]{1, 2, 5, 2, 3}, 2)); // [1, 2]
        System.out.println(targetIndices(new int[]{1, 2, 5, 2, 3}, 3)); // [3]
        System.out.println(targetIndices(new int[]{1, 2, 5, 2, 3}, 5)); // [4]
        System.out.println(targetIndices(new int[]{1, 2, 3, 4, 5}, 6)); // []
        System.out.println(targetIndices(new int[]{2, 2, 2, 2, 2}, 2)); // [0, 1, 2, 3, 4]
    }
}