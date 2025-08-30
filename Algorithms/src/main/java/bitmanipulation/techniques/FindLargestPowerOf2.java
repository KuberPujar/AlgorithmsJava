package bitmanipulation.techniques;
/*
Find the largest power of 2 ((most significant bit) in binary form), which is less than or equal to the given number N.
 Given a number 'n' you have to determine the largest number which is in range of 0-n and is a power of 2.

 */
public class FindLargestPowerOf2 {
    /*
    A simple approach would be to start from n and then keep decrementing until you find a number that is a power of 2.
The time and space complexity for this is O(n) and O(1) respectively.
     */
//traditional approach
    static int highestPowerOf2(int n)
    {
        int res = 0;
        for (int i = n; i >= 1; i--) {
            // If i is a power of 2
            if ((i & (i - 1)) == 0) {
                res = i;
                break;
            }
        }
        return res;
    }

    /*
    Idea
Change all the bits which are at the right side of the most significant digit, to 1.

Property
As we know that when all the bits of a number N are 1, then N must be equal to the 2i -1 , where i is the number of bits in N

Example
Let's say the binary form of a N is 11112 which is equal to 15.

15 = 24-1, where 4 is the number of bits in N.

This property can be used to find the largest power of 2 less than or equal to N. How?

If we somehow, change all the bits which are at right side of the most significant bit of N to 1, then the number will become x + (x-1) = 2 * x -1 , where x is the required answer.

For eg

Let’s say N = 21 = 10101, here the most significant bit is the 4th one. (counting from 0th digit) and so the answer should be 16.So let's change all the right side bits of the most significant bit to 1. Now the number changes to 11111 = 31 = 2 * 16 -1 = Y (let’s say).

Now the required answer is (Y+1)>>1 or (Y+1)/2.

Now the question arises here is how can we change all right side bits of the most significant bit to 1?

Let's take the N as a 16 bit integer and binary form of N is 1000000000000000.Here we have to change all the right side bits to 1.

Initially we will copy that most significant bit to its adjacent right side by: N = N | (N >> 1).


As you can see, in the above diagram, after performing the operation, the rightmost bit has been copied to its adjacent place. Now we will copy the 2 rightmost set bits to their adjacent right side. N = N | (N >> 2).


Now we will copy the 4 rightmost set bits to their adjacent right side. N = N | (N>>4).


Now we will copy these 8 rightmost set bits to their adjacent right side. N = N| (N>>8).


Now all the right side bits of the most significant set bit has been changed to 1 . This is how we can change right side bits. This explanation is for 16 bit integers, and it can be extended for 32 or 64 bit integers too.
     Each of the bitwise operations will take a constant amount of time and at no place are using any extra space but only modifying 'N'.

Time Complexity: O(1)

Space Complexity: O(1)
     */

    //Bit Manipulation approach
   static  long largest_power(long N)
    {
        //changing all right side bits to 1.
        N = N| (N>>1);
        N = N| (N>>2);
        N = N| (N>>4);
        N = N| (N>>8);
  /* as now the number is 2 * x-1, where x is the required answer,
  so adding 1 and dividing it by 2. */
        return (N+1)>>1;
    }

    public static void main(String[] args) {
        int n = 1000;
        System.out.println("Using traditional approach: " + highestPowerOf2(n));
        System.out.println("Using Bit Manipulation approach: " + largest_power(n));
    }
}
