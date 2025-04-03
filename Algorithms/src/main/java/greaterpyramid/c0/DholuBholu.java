package greaterpyramid.c0;

import java.util.Scanner;

public class DholuBholu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();

        System.out.println(determineWinner(a, b, n));
    }

    public static int determineWinner(int a, int b, int n) {
        boolean bholuTurn = true; // Bholu starts first

        while (true) {
            if (bholuTurn) {
                int stonesToTake = gcd(a, n);
                if (n < stonesToTake) {
                    return 1; // Bholu can't move, Dholu wins
                }
                n -= stonesToTake;
            } else {
                int stonesToTake = gcd(b, n);
                if (n < stonesToTake) {
                    return 0; // Dholu can't move, Bholu wins
                }
                n -= stonesToTake;
            }
            bholuTurn = !bholuTurn; // Switch turns
        }
    }

    // Helper method to calculate GCD using Euclidean algorithm
    public static int gcd(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
}
