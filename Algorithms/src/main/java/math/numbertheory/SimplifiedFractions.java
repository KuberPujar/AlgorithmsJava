package math.numbertheory;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is less-than-or-equal-to n. You can return the answer in any order.



Example 1:

Input: n = 2
Output: ["1/2"]
Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
Example 2:

Input: n = 3
Output: ["1/2","1/3","2/3"]
Example 3:

Input: n = 4
Output: ["1/2","1/3","1/4","2/3","3/4"]
Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".


Constraints:

1 <= n <= 100
 */
public class SimplifiedFractions {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(simplifiedFractions(n));

        n = 3;
        System.out.println(simplifiedFractions(n));

        n = 2;
        System.out.println(simplifiedFractions(n));

        n = 1;
        System.out.println(simplifiedFractions(n));

        n = 5;
        System.out.println(simplifiedFractions(n));
    }

    private static List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();

        // Iterate through all possible denominators
        for (int denominator = 2; denominator <= n; denominator++) {
            // Iterate through all possible numerators
            for (int numerator = 1; numerator < denominator; numerator++) {
                // Check if fraction is simplified (GCD of numerator and denominator is 1)
                if (gcd(numerator, denominator) == 1) {
                    result.add(numerator + "/" + denominator);
                }
            }
        }

        return result;
    }

    // Helper method to calculate GCD using Euclidean algorithm
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
