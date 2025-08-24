package bitmanipulation.techniques;
/*
The number n if it is a power of 2, when represented in binary will have all elements as
zero except the first one, for example: binary representation of 4 is 100, for 8 is 1000.
Now, let's say the number 'n' is a power of 2 and let's see the binary representation of 'n-1',
so for example: 4-1 = 3 whose binary representation is 011 and for 8-1=7 the binary representation is 0111.
Thus, if n is a power of 2 then n-1 will have all its bits flipped with respect to n.
So, AND operation of both these numbers would result in zero.
For the above code implementation the time complexity will be O(1) and space complexity will be O(1).
 */
public class CheckIfNumberIsPowerOf2Bit {

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(18));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(0));
        System.out.println(isPowerOfTwo(-64));
    }
    static boolean isPowerOfTwo(int n){
        if(n==0) return false;
        return (n & (n - 1)) == 0 ;
    }
}
