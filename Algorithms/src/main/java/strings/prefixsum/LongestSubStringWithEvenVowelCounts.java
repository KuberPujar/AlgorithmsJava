package strings.prefixsum;

import java.util.HashMap;
import java.util.Map;

/*
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.



Example 1:

Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
Example 2:

Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.
Example 3:

Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.


Constraints:

1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
 */
public class LongestSubStringWithEvenVowelCounts {
    public static void main(String[] args) {
        String s = "eleetminicoworoep";
        System.out.println(longestSubstring(s));
    }

    private static int longestSubstring(String s) {
        // Map to store the first occurrence of each vowel pattern
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, -1); // Initialize with pattern 0 at index -1

        int maxLen = 0;
        int currentPattern = 0;

        // Bitmask mapping for vowels
        final int[] vowelMask = new int[26];
        vowelMask[0] = 1;
        vowelMask['e' - 'a'] = 2;
        vowelMask['i' - 'a'] = 4;
        vowelMask['o' - 'a'] = 8;
        vowelMask['u' - 'a'] = 16;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Update the current pattern if the character is a vowel
            if (vowelMask[c - 'a'] != 0) {
                currentPattern ^= vowelMask[c - 'a'];
            }

            // Check if we've seen this pattern before
            if (prefixMap.containsKey(currentPattern)) {
                maxLen = Math.max(maxLen, i - prefixMap.get(currentPattern));
            } else {
                prefixMap.put(currentPattern, i);
            }
        }

        return maxLen;
    }
}
