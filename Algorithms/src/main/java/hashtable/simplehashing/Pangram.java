package hashtable.simplehashing;

import java.util.HashSet;

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
    public static boolean isPangram(String sentence) {
        // HashSet to store unique letters
        HashSet<Character> letters = new HashSet<>();

        // Add each character to the set
        for (char c : sentence.toCharArray()) {
            letters.add(c);
        }

        // If the set contains all 26 letters, it's a pangram
        return letters.size() == 26;
    }

    public static void main(String[] args) {
        // Example 1
        String sentence1 = "thequickbrownfoxjumpsoverthelazydog";
        System.out.println("Output: " + isPangram(sentence1)); // Output: true

        // Example 2
        String sentence2 = "leetcode";
        System.out.println("Output: " + isPangram(sentence2)); // Output: false
    }
}
