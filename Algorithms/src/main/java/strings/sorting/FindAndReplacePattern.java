package strings.sorting;

import java.util.ArrayList;
import java.util.List;

/*
Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.



Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
Example 2:

Input: words = ["a","b","c"], pattern = "a"
Output: ["a","b","c"]


Constraints:

1 <= pattern.length <= 20
1 <= words.length <= 50
words[i].length == pattern.length
pattern and words[i] are lowercase English letters.
 */
public class FindAndReplacePattern {
    public static void main(String[] args) {
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "abb";
        System.out.println(findAndReplacePattern(words, pattern)); // Output: ["mee","aqq"]

        words = new String[]{"a", "b", "c"};
        pattern = "a";
        System.out.println(findAndReplacePattern(words, pattern)); // Output: ["a","b","c"]
    }

    private static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        int[] patternEncoded = encode(pattern);

        for (String word : words) {
            if (matchesPattern(word, patternEncoded)) {
                result.add(word);
            }
        }
        return result;
    }

    private static int[] encode(String s) {
        int[] encoding = new int[s.length()];
        int[] charToIndex = new int[26];
        int counter = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charToIndex[c - 'a'] == 0) {
                charToIndex[c - 'a'] = counter++;
            }
            encoding[i] = charToIndex[c - 'a'];
        }
        return encoding;
    }

    private static boolean matchesPattern(String word, int[] patternEncoded) {
        if (word.length() != patternEncoded.length) return false;

        int[] wordEncoded = encode(word);
        for (int i = 0; i < wordEncoded.length; i++) {
            if (wordEncoded[i] != patternEncoded[i]) {
                return false;
            }
        }
        return true;
    }
}
