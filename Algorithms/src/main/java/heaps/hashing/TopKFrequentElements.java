package heaps.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a bucket where the index represents frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int num : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(num);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(num);
        }

        // Step 3: Extract the top k frequent elements
        List<Integer> resultList = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && resultList.size() < k; i--) {
            if (buckets[i] != null) {
                resultList.addAll(buckets[i]);
            }
        }

        // Convert the list to an array, handling cases where we might have added more than k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        // Example 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] result1 = solution.topKFrequent(nums1, k1);
        System.out.print("Example 1 Output: ");
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example 2
        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = solution.topKFrequent(nums2, k2);
        System.out.print("Example 2 Output: ");
        for (int num : result2) {
            System.out.print(num + " ");
        }
    }
}
