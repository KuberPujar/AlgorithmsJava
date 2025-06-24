package stacks.dpbased;

import java.util.HashSet;
import java.util.Set;

/*
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true


Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */
public class ValidParenthesis {
    /**
     * Checks if a string containing '(', ')', and '*' characters is valid using a linear scan
     * dynamic programming approach (often called a greedy approach).
     *
     * The validity rules are:
     * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * 4. '*' could be treated as a single right parenthesis ')', a single left parenthesis '(', or an empty string "".
     *
     * This solution tracks the minimum and maximum possible open parenthesis counts (balance)
     * at each point in the string.
     *
     * @param s The input string to validate.
     * @return True if the string is valid, false otherwise.
     */
    public boolean checkValidString(String s) {
        // 'low' represents the minimum possible open parentheses balance assuming '*' acts as ')' or empty string.
        // 'high' represents the maximum possible open parentheses balance assuming '*' acts as '(' or empty string.
        int low = 0;
        int high = 0;

        // Iterate through each character in the input string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If it's an open parenthesis, both low and high balances must increase.
                low++;
                high++;
            } else if (c == ')') {
                // If it's a closing parenthesis, both low and high balances must decrease.
                low--;
                high--;
            } else { // c == '*'
                // If it's an asterisk:
                // 'low' decreases: '*' can act as a ')' to balance an open '('.
                // We take the minimum with 0 because the balance cannot go below zero for a valid string prefix.
                low--;
                // 'high' increases: '*' can act as a '(' adding to the open count.
                high++;
            }

            // Crucial check: If 'high' becomes negative at any point, it means
            // there are too many closing parentheses that cannot be matched,
            // even if all '*' are treated as opening parentheses. Thus, the string is invalid.
            if (high < 0) {
                return false;
            }

            // 'low' cannot go below 0. If 'low' is negative, it means we had to treat
            // some '*' as ')' to keep the balance non-negative. We effectively cap 'low' at 0.
            // This ensures we don't incorrectly fail if '*' could "absorb" a temporary negative balance.
            low = Math.max(0, low);
        }

        // After iterating through the entire string, for the string to be valid,
        // the minimum possible open parenthesis count ('low') must be 0.
        // This indicates that it's possible to achieve a perfectly balanced string.
        return low == 0;
    }

    public static void main(String[] args) {
        ValidParenthesis solution = new ValidParenthesis();

        // Example 1
        String s1 = "()";
        System.out.println("Is valid: " + solution.checkValidString(s1)); // Expected: true

        // Example 2
        String s2 = "(*)";
        System.out.println("Is valid: " + solution.checkValidString(s2)); // Expected: true

        // Example 3
        String s3 = "(*))";
        System.out.println("Is valid: " + solution.checkValidString(s3)); // Expected: true

        // Additional test cases
        String s4 = "(*()";
        System.out.println("Is valid: " + solution.checkValidString(s4)); // Expected: false

        String s5 = "((*)";
        System.out.println("Is valid: " + solution.checkValidString(s5)); // Expected: true
    }
}
