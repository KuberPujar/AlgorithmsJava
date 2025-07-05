package heaps.binarysearch;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
ou are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.

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
public class TheKWeakestRowsInAMatrix {

    // Main solution using binary search and min-heap
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;

        // Min-heap to store k weakest rows
        // Comparator: first by soldier count (ascending), then by row index (ascending)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1]; // Compare by soldier count
            }
            return a[0] - b[0]; // Compare by row index if soldier counts are equal
        });

        // Process each row
        for (int i = 0; i < m; i++) {
            int soldiers = countSoldiers(mat[i]);
            minHeap.offer(new int[]{i, soldiers}); // {row_index, soldier_count}
        }

        // Extract k weakest rows
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }

        return result;
    }

    // Binary search to count soldiers in a row (since all 1's are to the left of 0's)
    private int countSoldiers(int[] row) {
        int left = 0, right = row.length;

        // Find the first position where we have a 0
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

    // Alternative approach using max-heap to maintain exactly k elements
    public int[] kWeakestRowsOptimized(int[][] mat, int k) {
        int m = mat.length;

        // Max-heap to keep track of k weakest rows
        // We use max-heap so we can remove the strongest among the k weakest
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return b[1] - a[1]; // Compare by soldier count (descending)
            }
            return b[0] - a[0]; // Compare by row index (descending) if soldier counts are equal
        });

        // Process each row
        for (int i = 0; i < m; i++) {
            int soldiers = countSoldiers(mat[i]);

            if (maxHeap.size() < k) {
                maxHeap.offer(new int[]{i, soldiers});
            } else if (soldiers < maxHeap.peek()[1] ||
                    (soldiers == maxHeap.peek()[1] && i < maxHeap.peek()[0])) {
                maxHeap.poll();
                maxHeap.offer(new int[]{i, soldiers});
            }
        }

        // Extract results and reverse to get correct order
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll()[0];
        }

        return result;
    }

    // Simple approach without heap (for comparison)
    public int[] kWeakestRowsSimple(int[][] mat, int k) {
        int m = mat.length;

        // Create array to store row info
        int[][] rowInfo = new int[m][2]; // {row_index, soldier_count}

        for (int i = 0; i < m; i++) {
            rowInfo[i][0] = i;
            rowInfo[i][1] = countSoldiers(mat[i]);
        }

        // Sort by soldier count, then by row index
        Arrays.sort(rowInfo, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        // Extract first k rows
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = rowInfo[i][0];
        }

        return result;
    }

    // Binary search alternative implementation
    private int countSoldiersAlternative(int[] row) {
        int left = 0, right = row.length - 1;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == 1) {
                result = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // Helper method to print matrix for debugging
    private void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Test the solution
    public static void main(String[] args) {
        TheKWeakestRowsInAMatrix solution = new TheKWeakestRowsInAMatrix();

        // Test Example 1
        int[][] mat1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k1 = 3;
        int[] result1 = solution.kWeakestRows(mat1, k1);
        System.out.println("Example 1:");
        System.out.println("Matrix:");
        solution.printMatrix(mat1);
        System.out.println("k = " + k1);
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: [2, 0, 3]");
        System.out.println();

        // Test Example 2
        int[][] mat2 = {
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        int k2 = 2;
        int[] result2 = solution.kWeakestRows(mat2, k2);
        System.out.println("Example 2:");
        System.out.println("Matrix:");
        solution.printMatrix(mat2);
        System.out.println("k = " + k2);
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: [0, 2]");
        System.out.println();

        // Test with optimized approach
        System.out.println("Optimized approach result: " + Arrays.toString(solution.kWeakestRowsOptimized(mat1, k1)));
        System.out.println("Simple approach result: " + Arrays.toString(solution.kWeakestRowsSimple(mat1, k1)));

        // Test soldier counting
        System.out.println("\nSoldier counts for Example 1:");
        for (int i = 0; i < mat1.length; i++) {
            System.out.println("Row " + i + ": " + solution.countSoldiers(mat1[i]) + " soldiers");
        }
    }
}

/*
Algorithm Explanation:

1. **Binary Search for Soldier Counting**:
   - Since all 1's appear before all 0's in each row, we can use binary search
   - Find the first position where we encounter a 0
   - The number of soldiers = first position of 0 (or row length if all 1's)
   - Time: O(log n) per row instead of O(n)

2. **Heap-Based Approaches**:

   **Min-Heap Approach**:
   - Add all rows to min-heap with their soldier counts
   - Extract k smallest elements
   - Time: O(m log m + k log m), Space: O(m)

   **Max-Heap Approach (Optimized)**:
   - Maintain heap of size k with strongest rows among current k weakest
   - When processing new row, compare with strongest in heap
   - Replace if current row is weaker
   - Time: O(m log k), Space: O(k)

3. **Comparator Logic**:
   - Primary: Compare by soldier count (ascending for weaker)
   - Secondary: Compare by row index (ascending for smaller index)
   - This ensures proper tie-breaking as per problem requirements

4. **Key Optimizations**:
   - Binary search reduces soldier counting from O(n) to O(log n)
   - Max-heap of size k is more efficient than min-heap of size m when k << m
   - Early termination possible in some cases

Time Complexity Analysis:
- Binary search counting: O(m log n)
- Min-heap approach: O(m log m + k log m) = O(m log m)
- Max-heap approach: O(m log k)
- Overall: O(m log n + m log k) for optimized approach

Space Complexity: O(k) for heap + O(k) for result = O(k)

Edge Cases Handled:
- All rows have same soldier count (sorted by row index)
- k = m (return all rows)
- Rows with all 1's or all 0's
- Single row matrix

The solution efficiently combines binary search for fast soldier counting with heap-based
selection to find the k weakest rows while maintaining the required ordering.
*/

