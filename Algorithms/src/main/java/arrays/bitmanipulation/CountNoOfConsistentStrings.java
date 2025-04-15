package arrays.bitmanipulation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.



Example 1:

Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
Example 2:

Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
Example 3:

Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.


Constraints:

1 <= words.length <= 104
1 <= allowed.length <= 26
1 <= words[i].length <= 10
The characters in allowed are distinct.
words[i] and allowed contain only lowercase English letters.
 */
public class CountNoOfConsistentStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the allowed characters: ");
        String allowed = scanner.nextLine();
        System.out.println("Enter the number of words: ");
        int n = scanner.nextInt();
        String[] words = new String[n];
        System.out.println("Enter the words: ");
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }
        scanner.close();
        int result = countConsistentStrings(allowed, words);
        System.out.println("Number of consistent strings: " + result);
    }

    private static int countConsistentStrings(String allowed, String[] words) {

        Set<Character> allowedSet = new HashSet<>();

        for(char c:allowed.toCharArray()){
            allowedSet.add(c);
        }
        int count = 0;
        for (String word : words) {
            boolean isConsistent = true;
            for (char c : word.toCharArray()) {
                if (!allowedSet.contains(c)) {
                    isConsistent = false;
                    break;
                }
            }
            if (isConsistent) {
                count++;
            }
        }
        return count;
    }
}
