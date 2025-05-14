package heaps;

import java.util.PriorityQueue;

/*
Light War
We are given an array of integers stones where stones[i] represents the weight of the (i^th) stone. The task is to simulate a game with the following rules:

On each turn, pick the two lightest stones and smash them together.
If the weights (x) and (y) ((x <= y)) are equal:
Both stones are destroyed.
If (x!=y):
The stone with weight (x) is destroyed.
The stone with weight (y) is updated to (y - x).
Repeat this process until at most one stone remains.
At the end of the game:

Return the weight of the last remaining stone.
If no stones remain, return 0.
Input Format:

An integer (n), the number of stones.
An array of integers nums of size (n), where each element represents the weight of a stone.
Output Format: A single integer representing the weight of the last remaining stone or (0) if no stones remain.

Examples:

Sample Input 1:

n = 6
nums = [2, 7, 1, 8, 1, 4]
Sample Output 1:

3
Explanation:

Smash 1 (lightest) and 1 (second lightest). Both are destroyed. Remaining stones: [2, 7, 8, 4].
Smash 2 and 4. Result: [2, 7, 8].
Smash 2 and 7. Result: [5, 8].
Smash 5 and 8. Result: [3].
Only one stone remains with weight 3.
Sample Input 2:

n = 4
nums = [10, 4, 6, 8]
Sample Output 2:

4
Explanation:

Smash 4 and 6. Result: [2, 8, 10].
Smash 2 and 8. Result: [6, 10].
Smash 6 and 10. Result: [4].
Only one stone remains with weight 4.
Constraints:

(1 <= stones.length <= 30)
(1 <= stones[i] <= 1000)
Notes:The function should return the result.
 */
public class LightWars {
    public static int findLastStoneWeight(int[] stones) {
        // Create a min-heap using PriorityQueue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all stones to the min-heap
        for (int stone : stones) {
            minHeap.add(stone);
        }

        // Simulate the game until at most one stone remains
        while (minHeap.size() > 1) {
            // Get the two lightest stones
            int x = minHeap.poll();
            int y = minHeap.poll();

            // If the stones have different weights, calculate the difference and add it back to the heap
            if (x != y) {
                minHeap.add(Math.abs(y - x));
            }
            // If they are equal, do nothing (both are destroyed)
        }

        // If there is a stone left, return its weight; otherwise, return 0
        return minHeap.isEmpty() ? 0 : minHeap.poll();
    }

    public static void main(String[] args) {
        // Example usage
        int[] stones1 = {2, 7, 1, 8, 1, 4};
        System.out.println("Last stone weight for stones1: " + findLastStoneWeight(stones1)); // Output: 3

        int[] stones2 = {10, 4, 6, 8};
        System.out.println("Last stone weight for stones2: " + findLastStoneWeight(stones2)); // Output: 4
    }
}
