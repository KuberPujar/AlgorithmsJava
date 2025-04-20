package strings.recursion;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = partition(s);
        System.out.println(result);
    }

    private static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        // Base case: if we've processed the entire string
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try all possible partitions
        for (int end = start; end < s.length(); end++) {
            // Check if current substring is a palindrome
            if (isPalindrome(s, start, end)) {
                // Add the palindrome substring to current partition
                current.add(s.substring(start, end + 1));

                // Recurse for the remaining substring
                backtrack(s, end + 1, current, result);

                // Backtrack - remove the last added substring
                current.removeLast();
            }
        }
    }

    // Helper method to check if a substring is palindrome
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
