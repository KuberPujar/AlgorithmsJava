package hashmap;

import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubarray {
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Initialize with sum 0 at index -1
        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Treat 0 as -1 and 1 as 1
            sum += (nums[i] == 0) ? -1 : 1;

            if (map.containsKey(sum)) {
                // If the sum has been seen before, update maxLen
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                // Store the first occurrence of this sum
                map.put(sum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Example usage
        int[] nums1 = {0, 1, 0};
        System.out.println(findMaxLength(nums1)); // Output: 2

        int[] nums2 = {0, 1, 1, 0, 1, 1, 0};
        System.out.println(findMaxLength(nums2)); // Output: 4
    }
}
