package math.geometry;

import java.util.ArrayList;
import java.util.List;

/*
You are given four integers row, cols, rCenter, and cCenter. There is a rows x cols matrix and you are on the cell with the coordinates (rCenter, cCenter).

Return the coordinates of all cells in the matrix, sorted by their distance from (rCenter, cCenter) from the smallest distance to the largest distance. You may return the answer in any order that satisfies this condition.

The distance between two cells (r1, c1) and (r2, c2) is |r1 - r2| + |c1 - c2|.



Example 1:

Input: rows = 1, cols = 2, rCenter = 0, cCenter = 0
Output: [[0,0],[0,1]]
Explanation: The distances from (0, 0) to other cells are: [0,1]
Example 2:

Input: rows = 2, cols = 2, rCenter = 0, cCenter = 1
Output: [[0,1],[0,0],[1,1],[1,0]]
Explanation: The distances from (0, 1) to other cells are: [0,1,1,2]
The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
Example 3:

Input: rows = 2, cols = 3, rCenter = 1, cCenter = 2
Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
Explanation: The distances from (1, 2) to other cells are: [0,1,1,2,2,3]
There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].


Constraints:

1 <= rows, cols <= 100
0 <= rCenter < rows
0 <= cCenter < cols
 */
public class MatrixCellsInDistanceOrder {
    public static void main(String[] args) {
        int rows = 2, cols = 3, rCenter = 1, cCenter = 2;
        int[][] result = allCellsDistOrder(rows, cols, rCenter, cCenter);
        for (int[] cell : result) {
            System.out.println("[" + cell[0] + ", " + cell[1] + "]");
        }
    }

    private static int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        List<int[]> result = new ArrayList<>();

        // Generate all cells in the matrix
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result.add(new int[]{r, c});
            }
        }

        // Sort cells by their Manhattan distance from the center
        result.sort((a, b) -> {
            int distA = Math.abs(a[0] - rCenter) + Math.abs(a[1] - cCenter);
            int distB = Math.abs(b[0] - rCenter) + Math.abs(b[1] - cCenter);
            return distA - distB;
        });

        // Convert to array
        return result.toArray(new int[0][0]);
    }
}
