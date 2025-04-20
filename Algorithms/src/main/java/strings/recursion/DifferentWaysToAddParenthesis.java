package strings.recursion;

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
public class DifferentWaysToAddParenthesis {
    public static void main(String[] args) {
        String expression = "2*3-4*5";
        System.out.println(diffWaysToCompute(expression));
    }

    private static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();

        // Base case: if expression is just a number
        if (isNumber(expression)) {
            result.add(Integer.parseInt(expression));
            return result;
        }

        // Iterate through the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // When we find an operator
            if (c == '+' || c == '-' || c == '*') {
                // Split into left and right parts
                String left = expression.substring(0, i);
                String right = expression.substring(i + 1);

                // Recursively compute all possible results for left and right
                List<Integer> leftResults = diffWaysToCompute(left);
                List<Integer> rightResults = diffWaysToCompute(right);

                // Combine results using the current operator
                for (int l : leftResults) {
                    for (int r : rightResults) {
                        switch (c) {
                            case '+':
                                result.add(l + r);
                                break;
                            case '-':
                                result.add(l - r);
                                break;
                            case '*':
                                result.add(l * r);
                                break;
                        }
                    }
                }
            }
        }

        return result;
    }

    private static boolean isNumber(String s) {
        // Check if string contains only digits
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
