package hashtable.divideandconquer;

import java.util.*;

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
        // Step 1: Count frequencies using a hash map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Convert the frequency map to an array of entries
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        // Step 2: Use QuickSelect to find the k most frequent elements
        quickSelect(entries, 0, entries.size() - 1, k);

        // Step 3: Extract the top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }

        return result;
    }

    private void quickSelect(List<Map.Entry<Integer, Integer>> entries, int left, int right, int k) {
        if (left >= right) return;

        int pivotIndex = partition(entries, left, right);

        if (pivotIndex == k) {
            return;
        } else if (pivotIndex < k) {
            quickSelect(entries, pivotIndex + 1, right, k);
        } else {
            quickSelect(entries, left, pivotIndex - 1, k);
        }
    }

    private int partition(List<Map.Entry<Integer, Integer>> entries, int left, int right) {
        // Choose pivot (middle element)
        int pivotIndex = left + (right - left) / 2;
        int pivotFrequency = entries.get(pivotIndex).getValue();

        // Move pivot to end
        Collections.swap(entries, pivotIndex, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (entries.get(i).getValue() > pivotFrequency) {
                Collections.swap(entries, i, storeIndex);
                storeIndex++;
            }
        }

        // Move pivot to its final place
        Collections.swap(entries, storeIndex, right);

        return storeIndex;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = solution.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result)); // Output: [1, 2] or [2, 1]
    }
}
