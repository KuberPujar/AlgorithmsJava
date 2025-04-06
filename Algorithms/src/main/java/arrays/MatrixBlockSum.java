package arrays;

public class MatrixBlockSum {
    public static int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;


        System.out.println("Matrix dimensions: " + m + "x" + n);

        // Create prefix sum matrix
        int[][] prefix = new int[m + 1][n + 1];

        // Calculate prefix sums
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] +
                        prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }

        // Initialize result matrix
        int[][] result = new int[m][n];

        // Calculate block sums using prefix sums
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);

                // Calculate sum using inclusion-exclusion principle
                result[i][j] = prefix[r2 + 1][c2 + 1] - prefix[r1][c2 + 1] -
                        prefix[r2 + 1][c1] + prefix[r1][c1];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int k = 1;

        System.out.println("Input matrix:");
        printMatrix(mat);

        int[][] result = matrixBlockSum(mat, k);

        System.out.println("\nBlock sum matrix (k=" + k + "):");
        printMatrix(result);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
