package heaps;

import java.util.*;

/*
Stone Smash
You have an array called stones containing integer values, where each value represents the weight of a stone.

The objective of the game is to smash stones together until there is at most one stone remaining.

During each turn, you select the two heaviest stones and smash them. If the two stones have the same weight, both are destroyed. Otherwise, the lighter stone is destroyed, and the heavier stone's weight is updated to the difference between their weights.

Your task is to determine the weight of the last remaining stone after all possible smashing operations are completed. If there are no stones remaining, return 0.

Input Format
The first line contains a single integer n, representing the number of elements in the array.
The second line contains n space-separated integers representing the elements of the array.
Output Format
Print a single integer representing the difference between the maximum and minimum element of the array.
Examples:

Input 1:

 4
 5 10 15 20

Output 1:

0
Explanation:

The two heaviest stones 20 and 15 are smashed, leaving a stone of weight 5. The remaining stones are 10, 5, 5. After further smashing, all stones cancel out, resulting in no remaining stone, so the output is 0.
Input 2:

1
5

Output 2:

 5
Explanation:

With only one stone 5, no smashing occurs, so the output is the stone's original weight, which is 5.
Constraints:
1 <= stones.length <= 30
1 <= stones[i] <= 1000

Note:The function should return the result. The driver code will handle printing the output.
 */
public class StoneSmash {

    /**
     * Solves the stone smash problem using a max heap
     * @param stones array of stone weights
     * @return weight of last remaining stone, or 0 if no stones remain
     */
    public static int lastStoneWeight(int[] stones) {
        // Create a max heap using PriorityQueue with reverse order comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all stones to the max heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Continue smashing until at most one stone remains
        while (maxHeap.size() > 1) {
            // Extract the two heaviest stones
            int heaviest = maxHeap.poll();
            int secondHeaviest = maxHeap.poll();

            // If they have different weights, add the difference back to heap
            if (heaviest != secondHeaviest) {
                int difference = heaviest - secondHeaviest;
                maxHeap.offer(difference);
            }
            // If they have the same weight, both are destroyed (nothing added back)
        }

        // Return the last remaining stone weight, or 0 if no stones remain
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    /**
     * Alternative implementation with detailed step-by-step tracking
     */
    public static int lastStoneWeightWithSteps(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize heap with all stones
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        int step = 1;
        System.out.println("Initial stones: " + Arrays.toString(stones));
        System.out.println("Max heap: " + maxHeap);

        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();

            System.out.println("\nStep " + step + ":");
            System.out.println("Smashing stones: " + stone1 + " and " + stone2);

            if (stone1 == stone2) {
                System.out.println("Both stones destroyed (same weight)");
            } else {
                int newStone = stone1 - stone2;
                maxHeap.offer(newStone);
                System.out.println("New stone with weight " + newStone + " added");
            }

            System.out.println("Remaining stones: " + maxHeap);
            step++;
        }

        int result = maxHeap.isEmpty() ? 0 : maxHeap.peek();
        System.out.println("\nFinal result: " + result);
        return result;
    }

    /**
     * Optimized version using ArrayList and manual heap operations
     * (for educational purposes to show heap operations)
     */
    public static int lastStoneWeightManualHeap(int[] stones) {
        List<Integer> heap = new ArrayList<>();

        // Build max heap manually
        for (int stone : stones) {
            insertIntoMaxHeap(heap, stone);
        }

        while (heap.size() > 1) {
            int max1 = extractMaxFromHeap(heap);
            int max2 = extractMaxFromHeap(heap);

            if (max1 != max2) {
                insertIntoMaxHeap(heap, max1 - max2);
            }
        }

        return heap.isEmpty() ? 0 : heap.get(0);
    }

    // Helper method to insert into max heap
    private static void insertIntoMaxHeap(List<Integer> heap, int value) {
        heap.add(value);
        heapifyUp(heap, heap.size() - 1);
    }

    // Helper method to extract max from heap
    private static int extractMaxFromHeap(List<Integer> heap) {
        if (heap.isEmpty()) return 0;

        int max = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(heap, 0);
        }

        return max;
    }

    // Heapify up operation for max heap
    private static void heapifyUp(List<Integer> heap, int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) <= heap.get(parentIndex)) {
                break;
            }
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
        }
    }

    // Heapify down operation for max heap
    private static void heapifyDown(List<Integer> heap, int index) {
        int size = heap.size();

        while (true) {
            int largest = index;
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            if (leftChild < size && heap.get(leftChild) > heap.get(largest)) {
                largest = leftChild;
            }

            if (rightChild < size && heap.get(rightChild) > heap.get(largest)) {
                largest = rightChild;
            }

            if (largest == index) {
                break;
            }

            Collections.swap(heap, index, largest);
            index = largest;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case 1
        int[] stones1 = {5, 10, 15, 20};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: " + Arrays.toString(stones1));
        System.out.println("Expected: 0");
        System.out.println("Result: " + lastStoneWeight(stones1));

        System.out.println("\n=== Detailed Steps for Test Case 1 ===");
        lastStoneWeightWithSteps(stones1.clone());

        // Test case 2
        int[] stones2 = {5};
        System.out.println("\n\n=== Test Case 2 ===");
        System.out.println("Input: " + Arrays.toString(stones2));
        System.out.println("Expected: 5");
        System.out.println("Result: " + lastStoneWeight(stones2));

        // Additional test cases
        int[] stones3 = {2, 7, 4, 1, 8, 1};
        System.out.println("\n=== Additional Test Case ===");
        System.out.println("Input: " + Arrays.toString(stones3));
        System.out.println("Result: " + lastStoneWeight(stones3));

        // Test manual heap implementation
        System.out.println("\n=== Manual Heap Implementation Test ===");
        System.out.println("Manual heap result: " + lastStoneWeightManualHeap(stones1.clone()));

        // Performance comparison
        System.out.println("\n=== Performance Test ===");
        int[] largeStones = new int[30];
        Random rand = new Random(42);
        for (int i = 0; i < largeStones.length; i++) {
            largeStones[i] = rand.nextInt(1000) + 1;
        }

        long startTime = System.nanoTime();
        int result = lastStoneWeight(largeStones);
        long endTime = System.nanoTime();

        System.out.println("Large array (30 stones) result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
