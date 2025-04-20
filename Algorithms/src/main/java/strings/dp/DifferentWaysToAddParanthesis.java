package strings.dp;

import java.util.ArrayList;
import java.util.List;

/*
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.



Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10


Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
The integer values in the input expression do not have a leading '-' or '+' denoting the sign.
 */
public class DifferentWaysToAddParanthesis {
    public static void main(String[] args) {
        String expression = "2-1-1";
        List<Integer> result = diffWaysToCompute(expression);
        System.out.println(result); // Output: [0, 2]

        expression = "2*3-4*5";
        result = diffWaysToCompute(expression);
        System.out.println(result); // Output: [-34, -14, -10, -10, 10]
    }

    private static List<Integer> diffWaysToCompute(String expression) {
        // Parse the expression into numbers and operators
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        int num = 0;
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                numbers.add(num);
                operators.add(c);
                num = 0;
            }
        }
        numbers.add(num);

        int n = numbers.size();
        // DP table: dp[i][j] = list of possible results from numbers i to j
        List<Integer>[][] dp = new List[n][n];

        // Initialize diagonal (single numbers)
        for (int i = 0; i < n; i++) {
            dp[i][i] = new ArrayList<>();
            dp[i][i].add(numbers.get(i));
        }

        // Fill DP table for subexpressions of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = new ArrayList<>();
                for (int k = i; k < j; k++) {
                    List<Integer> left = dp[i][k];
                    List<Integer> right = dp[k+1][j];
                    char op = operators.get(k);
                    for (int l : left) {
                        for (int r : right) {
                            dp[i][j].add(compute(l, r, op));
                        }
                    }
                }
            }
        }

        return dp[0][n-1];
    }

    private static int compute(int a, int b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }
}
