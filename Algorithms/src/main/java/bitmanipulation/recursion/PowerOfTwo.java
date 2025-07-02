package bitmanipulation.recursion;
/*
Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.



Example 1:

Input: n = 1
Output: true
Explanation: 20 = 1
Example 2:

Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:

Input: n = 3
Output: false


Constraints:

-231 <= n <= 231 - 1

 */
public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        // n is a power of two if it's even and n/2 is a power of two
        return (n & 1) == 0 && isPowerOfTwo(n >> 1);
    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(1));  // true
        System.out.println(powerOfTwo.isPowerOfTwo(16)); // true
        System.out.println(powerOfTwo.isPowerOfTwo(3));  // false
        System.out.println(powerOfTwo.isPowerOfTwo(0));  // false
        System.out.println(powerOfTwo.isPowerOfTwo(-2)); // false
    }
}
