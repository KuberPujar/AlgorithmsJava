package hashtable.counting;

import java.util.HashMap;
import java.util.Hashtable;

/*
You are given a string s. Reorder the string using the following algorithm:

Remove the smallest character from s and append it to the result.
Remove the smallest character from s that is greater than the last appended character, and append it to the result.
Repeat step 2 until no more characters can be removed.
Remove the largest character from s and append it to the result.
Remove the largest character from s that is smaller than the last appended character, and append it to the result.
Repeat step 5 until no more characters can be removed.
Repeat steps 1 through 6 until all characters from s have been removed.
If the smallest or largest character appears more than once, you may choose any occurrence to append to the result.

Return the resulting string after reordering s using this algorithm.



Example 1:

Input: s = "aaaabbbbcccc"
Output: "abccbaabccba"
Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
After steps 4, 5 and 6 of the first iteration, result = "abccba"
First iteration is done. Now s = "aabbcc" and we go back to step 1
After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
Example 2:

Input: s = "rat"
Output: "art"
Explanation: The word "rat" becomes "art" after re-ordering it with the mentioned algorithm.


Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters.
 */
public class IncreasingDecreasingString {
    public static String sortString(String s) {
        // Step 1: Count frequency of each character using a hashtable
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        int totalChars = s.length();

        while (result.length() < totalChars) {
            // Step 2: Pick smallest available characters in increasing order
            for (char c = 'a'; c <= 'z'; c++) {
                if (countMap.getOrDefault(c, 0) > 0) {
                    result.append(c);
                    countMap.put(c, countMap.get(c) - 1);
                }
            }
            // Step 3: Pick largest available characters in decreasing order
            for (char c = 'z'; c >= 'a'; c--) {
                if (countMap.getOrDefault(c, 0) > 0) {
                    result.append(c);
                    countMap.put(c, countMap.get(c) - 1);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s1 = "aaaabbbbcccc";
        System.out.println("Output: " + sortString(s1)); // abccbaabccba

        String s2 = "rat";
        System.out.println("Output: " + sortString(s2)); // art
    }
}
