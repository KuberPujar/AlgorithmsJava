package sorting;

import java.util.Scanner;

public class SortThePermutation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close(); // Close the scanner to prevent resource leak

        for (int i = 1; i < n - 2; i++) {
            if (a[i] > a[i + 1] && a[i] > a[i - 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(" ");
            if (a[i] > a[i + 1]) {
                System.out.println("NO");
                break;
            }
            else {
                System.out.println("YES");
                break;
            }
        }
    }
}