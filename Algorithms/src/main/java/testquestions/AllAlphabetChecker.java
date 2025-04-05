package testquestions;
/*
All Alphabets
Write a program that takes lowercase string str as input and checks if it contains all 26 alphabets at least once. Given a string str, return true if str contains all 26 alphabets, or false otherwise.

Input:
The first line contains a lowercase string str.

Output:
Return a boolean value true or false.

Constraints:
1 <= str.length <= 10^4
str consists of lowercase English letters.
Example 1
Input:

thequickbrownfoxjumpsoverthelazydog
Output

true
Explanation:
str contains at least one of every letter of the English alphabet.

Example 2
Input:
heycoach

Output
false

Explanation:
str does not contain at least one of every letter of the English alphabet.
 */
import java.util.Scanner;

public class AllAlphabetChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(checkAllAlphabets(str));
    }

    public static boolean checkAllAlphabets(String str) {
        // Create a boolean array to track presence of each letter
        boolean[] alphabetPresent = new boolean[26];

        // Iterate through each character in the string
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // Mark the corresponding alphabet as present
            alphabetPresent[c - 'a'] = true;
        }

        // Check if all alphabets are present
        for (boolean present : alphabetPresent) {
            if (!present) {
                return false;
            }
        }
        return true;
    }
}