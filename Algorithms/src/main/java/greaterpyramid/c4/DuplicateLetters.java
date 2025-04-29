package greaterpyramid.c4;

import java.util.Arrays;
import java.util.Stack;

/*
Dholu is very creative and wants to play with strings and he has a very good idea. You have given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order

Input Format:
first line consists of a string
Output Format:
print the output string
Sample Input:
cbacdcbc

Sample Output:
abcd

Explanation: Since if we only collect unique letters, they come out to be 'c', 'b', 'a' and 'd' and when they are arranged lexicographically the output is abcd.

Constraints:
1<= s.length<= 10000
Note: Input consists of only lower case letters.
 */
public class DuplicateLetters {
    public static void main(String[] args) {
        String s = "ropuptijikiloa";
        System.out.println(removeDuplicateLetters(s)); // Output: "abcd"
    }

    public static String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();

        // Count the occurrences of each character
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            count[c - 'a']--;

            if (visited[c - 'a']) {
                continue;
            }

            // While the stack is not empty, the current character is smaller than the top of the stack,
            // and the top character will appear again later, pop the stack
            while (!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[c - 'a'] = true;
        }

        // Build the result string from the stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        char[] ch=sb.toString().toCharArray();
        Arrays.sort(ch);

        return new String(ch);
    }
}
