package strings.bitmanipulation;

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
        System.out.println(letterCasePermutation(s));
    }

    private static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        int letterCount = 0;

        // Count the number of letters in the string
        for (char c : chars) {
            if (Character.isLetter(c)) {
                letterCount++;
            }
        }

        // Generate all possible bit masks for the letters
        for (int mask = 0; mask < (1 << letterCount); mask++) {
            result.add(applyMask(chars, mask));
        }

        return result;
    }

    private static String applyMask(char[] chars, int mask) {
        char[] newChars = chars.clone();
        int bitPos = 0;

        for (int i = 0; i < newChars.length; i++) {
            if (Character.isLetter(newChars[i])) {
                // Check the current bit in the mask
                if ((mask & (1 << bitPos)) != 0) {
                    // Toggle case
                    newChars[i] = Character.isLowerCase(newChars[i]) ?
                            Character.toUpperCase(newChars[i]) :
                            Character.toLowerCase(newChars[i]);
                }
                bitPos++;
            }
        }

        return new String(newChars);
    }
}
