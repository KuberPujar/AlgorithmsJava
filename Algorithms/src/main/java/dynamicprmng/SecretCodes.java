package dynamicprmng;
/*
Secret Codes
You and your friend have decided to exchange messages in the form of secret codes. You will be communicating in numbers where number '1' represents 'a', number '2' represents 'b', and so on until the number '26' that represents 'z'. Every message has a difficulty which is represented as the total number of ways your message can be interpreted. For example: 23 can be interpreted as 'bc' as well as 'w' so it has a difficulty of 2. Given an array ar containing ( n ) number of integers, calculate its difficulty.

Note: Try to see if you can solve using brute force first then optimize your solution

Input Format:

The first line contains a single integer ( n ) denoting the size of the array.
The second line contains ( n ) space-separated integers.
Output Format:

Return the difficulty of the secret code which would be an integer.
Sample Input 1:

3
1 2 3
Sample Output 1:

3
Explanation 1:

Since there are a total of 3 ways the message can be interpreted which are 'abc', 'lc', and 'aw'. Thus, the difficulty would be 3.

Constraints:

( 1 <= n <= 10^4 )
( 0 < ar[i] <= 9 )
Note: The function should return the result.
 */
/*
Brute force solution

public class SecretCodes {

    // Brute force recursive solution
    public static int numDecodings(int[] digits) {
        return helper(digits, 0);
    }

    private static int helper(int[] digits, int index) {
        // Base case: reached end of array
        if (index == digits.length) {
            return 1;
        }

        // Can't decode if current digit is 0
        if (digits[index] == 0) {
            return 0;
        }

        // Decode single digit
        int ways = helper(digits, index + 1);

        // Decode two digits if possible
        if (index + 1 < digits.length) {
            int twoDigit = digits[index] * 10 + digits[index + 1];
            if (twoDigit >= 10 && twoDigit <= 26) {
                ways += helper(digits, index + 2);
            }
        }

        return ways;
    }

    public static void main(String[] args) {
        int[] digits1 = {1, 2, 3};
        System.out.println(numDecodings(digits1)); // Output: 3

        int[] digits2 = {1, 0, 2};
        System.out.println(numDecodings(digits2)); // Output: 1 ("10 2" -> "jb")
    }
}
 */

public class SecretCodes {

    // Optimized DP solution
    public static int numDecodingsDP(int[] digits) {
        if (digits == null || digits.length == 0) {
            return 0;
        }

        int n = digits.length;
        int[] dp = new int[n + 1];
        dp[n] = 1; // Base case: empty string has 1 way to decode

        for (int i = n - 1; i >= 0; i--) {
            // Can't decode if current digit is 0
            if (digits[i] == 0) {
                dp[i] = 0;
                continue;
            }

            // Single digit decoding
            dp[i] = dp[i + 1];

            // Two digit decoding if possible
            if (i + 1 < n) {
                int twoDigit = digits[i] * 10 + digits[i + 1];
                if (twoDigit >= 10 && twoDigit <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }

    // Space optimized DP solution (O(1) space)
    public static int numDecodingsOptimal(int[] digits) {
        if (digits == null || digits.length == 0) {
            return 0;
        }

        int n = digits.length;
        int next = 1; // dp[i+1]
        int nextNext = 1; // dp[i+2]
        int current = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 0) {
                current = 0;
            } else {
                current = next;

                if (i + 1 < n) {
                    int twoDigit = digits[i] * 10 + digits[i + 1];
                    if (twoDigit >= 10 && twoDigit <= 26) {
                        current += nextNext;
                    }
                }
            }

            nextNext = next;
            next = current;
        }

        return current;
    }

    public static void main(String[] args) {
        int[] digits1 = {1, 2, 3};
        System.out.println(numDecodingsDP(digits1)); // Output: 3
        System.out.println(numDecodingsOptimal(digits1)); // Output: 3

        int[] digits2 = {1, 0, 2};
        System.out.println(numDecodingsDP(digits2)); // Output: 1
        System.out.println(numDecodingsOptimal(digits2)); // Output: 1

        int[] digits3 = {2, 2, 6};
        System.out.println(numDecodingsDP(digits3)); // Output: 3
        System.out.println(numDecodingsOptimal(digits3)); // Output: 3
    }
}