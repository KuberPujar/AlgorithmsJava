package sorting;

import java.util.Scanner;

public class ArrangeTheBalls {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        char[] a = new char[n];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.next().charAt(0);
        }
        sc.close(); // Close the scanner to prevent resource leak
        arrangeBalls(n, a);
    }

    private static void arrangeBalls(int n, char[] balls) {
        if (n >= 3) {
            int saffronCount = 0, whiteCount = 0, greenCount = 0;
            for (char b : balls) {
                if (b == 'S') {
                    saffronCount++;
                } else if (b == 'W') {
                    whiteCount++;
                } else if (b == 'G') {
                    greenCount++;
                }
            }
            int index = 0;
            for (int i = 0; i < saffronCount; i++) {
                balls[index++] = 'S';
            }
            for (int i = 0; i < whiteCount; i++) {
                balls[index++] = 'W';
            }
            for (int i = 0; i < greenCount; i++) {
                balls[index++] = 'G';
            }

            for (int i = 0; i < balls.length - 1; i++) {
                System.out.print(balls[i] + " ");
            }
            System.out.print(balls[balls.length - 1]);
        }
    }
}
