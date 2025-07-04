package heaps.sorting;

import java.util.Arrays;

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
public class TheWeakestRowInMatrix {

    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] strength = new int[m][2]; // [soldierCount, rowIndex]

        for (int i = 0; i < m; i++) {
            int count = 0;
            // Since all 1's are before 0's, we can stop at first 0
            while (count < n && mat[i][count] == 1) {
                count++;
            }
            strength[i][0] = count;
            strength[i][1] = i;
        }

        Arrays.sort(strength, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = strength[i][1];
        }
        return result;
    }

    public static void main(String[] args) {
        TheWeakestRowInMatrix solution = new TheWeakestRowInMatrix();
        int[][] mat1 = {
            {1, 1, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1}
        };
        System.out.println(Arrays.toString(solution.kWeakestRows(mat1, 3))); // Output: [2, 0, 3]

        int[][] mat2 = {
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };
        System.out.println(Arrays.toString(solution.kWeakestRows(mat2, 2))); // Output: [0, 2]
    }
}
