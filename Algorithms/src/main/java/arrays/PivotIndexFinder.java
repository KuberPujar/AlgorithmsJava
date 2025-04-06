package arrays;

public class PivotIndexFinder {
    public static int pivotIndex(int[] nums) {
        int totalSum = 0;
        // Calculate total sum of all elements
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // Right sum = total sum - left sum - current element
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i; // Found pivot index
            }
            leftSum += nums[i];
        }

        return -1; // No pivot index found
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println("Pivot index for [1,7,3,6,5,6]: " + pivotIndex(nums1));

        int[] nums2 = {1, 2, 3};
        System.out.println("Pivot index for [1,2,3]: " + pivotIndex(nums2));

        int[] nums3 = {2, 1, -1};
        System.out.println("Pivot index for [2,1,-1]: " + pivotIndex(nums3));
    }
}