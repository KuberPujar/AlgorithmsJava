package dynamicprmng;

import java.util.Arrays;

/*
The new classmate
A new classmate has come today and his name is represented by a string 's'. His name is pretty random and contains only lowercase Latin letters. You and your classmates have started playing a game based on his name. The game is simple whoever finds the greatest lexicographically arranged sequence from string 's' wins.
For example if his name is "dhananjay", the winner would be the one who says 5 and the greatest lexicographically arranged sequence would be "dhnny". Given string 's' calculate the size of greatest lexicographically arranged sequence and return the result.

Input Format:
A single integer 'n' denoting the size of string.

The second line contains a string 's' of size n.

Output format:
Return a single integer denoting the size of the greatest lexicographically arranged sequence.
Sample Input:
6

klepto
Sample Output:
4

Explanation:
The sequence "klpt" which has 4 characters is the maximum possible answer.
Constraints:
1<=n<=10^3
Note: The function should return the result.
 */
public class TheNewClassmate {
    public static void main(String[] args) {
        String s = "dhananjay";
        System.out.println(longestLexIncreasingSubsequence(s)); // Output: 5

        String s2 = "klepto";
        System.out.println(longestLexIncreasingSubsequence(s2)); // Output: 4
    }

    public static int longestLexIncreasingSubsequence(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each character is at least a subsequence of length 1

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) >= s.charAt(j)) {  // Changed to >= to allow equal characters
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        int maxLength = 0;
        for (int len : dp) {
            if (len > maxLength) {
                maxLength = len;
            }
        }

        return maxLength;
    }
}
