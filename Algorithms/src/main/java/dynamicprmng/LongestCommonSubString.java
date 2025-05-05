package dynamicprmng;
/*
Longest Common Substring
Given two strings, find out the longest common substring.
A substring is a contiguous segment within a string.

Input:
str1 : First String

str2 : Second string
Output:
Return a string which is a longest common substring in both str1 and str2.
 If there are multiple substring with longest length return which has the smallest starting index.
Examples:
Example 1:

Input:
str1 = "abcdefgabcdegh"

str2 = "abcdeghabfvsnm"
Output:

"abcdegh"
Explanation:
For the strings "abcdefgabcdegh" and "abcdeghabfvsnm":

The longest common substring is "abcdegh", appearing in the same order in both strings.
Example 2:

Input:
str1 = "abc"

str2 = "def"
Output:
""
Constraints:
1 <= str1.length <= 1000

1 <= str2.length <= 1000

str1 & str2 consist only of lowercase English letters.

Explanation:
For the strings "abc" and "def":

No common substring is present.
Note: The function should return the result.
 */
public class LongestCommonSubString {
    public static void main(String[] args) {
        String str1 = "abcdefgabcdegh";
        String str2 = "abcdeghabfvsnm";
        System.out.println("Longest Common Substring: " + longestCommonSubstring(str1, str2)); // Expected Output: "abcdegh"

        String str3 = "abc";
        String str4 = "def";
        System.out.println("Longest Common Substring: " + longestCommonSubstring(str3, str4)); // Expected Output: ""
    }

    private static String longestCommonSubstring(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLength = 0;
        int endIndex = -1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }

        return maxLength == 0 ? "" : str1.substring(endIndex - maxLength, endIndex);
    }
}
