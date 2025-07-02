package hashtable.dp;

import java.util.HashMap;
import java.util.Map;

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
public class CountSubStringDiffByOneChar {
    public int countSubstrings(String s, String t) {
        int m = s.length();
        int n = t.length();
        int result = 0;

        // Precompute all substrings of s with their lengths
        Map<Integer, Map<String, Integer>> sSubstrings = new HashMap<>();
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < m; j++) {
                sb.append(s.charAt(j));
                String substr = sb.toString();
                int len = substr.length();
                sSubstrings.putIfAbsent(len, new HashMap<>());
                sSubstrings.get(len).put(substr, sSubstrings.get(len).getOrDefault(substr, 0) + 1);
            }
        }

        // Generate all substrings of t and compare with s substrings of same length
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) {
                sb.append(t.charAt(j));
                String tSubstr = sb.toString();
                int len = tSubstr.length();

                if (!sSubstrings.containsKey(len)) continue;

                // Compare with all s substrings of same length
                for (Map.Entry<String, Integer> entry : sSubstrings.get(len).entrySet()) {
                    String sSubstr = entry.getKey();
                    int count = entry.getValue();

                    // Count differences
                    int diff = 0;
                    for (int k = 0; k < len; k++) {
                        if (sSubstr.charAt(k) != tSubstr.charAt(k)) {
                            diff++;
                            if (diff > 1) break;
                        }
                    }

                    if (diff == 1) {
                        result += count;
                    }
                }
            }
        }

        return result;
    }

    public int countSubstringsDp(String s, String t) {
            int m = s.length();
            int n = t.length();
            int result = 0;

            // Precompute all substrings of s with their lengths using hash table
            Map<Integer, Map<String, Integer>> sSubstrings = new HashMap<>();
            for (int i = 0; i < m; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < m; j++) {
                    sb.append(s.charAt(j));
                    String substr = sb.toString();
                    int len = substr.length();
                    sSubstrings.putIfAbsent(len, new HashMap<>());
                    sSubstrings.get(len).put(substr, sSubstrings.get(len).getOrDefault(substr, 0) + 1);
                }
            }

            // DP approach for t substrings
            for (int len = 1; len <= n; len++) {
                if (!sSubstrings.containsKey(len)) continue;

                // Generate all substrings of t with current length
                for (int i = 0; i <= n - len; i++) {
                    String tSubstr = t.substring(i, i + len);

                    // Compare with all s substrings of same length
                    for (Map.Entry<String, Integer> entry : sSubstrings.get(len).entrySet()) {
                        String sSubstr = entry.getKey();
                        int count = entry.getValue();

                        // Count differences using optimized comparison
                        int diff = 0;
                        for (int k = 0; k < len && diff <= 1; k++) {
                            if (sSubstr.charAt(k) != tSubstr.charAt(k)) {
                                diff++;
                            }
                        }

                        if (diff == 1) {
                            result += count;
                        }
                    }
                }
            }

            return result;
    }

    public static void main(String[] args) {
        CountSubStringDiffByOneChar solution = new CountSubStringDiffByOneChar();
        String s = "aba";
        String t = "baba";
        int result = solution.countSubstrings(s, t);
        System.out.println("Number of substrings differing by one character: " + result); // Output: 6

        // Test with another example
        s = "ab";
        t = "bb";
        result = solution.countSubstringsDp(s, t);
        System.out.println("Number of substrings differing by one character: " + result); // Output: 3
    }
}
