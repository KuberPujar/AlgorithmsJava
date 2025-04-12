package recursion.test;

import java.util.Scanner;

/*
Gram and the string
In a picturesque village, Gram, the revered elder, received a magical string named "s." The village, filled with avid letter collectors, eagerly anticipated Gram's ingenuity. With a quest to create a unique and lexicographically ordered sequence, Gram carefully crafted an algorithm. Duplicates vanished, and the string transformed into a captivating tale of linguistic elegance.

The village celebrated Gram as the guardian of lexicographical order, and the magical string "s" became a symbol of their commitment to precision. The story resonated through the hills, inspiring generations to unravel the mysteries of letters and strings.

Input:
A string s of length between 1 and 10,000.

Output:
Return the smallest lexicographical order string after removing duplicates.

Example 1:
Input:

s = "bcabc"

Output:
"abc"

Example2:
Input:
s = "cbacdcbc"

Output:
"acdb"

Constraints:
The length of the input string s is within the range [1, 10,000].
The string s consists of lowercase English letters.
 */
public class GramAndTheString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        scanner.close();
        String result = removeDuplicates(s);
        System.out.println(result);
    }

    private static String removeDuplicates(String s) {
        boolean[] visited = new boolean[26];
        StringBuilder result = new StringBuilder();
        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (visited[currentChar - 'a']) {
                continue;
            }
            while (!result.isEmpty() && result.charAt(result.length() - 1) > currentChar && lastIndex[result.charAt(result.length() - 1) - 'a'] > i) {
                visited[result.charAt(result.length() - 1) - 'a'] = false;
                result.deleteCharAt(result.length() - 1);
            }
            result.append(currentChar);
            visited[currentChar - 'a'] = true;
        }
        return result.toString();
    }

}
