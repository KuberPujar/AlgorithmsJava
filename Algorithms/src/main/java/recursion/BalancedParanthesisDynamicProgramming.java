package recursion;

import java.util.Scanner;

public class BalancedParanthesisDynamicProgramming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(countBalancedParentheses(n));
    }

    public static int countBalancedParentheses(int n) {
        int[] catalan = new int[n + 1];
        catalan[0] = catalan[1] = 1;
        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - j - 1];
            }
        }
        return catalan[n];
    }
}
