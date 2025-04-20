package strings.stringmatching;
/*
Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.



Example 1:

Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.
Example 2:

Input: s = "aba"
Output: false
Example 3:

Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" twice.


Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 */
public class RepeatedSubStringPattern {
    public static void main(String[] args) {
        String s = "abab";
        System.out.println(repeatedSubstringPattern(s));
    }

    private static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        // Try all possible substring lengths that divide n
        for (int len = 1; len <= n / 2; len++) {
            if (n % len == 0) {
                String pattern = s.substring(0, len);
                boolean match = true;
                // Check if the pattern repeats throughout the string
                for (int i = len; i < n; i += len) {
                    if (!s.substring(i, i + len).equals(pattern)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    // More optimized version using string concatenation
    public boolean repeatedSubstringPatternOptimized(String s) {
        String doubled = s + s;
        return doubled.substring(1, doubled.length() - 1).contains(s);
    }
}
