package strings.dp;
/*
Given two strings s and t, find the number of ways you can choose a non-empty substring of s and replace a single character by a different character such that the resulting substring is a substring of t. In other words, find the number of substrings in s that differ from some substring in t by exactly one character.

For example, the underlined substrings in "computer" and "computation" only differ by the 'e'/'a', so this is a valid way.

Return the number of substrings that satisfy the condition above.

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: s = "aba", t = "baba"
Output: 6
Explanation: The following are the pairs of substrings from s and t that differ by exactly 1 character:
("aba", "baba")
("aba", "baba")
("aba", "baba")
("aba", "baba")
("aba", "baba")
("aba", "baba")
The underlined portions are the substrings that are chosen from s and t.
​​Example 2:
Input: s = "ab", t = "bb"
Output: 3
Explanation: The following are the pairs of substrings from s and t that differ by 1 character:
("ab", "bb")
("ab", "bb")
("ab", "bb")
​​​​The underlined portions are the substrings that are chosen from s and t.


Constraints:

1 <= s.length, t.length <= 100
s and t consist of lowercase English letters only.
 */
public class CountSubStringThatDifferBy1Char {
    public static void main(String[] args) {
        String s = "aba";
        String t = "baba";
        System.out.println(countSubstrings(s, t)); // Output: 6

        s = "ab";
        t = "bb";
        System.out.println(countSubstrings(s, t)); // Output: 3
    }

    private static int countSubstrings(String s, String t) {
        int m = s.length();
        int n = t.length();
        int count = 0;

        // DP table where dp[i][j] represents the number of matching characters
        // in the substrings ending at s[i-1] and t[j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Another table to store the number of substrings with exactly one difference
        int[][] diff = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    diff[i][j] = diff[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;
                    diff[i][j] = dp[i - 1][j - 1] + 1;
                }
                count += diff[i][j];
            }
        }

        return count;
    }
}
