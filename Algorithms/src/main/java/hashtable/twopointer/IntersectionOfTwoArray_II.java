package hashtable.twopointer;

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
public class IntersectionOfTwoArray_II {
    /**
     * Hash Table Approach - O(n + m) time, O(min(n, m)) space
     * Uses frequency counting to find intersection
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // Optimize by using smaller array for hash map
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        // Count frequencies in the smaller array
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Find intersection by checking nums2 against frequency map
        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                result.add(num);
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        // Convert to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Two Pointer Approach (for sorted arrays) - O(n log n + m log m) time, O(1) space
     * More efficient when arrays are already sorted
     */
    public int[] intersectSorted(int[] nums1, int[] nums2) {
        // Sort both arrays first
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return result.stream().mapToInt(x -> x).toArray();
    }

    /**
     * Memory-efficient approach for when one array is much smaller
     * Uses the smaller array for hash map to minimize memory usage
     */
    public int[] intersectMemoryOptimized(int[] nums1, int[] nums2) {
        // Always use the smaller array for frequency counting
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] larger = nums1.length > nums2.length ? nums1 : nums2;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : smaller) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : larger) {
            if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                result.add(num);
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Streaming approach for large datasets (simulates disk-based processing)
     * Useful when nums2 is too large to fit in memory
     */
    public int[] intersectStreaming(int[] nums1, int[] nums2) {
        // Build frequency map for nums1 (assumed to be smaller)
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();

        // Process nums2 in chunks (simulating reading from disk)
        int chunkSize = 100; // Simulate memory constraint
        for (int start = 0; start < nums2.length; start += chunkSize) {
            int end = Math.min(start + chunkSize, nums2.length);

            // Process current chunk
            for (int i = start; i < end; i++) {
                int num = nums2[i];
                if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                    result.add(num);
                    freqMap.put(num, freqMap.get(num) - 1);
                }
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    // Test methods
    public static void main(String[] args) {
        IntersectionOfTwoArray_II solution = new IntersectionOfTwoArray_II();

        // Test Case 1
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("Test 1 - Hash Table: " + Arrays.toString(solution.intersect(nums1, nums2)));
        System.out.println("Test 1 - Two Pointer: " + Arrays.toString(solution.intersectSorted(nums1, nums2)));

        // Test Case 2
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        System.out.println("Test 2 - Hash Table: " + Arrays.toString(solution.intersect(nums3, nums4)));
        System.out.println("Test 2 - Two Pointer: " + Arrays.toString(solution.intersectSorted(nums3, nums4)));

        // Test Case 3 - Edge case with duplicates
        int[] nums5 = {1, 2, 2, 1, 3, 3};
        int[] nums6 = {2, 2, 3};
        System.out.println("Test 3 - Hash Table: " + Arrays.toString(solution.intersect(nums5, nums6)));

        // Performance comparison
        System.out.println("\n--- Performance Analysis ---");
        System.out.println("Hash Table Approach:");
        System.out.println("  Time: O(n + m), Space: O(min(n, m))");
        System.out.println("  Best when arrays are unsorted or size difference is small");

        System.out.println("\nTwo Pointer Approach (sorted):");
        System.out.println("  Time: O(n log n + m log m), Space: O(1)");
        System.out.println("  Best when arrays are already sorted");

        System.out.println("\nMemory-Optimized Approach:");
        System.out.println("  Always uses smaller array for hash map");
        System.out.println("  Best when one array is much smaller than the other");

        System.out.println("\nStreaming Approach:");
        System.out.println("  Processes large arrays in chunks");
        System.out.println("  Best when memory is limited or data is disk-based");
    }
}
