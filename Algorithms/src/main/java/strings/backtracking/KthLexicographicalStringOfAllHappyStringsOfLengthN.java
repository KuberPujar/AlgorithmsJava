package strings.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
A happy string is a string that:

consists only of letters of the set ['a', 'b', 'c'].
s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.

Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.

Return the kth string of this list or return an empty string if there are less than k happy strings of length n.



Example 1:

Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
Example 2:

Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.
Example 3:

Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"


Constraints:

1 <= n <= 10
1 <= k <= 100
 */
public class KthLexicographicalStringOfAllHappyStringsOfLengthN {
    public static void main(String[] args) {

        // Test case 1
        System.out.println(getHappyString(1, 3)); // Output: "c"

        // Test case 2
        System.out.println(getHappyString(1, 4)); // Output: ""

        // Test case 3
        System.out.println(getHappyString(3, 9)); // Output: "cab"

        // Additional test case
        System.out.println(getHappyString(2, 5)); // Output: "ca"
    }

    private static List<String> happyStrings;
    private static int count;
    private static String result;

    public static String getHappyString(int n, int k) {
        happyStrings = new ArrayList<>();
        count = 0;
        result = "";
        backtrack(n, k, new StringBuilder());
        return result;
    }

    private static void backtrack(int n, int k, StringBuilder current) {
        // If we've found the k-th string, stop further processing
        if (count >= k) {
            return;
        }

        // If current string has reached desired length
        if (current.length() == n) {
            count++;
            if (count == k) {
                result = current.toString();
            }
            return;
        }

        // Try adding 'a', 'b', 'c' in order (to maintain lex order)
        for (char c : new char[]{'a', 'b', 'c'}) {
            // Skip if same as last character
            if (!current.isEmpty() && current.charAt(current.length() - 1) == c) {
                continue;
            }

            current.append(c);
            backtrack(n, k, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
