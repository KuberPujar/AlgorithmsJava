package heaps.bitmanipulation;

import java.util.ArrayList;
import java.util.Collections;

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
public class FindKthLargestXORCoordinateValue {

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] prefix = new int[m][n];
        ArrayList<Integer> values = new ArrayList<>();

        prefix[0][0] = matrix[0][0];
        values.add(prefix[0][0]);

        for (int i = 1; i < m; i++) {
            prefix[i][0] = prefix[i-1][0] ^ matrix[i][0];
            values.add(prefix[i][0]);
        }
        for (int j = 1; j < n; j++) {
            prefix[0][j] = prefix[0][j-1] ^ matrix[0][j];
            values.add(prefix[0][j]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                prefix[i][j] = prefix[i-1][j] ^ prefix[i][j-1] ^ prefix[i-1][j-1] ^ matrix[i][j];
                values.add(prefix[i][j]);
            }
        }

        Collections.sort(values, Collections.reverseOrder());
        return values.get(k-1);
    }

    public static void main(String[] args) {
        FindKthLargestXORCoordinateValue solution = new FindKthLargestXORCoordinateValue();
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 1;
        int result = solution.kthLargestValue(matrix, k);
        System.out.println("The " + k + "th largest XOR coordinate value is: " + result);
    }
}
