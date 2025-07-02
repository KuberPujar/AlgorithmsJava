package bitmanipulation.recursion;
/*
iven an integer n, return true if it is a power of four. Otherwise, return false.

An integer n is a power of four, if there exists an integer x such that n == 4x.



Example 1:

Input: n = 16
Output: true
Example 2:

Input: n = 5
Output: false
Example 3:

Input: n = 1
Output: true


Constraints:

-231 <= n <= 231 - 1

 */
public class PowerOfFour {

    public boolean isPowerOfFour(int n) {
        if (n == 1) return true;
        if (n <= 0 || (n & 3) != 0) return false; // n % 4 != 0
        return isPowerOfFour(n >> 2); // n / 4
    }

    public static void main(String[] args) {
        PowerOfFour powerOfFour = new PowerOfFour();
        System.out.println(powerOfFour.isPowerOfFour(16)); // true
        System.out.println(powerOfFour.isPowerOfFour(5));  // false
        System.out.println(powerOfFour.isPowerOfFour(1));  // true
        System.out.println(powerOfFour.isPowerOfFour(0));  // false
        System.out.println(powerOfFour.isPowerOfFour(-4)); // false
    }
}
