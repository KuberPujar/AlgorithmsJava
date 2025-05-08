package dynamicprmng;
/*
You're a renowned mathematician on a quest to unlock the secrets of the Tribonacci sequence. This ancient sequence, unlike its Fibonacci cousin, involves three past terms instead of two. Your mission is to travel through time, unraveling the mysteries of each Tribonacci number.

Given an integer n representing a point in time, calculate the nth Tribonacci number. Remember, T0 = 0, T1 = 1, T2 = 1, and for n >= 0, Tn+3 = Tn + Tn+1 + Tn+2. Conquer larger and larger challenges with n values ranging from 0 to 37. Show off your computational prowess and unveil the hidden patterns within the sequence!

Input:
An integer n representing a point in time (0 <= n <= 37).

Output:
The Tribonacci number Tn corresponding to the chosen point in time. Remember: T0 = 0 T1 = 1 T2 = 1 Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0

Example 1:
Input: n = 4 Output: 4 Explanation: T_3 = 0 + 1 + 1 = 2 T_4 = 1 + 1 + 2 = 4

Example 2:
Input: n = 25 Output: 1389537

Constraints:
n must be an integer between 0 and 37 (inclusive).

The output Tn is guaranteed to fit within a 32-bit integer (maximum value: 2^31 - 1).
 */
public class AllienSequence {
    public static void main(String[] args) {
        int n = 4; // Example input
        System.out.println(tribonacci(n)); // Output: 4
        n = 25; // Example input
        System.out.println(tribonacci(n)); // Output: 1389537
        n = 0; // Example input
        System.out.println(tribonacci(n)); // Output: 0
        n = 1; // Example input
        System.out.println(tribonacci(n)); // Output: 1
        n = 2; // Example input
        System.out.println(tribonacci(n)); // Output: 1
    }
    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
