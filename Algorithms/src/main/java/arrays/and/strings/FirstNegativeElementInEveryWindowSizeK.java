package arrays.and.strings;

import java.util.Scanner;

public class FirstNegativeElementInEveryWindowSizeK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        findFirstNegativeInWindow(arr, n, k);
    }

    public static void findFirstNegativeInWindow(int[] arr, int n, int k) {
        int[] result = new int[n - k + 1];
        int index = 0;

        for (int i = 0; i <= n - k; i++) {
            result[i] = 0; // Initialize with 0
            for (int j = 0; j < k; j++) {
                if (arr[i + j] < 0) {
                    result[i] = arr[i + j];
                    break;
                }
            }
        }

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}