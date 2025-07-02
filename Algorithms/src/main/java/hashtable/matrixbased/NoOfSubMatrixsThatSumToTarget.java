package hashtable.matrixbased;

import java.util.HashMap;
import java.util.Map;

/*
Given a matrix and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.



Example 1:


Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
Example 3:

Input: matrix = [[904]], target = 0
Output: 0


Constraints:

1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i][j] <= 1000
-10^8 <= target <= 10^8
 */
public class NoOfSubMatrixsThatSumToTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, res = 0;
        // Prefix sum for each row
        for (int top = 0; top < m; top++) {
            int[] colSum = new int[n];
            for (int bottom = top; bottom < m; bottom++) {
                for (int col = 0; col < n; col++) {
                    colSum[col] += matrix[bottom][col];
                }
                res += subarraySum(colSum, target);
            }
        }
        return res;
    }

    // Standard subarray sum equals k using HashMap
    private int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        NoOfSubMatrixsThatSumToTarget solution = new NoOfSubMatrixsThatSumToTarget();

        int[][] matrix1 = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        System.out.println(solution.numSubmatrixSumTarget(matrix1, 0)); // Output: 4

        int[][] matrix2 = {{1, -1}, {-1, 1}};
        System.out.println(solution.numSubmatrixSumTarget(matrix2, 0)); // Output: 5

        int[][] matrix3 = {{904}};
        System.out.println(solution.numSubmatrixSumTarget(matrix3, 0)); // Output: 0
    }
}
