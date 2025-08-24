package bitmanipulation.techniques;
/*
Given a number n and a bit number i, check if the ith bit of N is set or not. A bit is called set if it is 1.
 */
public class CheckIfithBitIsSet {

    //traditional method
    //This method leverages the modulo operation to extract the remainder of the number after dividing by
    // the power of two corresponding to bit position plus one.
    // A subsequent division by the power of two corresponding to the bit position yields either a 0 or 1.
     static  int is_bit_set(int num, int pos) {
        // Calculate the mask by left shifting 1 by (pos + 1) bits
        int mask = 1 << (pos + 1);

        // Calculate the remainder when dividing num by the mask
        int masked_num = num % mask;

        // Shift the masked number right by pos bits to extract the bit at pos
        int bit_at_pos = masked_num >> pos;

        // Return the extracted bit
        return bit_at_pos;
    }

    //bit manipulation method
    //This method uses a bitwise AND operation to isolate the bit at the specified position.
    static int is_bit_set_bit_manipulation(int num, int pos) {
        return (num & (1 << pos));
    }

    public static void main(String[] args) {
        int num = 5; // Binary representation: 101
        int pos = 2;

        // Using traditional method
        int result1 = is_bit_set(num, pos);
        System.out.println("Using traditional method: The bit at position " + pos + " is " + (result1 == 1 ? "set" : "not set"));

        // Using bit manipulation method
        int result2 = is_bit_set_bit_manipulation(num, pos);
        System.out.println("Using bit manipulation method: The bit at position " + pos + " is " + (result2 != 0 ? "set" : "not set"));
    }

}
