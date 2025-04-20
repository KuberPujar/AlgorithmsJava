package strings.bitmanipulation;
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
public class NiceString {
    public static void main(String[] args) {
        String s = "YazaAay";
        System.out.println(longestNiceSubstring(s)); // Output: "aAa"
    }

    private static String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";

        String longest = "";
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int lowerMask = 0;
            int upperMask = 0;

            for (int j = i; j < n; j++) {
                char c = s.charAt(j);

                // Update the bit masks
                if (Character.isLowerCase(c)) {
                    lowerMask |= (1 << (c - 'a'));
                } else {
                    upperMask |= (1 << (c - 'A'));
                }

                // Check if the substring is nice
                if ((lowerMask ^ upperMask) == 0) {
                    String current = s.substring(i, j + 1);
                    if (current.length() > longest.length()) {
                        longest = current;
                    }
                }
            }
        }

        return longest;
    }
}
