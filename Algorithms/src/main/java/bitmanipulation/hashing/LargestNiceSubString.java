package bitmanipulation.hashing;

import java.util.HashSet;
import java.util.Set;

/*


Hint
A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.



Example 1:

Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.
Example 2:

Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
Example 3:

Input: s = "c"
Output: ""
Explanation: There are no nice substrings.


Constraints:

1 <= s.length <= 100
s consists of uppercase and lowercase English letters.
 */
public class LargestNiceSubString {

    public String longestNiceSubstring(String s) {
        int n = s.length();
        String res = "";
        Set<String> checked = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (Character.isLowerCase(c)) {
                    lower |= 1 << (c - 'a');
                } else {
                    upper |= 1 << (c - 'A');
                }
                String key = i + "," + j;
                if (checked.contains(key)) continue;
                checked.add(key);
                if (lower == upper && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LargestNiceSubString solution = new LargestNiceSubString();
        String s1 = "YazaAay";
        System.out.println(solution.longestNiceSubstring(s1)); // Output: "aAa"

        String s2 = "Bb";
        System.out.println(solution.longestNiceSubstring(s2)); // Output: "Bb"

        String s3 = "c";
        System.out.println(solution.longestNiceSubstring(s3)); // Output: ""
    }
}
