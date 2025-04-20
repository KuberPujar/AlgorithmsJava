package strings.slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.



Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length
 */
public class MaximumNoOfVowelsInaString {
    public static void main(String[] args) {
        String s = "abciiidef";
        int k = 3;
        System.out.println(maxVowels(s, k)); // Output: 3
        s = "aeiou";
        k = 2;
        System.out.println(maxVowels(s, k)); // Output: 2
        s = "leetcode";
        k = 3;
        System.out.println(maxVowels(s, k)); // Output: 2
    }

    private static int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int maxCount = 0;
        int currentCount = 0;

        // Initialize the first window
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))) {
                currentCount++;
            }
        }
        maxCount = currentCount;

        // Slide the window through the rest of the string
        for (int i = k; i < s.length(); i++) {
            // Remove the leftmost character of the previous window
            if (vowels.contains(s.charAt(i - k))) {
                currentCount--;
            }
            // Add the new character to the window
            if (vowels.contains(s.charAt(i))) {
                currentCount++;
            }
            // Update the maximum count
            maxCount = Math.max(maxCount, currentCount);

            // Early exit if we find the maximum possible
            if (maxCount == k) {
                break;
            }
        }

        return maxCount;
    }
}
