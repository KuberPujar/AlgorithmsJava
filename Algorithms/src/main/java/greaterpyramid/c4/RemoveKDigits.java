package greaterpyramid.c4;

import java.util.Stack;

/*
Given a non-negative integer represented as a string num and an integer k, remove k digits from the number so that the new number is the smallest possible. The remaining digits should maintain their original order in the string. Return the result as a string.

Input Format:

The first line contains the string num, representing the non-negative integer.
The second line contains the integer k.
Output Format:

Print the smallest possible integer as a string after removing k digits.
Sample Input 1:

1432219
3
Sample Output 1:

1219
Explanation:

Removing the digits 4, 3, and 2 from "1432219" leads to the new number "1219", which is the smallest possible result.

Sample Input 2:

10200
1
Sample Output 2:

200
Explanation:

Removing one digit (the leading 1) from "10200" results in "0200", and removing the leading zeroes gives "200" as the smallest possible result.

Constraints:
1<=k<= num.length<= 100000
num contains of only digits
 */
public class RemoveKDigits {
    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        System.out.println(removeKdigits(num, k)); // Output: "1219"

        String num2 = "10200";
        int k2 = 1;
        System.out.println(removeKdigits(num2, k2)); // Output: "200"
    }

    public static String removeKdigits(String num, int k) {
        if (k == num.length()) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();

        for (char digit : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }

        // Remove remaining digits from the end if needed
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Build the result string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

}

