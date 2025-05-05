package sorting;

import java.util.Arrays;

/*
Given the array nums, for each nums[i] find out how many numbers in the array
are smaller than it. That is, for each nums[i] you have to count the number
of valid j's such that j != i and nums[j] < nums[i].

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
public class NumbersSmallerThanCurrentNumbers {
    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3};
        int[] result = smallerNumbersThanCurrent(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output: [4,0,1,1,3]
        System.out.println();
        nums = new int[]{6, 5, 4, 8};
        result = smallerNumbersThanCurrent(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output: [2,1,0,3]
    }

    private static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        int[] sorted = Arrays.copyOf(nums, nums.length);

        mergeSort(sorted, 0, sorted.length - 1);

        for (int i = 0; i < nums.length; i++) {
            // Use binary search to find the first occurrence
            int index = binarySearch(sorted, nums[i]);
            // Handle duplicates by finding the first occurrence
            while (index > 0 && sorted[index - 1] == nums[i]) {
                index--;
            }
            result[i] = Math.max(index, 0);
        }

        return result;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // Return the index of the first element greater than or equal to target
    }
}
