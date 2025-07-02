package bitmanipulation.backtracking;

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

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        backtrack(chars, 0, result);
        return result;
    }

    private void backtrack(char[] chars, int idx, List<String> result) {
        if (idx == chars.length) {
            result.add(new String(chars));
            return;
        }
        char c = chars[idx];
        if (Character.isLetter(c)) {
            // Keep original case
            backtrack(chars, idx + 1, result);
            // Flip case using bit manipulation
            chars[idx] = (char) (c ^ (1 << 5));
            backtrack(chars, idx + 1, result);
            // Restore original character
            chars[idx] = c;
        } else {
            backtrack(chars, idx + 1, result);
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation solution = new LetterCasePermutation();
        String input = "a1b2";
        List<String> permutations = solution.letterCasePermutation(input);
        System.out.println(permutations); // Output: [a1b2, a1B2, A1b2, A1B2]
    }
}
