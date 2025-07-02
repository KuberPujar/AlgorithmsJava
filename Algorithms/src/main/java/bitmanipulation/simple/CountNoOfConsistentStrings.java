package bitmanipulation.simple;
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
    public int countConsistentStrings(String allowed, String[] words) {
        int allowedMask = 0;
        for (char c : allowed.toCharArray()) {
            allowedMask |= 1 << (c - 'a');
        }
        int count = 0;
        for (String word : words) {
            int wordMask = 0;
            for (char c : word.toCharArray()) {
                wordMask |= 1 << (c - 'a');
            }
            if ((wordMask | allowedMask) == allowedMask) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountNoOfConsistentStrings solution = new CountNoOfConsistentStrings();
        String allowed1 = "ab";
        String[] words1 = {"ad", "bd", "aaab", "baa", "badab"};
        System.out.println(solution.countConsistentStrings(allowed1, words1)); // Output: 2

        String allowed2 = "abc";
        String[] words2 = {"a", "b", "c", "ab", "ac", "bc", "abc"};
        System.out.println(solution.countConsistentStrings(allowed2, words2)); // Output: 7

        String allowed3 = "cad";
        String[] words3 = {"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"};
        System.out.println(solution.countConsistentStrings(allowed3, words3)); // Output: 4
    }
}
