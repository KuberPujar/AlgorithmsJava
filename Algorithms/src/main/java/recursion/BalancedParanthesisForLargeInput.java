package recursion;

import java.math.BigInteger;
import java.util.Scanner;

public class BalancedParanthesisForLargeInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(countBalancedParentheses(n));
    }

    public static BigInteger countBalancedParentheses(int n) {
        return binomialCoefficient(2 * n, n).divide(BigInteger.valueOf(n + 1));
    }

    private static BigInteger binomialCoefficient(int n, int k) {
        BigInteger res = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            res = res.multiply(BigInteger.valueOf(n - i))
                    .divide(BigInteger.valueOf(i + 1));
        }
        return res;
    }
}
