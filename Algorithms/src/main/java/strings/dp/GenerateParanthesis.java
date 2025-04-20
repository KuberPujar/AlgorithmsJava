package strings.dp;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
public class GenerateParanthesis {
    public static void main(String[] args) {
        int n = 3;
        List<String> result = generateParenthesis(n);
        System.out.println(result); // Output: ["((()))","(()())","(())()","()(())","()()()"]
    }

    private static List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>();

        // Base case: n = 0 has one empty combination
        dp.add(new ArrayList<>());
        dp.get(0).add("");

        for (int i = 1; i <= n; i++) {
            List<String> current = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String left : dp.get(j)) {
                    for (String right : dp.get(i - 1 - j)) {
                        // Combine solutions: "(left)right"
                        current.add("(" + left + ")" + right);
                    }
                }
            }
            dp.add(current);
        }

        return dp.get(n);
    }
}
