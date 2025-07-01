package hashtable.counting;

import java.util.HashMap;
import java.util.HashSet;

/*
Given a string s, return true if s is a good string, or false otherwise.

A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).



Example 1:

Input: s = "abacbc"
Output: true
Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
Example 2:

Input: s = "aaabb"
Output: false
Explanation: The characters that appear in s are 'a' and 'b'.
'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class CheckAllNumberCharsHaveEqualOccurances {
    public static boolean isGoodString(String s) {
        // Step 1: Count frequency of each character
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Collect all unique frequencies
        HashSet<Integer> freqSet = new HashSet<>();
        for (int freq : freqMap.values()) {
            freqSet.add(freq);
        }

        // Step 3: If all frequencies are the same, set size should be 1
        return freqSet.size() == 1;
    }

    public static void main(String[] args) {
        String s1 = "abacbc";
        System.out.println("Output: " + isGoodString(s1)); // true

        String s2 = "aaabb";
        System.out.println("Output: " + isGoodString(s2)); // false
    }
}
