package hashtable.matrixbased;

import java.util.HashMap;
import java.util.Map;

/*
You are given an m x n binary matrix matrix.

You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).

Return the maximum number of rows that have all values equal after some number of flips.



Example 1:

Input: matrix = [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: matrix = [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is either 0 or 1.
 */
public class FlipColumnsForMaximumNoOfEqualRows {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> patternCount = new HashMap<>();
        for (int[] row : matrix) {
            StringBuilder pattern = new StringBuilder();
            int first = row[0];
            for (int val : row) {
                pattern.append(val ^ first); // Normalize: flip if first is 1
            }
            String key = pattern.toString();
            patternCount.put(key, patternCount.getOrDefault(key, 0) + 1);
        }
        int max = 0;
        for (int count : patternCount.values()) {
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        FlipColumnsForMaximumNoOfEqualRows solution = new FlipColumnsForMaximumNoOfEqualRows();

        int[][] matrix1 = {{0, 1}, {1, 1}};
        System.out.println(solution.maxEqualRowsAfterFlips(matrix1)); // Output: 1

        int[][] matrix2 = {{0, 1}, {1, 0}};
        System.out.println(solution.maxEqualRowsAfterFlips(matrix2)); // Output: 2

        int[][] matrix3 = {{0, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        System.out.println(solution.maxEqualRowsAfterFlips(matrix3)); // Output: 2
    }
}
