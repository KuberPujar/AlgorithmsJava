package heaps.matrix;

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
public class KthLargestXORCordinateValue {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Create a 2D array to store XOR values
        int[][] xor = new int[m][n];

        // Use a min-heap to keep track of top k elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    xor[i][j] = matrix[i][j];
                } else if (i == 0) {
                    xor[i][j] = xor[i][j-1] ^ matrix[i][j];
                } else if (j == 0) {
                    xor[i][j] = xor[i-1][j] ^ matrix[i][j];
                } else {
                    xor[i][j] = xor[i-1][j] ^ xor[i][j-1] ^ xor[i-1][j-1] ^ matrix[i][j];
                }

                // Add to heap
                minHeap.offer(xor[i][j]);
                // If heap size exceeds k, remove the smallest element
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        // The root of the heap is the k-th largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestXORCordinateValue solution = new KthLargestXORCordinateValue();
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 1;
        System.out.println(solution.kthLargestValue(matrix, k)); // Output: 7

        k = 2;
        System.out.println(solution.kthLargestValue(matrix, k)); // Output: 5

        k = 3;
        System.out.println(solution.kthLargestValue(matrix, k)); // Output: 4
    }

}
