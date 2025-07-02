package hashtable.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.



Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
 */
public class LargestStringChain {
    public int longestStrChain(String[] words) {
        // Sort words by length to process from shortest to longest
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        // Hash map to store the longest chain length for each word
        Map<String, Integer> dp = new HashMap<>();

        int maxChain = 1; // Minimum chain length is 1 (single word)

        for (String word : words) {
            int currentMax = 1; // Initialize chain length for current word

            // Generate all possible predecessors by removing one character
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                String predecessor = sb.deleteCharAt(i).toString();

                // If predecessor exists in our map, update current max chain length
                if (dp.containsKey(predecessor)) {
                    currentMax = Math.max(currentMax, dp.get(predecessor) + 1);
                }
            }

            // Store the longest chain for current word
            dp.put(word, currentMax);

            // Update overall maximum chain length
            maxChain = Math.max(maxChain, currentMax);
        }

        return maxChain;
    }

    public static void main(String[] args) {
        LargestStringChain lsc = new LargestStringChain();
        String[] words1 = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(lsc.longestStrChain(words1)); // Output: 4

        String[] words2 = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println(lsc.longestStrChain(words2)); // Output: 5

        String[] words3 = {"abcd", "dbqca"};
        System.out.println(lsc.longestStrChain(words3)); // Output: 1
    }
}
