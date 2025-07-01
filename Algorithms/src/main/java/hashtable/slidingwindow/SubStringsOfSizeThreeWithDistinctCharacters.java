package hashtable.slidingwindow;

import java.util.HashMap;

/*
A string is good if there are no repeated characters.

Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.

Note that if there are multiple occurrences of the same substring, every occurrence should be counted.

A substring is a contiguous sequence of characters in a string.



Example 1:

Input: s = "xyzzaz"
Output: 1
Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
The only good substring of length 3 is "xyz".
Example 2:

Input: s = "aababcabc"
Output: 4
Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".


Constraints:

1 <= s.length <= 100
s​​​​​​ consists of lowercase English letters.
 */
public class SubStringsOfSizeThreeWithDistinctCharacters {
    public int countGoodSubstrings(String s) {
        if (s.length() < 3) return 0;

        int count = 0;
        HashMap<Character, Integer> window = new HashMap<>();

        // Initialize the first window
        for (int i = 0; i < 3; i++) {
            char c = s.charAt(i);
            window.put(c, window.getOrDefault(c, 0) + 1);
        }
        if (window.size() == 3) count++;

        // Slide the window through the rest of the string
        for (int i = 3; i < s.length(); i++) {
            // Remove the leftmost character of the previous window
            char leftChar = s.charAt(i - 3);
            if (window.get(leftChar) == 1) {
                window.remove(leftChar);
            } else {
                window.put(leftChar, window.get(leftChar) - 1);
            }

            // Add the new character to the window
            char newChar = s.charAt(i);
            window.put(newChar, window.getOrDefault(newChar, 0) + 1);

            // Check if current window has all unique characters
            if (window.size() == 3) {
                count++;
            }
        }

        return count;
    }

    // Alternative solution using array as frequency map (more efficient for lowercase letters)
    public int countGoodSubstringsArray(String s) {
        if (s.length() < 3) return 0;

        int count = 0;
        int[] freq = new int[26]; // Since input is lowercase letters

        // Initialize first window
        for (int i = 0; i < 3; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        if (isGood(freq)) count++;

        // Slide window
        for (int i = 3; i < s.length(); i++) {
            // Remove leftmost character
            freq[s.charAt(i - 3) - 'a']--;
            // Add new character
            freq[s.charAt(i) - 'a']++;

            if (isGood(freq)) count++;
        }

        return count;
    }

    private boolean isGood(int[] freq) {
        for (int count : freq) {
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SubStringsOfSizeThreeWithDistinctCharacters solution = new SubStringsOfSizeThreeWithDistinctCharacters();

        // Example 1
        String s1 = "xyzzaz";
        System.out.println(solution.countGoodSubstrings(s1)); // Output: 1
        System.out.println(solution.countGoodSubstringsArray(s1)); // Output: 1

        // Example 2
        String s2 = "aababcabc";
        System.out.println(solution.countGoodSubstrings(s2)); // Output: 4
        System.out.println(solution.countGoodSubstringsArray(s2)); // Output: 4
    }
}
