package greaterpyramid.c2;
/*
Sort the Array
You have to sort the string s in decreasing order based on the frequency of the characters. Return the sorted string. if frequency of two character are same then sort in alphabetical order.

Example 1
Input
s = "tree"
Output
"eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once. So 'e' must appear before both 'r' and 't'. "eetr" is not a valid answer as r appears before t.

Example 2
Input
 s = "cccaaa"
Output
"aaaccc"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is valid answer. Note that "cccaaa" is not a valid answer as both 'c' and 'a' appear three times but a comes before c in dictionary. Note that "cacaca" is incorrect, as the same characters must be together.

Example 3
Input
 s = "aA"
Output
"Aa"
Constraints:
1 <= s.length <= 5 * 10^5
s consists of uppercase and lowercase English letters and digits.
 */
import java.util.*;

public class SortingCharacterByFrequency {
    public static String frequencySort(String s) {
        // Count frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Create a list of characters and sort them based on frequency and alphabetical order
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());
        Collections.sort(characters, (a, b) -> {
            int freqCompare = frequencyMap.get(b) - frequencyMap.get(a);
            if (freqCompare == 0) {
                return Character.compare(a, b); // Alphabetical order if frequencies are equal
            }
            return freqCompare;
        });

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            int frequency = frequencyMap.get(c);
            for (int i = 0; i < frequency; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        // Test cases
        System.out.println(frequencySort("tree"));    // Output: "eert"
        System.out.println(frequencySort("cccaaa"));  // Output: "aaaccc"
        System.out.println(frequencySort("aA"));      // Output: "Aa"
    }
}