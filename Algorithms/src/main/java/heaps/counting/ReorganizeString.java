package heaps.counting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.



Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class ReorganizeString {

    public String reorganizeString(String s) {
        // Count frequency of each character
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if reorganization is possible
        int maxCount = 0;
        for (int freq : count) {
            maxCount = Math.max(maxCount, freq);
        }

        // If any character appears more than (n+1)/2 times, it's impossible
        if (maxCount > (s.length() + 1) / 2) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        // Greedy approach: always pick the most frequent character
        // that's different from the last placed character
        while (result.length() < s.length()) {
            char lastChar = result.length() > 0 ? result.charAt(result.length() - 1) : '\0';

            // Find the character with maximum frequency that's different from lastChar
            int maxFreq = 0;
            char bestChar = '\0';

            for (int i = 0; i < 26; i++) {
                if (count[i] > maxFreq && (char)('a' + i) != lastChar) {
                    maxFreq = count[i];
                    bestChar = (char)('a' + i);
                }
            }

            // If no valid character found, return empty string
            if (bestChar == '\0') {
                return "";
            }

            // Place the character and decrement its count
            result.append(bestChar);
            count[bestChar - 'a']--;
        }

        return result.toString();
    }

    // Alternative approach using sorting for better performance
    public String reorganizeStringOptimized(String s) {
        // Count frequency of each character
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if reorganization is possible
        int maxCount = 0;
        for (int freq : count) {
            maxCount = Math.max(maxCount, freq);
        }

        if (maxCount > (s.length() + 1) / 2) {
            return "";
        }

        // Create list of characters with their frequencies
        List<int[]> charFreq = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                charFreq.add(new int[]{i, count[i]});
            }
        }

        // Sort by frequency in descending order
        Collections.sort(charFreq, (a, b) -> b[1] - a[1]);

        char[] result = new char[s.length()];
        int index = 0;

        // Place characters at even positions first (0, 2, 4, ...)
        for (int[] cf : charFreq) {
            char ch = (char)('a' + cf[0]);
            int freq = cf[1];

            for (int i = 0; i < freq; i++) {
                if (index >= s.length()) {
                    index = 1; // Switch to odd positions
                }
                result[index] = ch;
                index += 2;
            }
        }

        return new String(result);
    }

    // Simple counting approach with array scanning
    public String reorganizeStringSimple(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check feasibility
        int maxFreq = Arrays.stream(count).max().orElse(0);
        if (maxFreq > (s.length() + 1) / 2) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while (sb.length() < s.length()) {
            char prev = sb.length() > 0 ? sb.charAt(sb.length() - 1) : 0;

            // Find character with max frequency that's different from prev
            int maxCount = 0;
            int bestIndex = -1;

            for (int i = 0; i < 26; i++) {
                if (count[i] > maxCount && (char)('a' + i) != prev) {
                    maxCount = count[i];
                    bestIndex = i;
                }
            }

            if (bestIndex == -1) {
                return ""; // Should not happen if feasibility check passed
            }

            sb.append((char)('a' + bestIndex));
            count[bestIndex]--;
        }

        return sb.toString();
    }

    // Test method
    public static void main(String[] args) {
        ReorganizeString sol = new ReorganizeString();

        // Example 1: s = "aab"
        String s1 = "aab";
        System.out.println("Example 1:");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: \"" + sol.reorganizeString(s1) + "\"");
        System.out.println("Expected: \"aba\" or \"baa\"");
        System.out.println("Optimized: \"" + sol.reorganizeStringOptimized(s1) + "\"");
        System.out.println("Simple: \"" + sol.reorganizeStringSimple(s1) + "\"");
        System.out.println();

        // Example 2: s = "aaab"
        String s2 = "aaab";
        System.out.println("Example 2:");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: \"" + sol.reorganizeString(s2) + "\"");
        System.out.println("Expected: \"\"");
        System.out.println("Optimized: \"" + sol.reorganizeStringOptimized(s2) + "\"");
        System.out.println("Simple: \"" + sol.reorganizeStringSimple(s2) + "\"");
        System.out.println();

        // Additional test cases
        System.out.println("Additional test cases:");

        String[] testCases = {
                "aabbcc",    // Should work: "abcabc"
                "aaabbc",    // Should work: "ababac"
                "vvvlo",     // Should work: "vovlv"
                "aaa",       // Should fail: ""
                "ab",        // Should work: "ab"
                "a",         // Should work: "a"
                "abcdef",    // Should work: "abcdef"
                "aaabb"      // Should work: "ababa"
        };

        for (String test : testCases) {
            String result = sol.reorganizeString(test);
            System.out.println("Input: \"" + test + "\" -> Output: \"" + result + "\"");

            // Verify the result is valid
            if (!result.isEmpty()) {
                boolean valid = isValid(result);
                System.out.println("  Valid: " + valid);
                if (!valid) {
                    System.out.println("  ERROR: Invalid result!");
                }
            }
        }
    }

    // Helper method to validate if a string has no adjacent duplicates
    private static boolean isValid(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
