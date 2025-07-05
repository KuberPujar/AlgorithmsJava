package heaps.simpleheap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:

Choose any piles[i] and remove ceil(piles[i] / 2) stones from it.
Notice that you can apply the operation on the same pile more than once.

Return the minimum possible total number of stones remaining after applying the k operations.

ceil(x) is the smallest integer that is greater than or equal to x (i.e., rounds x up).



Example 1:

Input: piles = [5,4,9], k = 2
Output: 12
Explanation: Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [5,4,5].
- Apply the operation on pile 0. The resulting piles are [3,4,5].
The total number of stones in [3,4,5] is 12.
Example 2:

Input: piles = [4,3,6,7], k = 3
Output: 12
Explanation: Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [4,3,3,7].
- Apply the operation on pile 3. The resulting piles are [4,3,3,4].
- Apply the operation on pile 0. The resulting piles are [2,3,3,4].
The total number of stones in [2,3,3,4] is 12.


Constraints:

1 <= piles.length <= 105
1 <= piles[i] <= 104
1 <= k <= 105
 */
public class RemoveStonesToMinimizeTools {

    public int minStoneSum(int[] piles, int k) {
        // Create a max heap using PriorityQueue with reverse order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all piles to the heap and calculate initial sum
        int totalSum = 0;
        for (int pile : piles) {
            maxHeap.offer(pile);
            totalSum += pile;
        }

        // Apply k operations
        for (int i = 0; i < k; i++) {
            // Get the largest pile
            int largest = maxHeap.poll();

            // The operation leaves ceil(largest / 2) stones
            // So we remove: largest - ceil(largest / 2)
            int remaining = (int) Math.ceil(largest / 2.0);
            int toRemove = largest - remaining;

            // Update total sum
            totalSum -= toRemove;

            // Put the remaining stones back in heap
            maxHeap.offer(remaining);
        }

        return totalSum;
    }

    // Test method
    public static void main(String[] args) {
        RemoveStonesToMinimizeTools sol = new RemoveStonesToMinimizeTools();

        // Example 1: piles = [5,4,9], k = 2
        int[] piles1 = {5, 4, 9};
        int k1 = 2;
        System.out.println("Example 1:");
        System.out.println("Input: piles = " + Arrays.toString(piles1) + ", k = " + k1);
        System.out.println("Output: " + sol.minStoneSum(piles1, k1));
        System.out.println("Expected: 12");
        System.out.println();

        // Example 2: piles = [4,3,6,7], k = 3
        int[] piles2 = {4, 3, 6, 7};
        int k2 = 3;
        System.out.println("Example 2:");
        System.out.println("Input: piles = " + Arrays.toString(piles2) + ", k = " + k2);
        System.out.println("Output: " + sol.minStoneSum(piles2, k2));
        System.out.println("Expected: 12");
        System.out.println();

        // Additional test case
        int[] piles3 = {10};
        int k3 = 1;
        System.out.println("Additional test:");
        System.out.println("Input: piles = " + Arrays.toString(piles3) + ", k = " + k3);
        System.out.println("Output: " + sol.minStoneSum(piles3, k3));
        System.out.println("Expected: 5");
    }
}
