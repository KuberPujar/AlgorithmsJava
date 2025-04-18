package arrays.binarysearch;
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
import java.util.PriorityQueue;

public class KthWeakestRowsInAMatrix {
    public static void main(String[] args) {
        int[][] mat = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k = 3;
        int[] result = kWeakRows(mat, k);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] kWeakRows(int[][] mat, int k) {
        // Create a priority queue to store the weakest rows
        // The queue is a max heap that keeps the weakest k elements
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> a[1] != b[1] ? b[1] - a[1] : b[0] - a[0]
        );

        for (int i = 0; i < mat.length; i++) {
            int strength = binarySearchCount(mat[i]);

            // Add to the heap
            maxHeap.offer(new int[]{i, strength});

            // Keep the heap size <= k
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Extract results from the heap
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll()[0];
        }

        return result;
    }

    // Binary search to count the number of 1's (soldiers)
    private static int binarySearchCount ( int[] row){
        int left = 0;
        int right = row.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
