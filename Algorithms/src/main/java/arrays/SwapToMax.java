package arrays;

import java.util.Scanner;

public class SwapToMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        scanner.close();

        for (int i = 0; i < n; i++) {
            if (a[i] > b[i]) {
                int temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
        }

        int maxA = a[0];
        int maxB = b[0];

        for (int i = 1; i < n; i++) {
            if (a[i] > maxA) {
                maxA = a[i];
            }
            if (b[i] > maxB) {
                maxB = b[i];
            }
        }

        if (a[n - 1] == maxA && b[n - 1] == maxB) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}