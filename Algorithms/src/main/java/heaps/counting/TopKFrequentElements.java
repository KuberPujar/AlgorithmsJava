package heaps.counting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {

        public int[] topKFrequent(int[] nums, int k) {
            // Step 1: Count frequency of each element
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }

            // Step 2: Use a min-heap to keep track of top k frequent elements
            // We use a min-heap of size k, so we can efficiently remove the least frequent
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                    (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
            );

            // Step 3: Process each unique element
            for (int num : frequencyMap.keySet()) {
                minHeap.offer(num);
                // If heap size exceeds k, remove the least frequent element
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }

            // Step 4: Extract elements from heap to result array
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = minHeap.poll();
            }

            return result;
        }

        // Alternative approach using max-heap (less efficient for large datasets)
        public int[] topKFrequentMaxHeap(int[] nums, int k) {
            // Count frequencies
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }

            // Use max-heap to get top k elements
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                    (a, b) -> frequencyMap.get(b) - frequencyMap.get(a)
            );

            maxHeap.addAll(frequencyMap.keySet());

            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = maxHeap.poll();
            }

            return result;
        }

        // Test the solution
        public static void main(String[] args) {
            TopKFrequentElements solution = new TopKFrequentElements();

            // Test Example 1
            int[] nums1 = {1, 1, 1, 2, 2, 3};
            int k1 = 2;
            int[] result1 = solution.topKFrequent(nums1, k1);
            System.out.println("Example 1 - Input: " + Arrays.toString(nums1) + ", k = " + k1);
            System.out.println("Output: " + Arrays.toString(result1));

            // Test Example 2
            int[] nums2 = {1};
            int k2 = 1;
            int[] result2 = solution.topKFrequent(nums2, k2);
            System.out.println("Example 2 - Input: " + Arrays.toString(nums2) + ", k = " + k2);
            System.out.println("Output: " + Arrays.toString(result2));

            // Additional test case
            int[] nums3 = {4, 1, -1, 2, -1, 2, 3};
            int k3 = 2;
            int[] result3 = solution.topKFrequent(nums3, k3);
            System.out.println("Additional test - Input: " + Arrays.toString(nums3) + ", k = " + k3);
            System.out.println("Output: " + Arrays.toString(result3));
        }
    }

/*
Algorithm Explanation:

1. **Frequency Counting**:
   - Use HashMap to count frequency of each element
   - Time: O(n), Space: O(n)

2. **Min-Heap Approach**:
   - Use a min-heap of size k
   - For each unique element, add to heap
   - If heap size > k, remove minimum (least frequent)
   - Time: O(n log k), Space: O(k)

3. **Why Min-Heap over Max-Heap?**:
   - Min-heap of size k is more efficient than max-heap of size n
   - We only need to maintain k elements, not all unique elements

Time Complexity: O(n log k) where n is array length*/
