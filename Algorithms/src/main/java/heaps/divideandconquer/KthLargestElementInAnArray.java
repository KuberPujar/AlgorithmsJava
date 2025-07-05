package heaps.divideandconquer;

import java.util.Random;

/*
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4


Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickselect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left);

        pivotIndex = partition(nums, left, right, pivotIndex);

        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickselect(nums, left, pivotIndex - 1, k);
        } else {
            return quickselect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i <= right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray solution = new KthLargestElementInAnArray();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = solution.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result); // Output: 5
    }
}
