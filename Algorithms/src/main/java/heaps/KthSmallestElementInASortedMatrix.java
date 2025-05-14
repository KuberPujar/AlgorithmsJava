package heaps;

import java.util.PriorityQueue;

/*
Kth Smallest Element in a Sorted Matrix

Description:

Given an n x n matrix where each row and column is sorted in non-decreasing order, find the kth smallest element in the matrix. Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Input:

An n x n matrix where 1 <= n <= 300.

All elements of the matrix are integers within the range [1, 10^9].

1 <= k <= n^2.

Output:

Return the kth smallest element in the matrix.

Example:

Input:

matrix =

[ [ 1, 5, 9],

[10, 11, 13],

[12, 13, 15] ],

k = 8

Output: 13

Explanation:

The 8th smallest element in the matrix is 13.

Constraints:

The matrix is guaranteed to have distinct integers.

You may assume that the input matrix is always valid.

The function should return the result.
 */
public class KthSmallestElementInASortedMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Create a min-heap to store elements.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all elements from the matrix to the min-heap.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minHeap.add(matrix[i][j]);
            }
        }

        // Poll the k-1 smallest elements from the heap.
        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }

        // The kth smallest element is now at the top of the heap.
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        int result = kthSmallest(matrix, k);
        System.out.println("The kth smallest element is: " + result); // Output: 13
    }
}
