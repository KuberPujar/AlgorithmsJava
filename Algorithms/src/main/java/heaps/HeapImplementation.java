package heaps;

import java.util.Arrays;

/*
Heap Implementation
Implement a max heap from a given array of integers.

Input Format
The first line contains a single integer ( n ), representing the number of elements in the array.
The second line contains ( n ) space-separated integers representing the elements of the array.
Output Format
Return the elements of the array re-arranged into a max heap.
Constraints
1 <= n <= 10^4
1 <= arr[i] <= 10^4, where arr[i] is the i-th element of the array.
Sample Input 1
9
5 3 2 1 6 7 8 9 4
Sample Output 1
9 6 8 4 5 7 2 1 3
Explanation
The output represents the given array re-arranged into a max heap, where every parent node is greater than its child nodes.

Sample Input 2
5
1 2 3 4 5
Sample Output 2
5 4 3 1 2
Explanation
Start by comparing the elements from the last parent node to the root.
For the last parent node (index 1), the two child nodes are 4 and 5. Since 5 is larger than 2, we swap them.
Move up to the root node (index 0). The child nodes are 2 (which has been swapped with 5) and 3. The largest among them is 5, so we swap 5 with 1.
Continue this process until the array satisfies the max heap property, where each parent node is greater than or equal to its children.
Final Max Heap: The array [5, 4, 3, 1, 2] represents a max heap where the root is the largest element (5), and each parent node is greater than its child nodes.
Note: The function should return the result. The driver code will handle printing the output.
 */
public class HeapImplementation {
    /**
     * Builds a max heap from the given array
     * Uses bottom-up heapify approach for O(n) construction
     *
     * @param arr The input array to be converted to max heap
     * @return The array rearranged as a max heap
     */
    public static int[] buildMaxHeap(int[] arr) {
        int n = arr.length;

        // Start from the last parent node and heapify down
        // Last parent is at index (n/2 - 1)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i);
        }

        return arr;
    }

    /**
     * Maintains max heap property by moving element down
     *
     * @param arr The heap array
     * @param heapSize Size of the heap
     * @param rootIndex Index of the root of subtree to heapify
     */
    private static void heapifyDown(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int leftChild = 2 * rootIndex + 1;   // Left child index
        int rightChild = 2 * rootIndex + 2;  // Right child index

        // Check if left child exists and is greater than root
        if (leftChild < heapSize && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        // Check if right child exists and is greater than current largest
        if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        // If largest is not root, swap and recursively heapify
        if (largest != rootIndex) {
            swap(arr, rootIndex, largest);
            heapifyDown(arr, heapSize, largest);
        }
    }

    /**
     * Swaps two elements in the array
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Utility method to print heap structure (for visualization)
     */
    public static void printHeapStructure(int[] heap) {
        int n = heap.length;
        int level = 0;
        int elementsInLevel = 1;
        int currentIndex = 0;

        System.out.println("Heap Structure:");
        while (currentIndex < n) {
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < elementsInLevel && currentIndex < n; i++) {
                System.out.print(heap[currentIndex] + " ");
                currentIndex++;
            }
            System.out.println();

            level++;
            elementsInLevel *= 2;
        }
        System.out.println();
    }

    /**
     * Validates if array represents a valid max heap
     */
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n / 2; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            // Check left child
            if (leftChild < n && arr[i] < arr[leftChild]) {
                return false;
            }

            // Check right child
            if (rightChild < n && arr[i] < arr[rightChild]) {
                return false;
            }
        }
        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Sample 1
        System.out.println("=== Sample Test 1 ===");
        int[] arr1 = {5, 3, 2, 1, 6, 7, 8, 9, 4};
        System.out.println("Input: " + Arrays.toString(arr1));

        int[] heap1 = buildMaxHeap(arr1.clone());
        System.out.println("Output: " + Arrays.toString(heap1));
        System.out.println("Expected: [9, 6, 8, 4, 5, 7, 2, 1, 3]");
        System.out.println("Valid Max Heap: " + isValidMaxHeap(heap1));
        printHeapStructure(heap1);

        // Test Sample 2
        System.out.println("=== Sample Test 2 ===");
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(arr2));

        int[] heap2 = buildMaxHeap(arr2.clone());
        System.out.println("Output: " + Arrays.toString(heap2));
        System.out.println("Expected: [5, 4, 3, 1, 2]");
        System.out.println("Valid Max Heap: " + isValidMaxHeap(heap2));
        printHeapStructure(heap2);

        // Additional test cases
        System.out.println("=== Additional Tests ===");

        // Already a max heap
        int[] arr3 = {10, 8, 9, 4, 7, 5, 6, 1, 2, 3};
        System.out.println("Already Max Heap Test:");
        System.out.println("Input: " + Arrays.toString(arr3));
        int[] heap3 = buildMaxHeap(arr3.clone());
        System.out.println("Output: " + Arrays.toString(heap3));
        System.out.println("Valid Max Heap: " + isValidMaxHeap(heap3));
        System.out.println();

        // Single element
        int[] arr4 = {42};
        System.out.println("Single Element Test:");
        System.out.println("Input: " + Arrays.toString(arr4));
        int[] heap4 = buildMaxHeap(arr4.clone());
        System.out.println("Output: " + Arrays.toString(heap4));
        System.out.println("Valid Max Heap: " + isValidMaxHeap(heap4));
        System.out.println();

        // Reverse sorted (worst case)
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Reverse Sorted Test:");
        System.out.println("Input: " + Arrays.toString(arr5));
        int[] heap5 = buildMaxHeap(arr5.clone());
        System.out.println("Output: " + Arrays.toString(heap5));
        System.out.println("Valid Max Heap: " + isValidMaxHeap(heap5));
    }

    /**
     * Solution method as required by the problem
     * This is the main method that should be called
     */
    public static int[] solution(int n, int[] arr) {
        return buildMaxHeap(arr);
    }
}
