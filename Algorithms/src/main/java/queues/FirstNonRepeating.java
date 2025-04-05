package queues;
/*
First Non Repeating
Given a string str which denotes a stream of characters, your task is to find a new string output_str. output_str is formed such that we have to find the first non-repeating character at each instance when a character is inserted into the stream and append it at the end of output_str. If no such non-repeating character is found, then append 'X' at the end of output_str.

Input Format:

Only one line which contains a string that needs to be converted to output_str.
Output Format:

Return the updated string.
Sample Input 1:

dabc
Sample Output 1:

dddd
Explanation:

"d" - first non-repeating character 'd'.
"da" - first non-repeating character 'd'.
"dab" - first non-repeating character 'd'.
"dabc" - first non-repeating character 'd'.
Sample Input 2:

bbe
Sample Output 2:

bXe
Explanation:

"b" - first non-repeating character 'b'.
"bb" - no non-repeating character so 'X'.
"bbe" - first non-repeating character 'e'.
Constraints:

(1 <= |str| <= 10^5)
Note: The function should return the result.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class FirstNonRepeating {
    public static void main(String[] args) {
        String str = "dabc"; // Sample input
        System.out.println(getFirstNonRepeatingStream(str));
    }

    public static String getFirstNonRepeatingStream(String str) {
        // To store frequency of characters
        HashMap<Character, Integer> freqMap = new HashMap<>();
        // To maintain the order of characters and potential candidates
        Queue<Character> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // Update frequency map
            freqMap.put(currentChar, freqMap.getOrDefault(currentChar, 0) + 1);

            // Add to queue if it's the first occurrence
            if (freqMap.get(currentChar) == 1) {
                queue.add(currentChar);
            }

            // Remove characters from front that are no longer non-repeating
            while (!queue.isEmpty() && freqMap.get(queue.peek()) > 1) {
                queue.remove();
            }

            // Append to result
            if (queue.isEmpty()) {
                result.append('X');
            } else {
                result.append(queue.peek());
            }
        }

        return result.toString();
    }
}