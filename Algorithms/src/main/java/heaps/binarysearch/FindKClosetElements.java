package heaps.binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3

Output: [1,2,3,4]

Example 2:

Input: arr = [1,1,2,3,4,5], k = 4, x = -1

Output: [1,1,2,3]



Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
 */
public class FindKClosetElements {

    // Custom class to represent an element with its value and distance from x
    static class Element implements Comparable<Element> {
        int value;
        int distance;

        Element(int value, int x) {
            this.value = value;
            this.distance = Math.abs(value - x);
        }

        // Compare elements for max heap
        // We want the element with larger distance (or larger value if distances are equal) at the top
        @Override
        public int compareTo(Element other) {
            if (this.distance != other.distance) {
                return Integer.compare(other.distance, this.distance); // Max heap by distance
            }
            return Integer.compare(other.value, this.value); // Max heap by value if distances are equal
        }
    }

    /**
     * Solution using Max Heap approach
     * Time Complexity: O(n log k) where n is array length
     * Space Complexity: O(k) for the heap
     */
    public static List<Integer> findClosestElementsHeap(int[] arr, int k, int x) {
        // Max heap to store k closest elements
        PriorityQueue<Element> maxHeap = new PriorityQueue<>();

        // Process each element in the array
        for (int num : arr) {
            Element element = new Element(num, x);

            if (maxHeap.size() < k) {
                // If heap is not full, add the element
                maxHeap.offer(element);
            } else {
                // If heap is full, compare with the top element
                Element top = maxHeap.peek();
                if (element.distance < top.distance ||
                        (element.distance == top.distance && element.value < top.value)) {
                    // Current element is closer, replace the top
                    maxHeap.poll();
                    maxHeap.offer(element);
                }
            }
        }

        // Extract elements from heap and sort them
        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll().value);
        }

        // Sort the result in ascending order
        Collections.sort(result);
        return result;
    }

    /**
     * Optimized solution using Binary Search + Two Pointers
     * Time Complexity: O(log n + k) where n is array length
     * Space Complexity: O(1) excluding result space
     */
    public static List<Integer> findClosestElementsOptimized(int[] arr, int k, int x) {
        // Binary search to find the position where x should be inserted
        int left = 0, right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare distances from x to arr[mid] and arr[mid + k]
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Build result from the optimal starting position
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    /**
     * Alternative heap solution using custom comparator
     */
    public static List<Integer> findClosestElementsHeapV2(int[] arr, int k, int x) {
        // Max heap with custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = Math.abs(a - x);
            int distB = Math.abs(b - x);

            if (distA != distB) {
                return Integer.compare(distB, distA); // Max heap by distance
            }
            return Integer.compare(b, a); // Max heap by value if distances are equal
        });

        // Add elements to heap
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the farthest element
            }
        }

        // Extract and sort result
        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }

        Collections.sort(result);
        return result;
    }

    // Helper method to print array nicely
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.print("]");
    }

    // Helper method to print list nicely
    public static void printList(List<Integer> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(",");
        }
        System.out.print("]");
    }

    // Method to demonstrate heap structure and operations
    public static void demonstrateHeapOperations(int[] arr, int k, int x) {
        System.out.println("=== Heap Operations Demo ===");
        System.out.print("Array: ");
        printArray(arr);
        System.out.println(", k = " + k + ", x = " + x);

        PriorityQueue<Element> maxHeap = new PriorityQueue<>();

        System.out.println("\nStep-by-step heap operations:");
        for (int i = 0; i < arr.length; i++) {
            Element element = new Element(arr[i], x);

            if (maxHeap.size() < k) {
                maxHeap.offer(element);
                System.out.printf("Added %d (distance: %d), heap size: %d%n",
                        element.value, element.distance, maxHeap.size());
            } else {
                Element top = maxHeap.peek();
                if (element.distance < top.distance ||
                        (element.distance == top.distance && element.value < top.value)) {
                    maxHeap.poll();
                    maxHeap.offer(element);
                    System.out.printf("Replaced %d (distance: %d) with %d (distance: %d)%n",
                            top.value, top.distance, element.value, element.distance);
                } else {
                    System.out.printf("Skipped %d (distance: %d) - not closer than top%n",
                            element.value, element.distance);
                }
            }
        }

        System.out.println("\nFinal heap contents:");
        List<Element> heapCopy = new ArrayList<>(maxHeap);
        heapCopy.sort((a, b) -> Integer.compare(a.value, b.value));
        for (Element e : heapCopy) {
            System.out.printf("Value: %d, Distance: %d%n", e.value, e.distance);
        }
    }

    public static void main(String[] args) {
        // Test Example 1
        int[] arr1 = {1, 2, 3, 4, 5};
        int k1 = 4, x1 = 3;

        System.out.println("Example 1:");
        System.out.print("Input: arr = ");
        printArray(arr1);
        System.out.println(", k = " + k1 + ", x = " + x1);

        List<Integer> result1 = findClosestElementsHeap(arr1, k1, x1);
        System.out.print("Output (Heap): ");
        printList(result1);
        System.out.println();

        List<Integer> result1Opt = findClosestElementsOptimized(arr1, k1, x1);
        System.out.print("Output (Optimized): ");
        printList(result1Opt);
        System.out.println();
        System.out.println("Expected: [1,2,3,4]");
        System.out.println();

        // Test Example 2
        int[] arr2 = {1, 1, 2, 3, 4, 5};
        int k2 = 4, x2 = -1;

        System.out.println("Example 2:");
        System.out.print("Input: arr = ");
        printArray(arr2);
        System.out.println(", k = " + k2 + ", x = " + x2);

        List<Integer> result2 = findClosestElementsHeap(arr2, k2, x2);
        System.out.print("Output (Heap): ");
        printList(result2);
        System.out.println();

        List<Integer> result2Opt = findClosestElementsOptimized(arr2, k2, x2);
        System.out.print("Output (Optimized): ");
        printList(result2Opt);
        System.out.println();
        System.out.println("Expected: [1,1,2,3]");
        System.out.println();

        // Test Example 3 - Edge case
        int[] arr3 = {0, 0, 1, 2, 3, 3, 4, 7, 7, 8};
        int k3 = 3, x3 = 5;

        System.out.println("Example 3:");
        System.out.print("Input: arr = ");
        printArray(arr3);
        System.out.println(", k = " + k3 + ", x = " + x3);

        List<Integer> result3 = findClosestElementsHeap(arr3, k3, x3);
        System.out.print("Output (Heap): ");
        printList(result3);
        System.out.println();

        List<Integer> result3Opt = findClosestElementsOptimized(arr3, k3, x3);
        System.out.print("Output (Optimized): ");
        printList(result3Opt);
        System.out.println();
        System.out.println();

        // Demonstrate heap operations
        demonstrateHeapOperations(arr1, k1, x1);

        // Performance comparison
        System.out.println("\n=== Performance Analysis ===");
        System.out.println("Heap Solution:");
        System.out.println("  Time Complexity: O(n log k)");
        System.out.println("  Space Complexity: O(k)");
        System.out.println("  Good for: When k is small compared to n");
        System.out.println();
        System.out.println("Optimized Solution (Binary Search + Two Pointers):");
        System.out.println("  Time Complexity: O(log n + k)");
        System.out.println("  Space Complexity: O(1)");
        System.out.println("  Good for: When array is sorted (like in this problem)");
        System.out.println();
        System.out.println("Binary Tree (Heap) Properties:");
        System.out.println("  - Complete binary tree stored in array");
        System.out.println("  - Parent at index i, children at 2i+1 and 2i+2");
        System.out.println("  - Max heap: parent >= children");
        System.out.println("  - Insertion/deletion: O(log k)");
    }
}
