package strings;

import java.util.Scanner;

public class MangoesAndPineapples {
    public static int findLowestPrefix(int n, String fruits) {
        int mangoes = 0, pineapples = 0;

        // Count total mangoes and pineapples
        for (char fruit : fruits.toCharArray()) {
            if (fruit == 'M') {
                mangoes++;
            } else if (fruit == 'P') {
                pineapples++;
            }
        }

        int prefixMangoes = 0, prefixPineapples = 0;

        for (int i = 0; i < n - 1; i++) {
            if (fruits.charAt(i) == 'M') {
                prefixMangoes++;
            } else if (fruits.charAt(i) == 'P') {
                prefixPineapples++;
            }

            int remainingMangoes = mangoes - prefixMangoes;
            int remainingPineapples = pineapples - prefixPineapples;

            if (prefixMangoes != remainingMangoes && prefixPineapples != remainingPineapples) {
                return i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String fruits = scanner.next();
        scanner.close();

        System.out.println(findLowestPrefix(n, fruits));
    }
}