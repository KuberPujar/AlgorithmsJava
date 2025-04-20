package strings.hashing;

import java.util.HashSet;
import java.util.Set;

/*
A pangram is a sentence where every letter of the English alphabet appears at least once.

Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.



Example 1:

Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
Output: true
Explanation: sentence contains at least one of every letter of the English alphabet.
Example 2:

Input: sentence = "leetcode"
Output: false


Constraints:

1 <= sentence.length <= 1000
sentence consists of lowercase English letters.
 */
public class Pangram {
    public static void main(String[] args) {
        String sentence1 = "thequickbrownfoxjumpsoverthelazydog";
        String sentence2 = "leetcode";

        System.out.println(checkIfPangram(sentence1)); // Output: true
        System.out.println(checkIfPangram(sentence2)); // Output: false
    }

    private static boolean checkIfPangram(String sentence){
        Set<Character> alphabet = new HashSet<>();

        for (char c : sentence.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                alphabet.add(c);
            }
        }

        return alphabet.size() == 26;
    }
}
