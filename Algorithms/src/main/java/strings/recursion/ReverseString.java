package strings.recursion;
/*
Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.



Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


Constraints:

1 <= s.length <= 105
s[i] is a printable ascii character.
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(s);
    }

    private static void reverseString(char[] s) {
        reverseHelper(s, 0, s.length - 1);
    }

    private static void reverseHelper(char[] s, int left, int right) {
        // Base case: when left pointer meets or passes right pointer
        if (left >= right) {
            return;
        }

        // Swap characters at left and right pointers
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        // Recurse with the next pair of characters
        reverseHelper(s, left + 1, right - 1);
    }
}
