package hashtable.slidingwindow;

import java.util.HashMap;

/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.



Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
Example 3:

Input: s = "abc"
Output: 1


Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        int count = 0;
        int left = 0;
        HashMap<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // When we have all three characters, count valid substrings
            while (freq.size() == 3) {
                count += s.length() - right; // All substrings ending at right

                // Move left pointer to try to shrink the window
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }
        }

        return count;
    }

    // Alternative optimized solution using array instead of HashMap
    public int numberOfSubstringsArray(String s) {
        int count = 0;
        int left = 0;
        int[] freq = new int[3]; // Index 0: a, 1: b, 2: c

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'a']++;

            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                count += s.length() - right;

                char leftChar = s.charAt(left);
                freq[leftChar - 'a']--;
                left++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
       NumberOfSubstringsContainingAllThreeCharacters solution = new NumberOfSubstringsContainingAllThreeCharacters();

        // Example 1
        String s1 = "abcabc";
        System.out.println(solution.numberOfSubstrings(s1)); // Output: 10
        System.out.println(solution.numberOfSubstringsArray(s1)); // Output: 10

        // Example 2
        String s2 = "aaacb";
        System.out.println(solution.numberOfSubstrings(s2)); // Output: 3
        System.out.println(solution.numberOfSubstringsArray(s2)); // Output: 3

        // Example 3
        String s3 = "abc";
        System.out.println(solution.numberOfSubstrings(s3)); // Output: 1
        System.out.println(solution.numberOfSubstringsArray(s3)); // Output: 1
    }
}
