package greaterpyramid.c8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

Given an array of words, devise a method to encode these words into a single reference string and an array of indices in such a way that:

The length of the array of words equals the length of the array of indices.
The reference string ends with the '#' character.
For each index indices[i], the substring of the reference string starting at indices[i] and ending before the next '#' character equals words[i].
Your task is to return the length of the shortest possible reference string that can be generated to validly encode the given array of words.

Input Format:

The input consists of an array of words words, where each word consists only of lowercase letters.
Output Format:

Return the length of the shortest reference string s possible of any valid encoding of words.
Example 1

Input

words = ["time", "me", "bell"]
Output

`10`
Explanation:

A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
Example 2

Input

` words = ["t"]`
Output

`2`
Explanation:

A valid encoding would be s = "t#" and indices = [0].

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 7
words[i] consists of only lowercase letters.
 */
public class Encoder {
    public static int minimumLengthEncoding(String[] words) {
        // Use a set to store unique words
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        // Remove all suffixes of each word from the set
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                uniqueWords.remove(word.substring(i));
            }
        }

        // Calculate the total length of the reference string
        int length = 0;
        for (String word : uniqueWords) {
            length += word.length() + 1; // +1 for the '#' character
        }

        return length;
    }

    public static void main(String[] args) {
        String[] words1 = {"time", "me", "bell"};
        System.out.println(minimumLengthEncoding(words1)); // Output: 10

        String[] words2 = {"t"};
        System.out.println(minimumLengthEncoding(words2)); // Output: 2
    }
}
