package bitmanipulation.dp;
/*
Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.



Example 1:

example-1
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
The product of their lengths is: 3 * 3 = 9.
Example 2:

Input: s = "bb"
Output: 1
Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
The product of their lengths is: 1 * 1 = 1.
Example 3:

Input: s = "accbcaxxcxx"
Output: 25
Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
The product of their lengths is: 5 * 5 = 25.


Constraints:

2 <= s.length <= 12
s consists of lowercase English letters only.
 */
public class MaximumProductofTheLengthOfTheTwoPalindromeSubsequences {

    public int maxProduct(String s) {
        int n = s.length();
        int size = 1 << n;
        int[] palLen = new int[size];

        // Precompute the length of the longest palindromic subsequence for each mask
        for (int mask = 1; mask < size; mask++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sb.append(s.charAt(i));
                }
            }
            if (isPalindrome(sb)) {
                palLen[mask] = sb.length();
            }
        }

        int maxProduct = 0;
        // Try all pairs of disjoint masks
        for (int mask1 = 1; mask1 < size; mask1++) {
            if (palLen[mask1] == 0) continue;
            for (int mask2 = mask1 + 1; mask2 < size; mask2++) {
                if ((mask1 & mask2) == 0 && palLen[mask2] > 0) {
                    maxProduct = Math.max(maxProduct, palLen[mask1] * palLen[mask2]);
                }
            }
        }
        return maxProduct;
    }

    private boolean isPalindrome(StringBuilder sb) {
        int l = 0, r = sb.length() - 1;
        while (l < r) {
            if (sb.charAt(l++) != sb.charAt(r--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MaximumProductofTheLengthOfTheTwoPalindromeSubsequences solution = new MaximumProductofTheLengthOfTheTwoPalindromeSubsequences();
        String s = "leetcodecom";
        int result = solution.maxProduct(s);
        System.out.println("Maximum product of lengths of two disjoint palindromic subsequences: " + result); // Output: 9
    }
}
