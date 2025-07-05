package heaps.matrix;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.

A row i is weaker than a row j if one of the following is true:

The number of soldiers in row i is less than the number of soldiers in row j.
Both rows have the same number of soldiers and i < j.
Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.



Example 1:

Input: mat =
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]],
k = 3
Output: [2,0,3]
Explanation:
The number of soldiers in each row is:
- Row 0: 2
- Row 1: 4
- Row 2: 1
- Row 3: 2
- Row 4: 5
The rows ordered from weakest to strongest are [2,0,3,1,4].
Example 2:

Input: mat =
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]],
k = 2
Output: [0,2]
Explanation:
The number of soldiers in each row is:
- Row 0: 1
- Row 1: 4
- Row 2: 1
- Row 3: 1
The rows ordered from weakest to strongest are [0,2,3,1].


Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
 */
public class TheKthWeakestRowInMatrix {

    public int[] kWeakestRows(int[][] mat, int k) {
        // Priority queue to store rows ordered by strength
        // Custom comparator: first by soldier count, then by row index
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0]; // Compare by soldier count
            }
            return a[1] - b[1]; // If same count, compare by row index
        });

        // Count soldiers in each row and add to heap
        for (int i = 0; i < mat.length; i++) {
            int soldierCount = countSoldiers(mat[i]);
            minHeap.offer(new int[]{soldierCount, i});
        }

        // Extract k weakest rows
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[1]; // Get row index
        }

        return result;
    }

    // Helper method to count soldiers in a row
    // Since soldiers are positioned before civilians (1's before 0's),
    // we can use binary search for optimization
    private int countSoldiers(int[] row) {
        int left = 0, right = row.length;

        // Binary search to find the first 0
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left; // Number of 1's (soldiers)
    }

    // Alternative approach using max-heap (keeps only k elements)
    public int[] kWeakestRowsOptimized(int[][] mat, int k) {
        // Max-heap to maintain only k weakest rows
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0]; // Compare by soldier count (descending)
            }
            return b[1] - a[1]; // If same count, compare by row index (descending)
        });

        for (int i = 0; i < mat.length; i++) {
            int soldierCount = countSoldiers(mat[i]);

            if (maxHeap.size() < k) {
                maxHeap.offer(new int[]{soldierCount, i});
            } else if (soldierCount < maxHeap.peek()[0] ||
                    (soldierCount == maxHeap.peek()[0] && i < maxHeap.peek()[1])) {
                maxHeap.poll();
                maxHeap.offer(new int[]{soldierCount, i});
            }
        }

        // Extract results and reverse order
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll()[1];
        }

        return result;
    }

    // Test method
    public static void main(String[] args) {
        TheKthWeakestRowInMatrix solution = new TheKthWeakestRowInMatrix();

        // Test case 1
        int[][] mat1 = {
                {1,1,0,0,0},
                {1,1,1,1,0},
                {1,0,0,0,0},
                {1,1,0,0,0},
                {1,1,1,1,1}
        };
        int k1 = 3;
        int[] result1 = solution.kWeakestRows(mat1, k1);
        System.out.println("Test 1 - Expected: [2,0,3], Got: " + Arrays.toString(result1));

        // Test case 2
        int[][] mat2 = {
                {1,0,0,0},
                {1,1,1,1},
                {1,0,0,0},
                {1,0,0,0}
        };
        int k2 = 2;
        int[] result2 = solution.kWeakestRows(mat2, k2);
        System.out.println("Test 2 - Expected: [0,2], Got: " + Arrays.toString(result2));

        // Test optimized version
        int[] result1Opt = solution.kWeakestRowsOptimized(mat1, k1);
        System.out.println("Test 1 Optimized - Expected: [2,0,3], Got: " + Arrays.toString(result1Opt));

        int[] result2Opt = solution.kWeakestRowsOptimized(mat2, k2);
        System.out.println("Test 2 Optimized - Expected: [0,2], Got: " + Arrays.toString(result2Opt));
    }
}
