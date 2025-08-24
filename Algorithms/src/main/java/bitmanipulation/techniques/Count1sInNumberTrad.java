package bitmanipulation.techniques;
/*
Traverse through the binary representation and do check how many 1s are there by using AND operation and shift operators.
 */
public class Count1sInNumberTrad {
    public static void main(String[] args) {
        int n = 29; // Example number
        long start=System.currentTimeMillis();
        int count = countSetBits(n);
        long end=System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" ms");
        System.out.println("Number of 1s in binary representation of " + n + " is: " + count);
    }

    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1); // Increment count if the last bit is 1
            n >>= 1;          // Right shift n to check the next bit
        }
        return count;
    }
}
