package hashtable.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.



Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105
 */
public class ContainsDuplicate_II {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            // Create a hash map to store the most recent index of each number
            Map<Integer, Integer> numIndices = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];

                // Check if we've seen this number before within the allowed window
                if (numIndices.containsKey(num) && i - numIndices.get(num) <= k) {
                    return true;
                }

                // Update the map with the current number's index
                numIndices.put(num, i);
            }

            return false;
        }

        // Alternative solution with sliding window optimization
        public boolean containsNearbyDuplicateSlidingWindow(int[] nums, int k) {
            // Handle edge case where k is 0
            if (k == 0) return false;

            Set<Integer> window = new HashSet<>();

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];

                // If the number exists in the current window, return true
                if (window.contains(num)) {
                    return true;
                }

                // Add the current number to the window
                window.add(num);

                // Maintain the window size of k
                if (i >= k) {
                    window.remove(nums[i - k]);
                }
            }

            return false;
        }

        public static void main(String[] args) {
            ContainsDuplicate_II solution = new ContainsDuplicate_II();

            // Example 1
            int[] nums1 = {1, 2, 3, 1};
            int k1 = 3;
            System.out.println(solution.containsNearbyDuplicate(nums1, k1)); // Output: true

            // Example 2
            int[] nums2 = {1, 0, 1, 1};
            int k2 = 1;
            System.out.println(solution.containsNearbyDuplicate(nums2, k2)); // Output: true

            // Example 3
            int[] nums3 = {1, 2, 3, 1, 2, 3};
            int k3 = 2;
            System.out.println(solution.containsNearbyDuplicate(nums3, k3)); // Output: false
        }
}
