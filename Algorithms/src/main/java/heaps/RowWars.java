package heaps;

import java.util.*;

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
    public static List<Integer> kWeakestRows(int[][] mat, int k) {
        // Use a PriorityQueue (max heap) to store row strengths and indices.
        // Use a PriorityQueue (min heap) to store row strengths and indices.
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return a[0] - b[0]; // Compare soldier counts.  Lower count is weaker.
                    } else {
                        return a[1] - b[1]; // If counts equal, lower index is weaker.
                    }
                }
        );

        // Iterate through the matrix to calculate row strengths.
        for (int i = 0; i < mat.length; i++) {
            int soldiers = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    soldiers++;
                }
            }
            // Add {soldier_count, row_index} to the priority queue.
            pq.offer(new int[]{soldiers, i});
        }

        // Extract the indices of the k weakest rows from the priority queue.
        List<Integer> result = new LinkedList<>();
        for (int i =0 ; i<=k - 1; i++) {
            result.add(Objects.requireNonNull(pq.poll())[1]);
        }
        return result;
    }

    public static void main(String[] args) {
    int[][] mat = {
        {1, 1, 0, 0, 0},
        {1, 1, 1, 1, 0},
        {1, 0, 0, 0, 0},
        {1, 1, 0, 0, 0},
        {1, 1, 1, 1, 1}
    };
        int k = 3;
        List<Integer> result = kWeakestRows(mat, k);
        for (int index : result) {
            System.out.print(index + " ");
        }
        System.out.println();
    }
}
