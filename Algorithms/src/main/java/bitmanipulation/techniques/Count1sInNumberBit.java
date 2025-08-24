package bitmanipulation.techniques;
/*
Why does this work?
Utilises the relationship between a number 'x' and 'x-1' in binary representation.
The operation n = n & (n - 1) flips the rightmost 1 in 'n' and bits to its right.
Iterating through this process reduces 'n' to a number with fewer ones, increasing the count in each iteration.
Example
For n = 23 (binary: 10111)
n = 22 (binary: 10110), count = 1
n = 20 (binary: 10100), count = 2
n = 16 (binary: 10000), count = 3
n = 0, count = 4
In our algorithm at each step we are doing an AND operation between two consecutive numbers which effectively flips the least significant '1' bit to zero. Thus our loop will terminate after all the ones are flipped to zero, and it will run only that many times as many ones are present in the binary representation.
Time Complexity: O(K), where K is the number of ones in the binary representation of the given number.
Space Complexity: O(1)
 */
public class Count1sInNumberBit {
    public static void main(String[] args) {
        int n = 29; // Example number
        int count = countSetBits(n);
        System.out.println("Number of 1s in binary representation of " + n + " is: " + count);
    }

    public static int countSetBits(int n) {
            int count = 0;
            while (n>0) {
                n = n & (n - 1);
                count++;
            }
            return count;
    }
}
