package strings.bitmanipulation;
/*
You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.



Example 1:

Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
Example 2:

Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
Example 3:

Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.


Constraints:

1 <= words.length <= 104
1 <= allowed.length <= 26
1 <= words[i].length <= 10
The characters in allowed are distinct.
words[i] and allowed contain only lowercase English letters.
 */
public class CountNoOfConsistentStrings {
    public static void main(String[] args) {
        String allowed = "ab";
        String[] words = {"ad", "bd", "aaab", "baa", "badab"};
        System.out.println(countConsistentStrings(allowed, words)); // Output: 2
    }

    private static int countConsistentStrings(String allowed, String[] words) {
        // Create a bitmask of allowed characters
        int mask = 0;
        for (char c : allowed.toCharArray()) {
            mask |= 1 << (c - 'a');
        }

        int count = 0;
        for (String word : words) {
            if (isConsistent(word, mask)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isConsistent(String word, int mask) {
        for (char c : word.toCharArray()) {
            if ((mask & (1 << (c - 'a'))) == 0) {
                return false;
            }
        }
        return true;
    }
}
