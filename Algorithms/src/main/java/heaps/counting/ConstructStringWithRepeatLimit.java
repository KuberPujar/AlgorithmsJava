package heaps.counting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*

You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.

Return the lexicographically largest repeatLimitedString possible.

A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.



Example 1:

Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
Example 2:

Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.


Constraints:

1 <= repeatLimit <= s.length <= 105
s consists of lowercase English letters.
 */
public class ConstructStringWithRepeatLimit {

        public String repeatLimitedString(String s, int repeatLimit) {
            // Step 1: Count frequency of each character
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Step 2: Use a max-heap to store characters by lexicographical order (largest first)
            PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> b - a);

            // Add all characters that have frequency > 0
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    maxHeap.offer((char)('a' + i));
                }
            }

            StringBuilder result = new StringBuilder();

            // Step 3: Build the result string
            while (!maxHeap.isEmpty()) {
                // Get the lexicographically largest character
                char first = maxHeap.poll();
                int firstFreq = freq[first - 'a'];

                // Use as many of this character as possible (up to repeatLimit)
                int useCount = Math.min(firstFreq, repeatLimit);

                // Add the character to result
                for (int i = 0; i < useCount; i++) {
                    result.append(first);
                }

                // Update frequency
                freq[first - 'a'] -= useCount;

                // If we still have more of this character, we need a different character to break the sequence
                if (freq[first - 'a'] > 0) {
                    if (maxHeap.isEmpty()) {
                        // No other character available, we're done
                        break;
                    }

                    // Get the next largest character
                    char second = maxHeap.poll();

                    // Add exactly one of the second character to break the sequence
                    result.append(second);
                    freq[second - 'a']--;

                    // Put characters back in heap if they still have frequency > 0
                    if (freq[second - 'a'] > 0) {
                        maxHeap.offer(second);
                    }
                    maxHeap.offer(first); // Put first character back since it still has remaining frequency
                }
            }

            return result.toString();
        }

        // Alternative approach using frequency array without heap (more efficient)
        public String repeatLimitedStringOptimized(String s, int repeatLimit) {
            // Count frequency of each character
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder result = new StringBuilder();
            int currentIndex = 25; // Start from 'z' (lexicographically largest)

            while (currentIndex >= 0) {
                // Find the next character with frequency > 0
                while (currentIndex >= 0 && freq[currentIndex] == 0) {
                    currentIndex--;
                }

                if (currentIndex < 0) break;

                char currentChar = (char)('a' + currentIndex);
                int useCount = Math.min(freq[currentIndex], repeatLimit);

                // Add the character to result
                for (int i = 0; i < useCount; i++) {
                    result.append(currentChar);
                }

                freq[currentIndex] -= useCount;

                // If we still have more of this character, find the next smaller character
                if (freq[currentIndex] > 0) {
                    int nextIndex = currentIndex - 1;

                    // Find next character with frequency > 0
                    while (nextIndex >= 0 && freq[nextIndex] == 0) {
                        nextIndex--;
                    }

                    if (nextIndex < 0) {
                        // No other character available
                        break;
                    }

                    // Add one character to break the sequence
                    result.append((char)('a' + nextIndex));
                    freq[nextIndex]--;

                    // Don't decrement currentIndex, we'll continue with the same character
                } else {
                    // Move to next character
                    currentIndex--;
                }
            }

            return result.toString();
        }

        // Helper class for cleaner heap-based solution
        static class CharFreq {
            char ch;
            int freq;

            CharFreq(char ch, int freq) {
                this.ch = ch;
                this.freq = freq;
            }
        }

        public String repeatLimitedStringWithClass(String s, int repeatLimit) {
            // Count frequencies
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char c : s.toCharArray()) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }

            // Max-heap based on character value (lexicographically largest first)
            PriorityQueue<CharFreq> maxHeap = new PriorityQueue<>((a, b) -> b.ch - a.ch);

            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                maxHeap.offer(new CharFreq(entry.getKey(), entry.getValue()));
            }

            StringBuilder result = new StringBuilder();

            while (!maxHeap.isEmpty()) {
                CharFreq first = maxHeap.poll();

                // Use as many of this character as possible
                int useCount = Math.min(first.freq, repeatLimit);
                for (int i = 0; i < useCount; i++) {
                    result.append(first.ch);
                }
                first.freq -= useCount;

                // If we still have more of this character
                if (first.freq > 0) {
                    if (maxHeap.isEmpty()) break;

                    CharFreq second = maxHeap.poll();
                    result.append(second.ch);
                    second.freq--;

                    // Put back if still has frequency
                    if (second.freq > 0) {
                        maxHeap.offer(second);
                    }
                    maxHeap.offer(first);
                }
            }

            return result.toString();
        }

        // Test the solution
        public static void main(String[] args) {
            ConstructStringWithRepeatLimit solution = new ConstructStringWithRepeatLimit();

            // Test Example 1
            String s1 = "cczazcc";
            int repeatLimit1 = 3;
            String result1 = solution.repeatLimitedString(s1, repeatLimit1);
            System.out.println("Example 1:");
            System.out.println("Input: s = \"" + s1 + "\", repeatLimit = " + repeatLimit1);
            System.out.println("Output: \"" + result1 + "\"");
            System.out.println("Expected: \"zzcccac\"");
            System.out.println();

            // Test Example 2
            String s2 = "aababab";
            int repeatLimit2 = 2;
            String result2 = solution.repeatLimitedString(s2, repeatLimit2);
            System.out.println("Example 2:");
            System.out.println("Input: s = \"" + s2 + "\", repeatLimit = " + repeatLimit2);
            System.out.println("Output: \"" + result2 + "\"");
            System.out.println("Expected: \"bbabaa\"");
            System.out.println();

            // Test edge cases
            String s3 = "aaabbbccc";
            int repeatLimit3 = 2;
            String result3 = solution.repeatLimitedString(s3, repeatLimit3);
            System.out.println("Edge case:");
            System.out.println("Input: s = \"" + s3 + "\", repeatLimit = " + repeatLimit3);
            System.out.println("Output: \"" + result3 + "\"");
            System.out.println();

            // Compare with optimized approach
            System.out.println("Optimized approach result: \"" + solution.repeatLimitedStringOptimized(s1, repeatLimit1) + "\"");
            System.out.println("Class-based approach result: \"" + solution.repeatLimitedStringWithClass(s1, repeatLimit1) + "\"");
        }
    }

/*
Algorithm Explanation:

1. **Character Counting**:
   - Count frequency of each character using array or HashMap
   - Time: O(n), Space: O(1) for array approach

2. **Greedy Strategy**:
   - Always try to use the lexicographically largest character first
   - Use as many as possible (up to repeatLimit)
   - When limit is reached, use one character of the next largest to break sequence

3. **Heap-Based Approach**:
   - Use max-heap to always get the lexicographically largest character
   - When current character limit is reached, use next largest character once
   - Continue until no more characters can be used

4. **Optimized Array Approach**:
   - Instead of heap, iterate through characters from 'z' to 'a'
   - More efficient as it avoids heap operations
   - Time: O(n + 26) = O(n), Space: O(1)

Algorithm Steps:
1. Count frequency of each character
2. While there are characters available:
   - Take the lexicographically largest character
   - Use up to repeatLimit consecutive occurrences
   - If more of this character exists, use one of the next largest character
   - Repeat until no valid moves possible

Time Complexity:
- Heap approach: O(n log 26) = O(n)
- Optimized approach: O(n)

Space Complexity: O(1) for character counting + O(n) for result = O(n)

Key Points:
- Greedy approach works because we always want lexicographically largest result
- Breaking sequences with smaller characters allows us to continue using larger ones
- The algorithm ensures no character appears more than repeatLimit times consecutively
*/
