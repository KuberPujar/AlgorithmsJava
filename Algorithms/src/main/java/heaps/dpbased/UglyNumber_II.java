package heaps.dpbased;
/*
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.



Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.


Constraints:

1 <= n <= 1690
 */
public class UglyNumber_II {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1; // The first ugly number is 1

        // Pointers for 2, 3, and 5
        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; i++) {
            // Calculate next candidates
            int next2 = ugly[i2] * 2;
            int next3 = ugly[i3] * 3;
            int next5 = ugly[i5] * 5;

            // Find the minimum of the candidates
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            ugly[i] = nextUgly;

            // Move the pointer(s) that contributed to this minimum
            if (nextUgly == next2) i2++;
            if (nextUgly == next3) i3++;
            if (nextUgly == next5) i5++;
        }

        return ugly[n - 1];
    }

    public static void main(String[] args) {
        UglyNumber_II solution = new UglyNumber_II();
        int n = 10; // Example input
        int result = solution.nthUglyNumber(n);
        System.out.println("The " + n + "th ugly number is: " + result); // Output: 12
    }
}
