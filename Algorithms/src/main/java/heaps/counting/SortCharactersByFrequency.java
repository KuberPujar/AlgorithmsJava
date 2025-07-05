package heaps.counting;

import java.util.*;

/*
Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.



Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.


Constraints:

1 <= s.length <= 5 * 105
s consists of uppercase and lowercase English letters and digits.
 */
public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        // Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Create list of characters and sort by frequency in descending order
        List<Character> chars = new ArrayList<>(freqMap.keySet());
        Collections.sort(chars, (a, b) -> freqMap.get(b) - freqMap.get(a));

        // Build result string
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            int freq = freqMap.get(c);
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Alternative approach using bucket sort for better performance
    public String frequencySortBucket(String s) {
        // Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Create buckets for each possible frequency
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= s.length(); i++) {
            buckets.add(new ArrayList<>());
        }

        // Place characters in buckets based on their frequency
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }

        // Build result string from highest frequency to lowest
        StringBuilder result = new StringBuilder();
        for (int i = buckets.size() - 1; i >= 0; i--) {
            for (char c : buckets.get(i)) {
                for (int j = 0; j < i; j++) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    // Approach using array counting for ASCII characters
    public String frequencySortArray(String s) {
        // Count frequency using array (supports ASCII characters)
        int[] count = new int[128]; // ASCII characters
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        // Create list of characters with their frequencies
        List<int[]> charFreq = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            if (count[i] > 0) {
                charFreq.add(new int[]{i, count[i]});
            }
        }

        // Sort by frequency in descending order
        Collections.sort(charFreq, (a, b) -> b[1] - a[1]);

        // Build result string
        StringBuilder result = new StringBuilder();
        for (int[] cf : charFreq) {
            char ch = (char) cf[0];
            int freq = cf[1];
            for (int i = 0; i < freq; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Most efficient approach using counting sort principle
    public String frequencySortOptimal(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // Count frequency of each character
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        // Find maximum frequency
        int maxFreq = 0;
        for (int freq : count) {
            maxFreq = Math.max(maxFreq, freq);
        }

        // Create frequency buckets
        List<Character>[] buckets = new List[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Place characters in buckets based on frequency
        for (int i = 0; i < 128; i++) {
            if (count[i] > 0) {
                buckets[count[i]].add((char) i);
            }
        }

        // Build result from highest frequency to lowest
        StringBuilder result = new StringBuilder();
        for (int freq = maxFreq; freq >= 1; freq--) {
            for (char c : buckets[freq]) {
                for (int i = 0; i < freq; i++) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    // Simple approach using character frequency pairs
    public String frequencySortSimple(String s) {
        // Count frequencies
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Convert to list of pairs and sort
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        // Build result
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entries) {
            char c = entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Test method
    public static void main(String[] args) {
        SortCharactersByFrequency sol = new SortCharactersByFrequency();

        // Example 1: s = "tree"
        String s1 = "tree";
        System.out.println("Example 1:");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: \"" + sol.frequencySort(s1) + "\"");
        System.out.println("Expected: \"eert\" or \"eetr\"");
        System.out.println("Bucket: \"" + sol.frequencySortBucket(s1) + "\"");
        System.out.println("Array: \"" + sol.frequencySortArray(s1) + "\"");
        System.out.println("Optimal: \"" + sol.frequencySortOptimal(s1) + "\"");
        System.out.println();

        // Example 2: s = "cccaaa"
        String s2 = "cccaaa";
        System.out.println("Example 2:");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: \"" + sol.frequencySort(s2) + "\"");
        System.out.println("Expected: \"aaaccc\" or \"cccaaa\"");
        System.out.println("Bucket: \"" + sol.frequencySortBucket(s2) + "\"");
        System.out.println("Array: \"" + sol.frequencySortArray(s2) + "\"");
        System.out.println("Optimal: \"" + sol.frequencySortOptimal(s2) + "\"");
        System.out.println();

        // Example 3: s = "Aabb"
        String s3 = "Aabb";
        System.out.println("Example 3:");
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: \"" + sol.frequencySort(s3) + "\"");
        System.out.println("Expected: \"bbAa\" or \"bbaA\"");
        System.out.println("Bucket: \"" + sol.frequencySortBucket(s3) + "\"");
        System.out.println("Array: \"" + sol.frequencySortArray(s3) + "\"");
        System.out.println("Optimal: \"" + sol.frequencySortOptimal(s3) + "\"");
        System.out.println();

        // Additional test cases
        System.out.println("Additional test cases:");

        String[] testCases = {
                "aabbcc",        // Equal frequencies
                "abcdef",        // All unique
                "aaabbbccc",     // All same frequency
                "Programming",   // Mixed case
                "1234567890",    // Digits
                "a",             // Single character
                "aA",            // Case sensitive
                "zzabc123"       // Mixed characters
        };

        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output: \"" + sol.frequencySort(test) + "\"");
            System.out.println("Optimal: \"" + sol.frequencySortOptimal(test) + "\"");
            System.out.println();
        }

        // Performance test
        System.out.println("Performance test with large string:");
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            large.append("abcdefghijklmnopqrstuvwxyz");
        }
        String largeStr = large.toString();

        long start = System.currentTimeMillis();
        String result = sol.frequencySortOptimal(largeStr);
        long end = System.currentTimeMillis();

        System.out.println("Large string length: " + largeStr.length());
        System.out.println("Result length: " + result.length());
        System.out.println("Time taken: " + (end - start) + " ms");
    }
}
