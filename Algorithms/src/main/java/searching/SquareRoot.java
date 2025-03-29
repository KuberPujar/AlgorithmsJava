package searching;

import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        System.out.println(findSquareRoot(n));
    }

    public static int findSquareRoot(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int left = 1, right = n, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == n / mid) {
                return mid;
            } else if (mid < n / mid) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}