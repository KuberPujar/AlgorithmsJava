package strings.dp;
/*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 */
public class IsSubsequence {
    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t)); // Output: true

        s = "axc";
        t = "ahbgdc";
        System.out.println(isSubsequence(s, t)); // Output: false
    }

    /**
     * Checks if string s is a subsequence of string t using dynamic programming.
     *
     * @param s The subsequence string.
     * @param t The main string.
     * @return True if s is a subsequence of t, false otherwise.
     */
    private static boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();

        // dp[i][j] will be true if the first i characters of s
        // form a subsequence of the first j characters of t.
        boolean[][] dp = new boolean[n + 1][m + 1];

        // An empty string s is always a subsequence of any string t
        for (int j = 0; j <= m; j++) {
            dp[0][j] = true;
        }

        // Iterate through the strings to fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // If the characters match, then s[0...i-1] is a
                    // subsequence of t[0...j-1] if s[0...i-2] is a
                    // subsequence of t[0...j-2].
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If the characters don't match, then s[0...i-1] is a
                    // subsequence of t[0...j-1] if s[0...i-1] is a
                    // subsequence of t[0...j-2] (we ignore the current
                    // character in t).
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        // The result is stored in dp[n][m], which indicates if the entire s
        // is a subsequence of the entire t.
        return dp[n][m];
    }
}
