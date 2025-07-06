package heaps.prefixsum;

import java.util.Collections;
import java.util.PriorityQueue;

/*
You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.

The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).

Find the kth largest value (1-indexed) of all the coordinates of matrix.



Example 1:

Input: matrix = [[5,2],[1,6]], k = 1
Output: 7
Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
Example 2:

Input: matrix = [[5,2],[1,6]], k = 2
Output: 5
Explanation: The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
Example 3:

Input: matrix = [[5,2],[1,6]], k = 3
Output: 4
Explanation: The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
0 <= matrix[i][j] <= 106
1 <= k <= m * n
 */
public class FindKthLargestXORCoordinateValue {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Create prefix XOR matrix
        int[][] prefixXor = new int[m][n];

        // Build prefix XOR matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixXor[i][j] = matrix[i][j];

                // Add XOR from top cell
                if (i > 0) {
                    prefixXor[i][j] ^= prefixXor[i-1][j];
                }

                // Add XOR from left cell
                if (j > 0) {
                    prefixXor[i][j] ^= prefixXor[i][j-1];
                }

                // Remove double counted XOR from top-left cell
                if (i > 0 && j > 0) {
                    prefixXor[i][j] ^= prefixXor[i-1][j-1];
                }
            }
        }

        // Use min heap to maintain k largest values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process all coordinate values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (minHeap.size() < k) {
                    minHeap.offer(prefixXor[i][j]);
                } else if (prefixXor[i][j] > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(prefixXor[i][j]);
                }
            }
        }

        return minHeap.peek();
    }

    // Alternative approach using max heap (simpler but uses more memory)
    public int kthLargestValueMaxHeap(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Create prefix XOR matrix
        int[][] prefixXor = new int[m][n];

        // Build prefix XOR matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixXor[i][j] = matrix[i][j];

                if (i > 0) {
                    prefixXor[i][j] ^= prefixXor[i-1][j];
                }

                if (j > 0) {
                    prefixXor[i][j] ^= prefixXor[i][j-1];
                }

                if (i > 0 && j > 0) {
                    prefixXor[i][j] ^= prefixXor[i-1][j-1];
                }
            }
        }

        // Use max heap to store all values
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all coordinate values to max heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxHeap.offer(prefixXor[i][j]);
            }
        }

        // Extract k-1 largest values
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }

        return maxHeap.peek();
    }

    // Helper method to print prefix XOR matrix for debugging
    public void printPrefixXor(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] prefixXor = new int[m][n];

        System.out.println("Original matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixXor[i][j] = matrix[i][j];

                if (i > 0) {
                    prefixXor[i][j] ^= prefixXor[i-1][j];
                }

                if (j > 0) {
                    prefixXor[i][j] ^= prefixXor[i][j-1];
                }

                if (i > 0 && j > 0) {
                    prefixXor[i][j] ^= prefixXor[i-1][j-1];
                }
            }
        }

        System.out.println("\nPrefix XOR matrix (coordinate values):");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(prefixXor[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FindKthLargestXORCoordinateValue solution = new  FindKthLargestXORCoordinateValue();

        // Test case 1
        int[][] matrix1 = {{5, 2}, {1, 6}};
        System.out.println("Test case 1:");
        solution.printPrefixXor(matrix1);
        System.out.println("1st largest: " + solution.kthLargestValue(matrix1, 1)); // Expected: 7
        System.out.println("2nd largest: " + solution.kthLargestValue(matrix1, 2)); // Expected: 5
        System.out.println("3rd largest: " + solution.kthLargestValue(matrix1, 3)); // Expected: 4
        System.out.println("4th largest: " + solution.kthLargestValue(matrix1, 4)); // Expected: 0

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test case 2
        int[][] matrix2 = {{5, 2}, {1, 6}};
        System.out.println("Test case 2 (using max heap approach):");
        System.out.println("1st largest: " + solution.kthLargestValueMaxHeap(matrix2, 1)); // Expected: 7
        System.out.println("2nd largest: " + solution.kthLargestValueMaxHeap(matrix2, 2)); // Expected: 5

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test case 3 - larger matrix
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Test case 3 (3x3 matrix):");
        solution.printPrefixXor(matrix3);
        System.out.println("1st largest: " + solution.kthLargestValue(matrix3, 1));
        System.out.println("2nd largest: " + solution.kthLargestValue(matrix3, 2));
        System.out.println("3rd largest: " + solution.kthLargestValue(matrix3, 3));
    }
}
