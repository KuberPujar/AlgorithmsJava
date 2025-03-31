package sorting;

public class SortColors {
    public static void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    // Swap with low pointer
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    // Just move mid pointer
                    mid++;
                    break;
                case 2:
                    // Swap with high pointer
                    swap(nums, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // Sample Input 1
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sortColors(nums1);
        System.out.println(java.util.Arrays.toString(nums1)); // Output: [0, 0, 1, 1, 2, 2]

        // Sample Input 2
        int[] nums2 = {2, 0, 1};
        sortColors(nums2);
        System.out.println(java.util.Arrays.toString(nums2)); // Output: [0, 1, 2]
    }
}