package greaterpyramid.c6;

import java.util.Arrays;
import java.util.List;

/*
Rotate Image
You are given an n x n 2D matrix representing a picture, rotate the image by 90 degrees (clockwise).

You have to rotate the picture in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example

input      output
1 2 3       7 4 1
4 5 6 => 8 5 2
7 8 9       9 6 3

Constraints:

1 <= n <= 20
-value of matrix[i][j] may be negative.
 */
public class RotateImage {
    //write a function to rotate the image by 90 degrees clockwise for List<List<Integer>> matrix
    public void rotate(List<List<Integer>> matrix) {
        int n = matrix.size();
        // First, transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(j).get(i));
                matrix.get(j).set(i, temp);
            }
        }
        // Then, reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(i).get(n - 1 - j));
                matrix.get(i).set(n - 1 - j, temp);
            }
        }
    }

    public static void main(String[] args) {
        RotateImage ri = new RotateImage();
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9)
        );
        ri.rotate(matrix);
        for (List<Integer> row : matrix) {
            System.out.println(row);
        }
    }
}
