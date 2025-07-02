package hashtable.matrixbased;

import java.util.HashSet;
import java.util.Set;

/*
An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).

Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.



Example 1:


Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
Output: true
Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
Hence, we return true.
Example 2:


Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
Output: false
Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
Hence, we return false.


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
1 <= matrix[i][j] <= n
 */
public class CheckIfEveryRowAndColumnContainsNumber {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            for (int j = 0; j < n; j++) {
                rowSet.add(matrix[i][j]);
                colSet.add(matrix[j][i]);
            }
            if (rowSet.size() != n || colSet.size() != n) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckIfEveryRowAndColumnContainsNumber solution = new CheckIfEveryRowAndColumnContainsNumber();
        int[][] matrix1 = {{1, 2, 3}, {3, 1, 2}, {2, 3, 1}};
        System.out.println(solution.checkValid(matrix1)); // Output: true

        int[][] matrix2 = {{1, 1, 1}, {1, 2, 3}, {1, 2, 3}};
        System.out.println(solution.checkValid(matrix2)); // Output: false
    }
}
