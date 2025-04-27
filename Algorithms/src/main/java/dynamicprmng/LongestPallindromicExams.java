package dynamicprmng;
/*
Longest Pallindromic Exams
Find the length of the longest palindromic subsequence within a given string, denoted by s. A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements. The goal is to determine the maximum length of a subsequence in s that is also a palindrome.

Constraints:
1 <= s.length <= 1000
s consists only of lowercase English letters.
Input Format:
A single string 's' consisting only of lowercase English letters.
Output Format:
An integer representing the length of the longest palindromic subsequence within the given string 's'.
Examples:
Example 1:

Input:

s = "bbbab"
Output:

4
Explanation: For the string "bbbab":

The longest palindromic subsequence is "bbbb", which is a palindrome of length 4.
Example 2:

Input:

s = "civic"
Output:

5
Explanation: For the string "civic":

The longest palindromic subsequence is "civic", which is a palindrome of length 5.
Note: The function should return the result.
 */
public class LongestPallindromicExams {
    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromicSubsequence(s)); // Output: 4

        String s2 = "civic";
        System.out.println(longestPalindromicSubsequence(s2)); // Output: 5
    }

    public static int longestPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
