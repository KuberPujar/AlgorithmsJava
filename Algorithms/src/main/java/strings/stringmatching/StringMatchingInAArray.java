package strings.stringmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of string words, return all strings in words that are a substring of another word. You can return the answer in any order.



Example 1:

Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
Example 2:

Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
Example 3:

Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.
 */
public class StringMatchingInAArray {
    public static void main(String[] args) {
        String[] words = {"mass", "as", "hero", "superhero"};
        System.out.println(stringMatching(words));
    }

    private static List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        int n = words.length;

        // Sort words by length in ascending order
        // Shorter words are more likely to be substrings of longer words
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (int i = 0; i < n; i++) {
            String current = words[i];
            // Only compare with longer words (words after current in the sorted array)
            for (int j = i + 1; j < n; j++) {
                if (words[j].contains(current)) {
                    result.add(current);
                    break; // No need to check other words once we find a match
                }
            }
        }

        return result;
    }
}
