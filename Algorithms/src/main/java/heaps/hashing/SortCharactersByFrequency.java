package heaps.hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
    /**
     * Sorts the given string based on the frequency of its characters.
     *
     * @param s The input string.
     * @return The sorted string.
     */
    public String frequencySort(String s) {
        // Step 1: Count the frequency of each character using a HashMap.
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Use a max-heap to sort characters by frequency.
        // The PriorityQueue will store characters, and its comparator will
        // prioritize the character with the highest frequency from the map.
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));
        maxHeap.addAll(frequencyMap.keySet());

        // Step 3: Build the result string.
        // Greedily append characters from the max-heap. The heap ensures
        // we always process the most frequent character next.
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            int count = frequencyMap.get(c);
            // Append the character 'count' times.
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Main method for testing the solution with the provided examples.
     */
    public static void main(String[] args) {
        SortCharactersByFrequency sol = new SortCharactersByFrequency();

        // Example 1
        String s1 = "tree";
        String result1 = sol.frequencySort(s1);
        System.out.println("Example 1 Input: s = \"" + s1 + "\"");
        System.out.println("Example 1 Output: \"" + result1 + "\""); // Expected: "eert" or "eetr"

        // Example 2
        String s2 = "cccaaa";
        String result2 = sol.frequencySort(s2);
        System.out.println("Example 2 Input: s = \"" + s2 + "\"");
        System.out.println("Example 2 Output: \"" + result2 + "\""); // Expected: "cccaaa" or "aaaccc"

        // Example 3
        String s3 = "Aabb";
        String result3 = sol.frequencySort(s3);
        System.out.println("Example 3 Input: s = \"" + s3 + "\"");
        System.out.println("Example 3 Output: \"" + result3 + "\""); // Expected: "bbAa" or "bbaA"
    }
}
