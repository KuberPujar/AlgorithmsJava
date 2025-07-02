package bitmanipulation.slidingwindow;

import java.util.*;

/*
The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.



Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]


Constraints:

1 <= s.length <= 105
s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if (n < 10) return Collections.emptyList();

        Map<Character, Integer> map = Map.of(
                'A', 0, 'C', 1, 'G', 2, 'T', 3
        );
        Set<Integer> seen = new HashSet<>();
        Set<String> res = new HashSet<>();
        int hash = 0, mask = (1 << 20) - 1; // 20 bits for 10 chars

        for (int i = 0; i < n; i++) {
            hash = ((hash << 2) | map.get(s.charAt(i))) & mask;
            if (i >= 9) {
                if (!seen.add(hash)) {
                    res.add(s.substring(i - 9, i + 1));
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        RepeatedDNASequences solution = new RepeatedDNASequences();
        System.out.println(solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")); // Output: ["AAAAACCCCC","CCCCCAAAAA"]
        System.out.println(solution.findRepeatedDnaSequences("AAAAAAAAAAAAA")); // Output: ["AAAAAAAAAA"]
        System.out.println(solution.findRepeatedDnaSequences("ACGTACGTACGT")); // Output: []
    }
}
