package hashtable.slidingwindow;

import java.util.HashMap;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubString {
        public String minWindow(String s, String t) {
            if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
                return "";
            }

            // Create frequency map for characters in t
            HashMap<Character, Integer> targetMap = new HashMap<>();
            for (char c : t.toCharArray()) {
                targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
            }

            int required = targetMap.size(); // Number of unique characters we need to match
            int left = 0, right = 0;
            int formed = 0; // Number of unique characters we've matched so far

            // Track current window character frequencies
            HashMap<Character, Integer> windowMap = new HashMap<>();

            // Result variables
            int[] result = {-1, 0, 0}; // {length, left, right}

            while (right < s.length()) {
                char c = s.charAt(right);
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

                // If current character's count in window matches target count, increment formed
                if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                    formed++;
                }

                // Try to contract the window from the left
                while (left <= right && formed == required) {
                    c = s.charAt(left);

                    // Update result if we found a smaller window
                    if (result[0] == -1 || right - left + 1 < result[0]) {
                        result[0] = right - left + 1;
                        result[1] = left;
                        result[2] = right;
                    }

                    // Remove left character from window
                    windowMap.put(c, windowMap.get(c) - 1);
                    if (targetMap.containsKey(c) && windowMap.get(c) < targetMap.get(c)) {
                        formed--;
                    }

                    left++;
                }

                right++;
            }

            return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
        }

        // Optimized version using array instead of HashMap (faster for ASCII characters)
        public String minWindowOptimized(String s, String t) {
            if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
                return "";
            }

            int[] targetCount = new int[128]; // ASCII characters
            for (char c : t.toCharArray()) {
                targetCount[c]++;
            }

            int required = 0;
            for (int count : targetCount) {
                if (count > 0) required++;
            }

            int left = 0, right = 0;
            int formed = 0;
            int[] windowCount = new int[128];
            int[] result = {-1, 0, 0}; // {length, left, right}

            while (right < s.length()) {
                char c = s.charAt(right);
                windowCount[c]++;

                if (targetCount[c] > 0 && windowCount[c] == targetCount[c]) {
                    formed++;
                }

                while (left <= right && formed == required) {
                    c = s.charAt(left);

                    if (result[0] == -1 || right - left + 1 < result[0]) {
                        result[0] = right - left + 1;
                        result[1] = left;
                        result[2] = right;
                    }

                    windowCount[c]--;
                    if (targetCount[c] > 0 && windowCount[c] < targetCount[c]) {
                        formed--;
                    }

                    left++;
                }

                right++;
            }

            return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
        }

        public static void main(String[] args) {
            MinimumWindowSubString solution = new MinimumWindowSubString();

            // Example 1
            String s1 = "ADOBECODEBANC";
            String t1 = "ABC";
            System.out.println(solution.minWindow(s1, t1)); // Output: "BANC"
            System.out.println(solution.minWindowOptimized(s1, t1)); // Output: "BANC"

            // Example 2
            String s2 = "a";
            String t2 = "a";
            System.out.println(solution.minWindow(s2, t2)); // Output: "a"
            System.out.println(solution.minWindowOptimized(s2, t2)); // Output: "a"

            // Example 3
            String s3 = "a";
            String t3 = "aa";
            System.out.println(solution.minWindow(s3, t3)); // Output: ""
            System.out.println(solution.minWindowOptimized(s3, t3)); // Output: ""
        }
}
