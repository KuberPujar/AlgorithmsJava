package heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/*
K largest using Heap
Given an array of N positive integers, return the sum of k largest elements from the array using Heap.

Input Format:

First line contains two space separated integers representing the size of array and the number of largest elements from array whose sum needs to be figured out.
The second line contains N space-separated positive integers representing the elements of the array.
Output Format:

Print a single integer representing the sum of the K largest elements in the array.
Example 1

Sample Input:
5 3

3 11 2 4 6

Sample Output:
21
Explanation :
Since we have to find the sum of k largest which in this case is 3 and the elements are 11,6,4 whose sum is equal to 21, thus the output is 21.
Example 2
Sample Input
10 3
411 628 759 940 40 767 89 434 370 623

Sample output
2466

Explanation
Array: The given array is [411, 628, 759, 940, 40, 767, 89, 434, 370, 623].
The largest elements in the array are 940, 767, and 759.
The sum of these elements is 940 + 767 + 759 = 2466.
Constraints:

1 ≤ N ≤ 10^4

K ≤ N

1 ≤ array[i] ≤ 10^5

Note:The function should return the result. The driver code will handle printing the output.
 */
public class KthLargestUsingHeap {
    /**
     * Method 1: Using Min Heap (Most Efficient)
     * Maintains a min heap of size K with the K largest elements
     * Time: O(N log K), Space: O(K)
     *
     * @param n Size of array
     * @param k Number of largest elements needed
     * @param arr Input array
     * @return Sum of K largest elements
     */
    public static long findKLargestSumMinHeap(int n, int k, int[] arr) {
        // Min heap to store K largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if (minHeap.size() < k) {
                // If heap size is less than k, add element
                minHeap.offer(arr[i]);
            } else if (arr[i] > minHeap.peek()) {
                // If current element is larger than smallest in heap
                minHeap.poll(); // Remove smallest
                minHeap.offer(arr[i]); // Add current element
            }
        }

        // Calculate sum of K largest elements
        long sum = 0;
        while (!minHeap.isEmpty()) {
            sum += minHeap.poll();
        }

        return sum;
    }

    /**
     * Method 2: Using Max Heap
     * Extract K largest elements from max heap
     * Time: O(N + K log N), Space: O(N)
     *
     * @param n Size of array
     * @param k Number of largest elements needed
     * @param arr Input array
     * @return Sum of K largest elements
     */
    public static long findKLargestSumMaxHeap(int n, int k, int[] arr) {
        // Max heap to store all elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all elements to max heap
        for (int num : arr) {
            maxHeap.offer(num);
        }

        // Extract K largest elements and sum them
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += maxHeap.poll();
        }

        return sum;
    }

    /**
     * Method 3: Using Custom Max Heap Implementation
     * Demonstrates heap operations without built-in PriorityQueue
     */
    public static long findKLargestSumCustomHeap(int n, int k, int[] arr) {
        MaxHeap heap = new MaxHeap(n);

        // Build max heap
        for (int num : arr) {
            heap.insert(num);
        }

        // Extract K largest elements
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += heap.extractMax();
        }

        return sum;
    }

    /**
     * Method 4: Using Sorting (Alternative approach)
     * Time: O(N log N), Space: O(1)
     */
    public static long findKLargestSumSorting(int n, int k, int[] arr) {
        Arrays.sort(arr);
        long sum = 0;

        // Sum the last K elements (largest)
        for (int i = n - k; i < n; i++) {
            sum += arr[i];
        }

        return sum;
    }

    // Custom Max Heap Implementation
    static class MaxHeap {
        private int[] heap;
        private int size;
        private int capacity;

        public MaxHeap(int capacity) {
            this.capacity = capacity;
            this.heap = new int[capacity];
            this.size = 0;
        }

        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return 2 * i + 1; }
        private int rightChild(int i) { return 2 * i + 2; }

        public void insert(int value) {
            if (size >= capacity) return;

            heap[size] = value;
            int current = size;
            size++;

            // Heapify up
            while (current > 0 && heap[current] > heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
        }

        public int extractMax() {
            if (size <= 0) return -1;
            if (size == 1) {
                size--;
                return heap[0];
            }

            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);

            return max;
        }

        private void heapifyDown(int i) {
            int largest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size && heap[left] > heap[largest])
                largest = left;

            if (right < size && heap[right] > heap[largest])
                largest = right;

            if (largest != i) {
                swap(i, largest);
                heapifyDown(largest);
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    // Main solution method (most efficient approach)
    public static long solution(int n, int k, int[] arr) {
        return findKLargestSumMinHeap(n, k, arr);
    }

    // Test method
    public static void main(String[] args) {
        // Test Example 1
        System.out.println("=== Example 1 ===");
        int[] arr1 = {3, 11, 2, 4, 6};
        int n1 = 5, k1 = 3;

        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("N = " + n1 + ", K = " + k1);

        long result1_method1 = findKLargestSumMinHeap(n1, k1, arr1);
        long result1_method2 = findKLargestSumMaxHeap(n1, k1, arr1);
        long result1_method3 = findKLargestSumCustomHeap(n1, k1, arr1);
        long result1_method4 = findKLargestSumSorting(n1, k1, arr1);

        System.out.println("Min Heap Result: " + result1_method1);
        System.out.println("Max Heap Result: " + result1_method2);
        System.out.println("Custom Heap Result: " + result1_method3);
        System.out.println("Sorting Result: " + result1_method4);
        System.out.println("Expected: 21");
        System.out.println("K largest elements: [11, 6, 4]");
        System.out.println();

        // Test Example 2
        System.out.println("=== Example 2 ===");
        int[] arr2 = {411, 628, 759, 940, 40, 767, 89, 434, 370, 623};
        int n2 = 10, k2 = 3;

        System.out.println("Array: " + Arrays.toString(arr2));
        System.out.println("N = " + n2 + ", K = " + k2);

        long result2 = findKLargestSumMinHeap(n2, k2, arr2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: 2466");

        // Find and display the K largest elements for verification
        PriorityQueue<Integer> maxHeapTest = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr2) maxHeapTest.offer(num);

        System.out.print("K largest elements: [");
        for (int i = 0; i < k2; i++) {
            System.out.print(maxHeapTest.poll());
            if (i < k2 - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();

        // Performance comparison test
        System.out.println("=== Performance Test ===");
        int[] largeArr = new int[1000];
        Random rand = new Random(42);
        for (int i = 0; i < 1000; i++) {
            largeArr[i] = rand.nextInt(100000) + 1;
        }

        long startTime, endTime;

        // Min Heap approach
        startTime = System.nanoTime();
        long resultMinHeap = findKLargestSumMinHeap(1000, 100, largeArr);
        endTime = System.nanoTime();
        System.out.println("Min Heap (O(N log K)): " + (endTime - startTime) / 1000000.0 + " ms");

        // Max Heap approach
        startTime = System.nanoTime();
        long resultMaxHeap = findKLargestSumMaxHeap(1000, 100, largeArr);
        endTime = System.nanoTime();
        System.out.println("Max Heap (O(N + K log N)): " + (endTime - startTime) / 1000000.0 + " ms");

        // Sorting approach
        startTime = System.nanoTime();
        long resultSorting = findKLargestSumSorting(1000, 100, largeArr.clone());
        endTime = System.nanoTime();
        System.out.println("Sorting (O(N log N)): " + (endTime - startTime) / 1000000.0 + " ms");

        System.out.println("All methods produce same result: " +
                (resultMinHeap == resultMaxHeap && resultMaxHeap == resultSorting));
    }
}
