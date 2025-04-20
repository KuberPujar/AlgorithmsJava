package strings.recursion;

import java.util.HashMap;
import java.util.Map;

/*
Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.



Example 1:

Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
Words "dad" and "dog" only get a score of 21.
Example 2:

Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
Output: 27
Explanation:
Score  a=4, b=4, c=4, x=5, z=10
Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
Word "xxxz" only get a score of 25.
Example 3:

Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
Output: 0
Explanation:
Letter "e" can only be used once.


Constraints:

1 <= words.length <= 14
1 <= words[i].length <= 15
1 <= letters.length <= 100
letters[i].length == 1
score.length == 26
0 <= score[i] <= 10
words[i], letters[i] contains only lower case English letters.
 */
public class MaximumScoreWordsFormedByLetters {
    public static void main(String[] args) {
        String[] words = {"dog", "cat", "dad", "good"};
        char[] letters = {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        int[] score = {1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2
                , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(maxScoreWords(words, letters, score)); // Output: 23
    }

    private static int maxScoreWords(String[] words, char[] letters, int[] score) {
        // Count available letters
        Map<Character, Integer> letterCount = new HashMap<>();
        for (char c : letters) {
            letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
        }

        // Calculate word scores and letter requirements
        int[] wordScores = new int[words.length];
        Map<Character, Integer>[] wordLetterCounts = new Map[words.length];

        for (int i = 0; i < words.length; i++) {
            wordScores[i] = 0;
            wordLetterCounts[i] = new HashMap<>();
            for (char c : words[i].toCharArray()) {
                wordScores[i] += score[c - 'a'];
                wordLetterCounts[i].put(c, wordLetterCounts[i].getOrDefault(c, 0) + 1);
            }
        }

        return backtrack(words, wordScores, wordLetterCounts, letterCount, 0);
    }

    private static int backtrack(String[] words, int[] wordScores, Map<Character, Integer>[] wordLetterCounts,
                          Map<Character, Integer> remainingLetters, int index) {
        if (index == words.length) {
            return 0;
        }

        // Option 1: Skip current word
        int maxScore = backtrack(words, wordScores, wordLetterCounts, remainingLetters, index + 1);

        // Option 2: Take current word if possible
        Map<Character, Integer> currentWordLetters = wordLetterCounts[index];
        boolean canTake = true;

        // Check if we have enough letters
        for (Map.Entry<Character, Integer> entry : currentWordLetters.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            if (remainingLetters.getOrDefault(c, 0) < count) {
                canTake = false;
                break;
            }
        }

        if (canTake) {
            // Use the letters
            for (Map.Entry<Character, Integer> entry : currentWordLetters.entrySet()) {
                char c = entry.getKey();
                int count = entry.getValue();
                remainingLetters.put(c, remainingLetters.get(c) - count);
            }

            // Recurse
            int scoreWithWord = wordScores[index] +
                    backtrack(words, wordScores, wordLetterCounts, remainingLetters, index + 1);

            maxScore = Math.max(maxScore, scoreWithWord);

            // Backtrack: return the letters
            for (Map.Entry<Character, Integer> entry : currentWordLetters.entrySet()) {
                char c = entry.getKey();
                int count = entry.getValue();
                remainingLetters.put(c, remainingLetters.get(c) + count);
            }
        }

        return maxScore;
    }
}
