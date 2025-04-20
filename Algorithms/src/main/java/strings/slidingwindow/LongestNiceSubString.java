package strings.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/*
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
public class LongestNiceSubString {
    public static void main(String[] args) {
        String s = "YazaAay";
        System.out.println(longestNiceSubstring(s)); // Output: "aAa"
        s = "Bb";
        System.out.println(longestNiceSubstring(s)); // Output: "Bb"
        s = "c";
        System.out.println(longestNiceSubstring(s)); // Output: ""
    }

    private static String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";

        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isNice(substring)) {
                    if (substring.length() > longest.length() ||
                            (substring.length() == longest.length() && i < s.indexOf(longest))) {
                        longest = substring;
                    }
                }
            }
        }

        return longest;
    }

    private static boolean isNice(String s) {
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            chars.add(c);
        }

        for (char c : chars) {
            char oppositeCase = Character.isLowerCase(c) ?
                    Character.toUpperCase(c) : Character.toLowerCase(c);
            if (!chars.contains(oppositeCase)) {
                return false;
            }
        }

        return true;
    }
}
