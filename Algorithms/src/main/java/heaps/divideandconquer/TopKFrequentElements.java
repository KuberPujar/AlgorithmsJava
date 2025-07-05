package heaps.divideandconquer;

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
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Convert the frequency map to an array of entries
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        // Step 3: Use quickselect to find the top k frequent elements
        quickselect(entries, 0, entries.size() - 1, k);

        // Step 4: Extract the top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(entries.size() - 1 - i).getKey();
        }

        return result;
    }

    private void quickselect(List<Map.Entry<Integer, Integer>> entries, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(entries, left, right);
        int target = entries.size() - k;
        if (pivotIndex == target) {
            return;
        } else if (pivotIndex < target) {
            quickselect(entries, pivotIndex + 1, right, k);
        } else {
            quickselect(entries, left, pivotIndex - 1, k);
        }
    }

    private int partition(List<Map.Entry<Integer, Integer>> entries, int left, int right) {
        int pivotFrequency = entries.get(right).getValue();
        int i = left;
        for (int j = left; j < right; j++) {
            if (entries.get(j).getValue() < pivotFrequency) {
                Collections.swap(entries, i, j);
                i++;
            }
        }
        Collections.swap(entries, i, right);
        return i;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = solution.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result)); // Output: [1, 2] or [2, 1]
    }
}
