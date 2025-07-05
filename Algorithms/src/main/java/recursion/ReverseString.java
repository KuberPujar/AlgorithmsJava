package recursion;
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
    public void reverseString(char[] s) {
        reverseHelper(s, 0, s.length - 1);
    }

    private void reverseHelper(char[] s, int left, int right) {
        if (left >= right) return;
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        reverseHelper(s, left + 1, right - 1);
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        reverseString.reverseString(s1);
        System.out.println(s1); // Output: ["o", "l", "l", "e", "h"]

        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        reverseString.reverseString(s2);
        System.out.println(s2); // Output: ["h", "a", "n", "n", "a", "H"]
    }
}
