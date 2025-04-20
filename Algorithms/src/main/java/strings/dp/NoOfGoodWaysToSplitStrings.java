package strings.dp;

import java.util.HashSet;
import java.util.Set;

/*
You are given a string s.

A split is called good if you can split s into two non-empty strings sleft and sright where their concatenation is equal to s (i.e., sleft + sright = s) and the number of distinct letters in sleft and sright is the same.

Return the number of good splits you can make in s.



Example 1:

Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
Example 2:

Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").


Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
 */
public class NoOfGoodWaysToSplitStrings {
    public static void main(String[] args) {
        String s = "aacaba";
        System.out.println(numSplits(s)); // Output: 2

        s = "abcd";
        System.out.println(numSplits(s)); // Output: 1
    }


    private static int numSplits(String s) {
        int n = s.length();
        if (n == 1) return 0;

        // DP arrays to store distinct counts
        int[] leftDistinct = new int[n];
        int[] rightDistinct = new int[n];

        // Track characters from left to right
        Set<Character> leftSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            leftSet.add(s.charAt(i));
            leftDistinct[i] = leftSet.size();
        }

        // Track characters from right to left
        Set<Character> rightSet = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            rightSet.add(s.charAt(i));
            rightDistinct[i] = rightSet.size();
        }

        // Count good splits using DP arrays
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (leftDistinct[i] == rightDistinct[i + 1]) {
                count++;
            }
        }

        return count;
    }
}
