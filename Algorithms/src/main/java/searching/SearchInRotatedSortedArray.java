package searching;

import java.util.Scanner;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        sc.close();

        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // Find the pivot point where the array is rotated
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int pivot = left;
        left = 0;
        right = nums.length - 1;

        // Determine which subarray to search
        if (target >= nums[pivot] && target <= nums[right]) {
            left = pivot;
        } else {
            right = pivot - 1;
        }

        // Perform binary search in the determined subarray
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Target not found
    }
}
