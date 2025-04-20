package strings.slidingwindow;
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
public class CountingSubStringsWithAllThreeCharacters {
    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println(countSubstrings(s)); // Output: 10
        s = "aaacb";
        System.out.println(countSubstrings(s)); // Output: 3
        s = "abc";
        System.out.println(countSubstrings(s)); // Output: 1
    }

    private static int countSubstrings(String s) {
        int count = 0;
        int[] lastSeen = {-1, -1, -1}; // Track last indices of a, b, c

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            lastSeen[c - 'a'] = i; // Update last seen index

            // The substring can start from the minimum of last seen indices
            int minLastSeen = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));

            if (minLastSeen != -1) {
                // All substrings ending at i and starting from 0 to minLastSeen are valid
                count += minLastSeen + 1;
            }
        }

        return count;
    }
}
