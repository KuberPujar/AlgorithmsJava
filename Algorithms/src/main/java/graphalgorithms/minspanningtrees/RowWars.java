package graphalgorithms.minspanningtrees;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Row Wars
A learner at heycoach goes to a parallel world by some magic, upon reaching there he saw that a war is going on between two nations, the soldiers are protecting the civilians, as he was one of the civilian there he saw that soldiers had create a grid like structure formation in which there are m rows and n columns and in which the soldiers are represented by 1 and civilians are represented by 0, all the soldiers are on left of civilians your task is to find out k weakest rows indexes out of those m rows. A row i is weaker than a row j if one of the following is true:

1). The number of soldiers in row i is less than the number of soldiers in row j.

2). Both rows have the same number of soldiers and i < j.

Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.

Input:

The first line contains three integers, m, n, and k representing the number of rows, the number of columns in the matrix, and the number of weakest rows to find, respectively.
The next m lines each contain n space-separated integers, representing the elements of each row in the matrix.
Output:

Print the indices of the k weakest rows in the matrix, ordered from weakest to strongest.
Examples:

Input 1:

 5 5 3
 1 1 0 0 0
 1 1 1 1 0
 1 0 0 0 0
 1 1 0 0 0
 1 1 1 1 1

Output 1:

2 0 3
Explanation:

The number of soldiers in each row is: [2, 4, 1, 2, 5].
The weakest rows are determined based on the number of soldiers and the row indices.
Row 2 has 1 soldier (weakest).
Row 0 and row 3 have 2 soldiers, but row 0 comes before row 3.
Thus, the weakest rows are [2, 0, 3].
Input 2:

4 4 2
0 0 0 1
1 0 1 0
0 1 1 0
1 0 0 1
Output 2:

 0 1
Explanation:

The number of soldiers in each row is: [1, 2, 2, 2].
The weakest rows are determined based on the number of soldiers and the row indices.
Row 0 has 1 soldier (weakest).
Row 1, 2, and 3 have 2 soldiers, but row 1 comes before row 2 and 3.
Thus, the weakest rows are [0, 1].
Constraints

2 <= n, m <= 100, elements of the matrix are either 0 or 1

Note:The function should return the result. The driver code will handle printing the output.
 */
public class RowWars {
    // Class to represent a row with its index and soldier count
    static class Row implements Comparable<Row> {
        int index;
        int soldierCount;

        public Row(int index, int soldierCount) {
            this.index = index;
            this.soldierCount = soldierCount;
        }

        @Override
        public int compareTo(Row other) {
            // First compare by soldier count
            if (this.soldierCount != other.soldierCount) {
                return Integer.compare(this.soldierCount, other.soldierCount);
            }
            // If soldier counts are equal, compare by index
            return Integer.compare(this.index, other.index);
        }
    }

    public static int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // Priority queue to store rows (min-heap based on weakness)
        PriorityQueue<Row> pq = new PriorityQueue<>();

        // Count soldiers in each row and add to priority queue
        for (int i = 0; i < m; i++) {
            int soldierCount = countSoldiers(mat[i]);
            pq.offer(new Row(i, soldierCount));
        }

        // Extract k weakest rows
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().index;
        }

        return result;
    }

    // Helper method to count soldiers in a row
    // Since soldiers are on the left, we can optimize by counting from left until we hit 0
    private static int countSoldiers(int[] row) {
        int count = 0;
        for (int val : row) {
            if (val == 1) {
                count++;
            }
        }
        return count;
    }

    // Optimized version using binary search (since soldiers are on the left)
    public static int[] kWeakestRowsOptimized(int[][] mat, int k) {
        int m = mat.length;

        // Priority queue to store rows (min-heap based on weakness)
        PriorityQueue<Row> pq = new PriorityQueue<>();

        // Count soldiers in each row using binary search and add to priority queue
        for (int i = 0; i < m; i++) {
            int soldierCount = countSoldiersOptimized(mat[i]);
            pq.offer(new Row(i, soldierCount));
        }

        // Extract k weakest rows
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll().index;
        }

        return result;
    }

    // Binary search to find the number of soldiers (since all soldiers are on the left)
    private static int countSoldiersOptimized(int[] row) {
        int left = 0, right = row.length;

        // Find the first position where we have 0
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left; // Number of 1s from the beginning
    }

    // Alternative approach using simple sorting
    public static int[] kWeakestRowsSimple(int[][] mat, int k) {
        int m = mat.length;

        // Create array to store [row_index, soldier_count]
        int[][] rowInfo = new int[m][2];

        for (int i = 0; i < m; i++) {
            rowInfo[i][0] = i; // row index
            rowInfo[i][1] = countSoldiers(mat[i]); // soldier count
        }

        // Sort based on soldier count, then by row index
        Arrays.sort(rowInfo, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]); // Compare by soldier count
            }
            return Integer.compare(a[0], b[0]); // Compare by row index
        });

        // Extract first k row indices
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = rowInfo[i][0];
        }

        return result;
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1
        int[][] mat1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int k1 = 3;

        System.out.println("Test Case 1:");
        System.out.println("Matrix:");
        printMatrix(mat1);
        System.out.println("K = " + k1);

        int[] result1 = kWeakestRows(mat1, k1);
        System.out.println("Result: " + Arrays.toString(result1));
        System.out.println("Expected: [2, 0, 3]");
        System.out.println();

        // Test case 2
        int[][] mat2 = {
                {0, 0, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        };
        int k2 = 2;

        System.out.println("Test Case 2:");
        System.out.println("Matrix:");
        printMatrix(mat2);
        System.out.println("K = " + k2);

        int[] result2 = kWeakestRows(mat2, k2);
        System.out.println("Result: " + Arrays.toString(result2));
        System.out.println("Expected: [0, 1]");
        System.out.println();

        // Test optimized version
        System.out.println("Testing optimized version:");
        int[] result1Opt = kWeakestRowsOptimized(mat1, k1);
        int[] result2Opt = kWeakestRowsOptimized(mat2, k2);
        System.out.println("Optimized Result 1: " + Arrays.toString(result1Opt));
        System.out.println("Optimized Result 2: " + Arrays.toString(result2Opt));
    }

    // Helper method to print matrix
    private static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }
}
