package strings.counting;
/*
Given a string s, return true if s is a good string, or false otherwise.

A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).



Example 1:

Input: s = "abacbc"
Output: true
Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
Example 2:

Input: s = "aaabb"
Output: false
Explanation: The characters that appear in s are 'a' and 'b'.
'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class CheckingGoodString {
    public static void main(String[] args) {
        String s = "abacbc";
        // Example 1
        System.out.println(isGoodString(s)); // Output: true
        s = "aaabb";
        // Example 2
        System.out.println(isGoodString(s)); // Output: false
    }

    private static boolean isGoodString(String s) {
        // Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Find the first non-zero frequency to compare with others
        int commonFreq = 0;
        for (int count : freq) {
            if (count > 0) {
                commonFreq = count;
                break;
            }
        }

        // Check all non-zero frequencies match the common frequency
        for (int count : freq) {
            if (count > 0 && count != commonFreq) {
                return false;
            }
        }

        return true;
    }
}
