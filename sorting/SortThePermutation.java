package sorting;

import java.util.Scanner;

public class SortThePermutation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        for(int i=1;i<=n;i++){
            a[i] = sc.nextInt();
        }

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i <= n - 2; i++) {
                if (a[i] > a[i + 1] && a[i] > a[i + 2]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        for (int i = 1; i <= n - 1; i++) {
            if (a[i] > a[i + 1]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
