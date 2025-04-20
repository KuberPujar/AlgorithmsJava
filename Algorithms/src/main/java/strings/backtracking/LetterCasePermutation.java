package strings.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. Return the output in any order.



Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]


Constraints:

1 <= s.length <= 12
s consists of lowercase English letters, uppercase English letters, and digits.
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        String s = "a1b2";
        System.out.println(letterCasePermutation(s)); // Output: ["a1b2","a1B2","A1b2","A1B2"]
    }

    private static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private static void backtrack(char[] chars, int index, List<String> result) {
        // Base case: if we've processed all characters
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        // If current character is a letter, explore both cases
        if (Character.isLetter(chars[index])) {
            // Lowercase branch
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(chars, index + 1, result);

            // Uppercase branch
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(chars, index + 1, result);
        } else {
            // For digits, just move to next character
            backtrack(chars, index + 1, result);
        }
    }
}
