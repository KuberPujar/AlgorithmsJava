package bitmanipulation.techniques;
/*
A straight way to approach this would be to keep on dividing by 2 and at any point
you get an odd number other than 1, then that means the value is a power of 2.
You have to address the edge case where for 0 you should return false.
 */

public class CheckIfNumberIsPowerOf2Traditional {

    public static void main(String[] args) {

        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(18));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(0));
    }

    static boolean isPowerOfTwo(int n){
        if(n==0) return false;

        while(n!=1){
            if(n%2!=0)
            {
                return false;
            }
            n=n/2;
        }
        return true;
    }

    //explain
    //Time Complexity: O(log n)
    //Space Complexity: O(1)

}
