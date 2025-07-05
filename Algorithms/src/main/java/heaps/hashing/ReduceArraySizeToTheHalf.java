package heaps.hashing;

import java.util.*;

/*
You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.



Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.


Constraints:

2 <= arr.length <= 105
arr.length is even.
1 <= arr[i] <= 105
 */
public class ReduceArraySizeToTheHalf {
    /**
     * Calculates the minimum size of the set of integers to remove.
     *
     * @param arr The input integer array.
     * @return The minimum size of the set.
     */
    public int minSetSize(int[] arr) {
        // Step 1: Count the frequency of each integer in the array using a HashMap.
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a max-heap (PriorityQueue) to store the frequencies.
        // This allows us to always process the most frequent numbers first.
        // We use Collections.reverseOrder() to configure the PriorityQueue as a max-heap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(frequencyMap.values());

        // Step 3: Determine the target number of elements to remove.
        int targetSize = arr.length / 2;
        int removedCount = 0;
        int setSize = 0;

        // Step 4: Greedily remove elements by choosing the most frequent ones.
        // Pull the highest frequency from the heap, add it to our removed count,
        // and increment the size of our removal set.
        while (removedCount < targetSize) {
            // Get the frequency of the most common element.
            int frequency = maxHeap.poll();

            // Add this frequency to the total count of removed elements.
            removedCount += frequency;

            // Increment the size of our set of numbers to remove.
            setSize++;
        }

        // The loop terminates once we have removed at least half the elements.
        // The value of setSize is our answer.
        return setSize;
    }

    /**
     * Main method for testing the solution with the provided examples.
     */
    public static void main(String[] args) {
        ReduceArraySizeToTheHalf sol = new ReduceArraySizeToTheHalf();

        // Example 1
        int[] arr1 = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        int result1 = sol.minSetSize(arr1);
        System.out.println("Example 1 Input: arr = " + Arrays.toString(arr1));
        System.out.println("Example 1 Output: " + result1); // Expected: 2

        // Example 2
        int[] arr2 = {7, 7, 7, 7, 7, 7};
        int result2 = sol.minSetSize(arr2);
        System.out.println("Example 2 Input: arr = " + Arrays.toString(arr2));
        System.out.println("Example 2 Output: " + result2); // Expected: 1

        // Example 3
        int[] arr3 = {1, 9};
        int result3 = sol.minSetSize(arr3);
        System.out.println("Example 3 Input: arr = " + Arrays.toString(arr3));
        System.out.println("Example 3 Output: " + result3); // Expected: 1

        // Example 4
        int[] arr4 = {1000, 1000, 3, 7};
        int result4 = sol.minSetSize(arr4);
        System.out.println("Example 4 Input: arr = " + Arrays.toString(arr4));
        System.out.println("Example 4 Output: " + result4); // Expected: 1
    }
}
